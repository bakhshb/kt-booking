/**
 * search using angular
 *
 */
myApp.factory('SearchCustomer', function ($resource){
	return $resource ('http://localhost:8080/rest/customer/search/:action',
			{action:'@id'},
			{
				query:{
					method:'GET',
					isArray: false,
			}
		});
});

myApp.controller('search_customerCtrl', function ($scope, SearchCustomer){
	$scope.submit = function(){
		SearchCustomer.query({},{'id':$scope.customerId}, function success (data){
			$scope.result =data;
			$scope.customerId=null;
		}, function error(data){
			$scope.result =data.statusText;
		});
		
	}
	
});