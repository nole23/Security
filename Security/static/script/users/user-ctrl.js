
'use strict';

angular.module('simeCenterApp')
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'LoginResources',
	    function($scope, $localStorage, $http, $window, $log, _, $rootScope, LoginResources) {
			
		$scope.user = {};
		
		$scope.login = function() {
			console.log("Loging");
			LoginResources.login($scope.user, callback);
		};
		
		function callback(success) {
			if(success == 'invalid'){
				$scope.message = "invalid";
			} else {
				window.location = '#/';
			}
			
		}
		
		$scope.logout = function () {
			LoginResources.logout();
		}
		
		/*registration*/
		$scope.registerUser = function(){
			console.log($scope.user);
			LoginResources.register($scope.user);
		}
		
		}]);