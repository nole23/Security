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
				templateUrl: 'view/main.html',
				resolve: {
					app: function($q, $localStorage, $location) {
						
						if ($localStorage.currentUser == null) {
							$location.path('/login');
						};
					}
				}
			}).when('/login', {
				templateUrl: 'view/login.html',
				controller: 'LoginCtrl',
		        controllerAs: 'LoginCtrl',
		        resolve: {
					app: function($q, $localStorage, $location) {
						
						if ($localStorage.currentUser != null) {
							$location.path('#/');
						};
					}
				}
			})
			.when('/all/agents', {
				templateUrl: 'view/listAgents.html',
				controller: 'AgentsCtrl',
				controllerAS: 'agentsControler'
			})
			.when('/all/users', {
				templateUrl: 'view/listUsers.html',
				//controller: 'UserCtrl'
			})
			.when('/add/agents', {
				templateUrl: 'view/addAgents.html',
				controller: 'AgentsAddCtrl'
			})
			.when('/agent/:id', {
				templateUrl: 'view/agentProfile.html',
				controller: 'UserCtrl',
				
			})
			.when('/register/user', {
				templateUrl: 'view/registerUser.html',
				controller: 'LoginCtrl',
				resolve: {
					app: function($q, $localStorage, $location) {
						
						if ($localStorage.currentUser == null && (!$localStorage.isAdmin)) {
							$location.path('#/login');
						};
					}
				}
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
        
        
        /*IS ADMIN*/
        $rootScope.isAdmin = function () {
            if (LoginResources.getCurrentUser()){
            	if (LoginResources.getCurrentUser().rola == "ADMIN")
            		return true;
            	else
            		return false;
            }
            else{
              return false;
            }
        }
        
    }]);