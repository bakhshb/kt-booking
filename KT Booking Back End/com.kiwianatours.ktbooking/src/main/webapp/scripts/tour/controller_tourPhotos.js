'use strict';

ktbookingApp.controller('TourPhotoController', function ($scope, $routeParams, $location, $timeout, Tour, TourPhoto, resolvedTourPhoto, modalService, ngProgress){

	var tourId = ($routeParams.tourId) ? parseInt($routeParams.tourId): 0, timer;
	$scope.tours = tourId;
	$scope.status = null;
	$scope.error = null;
	init() ;

	/*
	 * first starts when app runs
	 */
	function init() {
		if (tourId > 0) {
			ngProgress.start();
			$timeout(function (){
				$scope.tour = Tour.get({id:tourId}, function (){

					$scope.tourphotos = TourPhoto.get({id:tourId}); 
					ngProgress.complete();

				}, function (data){
					processError(data.statusText);
				});
			}, 100);

		} else {
			$scope.tour ={};
			$location.path('/tour');
		}
	};

	$scope.primary = function (tourId,tourPhotoId) {
		var modalOptions = {
				closeButtonText : 'Cancel',
				actionButtonText : 'Make it Primary ',
				headerText : 'Primary Photo Id: ' + tourPhotoId + '?',
				bodyText : 'Are you sure you want to make this tour photo primary?'
		};
		modalService.showModal({}, modalOptions).then(function (result) {
			if (!result) {
				TourPhoto.put({tourId:tourId, tourPhotoId: tourPhotoId},function () {
					$scope.tourphotos = TourPhoto.get({id:tourId});
					processSuccess('Photo Id '+tourPhotoId+' is Primary!');
				});
			}
		});
	};

	$scope.delete = function (id) {
		var modalOptions = {
				closeButtonText : 'Cancel',
				actionButtonText : 'Delete Photo',
				headerText : 'Delete Photo Id ' + id + '?',
				bodyText : 'Are you sure you want to delete this tour photo?'
		};
		modalService.showModal({}, modalOptions).then(function (result) {
			if (!result) {
				TourPhoto.delete({id: id},function () {
					$scope.tourphotos = TourPhoto.get({id:tourId});
					processSuccess('Photo Id: '+id+' was deleted!');
				});
			}
		});

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
	
	$scope.extStr = function (filename) {
		return (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename) : undefined;
	};

});