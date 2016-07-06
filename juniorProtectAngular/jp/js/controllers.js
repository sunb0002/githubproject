/**
 * Junior Protect AngularJS Web Application
 * 
 * @version 2016-6-23
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 */


/**
 * Root controller. 
 * Controls all pages.
 */
app.controller('IndexCtrl', function($scope, $rootScope, $routeSegment, $http, $location) {

    console.log("IndexCtrl reporting.");

    $http.get("/stub/appconfig.json").success(function(data, status) {
        console.log(data);
        $rootScope.AppConfig = data;
    }).error(function(data, status) {
        console.log("status: " + status + " data: " + data);
        // If anything wrong, redirect to error
        $location.path($routeSegment.getSegmentUrl('routeError503'));
    });


    $scope.$on('init-data-userprofile', function(event, data) {
        console.log("Init user profile data, requested by: " + data);

        // Get the latest data and init the whole site.
        $http.get("/stub/userprofile.madoka.json").success(function(data, status) {
            console.log(data);
            $rootScope.UserProfile = data;

            var deviceCount = ($rootScope.UserProfile == null) ? 0 : $scope.UserProfile.devices.length;
            $rootScope.hasNoDevice = (deviceCount < 1);
            // TODO: check with StarHub what to display if user has no device.
        }).error(function(data, status) {
            console.log("status: " + status + " data: " + data);
            // If anything wrong, redirect to error
            $location.path($routeSegment.getSegmentUrl('routeError503'));
        });
    });

    // Some global-useful utility functions
    $scope.compareArray = function(array1, array2) {
        var is_same = array1.length == array2.length && array1.every(function(element) {
            return array2.indexOf(element) > -1;
        });
        return is_same;
    };

    $scope.diffArray = function(array1, array2) {
        var diffarr = [];
        angular.forEach(array1, function(element) {
            if (array2.indexOf(element) < 0) {
                diffarr.push(element);
            }
        });
        return diffarr;
    };

    $scope.parseObjArrayforValues = function(arr, key) {
        var valuearr = [];
        for (var i in arr) {
            valuearr.push(arr[i][key]);
        }
        return valuearr;
    };

});


/**
 * Controls for "overview" pages.
 */
app.controller('PageCtrl_Overview', function($scope, $routeSegment, $location) {

    console.log("PageCtrl_Overview reporting.");
    // Must inject $routeSegment to $scope
    $scope.$routeSegment = $routeSegment;

    // Unconditionally reload UserProfile.
    $scope.$emit('init-data-userprofile', 'PageCtrl_Overview');

    // Redirects to default seg, because $routeSegmentProvider is not working.
    if ($routeSegment.$routeParams.id == null) {
        $location.path($location.path() + '/0');
    }
});


/**
 * Controls "filter settings" pages.
 */
app.controller('PageCtrl_FilterSettings', function($scope, $rootScope, $routeSegment, $http, $location) {

    console.log("PageCtrl_FilterSettings reporting.");
    $scope.$routeSegment = $routeSegment;

    // Reload UserProfile if data is not loaded.
    if ($rootScope.UserProfile == null) {
        $scope.$emit('init-data-userprofile', 'PageCtrl_FilterSettings');
    }

    if ($routeSegment.$routeParams.id == null) {
        $location.path($location.path() + '/0');
    }
});



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
app.controller('ElementCtrl_BlackWhiteDomainList', function($scope, $rootScope, $http) {
    console.log("ElementCtrl_BlackWhiteDomainList reporting.");

    // currentDevice is bound with $rootScope by reference. DO NOT update unless backend is updated.
    var currentDevice = $rootScope.UserProfile.devices[$scope.itemIndex]; // DO NOT update.
    // $scope.thisDevice is a data photocopy. Bind HTML view to this photocopy.
    $scope.thisDevice = angular.copy(currentDevice);

    $scope.removeItem = function(x, whichList) {
        if ('black' == whichList) {
            $scope.thisDevice.restrict_sites.splice(x, 1);
            console.log($scope.thisDevice.restrict_sites);
        } else {
            $scope.thisDevice.allow_sites.splice(x, 1);
            console.log($scope.thisDevice.allow_sites);
        }
    };

    $scope.addItem = function(whichList) {
        // thisList is bound with $scope.thisDevice (HTML view) by reference.
        if ('black' == whichList) {
            var thisList = $scope.thisDevice.restrict_sites;
            var newItem = $scope.addBlack;
        } else {
            var thisList = $scope.thisDevice.allow_sites;
            var newItem = $scope.addWhite;
        }

        if (newItem == null) {
            alert('Empty input.');
            return;
        }
        // Force convert to lower case
        newItem = newItem.toLowerCase();

        if (thisList.indexOf(newItem) > -1) {
            alert('Already in the list.');
            return;
        }

        console.log('List before change: ' + thisList);
        thisList.push(newItem);
        console.log('List after change: ' + thisList);

    };


    // Diff two arrays and post "add"  and "remove"
    $scope.postListUpdates = function postSelection() {

        var postData = {
            'device_number': $scope.thisDevice.number,
            'blacklist_add': $scope.diffArray($scope.thisDevice.restrict_sites, currentDevice.restrict_sites),
            'blacklist_remove': $scope.diffArray(currentDevice.restrict_sites, $scope.thisDevice.restrict_sites),
            'whitelist_add': $scope.diffArray($scope.thisDevice.allow_sites, currentDevice.allow_sites),
            'whitelist_remove': $scope.diffArray(currentDevice.allow_sites, $scope.thisDevice.allow_sites)
        };

        console.log(postData);

        $http.post("/stub/ok.json", postData).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                // Update $rootScope
                currentDevice.restrict_sites = angular.copy($scope.thisDevice.restrict_sites);
                currentDevice.allow_sites = angular.copy($scope.thisDevice.allow_sites);
            })
            .error(function myError(data, status) {
                alert("Post Failed... status: " + status + " data: " + data);
                // Rollback $scope.thisDevice
                $scope.thisDevice = angular.copy(currentDevice);
            });
    };

});




