myApp.factory('SearchCustomer', function($resource) {
	return $resource('http://localhost:8080/rest/customer/search/:action', {
		action : '@id'
	}, {
		query : {
			method : 'GET',
			isArray : false,
		}
	});
});

myApp.factory('UpdateCustomer', function($resource) {
	return $resource('http://localhost:8080/rest/customer/update/:action', {},
			{
				update : {
					method : 'POST',
					isArray : false,
				}
			});
});

myApp.controller('update_customerCtrl', function($scope, $routeParams, $http,
		SearchCustomer, UpdateCustomer) {

	SearchCustomer.query({}, {
		'id' : $routeParams.id
	}, function success(data) {
		$scope.firstName = data.firstName;
		$scope.lastName = data.lastName;
		$scope.nickName = data.nickName;
		$scope.nationality = data.nationality;
		$scope.birthDate = data.birthDate;
		$scope.gender = data.gender;
		$scope.address = data.address;
		$scope.email = data.email;
		$scope.contactNo = data.contactNo;

	}, function error(data) {
		$scope.result = data.statusText;
	});
	var LogedinUser = null;
	var getLogedinUser = $http.get("http://localhost:8080/rest/account/login");
	getLogedinUser.success(function(data, status, headers, config) {
		LogedinUser = data;
		console.log(data);
	});
	$scope.submit = function() {

		// put the data filed into object
		var customer = new Object();
		customer.id = $routeParams.id;
		customer.firstName = $scope.firstName;
		customer.lastName = $scope.lastName;
		customer.nickName = $scope.nickName;
		customer.nationality = $scope.nationality;
		customer.birthDate = $scope.birthDate;
		customer.gender = $scope.gender;
		customer.address = $scope.address;
		customer.email = $scope.email;
		customer.contactNo = $scope.contactNo;
		customer.createdBy = LogedinUser;

		// insert the data into the api and get server response
		UpdateCustomer.update({}, customer, function success(data) {
			$scope.serverResponse = 'was sent successfuly';
		}, function err() {
			$scope.serverResponse = 'Please contact the admin';
		});
	}
});
