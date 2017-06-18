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
			}).when('/login', {
				templateUrl: 'view/login.html',
				controller: 'LoginCtrl',
		        controllerAs: 'LoginCtrl'
			})
			.otherwise({
                redirectTo: '/'
            });
	}])
	
	.run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage','LoginResources', function(Restangular, $log, $rootScope, $http, $location, $localStorage, LoginResources) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        
        $rootScope.logout = function () {
        	LoginResources.logout();
        }
        
        $rootScope.getCurrentUserRole = function () {
            if (!LoginResources.getCurrentUser()){
              return undefined;
            }
            else{
            
              return LoginResources.getCurrentUser().rola;
            }
        }
        $rootScope.getCurrentUserUser = function () {
            if (!LoginResources.getCurrentUser()){
              return undefined;
            }
            else{
              return LoginResources.getCurrentUser().username;
            }
        }
        $rootScope.isLoggedIn = function () {
            if (LoginResources.getCurrentUser()){
              return true;
            }
            else{
              return false;
            }
        }
        
    }]);