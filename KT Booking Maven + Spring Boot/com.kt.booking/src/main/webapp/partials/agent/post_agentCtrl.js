myApp.factory('PostAgent', function($resource) {
	return $resource('http://localhost:8080/rest/account/create', {}, {
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
myApp.controller('post_agentCtrl',
		function($scope,$http,$timeout,$location,$route,PostAgent) {

			// to get the login info
			var LogedinUser = null;
			var getLogedinUser = $http
					.get("http://localhost:8080/rest/account/login");
			getLogedinUser.success(function(data, status, headers, config) {
				LogedinUser = data;
			});

			$scope.submit = function() {
				$scope.agent.createdBy = LogedinUser;
				$scope.agent.isEnabled = true;
				// insert the data into the api and get server response
				PostAgent.create({}, $scope.agent, function success(data) {
					$scope.successResponse = 'was sent successfuly';
					$scope.errorResponse = '';
					$scope.agentForm.$setPristine();
					$scope.agent = {};
					$scope.showMessage = true;
					 $timeout(function (data, status, headers, config) { 
						 $scope.showMessage = false;
						 $location.path('/');
						 $route.reload();
						 }, 3000);  
				}, function err(data, status, headers, config) {
					$scope.errorResponse = data.headers('MyResponseHeader');
					$scope.successResponse = '';
					$scope.showMessage = true;
					 $timeout(function () { 
						 $scope.showMessage = false;
						 }, 3000);  
				});
			};
		});
