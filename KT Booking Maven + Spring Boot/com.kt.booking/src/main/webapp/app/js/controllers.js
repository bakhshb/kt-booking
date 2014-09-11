'use strict';

angular.module('tourControllers', [])

.controller('ToursCtrl', ['$scope', 'Tours',
  function($scope, Tours) {
    $scope.tours = Tours.query();
    $scope.orderProp = 'age';
  }])

.controller('TourDetailsCtrl', ['$scope', '$routeParams', 'Tours',
  function($scope, $routeParams, Tours) {
    $scope.tour = Tours.get({tourId: 'tour'+$routeParams.tourId}, function(Tours) {
      $scope.mainImageUrl = Tour.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    }
  }]);
