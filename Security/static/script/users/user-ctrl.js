
'use strict';

angular.module('simeCenterApp')
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'LoginResources',
	    function($scope, $localStorage, $http, $window, $log, _, $rootScope, LoginResources) {
			
		$scope.user = {};
		$scope.returnedLogs = [];
		
		$scope.login = function() {
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
			  
				 
				  
				LoginResources.register($scope.user);
		}
		
		
		$scope.searchLogs = function(){
			
			LoginResources.searchLogs($scope.log,$scope.returnedLogs);
			
	}
		function callBack(data){
			$scope.returnedLogs = data;}
		
		}]);