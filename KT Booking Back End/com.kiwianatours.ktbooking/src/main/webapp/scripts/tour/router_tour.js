'use strict';

ktbookingApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
            .when('/tour', {
            	templateUrl: 'views/tours.html',
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
            	templateUrl: 'views/tourPhotos.html',
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
            	templateUrl: 'views/tourPhotoUpload.html',
            	controller: 'TourPhotoUploadController',
            	access: {
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
    });
