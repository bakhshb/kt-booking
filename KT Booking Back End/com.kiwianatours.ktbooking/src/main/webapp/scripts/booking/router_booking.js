'use strict';

ktbookingApp
.config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
	$routeProvider
	.when('/booking', {
		templateUrl: 'views/bookings.html',
		controller: 'BookingController',
		resolve:{
			resolvedBooking: ['Booking', function (Customer) {
				return Customer.query();
			}]
		},
		access: {
			authorizedRoles: [USER_ROLES.all]
		}
	})
});
