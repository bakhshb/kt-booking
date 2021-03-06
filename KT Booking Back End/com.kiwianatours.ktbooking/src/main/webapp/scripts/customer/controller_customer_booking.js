'use strict';

ktbookingApp.controller('CustomerBookingController',function($scope, $routeParams,$timeout, $location, Customer, Booking, resolvedBooking, resolvedTour, TourScheduleTour,  modalService, ngProgress){

	var customerId = ($routeParams.customerId) ? parseInt($routeParams.customerId): 0, timer;
	$scope.customers = customerId;
	$scope.tours = resolvedTour;
	$scope.status = null;
	$scope.error = null;
	
	/*
	 * yesterday date
	 */
	var parse = new Date();
	parse.setDate(parse.getDate()-1);
	$scope.yesterday = parse;
	
	init() ;

	function init() {
		if (customerId > 0) {
			$scope.customer =  Customer.get({id:customerId}, function(){
				ngProgress.start();
				$timeout(function (){
					$scope.bookings = Booking.get({id:customerId});
					ngProgress.complete();
				}, 100);
			}, function(data){
				processError(data.statusText);
			});

		} else {
			$scope.customer = {};
			$location.path('/customer');
		}
	};
	
	$scope.change = function (id, status) {
		Booking.update({id:id, status: status}, function (){
			$scope.bookings = Booking.get({id:customerId});
		});
	};

	$scope.delete = function (id) {
		Customer.get({id:customerId}, function (data){
			var custName = data.firstName + ' '+ data.lastName;
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Customer Booking',
					headerText : 'Delete booking for ' + custName + '?',
					bodyText : 'Are you sure you want to delete this customer booking?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					Booking.delete({id: id},
							function () {
						processSuccess('Booking for '+custName+' has been deleted!');
						$scope.bookings = Booking.get({id:customerId});
					});
				}
			});
		});
	};
	
	$scope.clear = function(){
		$scope.tour ={};
		$scope.customer.tourSchedule = {};
	}
	
	
	$scope.tour={};
	$scope.tour.id='';
	$scope.$watch('tour.id', function (newValue, oldValue){
		if ($scope.tour.id != '') {
			$scope.tourSchedules = TourScheduleTour.get({id:newValue});
		}

	});

	/*
	 * Make booking when the url has customer id
	 */
	$scope.addBooking = function (id){
		$('#saveBookingModal').modal('show');

		Customer.get({id:id}, function success (data){
			$scope.saveBooking = function (){
				if ($scope.form.$valid) {
					Booking.save($scope.customer,function () {
						$scope.clear();
						$scope.bookings = Booking.get({id:customerId});
						$('#saveBookingModal').modal('hide');
						processSuccess('Booking has been made and the details has been sent.');
					});
				};
			};
		});
	};

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
			$scope.status = null;
			$scope.error = null;
		}, 3000);
	};

});