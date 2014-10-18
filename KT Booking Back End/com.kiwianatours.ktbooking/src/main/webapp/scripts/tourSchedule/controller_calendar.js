'use strict';

ktbookingApp.controller('CalendarController', function ($scope, $timeout, $filter, TourSchedule, resolvedTourSchedule,ngProgress) {
	var tours = resolvedTourSchedule;
	$scope.tourSchedules ={};
	$scope.tourSchedules.color = 'navey';	
	$scope.tourSchedules.events = [];
	$scope.eventSource = {
			url: "https://www.google.com/calendar/feeds/en-gb.new_zealand%23holiday%40group.v.calendar.google.com/public/basic",
			className: 'gcal-event',           // an option!
	};
	$scope.eventSource.color ='red';

	if( window.localStorage )
	{
		if( !localStorage.getItem( 'firstLoad' ) )
		{
			localStorage[ 'firstLoad' ] = true;
			window.location.reload();

		}  
		else
			localStorage.removeItem( 'firstLoad' );
		ngProgress.start();
		$timeout(function (){
			ngProgress.complete();
		}, 100);

	}


	init();

	function init() {
		for (var i=0;i<tours.length;i++){
			var e = new Date(tours[i].returnDate)
			e.setDate(e.getDate()+1);
			$scope.tourSchedules.events.push({
				title: tours[i].tour.name,
				start: tours[i].departureDate,
				end: $filter('date')(new Date(e), 'yyyy-MM-dd')
			});
		}
	};

	$scope.eventSources = [$scope.eventSource, $scope.tourSchedules ];


});