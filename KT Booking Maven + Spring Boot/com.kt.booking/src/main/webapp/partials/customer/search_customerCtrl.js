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

myApp.controller('search_customerCtrl', function ($scope,$timeout,$location,$route, SearchCustomer){
	$scope.submit = function(){
		SearchCustomer.query({},{'id':$scope.customer.id}, function success (data){
			$scope.result =data;
			$scope.error='';
		}, function error(data){
			$scope.error =data.statusText;
			$scope.result ='';
			$scope.customer={};
			$scope.showMessage = true;
			 $timeout(function () { 
				 $scope.showMessage = false;
				 }, 3000);  
		});
		
	};
	
});