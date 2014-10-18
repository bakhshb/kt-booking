'use strict';

ktbookingApp
.config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
	$routeProvider
	.when('/tour/schedule', {
		templateUrl: 'views/tourSchedules.html',
		controller: 'TourScheduleController',
		resolve:{
			resolvedTour: ['Tour', function (Tour) {
				return Tour.query();
			}],
			resolvedTourSchedule: ['TourSchedule', function (TourSchedule) {
				return TourSchedule.query();
			}]
		},
		access: {
			authorizedRoles: [USER_ROLES.all]
		}
	})
	.when('/tour/schedule/booking/:Id', {
		templateUrl: 'views/tourScheduleBookings.html',
		controller: 'TourScheduleBookingController',
		resolve:{
			resolvedTourSchedule: ['TourSchedule', function (TourSchedule) {
				return TourSchedule.query();
			}]
		},
		access: {
			authorizedRoles: [USER_ROLES.all]
		}
	})
	.when('/tour/schedule/calendar', {
		templateUrl: 'views/calendar.html',
		controller: 'CalendarController',
		resolve:{
			resolvedTourSchedule: ['TourSchedule', function (TourSchedule) {
				return TourSchedule.query();
			}]
		},
		access: {
			authorizedRoles: [USER_ROLES.all]
		}
	})
});
