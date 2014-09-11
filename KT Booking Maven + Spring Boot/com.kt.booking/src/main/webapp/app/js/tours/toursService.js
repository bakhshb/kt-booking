'use strict';

angular.module('ktApp.tourz').factory('tourzService', ['$resource', function($resource){
	
	return $resource('tours/:tourId.json', {}, {
		query: {
			method: 'GET', 
			params: {
				phoneId:'tours' //get the whole list .json
			}, 
			isArray:true
		}
	});
	
}]);