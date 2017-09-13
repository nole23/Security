
(function() {
	angular.module('App')
		.controller('LogCtrl', ['$state', '$location', '$http', '$window', 'LogService', LogCtrl]);
	
	function LogCtrl($scope, $location, $http, $window, LogService){
		
		var vm = this;
		
		vm.log = [];
		
		

		vm.search = function() {
			
			if(vm.agent=="null"){
				console.log("radi")
				LogService.findAll(vm.agent, function(resource) {
					vm.message1 = true;
					vm.log = resource;
					console.log(vm.log);
					
				})
			} else {
				console.log("radi")
				LogService.findAll(vm.agent, function(resource) {
					vm.message2 = true;
					vm.log = resource;
					console.log(vm.log);
					
				})
			}
			
		}
		
	}
})(angular);