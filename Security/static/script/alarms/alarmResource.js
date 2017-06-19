(function () {
	angular
		.module('simeCenterApp')
		.factory('AlarmResource', Service);
	
	function Service($http, $localStorage, $log, $window) {
		var service = {};
		
		service.addAlarm = addAlarm;
		
		return service;
		
		function addAlarm(alarm, callback) {
			$http.post('https://localhost:8080/api/alarm/new', alarm)
				.success(function (response) {
					callback(response);
				})
		}
	}
})();