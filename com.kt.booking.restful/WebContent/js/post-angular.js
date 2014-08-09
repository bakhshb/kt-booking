/**
 * 
 */

var myApp = angular.module('app',['ngResource']);

myApp.factory('Post', function ($http,$resource,$log){
	return $resource ('http://localhost:8080/com.kt.booking.restful/api/v1/customer/',
			{},{
				create:{
					method:'POST',
					isArray:true
				}
			});
});

myApp.controller('postController', function ($scope, Post){
	
	$scope.submit = function(){
		console.log($scope.commitData);
		var postObject = new Object();
		postObject.first_name = $scope.commitData;
		Post.create({},postObject);
	}
});