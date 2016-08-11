/**
 * Junior Protect AngularJS Web Application
 * 
 * @version 2016-7-19
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 */

/**
 Some global utility functions
*/
app.factory('Utils', function($rootScope, $location, $anchorScroll) {

    var obj = {};
    obj.compareArray = function(array1, array2) {
        var is_same = true;
        try {
            is_same = array1.length == array2.length && array1.every(function(element) {
                return array2.indexOf(element) > -1;
            });
        } catch (e) {
            // Do nothing, expect both arrays to exist.
        }
        return is_same;
    };

    obj.diffArray = function(array1, array2) {
        var diffarr = [];
        angular.forEach(array1, function(element) {
            if (array2.indexOf(element) < 0) {
                diffarr.push(element);
            }
        });
        return diffarr;
    };

    obj.parseObjArrayforValues = function(arr, key) {
        var valuearr = [];
        for (var i in arr) {
            valuearr.push(arr[i][key]);
        }
        return valuearr;
    };

    obj.parseURLforDomain = function(url) {
        try {
            if (url.indexOf("://") < 0) {
                url = "http://" + url;
            }
            return (new URL(url)).hostname;
        } catch (e) {
            console.log("Failed to parse URL: " + url);
            return url;
        }
    };

    obj.isValidDomainName = function(domain) {
        return (/^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9](?:\.[a-zA-Z]{2,})+$/.test(domain));
    };

    obj.isStringAlphaNumeric = function(str) {
        return !(/[^a-zA-Z0-9]/.test(str));
    };

    obj.scrollTo = function(id) {
        var old = $location.hash();
        $location.hash(id);
        $anchorScroll();
        //reset to old to keep any additional routing logic from kicking in
        $location.hash(old);
    };

    obj.showGlobalModalError = function(errmsg) {
        obj.showGlobalModalMessage("Error Occurred", errmsg);
    };

    obj.showGlobalModalMessage = function(title, msg) {
        $rootScope.globalErrorMessage = {};
        $rootScope.globalErrorMessage.title = title;
        $rootScope.globalErrorMessage.content = msg;
        angular.element("#global-message-modal").modal('show');
    };

    return obj;
});

/**
 Some global data loader.
*/
app.factory("DataInitiator", function($q, $timeout, $http, $rootScope, $location, $routeSegment, JP_REST_ENDPOINT) {

    var obj = {};
    obj.internalTest = function() {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve("Test finished!");
        }, 1000);
        return deferred.promise;
    };

    obj.initUserProfile = function(forceReload) {
        console.log("Init UserProfile.");
        var deferred = $q.defer();
        if ((!(forceReload === true)) && ($rootScope.UserProfile)) {
            deferred.resolve("UserProfile already loaded, nothing to do.");
            return deferred.promise;
        }

        $http.get(JP_REST_ENDPOINT.GET_USER_PROFILE).success(function(data, status) {
            console.log(data);
            $rootScope.UserProfile = data;
            // If no device under this user, redirect to nodevice page.
            var deviceCount = (data == null) ? 0 : $rootScope.UserProfile.devices.length;
            if (deviceCount < 1) {
                $location.path($routeSegment.getSegmentUrl('routeErrorNoDevice'));
            }
            deferred.resolve("UserProfile loaded successfully.");
        }).error(function(data, status) {
            console.log("error status: " + status + " data: " + data);
            deferred.reject("Failed to load UserProfile.");
        });
        return deferred.promise;
    };

    obj.initAppConfig = function() {
        console.log("Init AppConfig.");
        var deferred = $q.defer();
        if ($rootScope.AppConfig) {
            deferred.resolve("AppConfig already loaded, nothing to do.");
            return deferred.promise;
        }
        $http.get(JP_REST_ENDPOINT.GET_APP_CONFIG).success(function(data, status) {
            console.log(data);
            $rootScope.AppConfig = data;
            deferred.resolve("AppConfig loaded successfully.");
        }).error(function(data, status) {
            console.log("error status: " + status + " data: " + data);
            deferred.reject("Failed to load AppConfig.");
        });
        return deferred.promise;
    };

    obj.initAllwTest = function(forceReload) {
        var promises = [];
        promises.push(obj.internalTest());
        promises.push(obj.initAppConfig());
        promises.push(obj.initUserProfile(forceReload));
        return $q.all(promises);
    };

    obj.initAll = function(forceReload) {
        var promises = [];
        promises.push(obj.initAppConfig());
        promises.push(obj.initUserProfile(forceReload));
        return $q.all(promises);
    };

    return obj;
});



