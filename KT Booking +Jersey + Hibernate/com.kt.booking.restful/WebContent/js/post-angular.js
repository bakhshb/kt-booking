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