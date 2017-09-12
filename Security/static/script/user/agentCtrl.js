
(function() {
	angular.module('App')
		.controller('AgentCtrl', ['$state', '$location', '$http', '$window', 'LoginService', AgentCtrl]);
	
	function AgentCtrl($scope, $location, $http, $window, LoginService){
		
		var vm = this;
		
		vm.user = {};
		
		vm.save = function(){
			console.log(vm.user);
			LoginService.saveAgent(vm.user, function(successful) {
				if(!successful.save){
					console.log(successful);
					vm.error = true;
				} else {
					vm.saveAs = true;
					console.log(successful);
				}
			});
		}
		
	}
})(angular);