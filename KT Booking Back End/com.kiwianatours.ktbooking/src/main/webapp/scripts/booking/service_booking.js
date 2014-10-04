'use strict';

ktbookingApp.factory('Booking', function ($resource) {
    return $resource('app/rest/bookings/:id', {}, {
        'query': { method: 'GET', isArray: true},
        'get': { method: 'GET', isArray: true},
        'update':{method:'PUT'}
    });
});
