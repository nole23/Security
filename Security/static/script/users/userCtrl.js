
'use strict';

angular.module('simeCenterApp')
	.controller('UserCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'UserResource',
	    function($scope, $localStorage, $http, $window, $log, _, $rootScope, UserResource) {
		
		var users = [];
		console.log("Dosao ovde");
		UserResource.getAllUser().then(function(items) {
			$scope.users = items;
			//console.log(items);
		});
		
	}]);