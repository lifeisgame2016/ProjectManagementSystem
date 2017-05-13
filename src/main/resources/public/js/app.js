var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/gallery',{
            templateUrl: 'views/gallery.html',
            controller: 'galleryController'
        })
        .when('/contact',{
            templateUrl: 'views/contact.html',
            controller: 'contactController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
