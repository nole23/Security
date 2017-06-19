'use strict';

angular.module('simeCenterApp')
	.controller('AgentsCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AgentResource) {
		
		var agents = [];
		
		AgentResource.getAllAgent().then(function (items) {
			$scope.agents = items;
			
			console.log(items);
		})
	}])
	
	.controller('AgentsAddCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AgentResource) {
		
		$scope.agent = {};
		
		
		$scope.addAgent = function() {
			AgentResource.addNewAgent($scope.agent).tehn(function(item) {
				console.log(item);
			})
		}
		
		
	}])
	.controller('AgentProfileCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AgentResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AgentResource) {
		
		console.log("dosao");
		$scope.agent = [];
		
		var id = $routeParams.id;
		$scope.oglasiAlarm = false;
		
		AgentResource.getAlarmType().then(function(alarm) {
			AgentResource.getAgentHH(id).then(function(item) {
				
				var count = item.length;
				console.log(count);
				if(count > alarm.countLog) {
					$scope.oglasiAlarm = true;
				}
				
			})
		})
		
		
		
	}])