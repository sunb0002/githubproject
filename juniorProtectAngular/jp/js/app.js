/**
 * Junior Protect AngularJS Web Application
 * 
 * @version 2016-6-23
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 */

var app = angular.module('JuniorProtectWebApp', [
    'ngRoute', 'route-segment', 'view-segment'
]);


app.constant('JP_REST_ENDPOINT', {
    "GET_USER_PROFILE": "/stub/userprofile.madoka.json",
    "GET_APP_CONFIG": "/stub/appconfig.json",
    "POST_UPDATE_CATEGORY_FILTER_LIST": "/stub/ok.json",
    "POST_UPDATE_BLACKWHITE_DOMAIN_LIST": "/stub/ok.json",
    "POST_UPDATE_DEVICE_PROFILE_NAME": "/stub/ok.json",
    "POST_UPDATE_PARENTACCESS_STATUS": "/stub/ok.json",
    "POST_UPDATE_PARENTACCESS_PWD": "/stub/ok.json",
    "POST_IMPORT_FILTER_SETTINGS": "/stub/ok.json",
    "POST_ADD_PARENTACCESS": "/stub/ok.json"
});

/*app.constant('JP_REST_ENDPOINT', {
    "GET_USER_PROFILE": "/jprotect/portal/getUserProfile",
    "GET_APP_CONFIG": "/jprotect/portal/getNominumConfig",
    "POST_UPDATE_CATEGORY_FILTER_LIST": "/jprotect/portal/updateCustomBlackListCategories",
    "POST_UPDATE_BLACKWHITE_DOMAIN_LIST": "/jprotect/portal/updatePersonalBlackListWhiteList",
    "POST_UPDATE_DEVICE_PROFILE_NAME": "/jprotect/portal/updateDeviceProfileName",
    "POST_UPDATE_PARENTACCESS_STATUS": "/jprotect/portal/updateParentAccessStatus",
    "POST_UPDATE_PARENTACCESS_PWD": "/jprotect/portal/updateParentAccessPassword",
    "POST_IMPORT_FILTER_SETTINGS": "/jprotect/portal/importSettings",
    "POST_ADD_PARENTACCESS": "/jprotect/portal/addParentAccess"
});*/


/**
 * Configure the Routes
 */
app.config(function($routeSegmentProvider, $routeProvider) {

    // Default route
    $routeProvider.when('/', { redirectTo: '/overview' });

    $routeSegmentProvider.options.autoLoadTemplates = true;
    $routeSegmentProvider
        .when('/overview', 'routeOverview')
        .when('/overview/:id', 'routeOverview.seg')
        .segment('routeOverview', {
            'default': true,
            templateUrl: 'partials/overview.html',
            controller: 'PageCtrl_Overview',
            resolve: {
                initUserProfile: function(DataInitiator) {
                    return DataInitiator.initAll(true);
                }
            },
            untilResolved: {
                templateUrl: 'partials/template/loading.html'
            },
            resolveFailed: { redirectTo: '/error/503' }
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/overview-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        });


    $routeSegmentProvider
        .when('/filtersettings', 'routeFilterSettings')
        .when('/filtersettings/:id', 'routeFilterSettings.seg')
        .segment('routeFilterSettings', {
            templateUrl: 'partials/filtersettings.html',
            controller: 'PageCtrl_FilterSettings',
            resolve: {
                initUserProfile: function(DataInitiator) {
                    return DataInitiator.initAll(false);
                }
            },
            untilResolved: {
                templateUrl: 'partials/template/loading.html'
            },
            resolveFailed: { redirectTo: '/error/503' }
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/filtersettings-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        });


    $routeSegmentProvider
        .when('/accountsettings', 'routeAccountSettings')
        .when('/accountsettings/:id', 'routeAccountSettings.seg')
        .segment('routeAccountSettings', {
            templateUrl: 'partials/accountsettings.html',
            controller: 'PageCtrl_AccountSettings',
            resolve: {
                initUserProfile: function(DataInitiator) {
                    return DataInitiator.initAll(false);
                }
            },
            untilResolved: {
                templateUrl: 'partials/template/loading.html'
            },
            resolveFailed: { redirectTo: '/error/503' }
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/accountsettings-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        });


    $routeSegmentProvider
        .when('/error/404', 'routeError404')
        .segment('routeError404', {
            templateUrl: 'partials/error/404.html'
        })
        .when('/error/503', 'routeError503')
        .segment('routeError503', {
            templateUrl: 'partials/error/503.html'
        })
        .when('/error/noDevice', 'routeErrorNoDevice')
        .segment('routeErrorNoDevice', {
            templateUrl: 'partials/error/nodevice.html'
        });

    $routeProvider.otherwise({ redirectTo: '/error/404' });

});
