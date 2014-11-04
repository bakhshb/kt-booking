'use strict';

ktbookingApp.controller('CalendarController', function ($scope, $timeout, $filter,resolvedTourSchedule,ngProgress) {
	var tours = resolvedTourSchedule;
	$scope.events = [];
	$scope.eventSource = {
			url: "https://www.google.com/calendar/feeds/en-gb.new_zealand%23holiday%40group.v.calendar.google.com/public/basic",
			className: 'gcal-event',           // an option!
	};
	$scope.eventSource.color ='darkred';

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
			var e = new Date(tours[i].returnDate);
			e.setDate(e.getDate()+1);
			$scope.events.push({
				title: tours[i].tour.name,
				start: tours[i].departureDate,
				end: $filter('date')(new Date(e), 'yyyy-MM-dd')
			});
		}
	};
   
    
    $scope.uiConfig = {
    	      calendar:{
    	        height: 500,
    	        editable: true,
    	        header:{
    	          left: 'month basicWeek  agendaWeek',
    	          center: 'title',
    	          right: 'today prev,next'
    	        },
    	        dayClick: $scope.alertEventOnClick,
    	        eventDrop: $scope.alertOnDrop,
    	        eventResize: $scope.alertOnResize
    	      }
    };

	$scope.eventSources = [$scope.eventSource,   $scope.events];


});