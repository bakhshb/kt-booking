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

 myApp.controller ('display_customerCtrl', function ($scope,GetCustomer) {
	 GetCustomer.query(function(data){
		$scope.posts = data;
	});
});

