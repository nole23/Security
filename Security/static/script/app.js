angular
	.module('App', [
		'ngResource',
            'ngRoute',
            'ngCookies',
            'ngStorage',
            'restangular',
            'ui.router'
	])
	.config(['$routeProvider', '$stateProvider', '$urlRouterProvider', function ($routeProvider, $stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/");
        $stateProvider
            .state('home', {
                url: '/',
                views: {
                    'content': {
                        templateUrl: 'view/home.html',
                    }
                },

            });


	}])
	
	.run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', 
		function(Restangular, $log, $rootScope, $http, $location, $localStorage) {
		Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        
        
        
        
	}])