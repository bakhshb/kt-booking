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
myApp.controller('post_customerCtrl', function($scope, $http,$timeout,$location,$route, PostCustomer) {

	/*
	 * 
	 * function getting the loged in username
	 */
	var LogedinUser = null;
	var getLogedinUser = $http.get("http://localhost:8080/rest/account/login");
	getLogedinUser.success(function(data, status, headers, config) {
		LogedinUser = data;
	}). error (function(data, status, headers, config){
		$scope.errorResponse = data.headers('MyResponseHeader');
	});
	// set default value for gender
	$scope.customer = {gender:'Male'};
	/**
	 * function submiting the form
	 */
	$scope.submit = function() {
		//add the login account for tracking
		$scope.customer.createdBy = LogedinUser;
		// insert the data into the api and get server response
		PostCustomer.create({}, $scope.customer, function success(data, status, headers, config) {
			$scope.serverResponse = 'was sent successfuly';
			$scope.errorResponse='';
			$scope.customerForm.$setPristine();
			$scope.customer = {};
			 $scope.showMessage = true;
			 $timeout(function () { 
				 $scope.showMessage = false;
				 $location.path('/customer/display');
				 $route.reload();
				 }, 3000);  
			
		}, function err(data, status, headers, config) {
			$scope.errorResponse = 'Error occurred while posting a customer';
			$scope.successResponse='';
			$scope.showMessage = true;
			 $timeout(function () { 
				 $scope.showMessage = false;
				 }, 3000);  
		});
	};
});
