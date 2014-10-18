'use strict';

ktbookingApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
            .when('/customer', {
            	templateUrl: 'views/customers.html',
            	controller: 'CustomerController',
            	resolve:{
            		resolvedCustomer: ['Customer', function (Customer) {
            			return Customer.query();
            		}]
            	},
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/customer/edit/:customerId', {
            	templateUrl: 'views/customerEdit.html',
            	controller: 'CustomerEditController',
            	resolve:{
            		resolvedCustomer: ['Customer', function (Customer) {
            			return Customer.query();
            		}],
            		resolvedTour: ['Tour', function (Tour) {
            			return Tour.query();
            		}],
            		resolvedTourSchedule: ['TourSchedule', function (TourSchedule) {
            			return TourSchedule.query();
            		}],
            	},
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/customer/booking/:customerId', {
            	templateUrl: 'views/customerBookings.html',
            	controller: 'CustomerBookingController',
            	resolve:{
            		resolvedBooking: ['Booking', function (Customer) {
            			return Customer.query();
            		}],
            		resolvedTour: ['Tour', function (Tour) {
            			return Tour.query();
            		}],
            		resolvedTourSchedule: ['TourSchedule', function (TourSchedule) {
            			return TourSchedule.query();
            		}],
            	},
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
    });
