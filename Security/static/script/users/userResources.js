angular.module('simeCenterApp')
	.factory('UserResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var user = [];
		
		
		retVal.getAllUser = function() {
			
			return Restangular.all("user/all").getList().then(function(entries) {
				agenti = entries;
				return agenti;
			})
		}
		
		
		
		
		return retVal;
	}])