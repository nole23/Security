
(function() {
	angular.module('App')
		.controller('ModalLogCtrl', ['$state', '$location', '$http', '$window', 'resource', 'LogService', ModalLogCtrl]);
	
	function ModalLogCtrl($scope, $location, $http, $window, resource, LogService){
		
		var vm = this;
		
		vm.oneLog = resource;
		
	}
})(angular);