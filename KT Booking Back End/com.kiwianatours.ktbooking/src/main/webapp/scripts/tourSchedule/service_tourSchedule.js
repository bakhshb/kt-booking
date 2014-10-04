'use strict';

ktbookingApp.factory('TourSchedule', function ($resource) {
	return $resource('app/rest/tourschedules/:id', {}, {
		'query': { method: 'GET', isArray: true},
		'get': { method: 'GET'},
	});
});

ktbookingApp.factory('TourScheduleBooking', function ($resource) {
	return $resource('app/rest/tour/bookings/:id', {}, {
		'get': { method: 'GET',  isArray: true},
	});
});