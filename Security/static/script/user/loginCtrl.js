
(function() {
	angular.module('App')
		.controller('LoginCtrl', ['$state', '$location', '$http', '$window', 'LoginService', LoginCtrl]);
	
	function LoginCtrl($scope, $location, $http, $window, LoginService){
		
		var vm = this;
		
		vm.user = {};
		
		//LoginService.logout();
		
		vm.logout = function(username) {
			console.log("Upao u login "+username);
			LoginService.logout(username);
		}
		
		vm.login = function(){
			console.log(vm.user);
			LoginService.login(vm.user, function(successful) {
				if(successful.jwt){
					console.log("ulogovao se");
					$window.location = '#/';
				} else {
					console.log("nisam se ulogovao");
					vm.message = true;
				}
			});
		}
		
	}
})(angular);