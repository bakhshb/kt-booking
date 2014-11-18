'use strict';

ktbookingApp.factory('Tour', function ($resource) {
	return $resource('app/rest/tours/:id', {}, {
		'query': { method: 'GET', isArray: true},
		'get': { method: 'GET'}
	});
});

ktbookingApp.factory('TourPhoto', function ($resource) {
	return $resource('app/rest/tourphotos/:id', {}, {
		'query': { method: 'GET', isArray: true},
		'get': { method: 'GET', isArray:true},
		'put':{method:'PUT'}
	});
});