
(function() {
	angular.module('App')
		.controller('LogCtrl', ['$state', '$location', '$http', '$window', 'LogService', LogCtrl]);
	
	function LogCtrl($scope, $location, $http, $window, LogService){
		
		var vm = this;
		
		vm.log = [];
		vm.admin = [];
		vm.startData = {};
		vm.endData = {};
		vm.agent = {};
		vm.tip = {};
		vm.searchDTO = {};
		
		
		
		LogService.findAllAgent(function(successful) {
			vm.admin = successful;
		})

		vm.search = function() {
			
			if(vm.agent != null ) {
				console.log(vm.agent);
				vm.searchDTO = {
						"vrst":"agent",
						"agentId":vm.agent
				}
				
			} else if(vm.startData != "") {
				vm.searchDTO = {
						"vrst":"vreme_agent",
						"agentId":vm.agent,
						"startData":vm.startData,
						"endData":vm.endData
				}
			}
			
			LogService.findAll(vm.searchDTO, function(successful) {
				vm.log = successful;
				vm.message1 = true;
			})
			
		}
		
	}
})(angular);