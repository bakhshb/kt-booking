var myApp =angular.module('spicyApp1', ['ngResource']);

myApp.factory('Get', function($resource) {
  return $resource('http://localhost:8080/com.kt.booking.rest/api/v1/inventory/:customer_id', {}, 
  {query: { method: 'GET', isArray: false }
  });
});

 myApp.controller ('SpicyController', function ($scope,Get) {
	Get.query(function(data){
	$scope.twitterResult = data;
	});
});
