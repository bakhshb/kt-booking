'use strict';

ktbookingApp.controller('TourScheduleBookingController', function ($scope, $routeParams, $location, $timeout, TourScheduleBooking, TourSchedule, modalService, ngProgress){

	var tourinfoId = ($routeParams.Id) ? parseInt($routeParams.Id): 0, timer;
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
			ngProgress.start();
			$timeout(function (){
				$scope.tourSchedule = TourSchedule.get({id:tourinfoId}, function (){
					$scope.bookings = TourScheduleBooking.get({id:tourinfoId});
					ngProgress.complete();
				}, function (data){
					processError(data.status);
				});
				
			}, 100);

		} else {
			$scope.bookings ={};
			$location.path('/tour/schedule');
		}
	};
	
	/*
	 * display message
	 */
	function processSuccess(success) {
		$scope.updateStatus = success;
		startTimer();
	};

	function processError(error) {
		$scope.errorMessage = error;
		startTimer();
	};

	function startTimer() {
		timer = $timeout(function() {
			$timeout.cancel(timer);
			$scope.errorMessage = '';
			$scope.updateStatus = '';
		}, 3000);
	};


});