'use strict';

angular.module('ktApp.tourz', [])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/tourz', {
		templateUrl: 'tours/tours.html',
		controller: 'ToursCtrl'
	});
}]);