/**
 * Root controller. 
 * Handles all pages.
 */
app.controller('IndexCtrl', function($scope, $rootScope) {
    console.log("IndexCtrl reporting.");

    $scope.deviceStatusCheck = function(deviceIndex, stat) {
        var flag = true;
        try {
            flag = angular.equals($rootScope.UserProfile.devices[deviceIndex].is_active, stat);
        } catch (e) {
            // Do nothing, expect UserProfile.devices to exist.
        }
        return flag;
    };

    $scope.isDeviceVisible = function(deviceIndex) {
        return ($scope.deviceStatusCheck(deviceIndex, 'Y') || $scope.deviceStatusCheck(deviceIndex, 'SU'));
    };
});


/**
 * Todo: upgrade this controller to handle each specific HTTP error code
 */
app.controller('IndexGeneralErrorCtrl', function($location, $routeSegment) {
    var defaultErr = 503;
    $location.path($routeSegment.getSegmentUrl('routeError' + defaultErr));
});



/**
 * ===============================================================================================
 * Page Controlers
 * ===============================================================================================
 */
/**
 * Controls for "overview" pages.
 */
app.controller('PageCtrl_Overview', function($scope, $routeSegment, $location, Utils) {

    console.log("PageCtrl_Overview reporting.");

    // Must inject $routeSegment to $scope
    $scope.$routeSegment = $routeSegment;

    // Redirects to default seg, because $routeSegmentProvider is not working.
    if ($routeSegment.$routeParams.id == null) {
        $location.path($location.path() + '/0');
    }

    $scope.gotoImportFilter = function() {
        $location.path($routeSegment.getSegmentUrl('routeAccountSettings') + "/" + $routeSegment.$routeParams.id);
        Utils.scrollTo("import-filter-content");
    };

});


/**
 * Controls "filter settings" pages.
 */
app.controller('PageCtrl_FilterSettings', function($scope, $routeSegment, $location) {

    console.log("PageCtrl_FilterSettings reporting.");
    $scope.$routeSegment = $routeSegment;

    if ($routeSegment.$routeParams.id == null) {
        $location.path($location.path() + '/0');
    }
});


/**
 * Controls "account settings" pages.
 */
app.controller('PageCtrl_AccountSettings', function($scope, $routeSegment, $location) {

    console.log("PageCtrl_AccountSettings reporting.");
    $scope.$routeSegment = $routeSegment;

    if ($routeSegment.$routeParams.id == null) {
        $location.path($location.path() + '/0');
    }
});


/**
 * ===============================================================================================
 * Segment Controlers
 * ===============================================================================================
 */
/**
 * Controls all segment sub-pages.
 */
app.controller('SegmentCtrl', function($scope, $routeSegment) {
    console.log("SegmentCtrl reporting.");
    $scope.itemIndex = $routeSegment.$routeParams.id;
});





/**
 * Controls the restrict/allowed website list section.
 */
