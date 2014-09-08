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
myApp.controller('post_agentCtrl', function($scope, PostAgent,$http) {

	// to get the login info
	var LogedinUser = null;
	var getLogedinUser = $http.get("http://localhost:8080/rest/account/login");
	getLogedinUser.success(function(data, status, headers, config) {
		LogedinUser=data;
		console.log(data);
    });
	
	$scope.submit = function() {
		// put the data filed into object
		var agent = new Object();
		agent.createdBy=LogedinUser;
		agent.name = $scope.commitData.name;
		agent.address = $scope.commitData.address;
		agent.email = $scope.commitData.email;
		agent.contactNo = $scope.commitData.contactNo;
		agent.userName = $scope.commitData.userName;
		agent.password = $scope.commitData.password;
		agent.isEnabled = true;
		agent.role=$scope.commitData.role;
		// insert the data into the api and get server response
		PostAgent.create({}, agent, function success(data) {
			$scope.serverResponse = 'was sent successfuly';
			$scope.commitData.name = '';
		}, function err() {
			$scope.serverResponse = 'Please contact the admin';
		});
	}
});
