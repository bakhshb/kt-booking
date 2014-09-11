'use strict';

angular.module('ktApp.tourz').controller('ToursCtrl', ['$scope', 'tourzService', function($scope, tourzService) {

	$scope.tours = tourzService.query();

}]);