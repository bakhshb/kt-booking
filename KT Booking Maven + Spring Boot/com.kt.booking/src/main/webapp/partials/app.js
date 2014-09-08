/**
 * 
 */
var myApp = angular.module('myApp', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

myApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'partials/index.html'
	}).when('/customer/post', {
		templateUrl : 'partials/customer/post_customer.html',
		controller : 'post_customerCtrl'
	}).when('/customer/search', {
		templateUrl : 'partials/customer/search_customer.html',
		controller : 'search_customerCtrl'
	}).when('/customer/display', {
		templateUrl : 'partials/customer/display_customer.html',
		controller : 'display_customerCtrl'
	}).when('/customer/update/:id', {
		templateUrl : 'partials/customer/update_customer.html',
		controller : 'update_customerCtrl'
	}).when('/agent/post', {
		templateUrl : 'partials/agent/post_agent.html',
		controller : 'post_agentCtrl'
	}).otherwise({
		redirectTo : ''
	});
});

/*
 * function mainCtrl($scope, $http, $cookieStore) {
 * $http.get('http://localhost:8080/rest/account/cookie').
 * success(function(data) { $cookieStore.put('hh',data.value); var username =
 * data.value; console.log(data.value); console.log(username); }); var username =
 * $cookieStore.get('username'); // var username = $cookieStore.get('username');
 * if (username !=''){ $scope.loginStatus=username+' Logout';
 * $scope.loginUrl='/logout';
 * 
 * }else{ $scope.loginStatus='Login'; $scope.loginUrl='/login'; } }
 */

myApp.controller('mainCtrl', [ '$scope', '$cookies',
		function($scope, $cookies) {
			var username = '';
			// Retrieving a cookie
			if ($cookies.username) {
				username = $cookies.username;
			}
			console.log(username);
			if (username != '' && username != ' undefined') {
				$scope.loginStatus = username + ' Logout';
				$scope.loginUrl = '/logout';

			} else {
				$scope.loginStatus = 'Login';
				$scope.loginUrl = '/login';

			}
		} ]);
