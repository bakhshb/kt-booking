'use strict';

ktbookingApp.controller('TourPhotoUploadController', function ($scope, $routeParams, $timeout, $location,Tour, TourPhoto, FileUploader, ngProgress){

	var tourId = ($routeParams.tourId) ? parseInt($routeParams.tourId): 0;

	if (tourId > 0) {
		$scope.tour = Tour.get({id:tourId});
		ngProgress.start();
		$timeout(function (){ngProgress.complete()}, 1000);

	} else {
		$scope.tour ={};
		$location.path('/tour');
	}


	var uploader = $scope.uploader = new FileUploader({
		url: 'app/rest/tourphotos'
	});

	// FILTERS

	uploader.filters.push({
		name: 'customFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			return this.queue.length < 10;
		}
	});

	// CALLBACKS

	uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
		console.info('onWhenAddingFileFailed', item, filter, options);
	};
	uploader.onAfterAddingFile = function(fileItem) {
		console.info('onAfterAddingFile', fileItem);
	};
	uploader.onAfterAddingAll = function(addedFileItems) {
		console.info('onAfterAddingAll', addedFileItems);
	};
	uploader.onBeforeUploadItem = function(item) {
		console.info('onBeforeUploadItem', item);
	};
	uploader.onProgressItem = function(fileItem, progress) {
		console.info('onProgressItem', fileItem, progress);
	};
	uploader.onProgressAll = function(progress) {
		console.info('onProgressAll', progress);
	};
	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		console.info('onSuccessItem', fileItem, response, status, headers);
		console.info('heelo', fileItem.file.name);
		var tourPhoto = {
				fileName: fileItem.file.name,
				tourId: tourId,
		};
		TourPhoto.update(tourPhoto);
	};
	uploader.onErrorItem = function(fileItem, response, status, headers) {
		console.info('onErrorItem', fileItem, response, status, headers);
	};
	uploader.onCancelItem = function(fileItem, response, status, headers) {
		console.info('onCancelItem', fileItem, response, status, headers);
	};
	uploader.onCompleteItem = function(fileItem, response, status, headers) {
		console.info('onCompleteItem', fileItem, response, status, headers);
	};
	uploader.onCompleteAll = function() {
		console.info('onCompleteAll');
	};

	console.info('uploader', uploader);

	$scope.navigate = function (url){
		$location.path(url);
	}




































	init() ;

	/*
	 * first starts when app runs
	 */
	function init() {
		if (tourId > 0) {
			$scope.upload=function(){
				var files = $scope.myFile;
				console.log('file is ' + JSON.stringify(files));
				var fd = new FormData();
				fd.append("file", files);
				UploadPhoto.save(fd, function (value, responseHeaders){
					var tourPhoto = {
							fileName: responseHeaders('uploadFile'),
							tourId: tourId,
					};
					TourPhoto.save(tourPhoto);
				}, function (){

				});
			};
		} else {

		}
	};


	$scope.uploadFile = function(files) {
		var fd = new FormData();
		//Take the first selected file
		fd.append("file", files[0]);
		UploadPhoto.save(fd);

	};

});