/**
 * search using angular
 *
 */

var myApp = angular.module('searchApp',['ngResource']);

myApp.factory('Search', function ($resource){
	return $resource ('http://localhost:8080/rest/customer/:action',
			{action:'@id'},
			{
				query:{
					method:'GET',
					isArray: false,
			}
		});
});

myApp.controller('searchController', function ($scope, Search){
	$scope.submit = function(){
		Search.query({},{'id':$scope.customerId}, function success (data){
			$scope.result =data;
		})
		
	}
	
});