'use strict';

ktbookingApp.controller('TourPhotoUploadController', function ($scope, $routeParams, $timeout, $location,Tour, TourPhoto, FileUploader, ngProgress){

	var tourId = ($routeParams.tourId) ? parseInt($routeParams.tourId): 0, timer;

	if (tourId > 0) {
		ngProgress.start();
		$timeout(function (){
			$scope.tour = Tour.get({id:tourId});
			ngProgress.complete();
		}, 100);

	} else {
		$scope.tour ={};
		$location.path('/tour');
	}


	var uploader = $scope.uploader = new FileUploader({
		url: 'app/rest/tourphotos',
	});

	// FILTERS

	uploader.filters.push({
		name: 'customFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
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
		console.info('file Name: ', headers.filename);
		if (headers.filename == null){
			alert('failed to upload');
		}
		else{
		$scope.status = 'ok';
		}
		var tourPhoto = {
				fileName: headers.filename,
				tourId: tourId,
		};
		TourPhoto.put(tourPhoto);
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

});