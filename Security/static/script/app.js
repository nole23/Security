angular
	.module('App', [
		'ngResource',
            'ngRoute',
            'ngCookies',
            'ngStorage',
            'restangular',
            'ui.router',
            
	])
	.config(['$routeProvider', '$stateProvider', '$urlRouterProvider', function ($routeProvider, $stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/login");
        $stateProvider
        .state('login', {
            url: '/login',
            views: {
                'login': {
                    templateUrl: 'view/login.html',
                    controller: 'LoginCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('profile', {
            url: '/profile',
            views: {
                'content': {
                    templateUrl: 'view/profile.html',
                }
            },

        })
        .state('home', {
            url: '/',
            views: {
                'content': {
                    templateUrl: 'view/home.html',
                }
            },

        })
        .state('activePC', {
            url: '/active',
            views: {
                'content': {
                    templateUrl: 'view/active.html',
                    controller: 'AgentActiveCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('addAgent', {
            url: '/add/agent',
            views: {
                'content': {
                    templateUrl: 'view/agentAdd.html',
                    controller: 'AgentCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('addAdmin', {
            url: '/add/admin',
            views: {
                'content': {
                    templateUrl: 'view/adminAdd.html',
                    controller: 'AdminCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('addAlarm', {
            url: '/add/alarm',
            views: {
                'content': {
                    templateUrl: 'view/addAlarm.html',
                    controller: 'AlarmCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('statistix', {
            url: '/statistic',
            views: {
                'content': {
                    templateUrl: 'view/chart.html',
                    controller: 'LogCtrl',
                    controllerAs: 'vm'
                }
            },

        })
        .state('alarms', {
            url: '/alarms',
            views: {
                'content': {
                    templateUrl: 'view/viewAlarm.html',
                    controller: 'ViewAlarmCtrl',
                    controllerAs: 'vm'
                    
                }
            },

        })
        .state('messages', {
            url: '/messages',
            views: {
                'content': {
                    templateUrl: 'view/messages.html',
                }
            },

        })
        .state('viewAlarm', {
            url: '/view/alarm',
            views: {
                'content': {
                    templateUrl: 'view/viewAlarm.html',
                }
            },

        })
        .state('allAlarm', {
            url: '/all/alarm',
            views: {
                'content': {
                    templateUrl: 'view/allAlarm.html',
                }
            },

        })
        .state('logout', {
            url: '/logout',
            views: {
                'login': {
                    templateUrl: 'view/newLogin.html',
                    controller: 'LogoutCtrl',
                    controllerAs: 'vm'
                }
            },

        });


	}])
	
	.run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', 'LoginService',
		function(Restangular, $log, $rootScope, $http, $location, $localStorage, LoginService) {
		Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        
        if ($localStorage.currentUser) {
        	console.log($localStorage.currentUser);
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
            $http.defaults.headers.post['X-XSRF-TOKEN']=$localStorage.currentUser.csrf;
        }
        
        $rootScope.getUser = function() {
			return $localStorage.currentUser;
		}
        
        $rootScope.isLoggedIn = function () {
            if (LoginService.getCurrentUser()){
              return true;
            }
            else{
              return false;
            }
        }
        
        
	}])