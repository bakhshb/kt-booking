/**
 * 
 */

var myApp = angular.module ('myApp',['ngRoute','ngResource']);

myApp.config (function($routeProvider){
	$routeProvider
	.when('/',{templateUrl:'partials/index.html'})
	.when('/post',{templateUrl:'partials/post_angular.html', controller:'postController'})
	.otherwise({redirectTo:''});
});

myApp.factory('Post', function ($http,$resource,$log){
	return $resource ('http://localhost:8080/rest/customer/',
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
		postObject.FirstName = $scope.commitData.firstName;
		postObject.LastName = $scope.commitData.lastName;
		postObject.NickName = $scope.commitData.nickName;
		postObject.Nationality = $scope.commitData.nationality;
		postObject.DateOfBirth = $scope.commitData.dateOfBirth;
		postObject.Gender = $scope.commitData.gender;
		postObject.Address = $scope.commitData.address;
		postObject.Email = $scope.commitData.email;
		postObject.ContactNo = $scope.commitData.contactNo;
		
		// insert the data into the api and get server response 
		Post.create({},postObject, function success(data) {
			if(data[0].HTTP_CODE == 200) {
				$scope.serverResponse= data[0].MSG;
			}
			},function err() {
				$scope.serverResponse= 'Please contact the admin';
			});
	}
});