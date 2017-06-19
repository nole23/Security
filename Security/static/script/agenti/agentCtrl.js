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
	.controller('AgentProfileCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$rootScope', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $rootScope, $log, _, AgentResource) {
		
		
		
		console.log("dosao");
		$scope.agent = [];
		
		var id = $routeParams.id;
		$scope.oglasiAlarm = false;
		$scope.oglasiAlarmW = false;
		
		var vm = this;
		var date = new Date();
		//Eror na dnevnom nivou
		//
			//var counter = 0;
			//if(alarma != null){
		
		AgentResource.getAllAgent("ERROR").then(function(item) {
			AgentResource.getAlarmType("ERROR").then(function(alarma) {
				
				for(var i=0; i<alarma.length; i++) {
					if(item.length > alarma[i].countLog){
						vm.log = alarma.length;
						vm.alarm = alarma[i].countTime;
						vm.type = "ERROR";
					}
				}
			})
			
		})
			//}
		//})
		
		AgentResource.getAlarmType("ALL").then(function(alarm) {
			if(alarm != null){
				AgentResource.getAgentHH(id).then(function(item) {
					if(item != null){
						var count = item.length;

						if(count < alarm.countLog) {
							//$scope.oglasiAlarm = true;
							
							vm.oglasiAlarm = true;
						}
					}
				})
			}
			
		})
		
	}])