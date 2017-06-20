angular.module('simeCenterApp')
	.factory('AgentResource', ['Restangular', '_', function(Restangular, _) {

		
		var retVal = {};
		
		var agenti = [];
		var poruke = [];
		var logovi = [];
		var alarm = [];
		
		retVal.getAgent = function() {
			return Restangular.all("agent/all").getList().then(function(entries) {
				agenti = entries;
				return agenti;
			})
		}
		
		retVal.getLogBySystem = function(id, source, time) {
			return Restangular.one("agent/day/log/"+id+"/" + source +"/" + time).get().then(function(entries) {
				logovi = entries;
				
				return logovi;
				
			})
		}
		
		
		retVal.getAllAgent = function(type) {
			
			return Restangular.all("agent/all/type/"+type).getList().then(function(entries) {
				logovi = entries;
				return logovi;
			})
		}
		
		retVal.addNewAgent = function(agent) {
			
			var link = 'user/registration/agent';
			return Restangular.all(link).post(agent).then(function(success) {
				poruke = success;
				return poruke;
			})
		}
		
		retVal.getAlarmType = function(id) {
			var link = 'alarm/all/'+id;
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
		
		retVal.getAgentMIN = function(id) {
			var link = 'agent/all/sec/'+id;
			return Restangular.all(link).getList().then(function(entries) {
				logovi = entries;
				return logovi;
			})
		}
		
		return retVal;
	}])