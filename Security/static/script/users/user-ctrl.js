
'use strict';

angular.module('simeCenterApp')
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'LoginResources',
	    function($scope, $localStorage, $http, $window, $log, _, $rootScope, LoginResources) {
			
		$scope.user = {};
		//$scope.log = {};
		//$scope.log.fileDto = {};
		/*$scope.log.fileDto.platform={};
		$scope.log.fileDto.agentId={};
		$scope.log.fileDto.logLevel={};
		$scope.log.fileDto.date={};*/
		
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