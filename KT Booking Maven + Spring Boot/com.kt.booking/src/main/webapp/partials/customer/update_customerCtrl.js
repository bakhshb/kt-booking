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
		$location, SearchCustomer, UpdateCustomer) {

	SearchCustomer.query({}, {
		'id' : $routeParams.id
	}, function success(data, status, headers, config) {
		// $scope.form.$setPristine();
		$scope.customer = {
			firstName : data.firstName,
			lastName : data.lastName,
			nickName : data.nickName,
			nationality : data.nationality,
			birthDate : data.birthDate,
			gender : data.gender,
			address : data.address,
			email : data.email,
			contactNo : data.contactNo
		};

	}, function error(data, status, headers, config) {
		$scope.result = data.statusText;
	});
	var LogedinUser = null;
	var getLogedinUser = $http.get("http://localhost:8080/rest/account/login");
	getLogedinUser.success(function(data, status, headers, config) {
		LogedinUser = data;
	});
	$scope.submit = function() {
		$scope.customer.id = $routeParams.id;
		$scope.customer.createdBy = LogedinUser;
		// insert the data into the api and get server response
		UpdateCustomer.update({}, $scope.customer, function success(data, status, headers, config) {
			alert(data.headers('MyResponseHeader'));
			$location.path('/display');
		}, function err(data, status, headers, config) {
			$scope.errorResponse = 'Error occured while trying to update the database';
		});
	};
});
