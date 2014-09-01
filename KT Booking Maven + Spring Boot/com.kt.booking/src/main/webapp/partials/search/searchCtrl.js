/**
 * search using angular
 *
 */
myApp.factory('Search', function ($resource){
	return $resource ('http://localhost:8080/rest/customer/search/:action',
			{action:'@id'},
			{
				query:{
					method:'GET',
					isArray: false,
			}
		});
});

myApp.controller('searchCtrl', function ($scope, Search){
	$scope.submit = function(){
		Search.query({},{'id':$scope.customerId}, function success (data){
			$scope.result =data;
		})
		
	}
	
});