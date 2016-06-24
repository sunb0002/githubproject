/**
 * Main AngularJS Web Application
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

    $routeSegmentProvider.options.autoLoadTemplates = true;
    $routeSegmentProvider
        .when('/overview', 's_overview')
        .when('/overview/:id', 's_overview.seg')
        .when('/filtersettings', 's_filtersettings')
        .when('/filtersettings/:id', 's_filtersettings.seg')
        .when('/accountsettings', 's_accountsettings')
        .segment('s_overview', {
            templateUrl: 'partials/overview.html',
            controller: 'PageCtrl_Overview'
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/overview-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        })
        .up()
        .segment('s_filtersettings', {
            templateUrl: 'partials/filtersettings.html',
            controller: 'PageCtrl_FilterSettings'
        })
        .within()
        .segment('seg', {
            templateUrl: 'partials/filtersettings-segment.html',
            controller: 'SegmentCtrl',
            dependencies: ['id']
        })
        .up()
        .segment('s_accountsettings', {
            templateUrl: 'partials/error.html',
            controller: ''
        });

    $routeProvider.otherwise({ redirectTo: '/overview' });
});

