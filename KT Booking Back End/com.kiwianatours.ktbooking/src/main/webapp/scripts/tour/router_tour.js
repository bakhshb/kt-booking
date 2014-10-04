'use strict';

ktbookingApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
            .when('/tour', {
            	templateUrl: 'views/tours/tours.html',
            	controller: 'TourController',
            	resolve:{
            		resolvedTour: ['Tour', function (Tour) {
            			return Tour.query();
            		}]
            	},
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/tourphoto/:tourId', {
            	templateUrl: 'views/tours/tourPhotos.html',
            	controller: 'TourPhotoController',
            	resolve:{
            		resolvedTourPhoto: ['TourPhoto', function (TourPhoto) {
            			return TourPhoto.query();
            		}],
            	},
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/tourphoto/upload/:tourId', {
            	templateUrl: 'views/tours/tourPhotoUpload.html',
            	controller: 'TourPhotoUploadController',
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
    });
