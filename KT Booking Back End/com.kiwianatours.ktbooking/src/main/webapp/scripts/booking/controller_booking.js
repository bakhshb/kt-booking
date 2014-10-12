'use strict';

ktbookingApp.controller('BookingController', function ($scope, $location,$timeout, Booking, resolvedBooking, modalService, ngProgress) {

	var timer;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	
	ngProgress.start();
	$timeout(function (){
		$scope.bookings = resolvedBooking;
		ngProgress.complete();
	}, 100);
	/*
	 * yesterday date
	 */
	var parse = new Date();
	parse.setDate(parse.getDate()-1);
	$scope.yesterday = parse;
	
	$scope.delete = function (id) {
		
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Customer Booking',
					headerText : 'Delete booking For id = '+ id +'?',
					bodyText : 'Are you sure you want to delete this customer booking?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					Booking.delete({id: id},
							function () {
						$scope.bookings = Booking.query();
						processSuccess('Booking Id: '+id+' has been deleted!');
						
					});
				}
			});
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
