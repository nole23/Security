(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('LogService', ['$http', '$window', '$state', '$localStorage', '$rootScope', LogService]);
	
	function LogService($http, $window, $state, $localStorage, $rootScope) {
		return {
			findAll: findAll,
			findAllAgent:findAllAgent,
			findAllEnum:findAllEnum
		};
		
		function findAll(searchDTO, callback){
			$http.post('/api/log/all', searchDTO)
				.success(function (response) {
					callback(response);
				})
		}
		
		function findAllAgent(callback) {
			$http.get('/api/agent/all/agent')
				.success(function(response) {
					callback(response);
				})
		}
		
		function findAllEnum(callback) {
			$http.get('/api/log/all/enum')
				.success(function(response) {
					callback(response);
				})
		}
		
	}
	
})(angular);