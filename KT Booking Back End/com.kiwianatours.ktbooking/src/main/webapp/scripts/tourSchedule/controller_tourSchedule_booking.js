'use strict';

ktbookingApp.controller('TourScheduleBookingController', function ($scope, $routeParams, $location, $timeout, TourScheduleBooking, TourSchedule, modalService, ngProgress){

	var tourinfoId = ($routeParams.Id) ? parseInt($routeParams.Id): 0;
	$scope.currentPage = 1;
	$scope.pageSize = 10;

	/*
	 * yesterday date
	 */
	var parse = new Date();
	parse.setDate(parse.getDate()-1);
	$scope.yesterday = parse;
	
	init() ;

	/*
	 * first starts when app runs
	 */
	function init() {
		if (tourinfoId > 0) {
			$scope.tourSchedule = TourSchedule.get({id:tourinfoId});
			$scope.bookings = TourScheduleBooking.get({id:tourinfoId});
			ngProgress.start();
			$timeout(function (){ngProgress.complete()}, 1000);

		} else {
			$scope.bookings ={};
			$location.path('/tour/schedule');
		}
	};

});