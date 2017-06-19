angular.module('simeCenterApp')
	.factory('AgentResource', ['Restangular', '_', function(Restangular, _) {

		
		var retVal = {};
		
		var agenti = [];
		var poruke = [];
		var logovi = [];
		var alarm = [];
		
		retVal.getAllAgent = function() {
			
			return Restangular.all("agent/all").getList().then(function(entries) {
				agenti = entries;
				return agenti;
			})
		}
		
		retVal.addNewAgent = function(agent) {
			console.log(agent);
			var link = 'user/registration/agent';
			return Restangular.all(link).post(agent).then(function(success) {
				poruke = success;
				return poruke;
			})
		}
		
		retVal.getAlarmType = function() {
			var link = 'alarm/all/ALL';
			return Restangular.one(link).get().then(function(entries) {
				alarm = entries;
				return alarm;
			})
		}
		
		retVal.getAgentHH = function(id) {
			var link = 'agent/all/sat/'+id;
			return Restangular.all(link).getList().then(function(entries) {
				logovi = entries;
				return logovi;
			})
		}
		
		
		return retVal;
	}])