angular.module('simeCenterApp')
	.factory('AgentResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var agenti = [];
		
		
		retVal.getAllAgent = function() {
			
			return Restangular.all("agent/all").getList().then(function(entries) {
				agenti = entries;
				return agenti;
			})
		}
		
		
		return retVal;
	}])