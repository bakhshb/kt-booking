'use strict';

ktbookingApp.controller('TourPhotoController', function ($scope, $routeParams, $location, $timeout, Tour, TourPhoto, resolvedTourPhoto, modalService, ngProgress){
	
	var tourId = ($routeParams.tourId) ? parseInt($routeParams.tourId): 0, timer;
	$scope.tours = tourId;
	init() ;
	
	/*
	 * first starts when app runs
	 */
	function init() {
		if (tourId > 0) {
			$scope.tour = Tour.get({id:tourId}, function (){
				$scope.tourphotos = resolvedTourPhoto; 
				ngProgress.start();
				$timeout(function (){ngProgress.complete()}, 1000);
			}, function (data){
				processError(data.statusText);
			});
			
		} else {
			$scope.tour ={};
			$location.path('/tour');
		}
	};
	
	$scope.primary = function (tourId,tourPhotoId) {
		TourPhoto.get({id:tourPhotoId}, function success (data){
			var tourName = data.photo;
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Make it Primary ',
					headerText : 'Primary ' + tourName + '?',
					bodyText : 'Are you sure you want to make this tour photo primary?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					TourPhoto.update({tourId:tourId, tourPhotoId: tourPhotoId},function () {
						$scope.tourphotos = TourPhoto.query();
						processSuccess(tourName+' is Primary!');
					});
				}
			});
		});
	};
	
	$scope.delete = function (id) {
		TourPhoto.get({id:id}, function success (data){
			var tourName = data.photo;
			var modalOptions = {
					closeButtonText : 'Cancel',
					actionButtonText : 'Delete Photo',
					headerText : 'Delete ' + tourName + '?',
					bodyText : 'Are you sure you want to delete this tour photo?'
			};
			modalService.showModal({}, modalOptions).then(function (result) {
				if (!result) {
					TourPhoto.delete({id: id},function () {
						$scope.tourphotos = TourPhoto.query();
						processSuccess(tourName+' was deleted!');
					});
				}
			});
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