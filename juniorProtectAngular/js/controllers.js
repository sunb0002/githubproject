/**
 * Main AngularJS Web Application
 * 
 * @version 2016-6-23
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 */


/**
 * Root controller. Controls all pages.
 */
app.controller('IndexCtrl', function($scope, $rootScope, $http) {

    console.log("IndexCtrl reporting.");
    $scope.$on('init-data-userprofile', function(event, data) {
        console.log("Init user profile data, requested by: " + data);

        // Get the latest data and init the whole site.
        $http.get("/ott/jp").then(function(response) {
            console.log('response object: ' + response.data);
            $rootScope.UserProfile = response.data;

            var deviceCount = ($rootScope.UserProfile == null) ? 0 : $scope.UserProfile.devices.length;

            $rootScope.hasNoDevice = (deviceCount < 1);
            // (TODO) Staraight away redirect to error page, 
            // or only show error message on the same page.
            // $location.path($routeSegment.getSegmentUrl('s_accountsettings')); // tested working

            $rootScope.items = [...Array(deviceCount).keys()]; // [0,1,2,3...deviceCount]
        });
    });

    $scope.compareArray = function(array1, array2) {
        var is_same = array1.length == array2.length && array1.every(function(element) {
            return array2.indexOf(element) > -1;
        });
        return is_same;
    };

});


/**
 * Controls for "overview" pages.
 */
app.controller('PageCtrl_Overview', function($scope, $routeSegment, $location) {

    console.log("PageCtrl_Overview reporting.");
    // Must inject $routeSegment to $scope
    $scope.$routeSegment = $routeSegment;

    // Regardlessly reload UserProfile.
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
    $scope.item = { id: $routeSegment.$routeParams.id };
});



/**
 * Controls the restrict/allowed website list section.
 */
app.controller('ElementCtrl_BlackWhiteDomainList', function($scope) {
    console.log("ElementCtrl_BlackWhiteDomainList reporting.");

    $scope.addItem = function(device_id, whichList) {
        var isBlacklist = ('black' == whichList);
        var domainList = isBlacklist ? $scope.UserProfile.devices[device_id].restrict_sites : $scope.UserProfile.devices[device_id].allow_sites;
        var newDomain = isBlacklist ? $scope.addBlack : $scope.addWhite;

        if (newDomain == null) {
            alert('Can\'t be empty.');
            return;
        }

        newDomain = newDomain.toLowerCase();

        if (domainList.indexOf(newDomain) > -1) {
            alert('Already in the list.');
            return;
        } else if (domainList.length > 9) {
            alert('At most 10 domains in the list.');
            return;
        }

        console.log('List before change: ' + domainList);
        domainList.push(newDomain);
        console.log('List after change: ' + domainList);

        if (isBlacklist) {
            $scope.UserProfile.devices[device_id].restrict_sites = domainList;
            $scope.addBlack = null;
        } else {
            $scope.UserProfile.devices[device_id].allow_sites = domainList;
            $scope.addWhite = null;
        }
    }

    $scope.removeItem = function(device_id, x, whichList) {

        var isBlacklist = ('black' == whichList);
        var domainList = isBlacklist ? $scope.UserProfile.devices[device_id].restrict_sites : $scope.UserProfile.devices[device_id].allow_sites;

        console.log('List before change: ' + domainList);
        domainList.splice(x, 1);
        console.log('List after change: ' + domainList);

        if (isBlacklist) {
            $scope.UserProfile.devices[device_id].restrict_sites = domainList;
        } else {
            $scope.UserProfile.devices[device_id].allow_sites = domainList;
        }
    }

});


/**
 * Controls the category filter list section.
 */
app.controller('ElementCtrl_CategoryFilter', function($scope, $rootScope, $routeSegment) {

    console.log("ElementCtrl_CategoryFilter reporting.");
    var seg_id = $routeSegment.$routeParams.id;

    // Hardcoded filter list
    var cat_Teens = ['Adult Content', 'Dating', 'Drugs & Alcohol', 'Gambling & Cheating', 'Hate & Intolerance', 'Internet Security Threats', 'Violence & Weapons'];
    var cat_Junior = cat_Teens.concat(['Games', 'Social Networks, Chats & Blogs', 'Streaming Media & Downloads']);
    var cat_All = cat_Junior.concat(['Entertainment & Recreation', 'Forums & Newsgroups', 'Politics & Government', 'Religion', 'Health & Medicine']);


    $scope.categoryFilter = {};
    $scope.categoryFilter.small = cat_Teens;
    $scope.categoryFilter.big = cat_Junior;
    $scope.categoryFilter.all = cat_All;

    $scope.selection = $rootScope.UserProfile.devices[seg_id].filter_list;
    // console.log("Current filter selections: " + $scope.selection);

    $scope.setCategoryFilter = function setCategoryFilter(which) {
        switch (which) {
            case "small":
                $scope.selection = $scope.categoryFilter.small.slice(0);
                break;
            case "big":
                $scope.selection = $scope.categoryFilter.big.slice(0);
                break;
            default:
                console.log('No such category filter.');
        }
        // console.log('setCategoryFilter after: ' + $scope.selection + '________________' + $scope.categoryFilter.small);
    };


    $scope.checkCategoryFilter = function checkCategoryFilter(which) {
        switch (which) {
            case "small":
                return $scope.compareArray($scope.selection, $scope.categoryFilter.small);
            case "big":
                return $scope.compareArray($scope.selection, $scope.categoryFilter.big);
            case "custom":
                return !$scope.compareArray($scope.selection, $scope.categoryFilter.small) && !$scope.compareArray($scope.selection, $scope.categoryFilter.big);
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
        // console.log($scope.selection);
    };

});