/**
 * Controls the category filter list section.
 */
app.controller('ElementCtrl_CategoryFilter', function($scope, $rootScope, $routeSegment, $http) {

    console.log("ElementCtrl_CategoryFilter reporting.");
    // currentDevice is bound with $rootScope by reference. DO NOT update unless backend is updated.
    var currentDevice = $rootScope.UserProfile.devices[$scope.itemIndex]; // DO NOT update.
    // $scope.selection is a data photocopy. Bind HTML view to this photocopy.
    $scope.selection = angular.copy(currentDevice.filter_list);

    $scope.allCategoryFilter = $rootScope.AppConfig.category_filters.all.content;

    // Parse for names of filter objects
    var cat_Light = $scope.parseObjArrayforValues($rootScope.AppConfig.category_filters.light.content, 'name');
    var cat_Medium = $scope.parseObjArrayforValues($rootScope.AppConfig.category_filters.medium.content, 'name');

    $scope.setCategoryFilter = function setCategoryFilter(which) {
        switch (which) {
            case "light":
                $scope.selection = angular.copy(cat_Light);
                break;
            case "medium":
                $scope.selection = angular.copy(cat_Medium);
                break;
            default:
                console.log('No such category filter.');
        }
        console.log('setCategoryFilter after: ' + $scope.selection);
    };

    $scope.checkCategoryFilter = function checkCategoryFilter(which) {
        switch (which) {
            case "light":
                return $scope.compareArray($scope.selection, cat_Light);
            case "medium":
                return $scope.compareArray($scope.selection, cat_Medium);
            case "custom":
                return !$scope.compareArray($scope.selection, cat_Light) && !$scope.compareArray($scope.selection, cat_Medium);
            default:
                console.log('No such category filter.');
        }
    };

    $scope.toggleSelection = function toggleSelection(catflt) {
        var filter_idx = $scope.selection.indexOf(catflt);

        // is currently selected
        if (filter_idx > -1) {
            console.log(catflt + ' currently is selected, toggle to splice it from selection: ' + filter_idx);
            $scope.selection.splice(filter_idx, 1);
        }
        // is newly selected
        else {
            console.log(catflt + ' currently not selected, toggle to add it to selection: ' + filter_idx);
            $scope.selection.push(catflt);
        }
    };

    $scope.postSelection = function postSelection(deviceIndex) {
        if ($scope.compareArray($scope.selection, currentDevice.filter_list)) {
            console.log("No change in selection, no post to backend.");
            return;
        }

        console.log($scope.selection);

        $http.post("/stub/ok.json", $scope.selection).success(function(data, status) {
                alert("Post Success! status: " + status + " data: " + data);
                // Update $rootScope
                currentDevice.filter_list = angular.copy($scope.selection);
            })
            .error(function myError(data, status) {
                alert("Post Failed... status: " + status + " data: " + data);
                // Rollback $scope.selection
                $scope.selection = angular.copy(currentDevice.filter_list);
            });
    };


});
