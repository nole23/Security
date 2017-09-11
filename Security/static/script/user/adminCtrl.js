
(function() {
	angular.module('App')
		.controller('AdminCtrl', ['$state', '$location', '$http', '$window', 'LoginService', AdminCtrl]);
	
	function AdminCtrl($scope, $location, $http, $window, LoginService){
		
		var vm = this;
		
		vm.user = {};
		
		vm.save = function(){
			console.log(vm.user);
			LoginService.saveAdmin(vm.user, function(successful) {
				if(!successful.error){
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