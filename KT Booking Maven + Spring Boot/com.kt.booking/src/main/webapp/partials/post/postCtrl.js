myApp.factory('Post', function($http, $resource, $log) {
	return $resource('http://localhost:8080/rest/customer/create', {}, {
		create : {
			method : 'POST',
			isArray : false,

		},
		headers : {
			'Content-Type' : 'application/json'
		}
	});
});

// call the controller defined in html file between <div>
myApp.controller('postCtrl', function($scope, Post) {

	$scope.submit = function() {
		console.log($scope.commitData);
		// put the data filed into object
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
		Post.create({}, postObject, function success(data) {
			$scope.serverResponse = 'was sent successfuly';
		}, function err() {
			$scope.serverResponse = 'Please contact the admin';
		});
	}
});
