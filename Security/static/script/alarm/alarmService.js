(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('AlarmService', ['$http', '$window', '$state', '$localStorage', '$rootScope', AlarmService]);
	
	function AlarmService($http, $window, $state, $localStorage, $rootScope) {
		return {
			saveAlarm:saveAlarm,
			allAlarm:allAlarm,
			updateAlarm:updateAlarm
		};
		
		function saveAlarm(alarmDef, callback) {
			$http.post('/api/alarm/define', alarmDef)
				.success(function(response) {
					callback(response);
				})
		}
		
		function allAlarm(callback) {
			$http.get('/api/alarm/all')
				.success(function (response) {
					callback(response);
				})
		}
		
		function updateAlarm(id, callback) {
			$http.get('/api/alarm/update/'+id)
			.success(function (response) {
				callback(response);
			})
		}
		
		
	}
	
})(angular);