app.controller('ElementCtrl_BlackWhiteDomainList', function($scope, $rootScope, $http, Utils, JP_REST_ENDPOINT) {
    console.log("ElementCtrl_BlackWhiteDomainList reporting.");

    // realDevice is bound with $rootScope by reference. DO NOT update unless backend is updated.
    var realDevice = $rootScope.UserProfile.devices[$scope.itemIndex]; // DO NOT update.
    // $scope.virtualDevice is a data photocopy. Bind HTML view to this photocopy.
    var resetDevice = function() {
        $scope.virtualDevice = angular.copy(realDevice);
    };
    resetDevice();

    // Genius way to handle suspended device.
    if ($scope.deviceStatusCheck($scope.itemIndex, 'SU')) {
        return;
    }

    $scope.removeItem = function(x, whichList) {
        if ('black' == whichList) {
            $scope.virtualDevice.restrict_sites.splice(x, 1);
            console.log($scope.virtualDevice.restrict_sites);
        } else {
            $scope.virtualDevice.allow_sites.splice(x, 1);
            console.log($scope.virtualDevice.allow_sites);
        }
    };

    $scope.addItem = function(whichList) {

        var isBlack = angular.equals("black", whichList);

        // thisList is bound with $scope.virtualDevice (HTML view) by reference.
        // newItem is bound by value. Be careful.
        if (isBlack) {
            var thisList = $scope.virtualDevice.restrict_sites;
            var newItem = $scope.addBlack;
        } else {
            var thisList = $scope.virtualDevice.allow_sites;
            var newItem = $scope.addWhite;
        }

        if (newItem == null) {
            Utils.showGlobalModalError('Empty input.');
            return;
        }

        // Force convert to lower case
        newItem = newItem.toLowerCase();
        // Force convert to domain name.
        newItem = Utils.parseURLforDomain(newItem);
        // Domain name validation
        if (!Utils.isValidDomainName(newItem)) {
            Utils.showGlobalModalError('Invalid domain name.');
            return;
        }

        if (thisList.indexOf(newItem) > -1) {
            Utils.showGlobalModalError('Domain ' + newItem + ' is already in the list.');
            return;
        }

        console.log('List before change: ' + thisList);
        thisList.push(newItem);
        console.log('List after change: ' + thisList);

        // Clear the input field
        if (isBlack) {
            $scope.addBlack = "";
        } else {
            $scope.addWhite = "";
        }

    };


    $scope.postListUpdates = function postSelection() {

        if ((Utils.compareArray($scope.virtualDevice.restrict_sites, realDevice.restrict_sites)) && (Utils.compareArray($scope.virtualDevice.allow_sites, realDevice.allow_sites))) {
            Utils.showGlobalModalError('There is no change in Restricted and Allowed websites.');
            return;
        }

        var postData = {
            'device_number': $scope.virtualDevice.number,
            'blacklist_add': Utils.diffArray($scope.virtualDevice.restrict_sites, realDevice.restrict_sites),
            'blacklist_remove': Utils.diffArray(realDevice.restrict_sites, $scope.virtualDevice.restrict_sites),
            'whitelist_add': Utils.diffArray($scope.virtualDevice.allow_sites, realDevice.allow_sites),
            'whitelist_remove': Utils.diffArray(realDevice.allow_sites, $scope.virtualDevice.allow_sites)
        };

        console.log(postData);

        $http.post(JP_REST_ENDPOINT.POST_UPDATE_BLACKWHITE_DOMAIN_LIST, postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                // Update $rootScope
                realDevice.restrict_sites = angular.copy($scope.virtualDevice.restrict_sites);
                realDevice.allow_sites = angular.copy($scope.virtualDevice.allow_sites);
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
                // Rollback $scope.virtualDevice
                resetDevice();
            });
    };

});





/**
 * Controls the category filter list section.
 */
app.controller('ElementCtrl_CategoryFilter', function($scope, $rootScope, $routeSegment, $http, Utils, JP_REST_ENDPOINT) {
    console.log("ElementCtrl_CategoryFilter reporting.");

    // realDevice is bound with $rootScope by reference. DO NOT update unless backend is updated.
    var realDevice = $rootScope.UserProfile.devices[$scope.itemIndex]; // DO NOT update.
    // $scope.selection is a data photocopy. Bind HTML view to this photocopy.
    var resetSelection = function() {
        $scope.selection = angular.copy(realDevice.filter_list);
    };
    resetSelection();

    $scope.$on('reset-categoryfilter-selection', function() {
        resetSelection();
    });

    var allFilters = $rootScope.AppConfig.category_filters.all.content;
    var half_length = Math.ceil(allFilters.length / 2);
    $scope.half_length = half_length;
    var leftSide = allFilters.slice(0, half_length);
    var rightSide = allFilters.slice(half_length, allFilters.length);
    $scope.allCategoryFilter = [];
    $scope.allCategoryFilter[0] = leftSide;
    $scope.allCategoryFilter[1] = rightSide;

    // console.log(allFilters);
    // console.log($scope.allCategoryFilter);

    // Parse for names of filter objects
    var cat_Medium = Utils.parseObjArrayforValues($rootScope.AppConfig.category_filters.medium.content, 'name');
    var cat_Strict = Utils.parseObjArrayforValues($rootScope.AppConfig.category_filters.strict.content, 'name');


    $scope.checkCategoryFilter = function checkCategoryFilter(which) {
        switch (which) {
            case "medium":
                return Utils.compareArray($scope.selection, cat_Medium);
            case "strict":
                return Utils.compareArray($scope.selection, cat_Strict);
            case "custom":
                return !Utils.compareArray($scope.selection, cat_Medium) && !Utils.compareArray($scope.selection, cat_Strict);
            default:
                console.log('No such category filter.');
        }
    };

    if ($scope.deviceStatusCheck($scope.itemIndex, 'SU')) {
        return;
    }

    $scope.setCategoryFilter = function setCategoryFilter(which) {
        switch (which) {
            case "medium":
                $scope.selection = angular.copy(cat_Medium);
                break;
            case "strict":
                $scope.selection = angular.copy(cat_Strict);
                break;
            default:
                console.log('No such category filter.');
        }
        console.log('setCategoryFilter after: ' + $scope.selection);
    };


    $scope.toggleSelection = function toggleSelection(catflt) {
        var filter_idx = $scope.selection.indexOf(catflt);

        if (filter_idx > -1) {
            console.log(catflt + ' currently is selected, toggle to splice it from selection: ' + filter_idx);
            $scope.selection.splice(filter_idx, 1);
        } else {
            console.log(catflt + ' currently not selected, toggle to add it to selection: ' + filter_idx);
            $scope.selection.push(catflt);
        }
    };

    $scope.postSelection = function postSelection(deviceIndex) {
        if (Utils.compareArray($scope.selection, realDevice.filter_list)) {
            console.log("No change in selection, no update request to backend.");
            return;
        }

        var cat_type = null;
        if (Utils.compareArray($scope.selection, cat_Medium)) {
            cat_type = "medium";
        } else if (Utils.compareArray($scope.selection, cat_Strict)) {
            cat_type = "strict";
        }


        var postData = {
            'device_number': realDevice.number,
            'profile_name': realDevice.account_type + "-" + realDevice.profile_name,
            'category_type': cat_type,
            'category_list': $scope.selection
        };

        console.log(postData);

        $http.post(JP_REST_ENDPOINT.POST_UPDATE_CATEGORY_FILTER_LIST, postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                // Update $rootScope
                realDevice.filter_list = angular.copy($scope.selection);
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
                resetSelection();
            });
    };

});





