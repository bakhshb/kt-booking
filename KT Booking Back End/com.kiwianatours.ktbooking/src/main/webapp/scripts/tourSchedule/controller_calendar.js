'use strict';

ktbookingApp.controller('CalendarController', function ($scope, $timeout, ngProgress){

	ngProgress.start();
	$timeout(function (){
		ngProgress.complete();
	}, 100);
});