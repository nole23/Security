'use strict';

angular.module('App')
	.controller('NavbarCtrl', ['$scope',
		function($scope) {
		
		$scope.setActive = function (item) {
			$scope.active = item;
		}
		
	}])