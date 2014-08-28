angular.module('cookieStoreExample', ['ngCookies']);
function Hello($scope, $http, $cookieStore) {
    $http.get('http://localhost:8080/rest/account/login').
        success(function(data) {
        	$cookieStore.put('myFavorite',data);
            $scope.greeting = $cookieStore.get('myFavorite');
        });
}