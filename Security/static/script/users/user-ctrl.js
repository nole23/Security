
'use strict';

angular.module('simeCenterApp')
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'LoginResources',
	    function($scope, $localStorage, $http, $window, $log, _, $rootScope, LoginResources) {
			
		$scope.user = {};
		
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
			  if($scope.search.type == "agent"){
				  console.log("radi");
				  console.log($scope.search.value);
			  }
				  $scope.log.fileDto.agentId=$scope.search.value;
				  
				LoginResources.register($scope.user);
		}
		
		
		$scope.searchLogs = function(){
			
			console.log($scope.log);
			//LoginResources.searchLogs($scope.log);
	}
		
		}]);