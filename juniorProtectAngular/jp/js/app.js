/**
 * Junior Protect AngularJS Web Application
 * 
 * @version 2016-6-23
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 */

var app = angular.module('JuniorProtectWebApp', [
    'ngRoute', 'route-segment', 'view-segment'
]);

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
            controller: 'PageCtrl_Overview'
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
            controller: 'PageCtrl_FilterSettings'
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/filtersettings-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        });


    $routeSegmentProvider
        .when('/accountsettings', 'routeAccountSettings')
        .segment('routeAccountSettings', {
            templateUrl: 'partials/error/404.html',
            controller: ''
        });


    $routeSegmentProvider
        .when('/error/404', 'routeError404')
        .segment('routeError404', {
            templateUrl: 'partials/error/404.html'
        })
        .when('/error/503', 'routeError503')
        .segment('routeError503', {
            templateUrl: 'partials/error/503.html'
        });

    $routeProvider.otherwise({ redirectTo: '/error/404' });

});
