'use strict';

/* Services */

angular.module('tourServices', ['ngResource'])

.factory('Tours', ['$resource',
  function($resource){
    return $resource('rest/:tourId.json', {}, {
      query: {method:'GET', params:{tourId:'tours'}, isArray:true}
    });
  }]);
