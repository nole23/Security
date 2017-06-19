
'use strict';

angular.module('simeCenterApp')
	.controller('AlarmsCtrl', ['$scope', '$uibModal', '$window', '$routeParams', '$log', '_', 'AlarmResource',
		function($scope, $uibModal, $window, $routeParams, $log, _, AlarmResource) {
		
		
		$scope.saveAlarm = function() {
			AlarmResource.addAlarm($scope.alarm, callBack);
		}
		
		function callBack(success) {
			console.log(success);
		}
	}])
	
	