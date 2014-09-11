'use strict';

/* App Module */

//organised by route. so each route has a module and any associated ctrls, services etc.

angular.module('ktApp', [
  'ngRoute',
  'ngResource',
  'tourAnimations',
  'tourControllers',
  'tourFilters',
  'tourServices'
])

.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
	  when('/home', {
        templateUrl: 'partials/home.html'
      }).
      when('/tours', {
        templateUrl: 'partials/tours.html',
        controller: 'ToursCtrl'
      }).
      when('/tours/:tourId', {
        templateUrl: 'partials/tourdetails.html',
        controller: 'TourDetailsCtrl'
      }).
      otherwise({
        redirectTo: '/tours'
      });
  }]);
