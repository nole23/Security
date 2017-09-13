(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('LogService', ['$http', '$window', '$state', '$localStorage', '$rootScope', LogService]);
	
	function LogService($http, $window, $state, $localStorage, $rootScope) {
		return {
			findAll: findAll
		};
		
		function findAll(parameter, callback){
			$http.get('/api/log/all/'+parameter)
				.success(function (response) {
					//console.log(response)
					console.log(response);
					callback(response);
				})
		}
		
		
		
	}
	
})(angular);