/**
 * Controls the account setting section.
 */
app.controller('ElementCtrl_ImportFilter', function($scope, $rootScope, $http, Utils, JP_REST_ENDPOINT) {
    console.log("ElementCtrl_ImportFilter reporting.");

    var realDevice = $rootScope.UserProfile.devices[$scope.itemIndex];

    if ($scope.deviceStatusCheck($scope.itemIndex, 'SU')) {
        return;
    }

    $scope.importSourceDevice = function() {

        if ($scope.sourceDevice.number == realDevice.number) {
            Utils.showGlobalModalError("Source device should be different.");
            return;
        }

        var postData = {
            'device_number': realDevice.number,
            'profile_name': realDevice.account_type + "-" + realDevice.profile_name,
            'import_device_number': $scope.sourceDevice.number
        };

        console.log(postData);

        $http.post(JP_REST_ENDPOINT.POST_IMPORT_FILTER_SETTINGS, postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                realDevice.filter_list = angular.copy($scope.sourceDevice.filter_list);
                realDevice.allow_sites = angular.copy($scope.sourceDevice.allow_sites);
                realDevice.restrict_sites = angular.copy($scope.sourceDevice.restrict_sites);
                //Broadcast to override subscope
                $scope.$broadcast('reset-categoryfilter-selection');
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
            });
    };

});




/**
 * Controls the account setting section.
 */
