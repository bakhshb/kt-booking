/**
 * posting data using angular 
 * it requires two javascript files that has to be downloaded from angular 
 * app is the name of the application defined in html file 
 */


var myApp = angular.module('app',['ngResource','ngCookies']);

myApp.run(function($http, $cookies) {
    $http.defaults.headers.post['X-CSRFToken'] = $cookies.csrftoken;
});

myApp.config(function($httpProvider) {
    $httpProvider.defaults.headers.common['X-CSRFToken'] = '{% csrf_value %}';
});

myApp.factory('Post', function ($http,$resource,$log){
	return $resource ('http://localhost:8080/rest/customer/create',
			{},{
				create:{
					method:'POST',
					isArray:false,
					
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
		postObject.firstName = $scope.commitData.firstName;
		postObject.lastName = $scope.commitData.lastName;
		/*postObject.NickName = $scope.commitData.nickName;
		postObject.Nationality = $scope.commitData.nationality;
		postObject.DateOfBirth = $scope.commitData.dateOfBirth;
		postObject.Gender = $scope.commitData.gender;
		postObject.Address = $scope.commitData.address;
		postObject.Email = $scope.commitData.email;
		postObject.ContactNo = $scope.commitData.contactNo;*/
		
		// insert the data into the api and get server response 
		Post.create({},postObject, function success(data) {
			$scope.serverResponse= 'was sent successfuly';
			},function err() {
				$scope.serverResponse= 'Please contact the admin';
			});
	}
});