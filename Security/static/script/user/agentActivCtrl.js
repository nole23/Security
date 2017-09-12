
(function() {
	angular.module('App')
		.controller('AgentActiveCtrl', ['$state', '$location', '$http', '$window', 'LoginService', AgentActiveCtrl]);
	
	function AgentActiveCtrl($scope, $location, $http, $window, LoginService){
		
		var vm = this;
		
		vm.agent = [];
		
		LoginService.activAgent(vm.user, function(successful) {
			if(!successful.save){
				console.log(successful);
				vm.error = true;
			} else {
				vm.saveAs = true;
				console.log(successful);
			}
		});

		
	}
})(angular);