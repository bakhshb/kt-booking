'use strict';

ktbookingApp
.controller('CustomerEditController',function($rootScope, $scope, $window, $location, $routeParams, $timeout,resolvedCustomer, Customer, Booking, resolvedTour, TourScheduleTour, modalService,  ngProgress) {

	var customerId = ($routeParams.customerId) ? parseInt($routeParams.customerId)
			: 0, timer, onRouteChangeOff;

	$scope.customer = {};
	$scope.title = (customerId > 0) ? 'Edit' : 'Add';
	$scope.buttonText = (customerId > 0) ? 'Update' : 'Add';
	$scope.customer.birthday= '1990';

	$scope.tours= resolvedTour;
	var customers = resolvedCustomer;
	/*
	 * yesterday date
	 */
	var parse = new Date();
	parse.setDate(parse.getDate()-1);
	$scope.yesterday = parse;

	init() ;
	$scope.$watch('customer.email', function (){
		$scope.errorEmailExists =null;

		for (var i =0 ; i<customers.length; i++){
			if (customers[i].email == $scope.customer.email && customerId == 0){
				$scope.id = customers[i].id;
				onRouteChangeOff();
				$scope.errorEmailExists ='has-error';
			}
		}

	});

	$scope.tour={};
	$scope.tour.id='';
	$scope.$watch('tour.id', function (newValue, oldValue){
		if ($scope.tour.id != '') {
			$scope.tourSchedules = TourScheduleTour.get({id:newValue});
		}

	});

	/*
	 * save customer
	 */
	$scope.saveCustomer = function() {
		if ($scope.form.$valid) {
			if (!$scope.customer.id) {
				var birthdayParse= Date.parse($scope.customer.birthday);
				$scope.customer.birthday = birthdayParse;
				Booking.save($scope.customer, function success () {
					onRouteChangeOff(); 
					$scope.success ='OK';
					$scope.insertStatus ='OK';
				}, function err (data){
					processError(data.statusText);
				});
			} else {
				var birthdayParse= Date.parse($scope.customer.birthday);
				$scope.customer.birthday = birthdayParse;
				Customer.save($scope.customer, function success () {
					onRouteChangeOff();
					$scope.success ='OK';
					$scope.updateStatus = 'OK';
				}, function err (data){
					processError(data.statusText);
				});
			}
		}
	};
	/*
	 * delete customer
	 */
	$scope.deleteCustomer = function() {
		var custName = $scope.customer.firstName + ' '
		+ $scope.customer.lastName;
		var modalOptions = {
				closeButtonText : 'Cancel',
				actionButtonText : 'Delete Customer',
				headerText : 'Delete ' + custName + '?',
				bodyText : 'Are you sure you want to delete this customer?'
		};

		modalService.showModal({}, modalOptions).then(function (result) {
			if (!result) {
				Customer.delete({id:$scope.customer.id}, function success(){
					//Stop listening for location changes
					onRouteChangeOff(); 
					$location.path('/customer');
				}, function err(data){
					if (data.status == 501){
						processError("This customer has Booking and cannot be deleted for security reason.");
					}
				});
			}
		});
	};
	/*
	 * first starts when app runs
	 */
	function init() {
		if (customerId > 0) {
			ngProgress.start();
			$timeout(function (){
				$scope.customer =  Customer.get({id:customerId}, function success(){
					ngProgress.complete();
				}, function err (data){
					processError(data.statusText);
				});
			}, 100);
		} else {

			ngProgress.start();
			$timeout(function (){
				$scope.customer = {};	
				ngProgress.complete();
			}, 500);
		}
		/*
		 * Make sure they're warned if they made a change but didn't save it
		 * Call to $on returns a "deregistration" function that can be called to
		 * remove the listener (see routeChange() for an example of using it)
		 */
		onRouteChangeOff = $rootScope.$on('$locationChangeStart', routeChange);
	};

	function routeChange(event, newUrl) {
		//Navigate to newUrl if the form isn't dirty
		if (!$scope.form.$dirty) return;
		var modalOptions = {
				closeButtonText: 'Cancel',
				actionButtonText: 'Ignore Changes',
				headerText: 'Unsaved Changes',
				bodyText: 'You have unsaved changes. Leave the page?',
		};
		modalService.showModal({}, modalOptions).then(function (result) {
			if (!result) {
				onRouteChangeOff(); //Stop listening for location changes
				$window.location.href = newUrl; //Go to page they're interested in
			}
		});

		/*
		 * prevent navigation by default since we'll handle it
		 * once the user selects a dialog option
		 */
		event.preventDefault();
		return;
	};


	/*
	 * display message
	 */
	function processError(error) {
		$scope.error = error;
		startTimer();
	};

	function startTimer() {
		timer = $timeout(function() {
			$timeout.cancel(timer);
			$scope.error = null;
		}, 3000);
	};

	$scope.clear = function () {
		$scope.customer = {};
	};

	// date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};

	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};
	/*
	 * make sure the customer's age is over 18
	 */
	$scope.calculateAge = function (birthday) { 
		// birthday is a date
		if (birthday != null){
			var ageDifMs = Date.now() - birthday;
			var ageDate = new Date(ageDifMs); 
			// miliseconds from epoch
			return Math.abs(ageDate.getUTCFullYear() - 1970);
		}
	};          

});
