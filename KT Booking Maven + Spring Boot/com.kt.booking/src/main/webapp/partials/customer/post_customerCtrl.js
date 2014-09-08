myApp.factory('PostCustomer', function($http, $resource, $log) {
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
myApp.controller('post_customerCtrl', function($scope, PostCustomer, $http) {

	var LogedinUser = null;
	var getLogedinUser = $http.get("http://localhost:8080/rest/account/login");
	getLogedinUser.success(function(data, status, headers, config) {
		LogedinUser=data;
		console.log(data);
    });
	
	$scope.submit = function() {
		console.log($scope.commitData);
		// put the data filed into object
		
		var customer = new Object();
		customer.createdBy = LogedinUser;
		customer.firstName = $scope.commitData.firstName;
		customer.lastName = $scope.commitData.lastName;
		/*customer.NickName = $scope.commitData.nickName;
		customer.Nationality = $scope.commitData.nationality;
		customer.DateOfBirth = $scope.commitData.dateOfBirth;
		customer.Gender = $scope.commitData.gender;
		customer.Address = $scope.commitData.address;
		customer.Email = $scope.commitData.email;
		customer.ContactNo = $scope.commitData.contactNo;*/

		// insert the data into the api and get server response
		PostCustomer.create({}, customer, function success(data) {
			$scope.serverResponse = 'was sent successfuly';
		}, function err() {
			$scope.serverResponse = 'Please contact the admin';
		});
	}
});
