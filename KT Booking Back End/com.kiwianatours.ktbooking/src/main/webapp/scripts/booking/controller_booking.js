'use strict';

ktbookingApp.controller('BookingController', function ($scope, $location,$timeout, Booking, resolvedBooking, modalService, ngProgress) {

	var timer;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	$scope.error = null;
	ngProgress.start();
	$timeout(function (){
		$scope.bookings = resolvedBooking;
		ngProgress.complete();
	}, 100);

});
