
(function() {
	angular.module('App')
		.controller('AgentActiveCtrl', ['$state', '$location', '$http', '$window', 'LoginService', AgentActiveCtrl]);
	
	function AgentActiveCtrl($scope, $location, $http, $window, LoginService){
		
		var vm = this;
		
		vm.agent = [];
		
		LoginService.activAgent(function(successful) {
			vm.agnet = successful.data.list;
			console.log(vm.agnet);
		});

		
	}
})(angular);