'use strict';

angular.module('simeCenterApp')
	.controller('AgentsCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AgentResource) {
		
		var agents = [];
		
		AgentResource.getAgent().then(function (items) {
			$scope.agents = items;
			
			console.log(items);
		})
	}])
	
	.controller('AgentsAddCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AgentResource) {
		
		$scope.messages = false;
		$scope.agent = {};
		
		$scope.addAgent = function() {
			console.log($scope.agent);
			AgentResource.addNewAgent($scope.agent).then(function(item) {
				if(item.message == "save"){
				    
					console.log("radi");
					$scope.messages = true;
				}
				
				window.location = '#/all/agents';
			})
		}
		
		
	}])
	.controller('AgentProfileCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$rootScope', '$localStorage', '$log', '_', 'AgentResource11', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $rootScope, $localStorage, $log, _, AgentResource11, AgentResource) {

		$scope.agent = [];
		$scope.dayLog = [];
		$scope.error = [];
		
		var id = $routeParams.id;
		$scope.oglasiAlarm = false;
		$scope.oglasiAlarmW = false;
		$scope.errorSys = {};
		$scope.Sys = {};
		var vm = this;
		var date = new Date();

		
		//alarm 1 Odradjeno
		AgentResource11.getLogBySystem(id, "Application", "100", callBack);
		
		AgentResource11.getLogBySystem(id, "System", "100", callBack1);
		
		function callBack(success) {
			
			$scope.Sys = success.type;
			//AgentResource.getSaveAlarm(success);
		}
		
		function callBack1(success) {
			
			$scope.App = success.type;
			//AgentResource.getSaveAlarm(success);
		}
		
		
		//alarm 2 odradjeno
		AgentResource.getERRORSystem(id).then(function(item) {
			$scope.errorSys = item.idAgenta;
			
			//AgentResource.getSaveAlarm(item);
		})
		
		//alarm 3 radi
		AgentResource.getType(id, "Error").then(function(item) {
			$scope.errorType = item.type;
			console.log(item);
		})

		$scope.lista = [];
		
		AgentResource.getAgentAll(id).then(function(item) {
			$scope.lista = item;
		})
		
		
		
		
		
	}])