var myApp =angular.module('spicyApp1', ['ngResource']);

myApp.factory('Get', function($resource) {
  return $resource(
  'http://localhost:8080/rest/customer/list/:Id', 
  {},{
  	query: 
  		{ method: 'GET',
  		 isArray: true 
  		 }
  });
});

 myApp.controller ('SpicyController', function ($scope,Get) {
	Get.query(function(data){
		$scope.posts = data;
	});
});