app.controller('ElementCtrl_Account', function($scope, $rootScope, $routeSegment, $http, $q, Utils, JP_REST_ENDPOINT) {
    console.log("ElementCtrl_Account reporting.");

    // Keep realDevice private, do not expose to $scope.
    var realDevice = $rootScope.UserProfile.devices[$scope.itemIndex];
    var resetDevice = function() {
        $scope.virtualDevice = angular.copy(realDevice);
    };
    resetDevice();

    if ($scope.deviceStatusCheck($scope.itemIndex, 'SU')) {
        return;
    }

    // My workaround. It won't work without AngularJS + BootStrap working together.
    angular.element('#set-password-modal').on('hidden.bs.modal', function() {
        $scope.virtualDevice.parent_access = angular.copy(realDevice.parent_access);
        $scope.$apply();
    });

    // This function is synchronous (deferred until complete)
    // This function is private.
    var updateParentAccessStatus = function(newPAstatus) {

        var deferred = $q.defer();

        if (!(typeof(newPAstatus) === "boolean")) {
            console.log("Invalid param for updateParentAccessStatus.");
            deferred.reject('ERROR');
            return deferred.promise;
        }

        /*var postData = {
            'device_number': $scope.virtualDevice.number,
            'parent_access': newPAstatus
        };*/
        var postData = {
            'parent_access_status': [{
                "device_id": $scope.virtualDevice.number,
                "status": (newPAstatus ? 1 : 0)
            }]
        };

        console.log(postData);
        $http.post(JP_REST_ENDPOINT.POST_UPDATE_PARENTACCESS_STATUS, postData).success(function(data, status) {

                alert("Post Success! status: " + status + " data: " + data);
                // Update rootScope
                realDevice.parent_access = newPAstatus;
                // Resolve the promise
                deferred.resolve("Update PA status scuceeded.");
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
                deferred.reject("Update PA status failed.");
            });
        //return the promise
        return deferred.promise;
    };


    // This function is synchronous (deferred until complete)
    // This function is private.
    var updateParentAccessPwd = function(pw) {

        var deferred = $q.defer();
        if (pw == null) {
            console.log("Invalid param for setPAPwd.");
            deferred.reject('ERROR');
            return deferred.promise;
        }

        var postData = {
            'device_number': $scope.virtualDevice.number,
            'pa_pwd': pw
        };
        var postEndPoint = JP_REST_ENDPOINT.POST_UPDATE_PARENTACCESS_PWD;

        // ===Temporary start===
        if (!$rootScope.UserProfile.parent_access_pwd) {
            postEndPoint = JP_REST_ENDPOINT.POST_ADD_PARENTACCESS;
            var postData = {
                'pa_pwd': pw
            };
            var parent_access_statusArray = [];
            angular.forEach($rootScope.UserProfile.devices, function(device, index) {
                var tmpobj = {};
                tmpobj.device_id = device.number;
                tmpobj.status = 0;
                parent_access_statusArray.push(tmpobj);
            });
            postData.parent_access_status = parent_access_statusArray;
        }

        // ===Temporary end ===

        console.log(postData);
        $http.post(postEndPoint, postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                $rootScope.UserProfile.parent_access_pwd = true;
                deferred.resolve('request successful');
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
                deferred.reject('ERROR');
            });
        return deferred.promise;
    }

    // This function is public (accessible from View).
    $scope.togglePAStatus = function() {

        var pa_status = $scope.virtualDevice.parent_access;
        var pa_pwd_status = $rootScope.UserProfile.parent_access_pwd;

        $scope.virtualDevice.parent_access = !pa_status;

        if ((!pa_pwd_status) && (!pa_status)) {
            // Go through set-password flow
            console.log("do nothing");
            angular.element("#pa-set-password-btn").trigger('click');
            return;
        }

        var myPromise = updateParentAccessStatus(!pa_status);
        myPromise.then(function(resolve) {
            console.log(resolve);
        }, function(reject) {
            console.log(reject);
            resetDevice();
        });
    };

    // This function is public (accessible from View).
    $scope.setPAPwd = function() {
        var pw1 = $scope.pa_set_pwd;
        var pw2 = $scope.pa_confirm_pwd;

        // Future upgrade: AngularJS validator; Javascript monitor.
        if (pw1 == null) {
            Utils.showGlobalModalError('Empty password.');
            return;
        }
        if (!(pw1 == pw2)) {
            Utils.showGlobalModalError('Not match.');
            return;
        }

        var myPromise = updateParentAccessPwd(pw2);
        myPromise.then(function(resolve) {
            console.log(resolve);
            angular.element(".papwd-cancel-btn").trigger('click');
        }, function(reject) {
            console.log(reject);
            resetDevice();
        });

    };

    // This function is public (accessible from View).
    $scope.setDeviceProfileName = function() {

        var newName = $scope.virtualDevice.profile_name;
        if ((newName == null) || (!Utils.isStringAlphaNumeric(newName))) {
            Utils.showGlobalModalError("Invalid new device profile name.");
            return;
        }

        if (newName == realDevice.profile_name) {
            Utils.showGlobalModalError("The new profile should be different from old profile name.");
            return;
        }

        var postData = {
            'device_number': realDevice.number,
            'old_profile_name': realDevice.account_type + "-" + realDevice.profile_name,
            'new_profile_name': realDevice.account_type + "-" + newName
        };

        console.log(postData);

        $http.post(JP_REST_ENDPOINT.POST_UPDATE_DEVICE_PROFILE_NAME, postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                // Update $rootScope
                realDevice.profile_name = angular.copy(newName);
            })
            .error(function myError(data, status) {
                Utils.showGlobalModalError("Post Failed... status: " + status + " data: " + data);
            }).finally(function() {
                console.log("finally");
                resetDevice();
            });
    };

});
