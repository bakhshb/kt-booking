/**
 * search using angular
 *
 */

var myApp = angular.module('searchApp',['ngResource']);

myApp.factory('Search', function ($resource){
	return $resource ('http://localhost:8080/com.kt.booking.restful/api/v1/customer/:action',
			{action:'@id'},
			{
				query:{
					method:'GET',
					isArray: true,
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