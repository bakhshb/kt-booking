'use strict';

ktbookingApp.controller('TourScheduleController', function ($scope, $location, $timeout, resolvedTourSchedule, TourSchedule, TourScheduleBooking,  resolvedTour ,Tour, modalService, ngProgress) {

	var timer;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	$scope.tours= resolvedTour;
	$scope.status = null;
	$scope.error = null;

	ngProgress.start();
	$timeout(function (){
		$scope.tourSchedules = resolvedTourSchedule;
		ngProgress.complete();
	}, 100);
	/*
	 * return date is not less than departure date
	 */
	$scope.tourSchedule ={};
	$scope.$watch('tourSchedule.returnDate', function (){
		$scope.returnDateError =null;
		if ($scope.tourSchedule.returnDate < $scope.tourSchedule.departureDate){
			$scope.returnDateError ='has-error';
		}
	});
	/*
	 * yesterday date
	 */
	var parse = new Date();
	parse.setDate(parse.getDate()-1);
	$scope.yesterday = parse;

	$scope.create = function () {
		if ($scope.form.$valid) {
			$scope.disableCheck = false;
			var departureDateParse= Date.parse($scope.tourSchedule.departureDate );
			$scope.tourSchedule.departureDate =departureDateParse ;
			var returnDateParse = Date.parse($scope.tourSchedule.returnDate );
			$scope.tourSchedule.returnDate =returnDateParse ;
			TourSchedule.save($scope.tourSchedule,function () {
				$scope.tourSchedules = TourSchedule.query();
				$('#saveTourScheduleModal').modal('hide');
				$scope.clear();
				$scope.checked = false;
				processSuccess('TourSchedule was updated!');
			});
		};
	};

	$scope.update = function (id) {
		$scope.disableCheck = true;
		$scope.tourSchedule = TourSchedule.get({id: id});
		$('#saveTourScheduleModal').modal('show');
	};

	$scope.delete = function (id) {
		TourSchedule.get({id:id}, function success(data){
			var tourScheduleDate = data.departureDate +' for '+ data.tour.name +" tour ";
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Tour Schedule',
					headerText : 'Delete Depature Date ' + tourScheduleDate + '?',
					bodyText : 'Are you sure you want to delete this tourSchedule?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					TourSchedule.delete({id: id},
							function () {
						$scope.tourSchedules = TourSchedule.query();
						processSuccess(tourScheduleDate +' has been deleted!');
					}, function (data){
						if (data.status == 501){
							processError("This tour schedule has customer Booking and cannot be deleted for security reason.");
						}
					});
				}
			});

		});
	};
		
	$scope.clear = function () {
		$scope.tourSchedule = {};
		$scope.tourSchedule.price= 0;
	};

	/*
	 * date picker
	 */
	$scope.toggleMin = function() {
		$scope.minDate = $scope.minDate ? null : new Date();
	};
	$scope.toggleMin();

	$scope.openDeparture = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.departureopened = true;
	};

	$scope.openReturn = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.returnopened = true;
	};
	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};
	/*
	 * display message
	 */
	function processSuccess(success) {
		$scope.status = success;
		startTimer();
	};

	function processError(error) {
		$scope.error = error;
		startTimer();
	};

	function startTimer() {
		timer = $timeout(function() {
			$timeout.cancel(timer);
			$scope.error = null;
			$scope.status = null;
		}, 3000);
	};
});
