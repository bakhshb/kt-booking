/**
 * posting data using angular 
 * it requires two javascript files that has to be downloaded from angular 
 * app is the name of the application defined in html file 
 */


var myApp = angular.module('app',['ngResource']);

myApp.factory('Post', function ($http,$resource,$log){
	return $resource ('http://localhost:8080/com.kt.booking.restful/api/v1/customer/',
			{},{
				create:{
					method:'POST',
					isArray:true,
					
				},headers:{
					'Content-Type':'application/json'
				}
			});
});

//call the controller defined in html file between <div>
myApp.controller('postController', function ($scope, Post){
	
	$scope.submit = function(){
		console.log($scope.commitData);
		//put the data filed into object
		var postObject = new Object();
		postObject.first_name = $scope.commitData;
		
		// insert the data into the api and get server response 
		Post.create({},postObject, function success(data) {
			if(data[0].HTTP_CODE == 200) {
				$scope.response= data[0].MSG;
			}
			},function err() {
				$scope.response= 'Please contact the admin';
			});
	}
});