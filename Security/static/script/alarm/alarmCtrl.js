
(function() {
	angular.module('App')
		.controller('AlarmCtrl', ['$state', '$location', '$http', '$window', 'AlarmService', AlarmCtrl]);
	
	function AlarmCtrl($scope, $location, $http, $window, AlarmService){
		
		var vm = this;
		
		
		vm.saveAlarm = function () {
			AlarmService.saveAlarm(vm.alarmDef, function(successful) {
				vm.message = true;
			})
		}
		
	}
})(angular);