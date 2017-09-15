
(function() {
	angular.module('App')
		.controller('ViewAlarmCtrl', ['$state', '$location', '$http', '$window', 'AlarmService', ViewAlarmCtrl]);
	
	function ViewAlarmCtrl($scope, $location, $http, $window, AlarmService){
		
		var vm = this;
		
		vm.alamr = [];
		
		AlarmService.allAlarm(function(response) {
			vm.alamr = response;
			console.log(vm.alamr)
		})


		
		vm.vide = function(id) {
			AlarmService.updateAlarm(id, function(response) {
				if(response){
					vm.message = true;
					$window.location.reload();
				}
			})
		}
		
	}
})(angular);