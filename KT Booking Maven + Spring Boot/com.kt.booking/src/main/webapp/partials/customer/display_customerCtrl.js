myApp.factory('GetCustomer', function($resource) {
  return $resource(
  'http://localhost:8080/rest/customer/list/:Id', 
  {},{
  	query: 
  		{ method: 'GET',
  		 isArray: true 
  		 }
  });
});

myApp.factory('DeleteCustomer', function ($resource){
	return $resource ('http://localhost:8080/rest/customer/delete/:action',
			{action:'@id'},
			{
				query:{
					method:'GET',
					isArray: false,
			}
		});
});

 myApp.controller ('display_customerCtrl', function ($scope,$timeout,$location,$route,GetCustomer,DeleteCustomer) {
	 
	 GetCustomer.query(function(data){
		$scope.posts = data;
	}, function error(data, status, headers, config){
		$scope.errorResponse = data.statusText;
	});
	
	 
	 $scope.deleteCustomer = function(event){
		 DeleteCustomer.query({},{'id':event.target.id}, function success (data, status, headers, config){
			 $scope.errorResponse='';
			 $scope.successResponse='was successfully sent';
			 $scope.showMessage = true;
			 $timeout(function () { 
				 $scope.showMessage = false;
				 $location.path('/customer/display');
				 $route.reload();
				 }, 3000);  
			 
		}, function error(data, status, headers, config){
			$scope.errorResponse='Error occured when we try to delete the content';
			$scope.successResponse='';
			$scope.showMessage = true;
			 $timeout(function () { 
				 $scope.showMessage = false;
				 }, 3000);  
		});
	 };
});

