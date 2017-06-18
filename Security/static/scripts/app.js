'use strict';



angular
    .module('siemCenter', [
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
	            templateUrl: 'index.html'
	        }).when('/login', {
	            templateUrl: 'views/login.html'
	        })
	        .otherwise({
                redirectTo: '/'
            });
        
    }])
    
    .run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', 'LoginResources', function(Restangular, $log, $rootScope, $http, $location, $localStorage, LoginResources) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; 
            }
            return true; 
        });
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
        }
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
