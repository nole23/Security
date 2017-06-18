'use strict';

angular
	.module('simeCenterApp', [
		'ngResource',
        'ngRoute',
        'ngCookies',
        'ngStorage',
        'restangular',
        'ui.bootstrap',
        'lodash'
	])
	.config(['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl: 'view/main.html'
			})
			.otherwise({
                redirectTo: '/'
            });
	}])
	
	.run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', function(Restangular, $log, $rootScope, $http, $location, $localStorage) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        
        
    }]);