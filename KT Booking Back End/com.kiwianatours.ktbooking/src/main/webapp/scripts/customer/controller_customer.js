'use strict';

ktbookingApp.controller('CustomerController', function ($scope, $location,$timeout, resolvedCustomer, Customer, modalService, ngProgress) {

	$scope.currentPage = 1;
	$scope.pageSize = 10;
	var timer;
	$scope.status = null;
	$scope.error = null;

	ngProgress.start();
	$timeout(function (){
		$scope.customers = resolvedCustomer;
		ngProgress.complete();
	}, 100);

	$scope.delete = function (id) {
		Customer.get({id: id}, function success(data){
			var custName = data.firstName + ' '+ data.lastName;
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Customer',
					headerText : 'Delete ' + custName + '?',
					bodyText : 'Are you sure you want to delete this customer?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					Customer.delete({id:id}, function (){
						$scope.customers = Customer.query();
						processSuccess(custName+' has been deleted!');
					}, function (data){
						if (data.status == 501){
							processError("This customer has Booking and cannot be deleted for security reason.");
						}
					});
				}
			});
		});
	};

	$scope.DisplayModeEnum = {
			Card: 0,
			List: 1
	};

	$scope.changeDisplayMode = function (displayMode) {
		switch (displayMode) {
		case $scope.DisplayModeEnum.Card:
			$scope.listDisplayModeEnabled = false;
			break;
		case $scope.DisplayModeEnum.List:
			$scope.listDisplayModeEnabled = true;
			break;
		}
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
	
	$scope.navigate = function (url, id){
		$location.path(url+id);
	}
	
	$scope.shortenStr = function (value, num) {
		var newvalue = value.length > num ? value.substr(0,num-1)+'...' : value;
		return newvalue;
	};

});
