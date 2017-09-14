
(function() {
	angular.module('App')
		.controller('LogCtrl', ['$state', '$location', '$http', '$window', 'LogService', LogCtrl]);
	
	function LogCtrl($scope, $location, $http, $window, LogService){
		
		var vm = this;
		
		vm.log = [];
		vm.admin = [];
		vm.startData = "";
		vm.endData = "";
		vm.agent = "";
		vm.tip = "";
		vm.searchDTO = {};
		vm.platforma = "";
		vm.enume = [];
		vm.show = false;
		vm.regular = "";
		
		vm.view = function() {
			vm.show = true;
		}
		
		vm.hiden = function() {
			vm.show = false;
		}
		
		LogService.findAllAgent(function(successful) {
			vm.admin = successful;
		})
		
		LogService.findAllEnum(function(successful) {
			vm.enume = successful;
		})

		vm.search = function() {
			
			vm.searchDTO = {
					"agentId":vm.agent,
					"type":vm.tip,
					"startData":vm.startData,
					"endData":vm.endData,
					"platforma":vm.platforma,
					"regular":vm.regular
			}
			LogService.findAll(vm.searchDTO, function(successful) {
				vm.log = successful;
				console.log(vm.log)
				vm.message1 = true;
			})
			
		}
		
		vm.azuriranje = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modal/viewLogs.html',
				controller: 'ModalLogCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
	}
})(angular);