'use strict';

ktbookingApp.controller('TourScheduleBookingController', function ($scope, $routeParams, $location, $timeout, Customer, TourScheduleBooking, TourSchedule, modalService,ngProgress){

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
			$scope.tourSchedule = TourSchedule.get({id:tourinfoId});
			$scope.bookings = TourScheduleBooking.get({id:tourinfoId});
			ngProgress.start();
			$timeout(function (){ngProgress.complete()}, 1000);

		} else {
			$scope.bookings ={};
			$location.path('/tour/schedule');
		}
	};

	$scope.delete = function (id) {
		var modalOptions = {
				closeButtonText : 'Cancel',
				actionButtonText : 'Delete Customer Booking',
				headerText : 'Delete booking For = '+ id +'?',
				bodyText : 'Are you sure you want to delete this customer booking?'
		};
		modalService.showModal({}, modalOptions).then(function (result) {
			if (!result) {
				Booking.delete({id: id},
						function () {
					$scope.bookings = Booking.query();
					processSuccess('Booking Id:'+ id+' has been deleted!');

				});
			}
		});
	};

	$scope.navigate= function (url){
		$location.path('/tourinfo');
	};

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