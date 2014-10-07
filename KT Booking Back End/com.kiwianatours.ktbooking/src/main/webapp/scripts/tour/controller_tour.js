'use strict';

ktbookingApp.controller('TourController', function ($scope, $location,$timeout, resolvedTour, Tour, modalService, ngProgress) {

	var timer;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	ngProgress.start();
	$timeout(function (){
		$scope.tours = resolvedTour;
		ngProgress.complete();
	}, 100);

	$scope.create = function () {
		if ($scope.form.$valid) {
			Tour.save($scope.tour,function () {
				$scope.tours = Tour.query();
				$('#saveTourModal').modal('hide');
				$scope.clear();
				processSuccess('Tour was updated!');

			});
		};
	};

	$scope.update = function (id) {
		$scope.tour = Tour.get({id: id});
		$('#saveTourModal').modal('show');
	};

	$scope.delete = function (id) {
		Tour.get({id:id}, function success (data){
			var tourName = data.name;
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Tour',
					headerText : 'Delete ' + tourName + '?',
					bodyText : 'Are you sure you want to delete this tour?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					Tour.delete({id: id},function () {
						$scope.tours = Tour.query();
						processSuccess('Tour was deleted!');
					});
				}
			});
		});
	};

	$scope.clear = function () {
		$scope.tour = {};
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
