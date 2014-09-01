/**
 * 
 */
var myApp = angular.module('myApp', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

myApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'partials/index.html'
	}).when('/post', {
		templateUrl : 'partials/post/post.html',
		controller : 'postCtrl'
	}).when('/search', {
		templateUrl : 'partials/search/search.html',
		controller : 'searchCtrl'
	}).when('/display', {
		templateUrl : 'partials/display/display.html',
		controller : 'displayCtrl'
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
