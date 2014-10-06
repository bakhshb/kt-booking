'use strict';

ktbookingApp.controller('CustomerController', function ($scope, $location,$timeout, resolvedCustomer, Customer, modalService, ngProgress) {

	$scope.currentPage = 1;
	$scope.pageSize = 10;
	$scope.customers = resolvedCustomer;
	ngProgress.start();
	$timeout(function (){ngProgress.complete()}, 1000);
	
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

	$scope.navigate = function (url, id) {
		$location.path(url+id);
	};

	function processSuccess(success) {
		$scope.updateStatus = success;
		startTimer();
	};

	function processError(error) {
		$scope.errorMessage = error;
		startTimer();
	};
	var timer;
	function startTimer() {
		timer = $timeout(function() {
			$timeout.cancel(timer);
			$scope.errorMessage = '';
			$scope.updateStatus = '';
		}, 3000);
	};

});
