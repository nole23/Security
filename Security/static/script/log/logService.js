(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('LogService', ['$http', '$window', '$state', '$localStorage', '$rootScope', LogService]);
	
	function LogService($http, $window, $state, $localStorage, $rootScope) {
		return {
			findAll: findAll,
			findAllAgent:findAllAgent
		};
		
		function findAll(searchDTO, callback){
			$http.post('/api/log/all', searchDTO)
				.success(function (response) {
					//console.log(response)
					console.log(response);
					callback(response);
				})
		}
		
		function findAllAgent(callback) {
			$http.get('/api/agent/all/agent')
				.success(function(response) {
					callback(response);
				})
		}
		
		
	}
	
})(angular);