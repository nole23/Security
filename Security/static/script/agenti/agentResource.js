angular.module('simeCenterApp')
	.factory('AgentResource', ['Restangular', '_', function(Restangular, _) {

		
		var retVal = {};
		
		var agenti = [];
		var poruke = [];
		var logovi = [];
		var alarm = [];
		
		//Ostaje
		retVal.getType = function(id, type) {
			return Restangular.one("agent/all/type/"+type+"/"+id).get().then(function(entries) {
				linkovi = entries;
				return linkovi;
			})
		}
		
		//Ostaje
		retVal.getAgent = function() {
			return Restangular.all("agent/all").getList().then(function(entries) {
				agenti = entries;
				return agenti;
			})
		}
		
		//Ostaje
		retVal.getLogBySystem = function(id, source, time) {
			return Restangular.one("agent/day/log/"+id+"/" + source +"/" + time).get().then(function(entries) {
				logovi = entries;
				
				return logovi;
				
			})
		}
		
		//Ostaje
		retVal.getERRORSystem = function(id, type) {
			
			return Restangular.one("agent/sec/log/"+id+"/System/100/Error").get().then(function(entries) {
				logovi = entries;
				return logovi;
			})
		}
		
		//Ostaje
		retVal.getERRORApplication = function(id, type, source, time) {
			
			return Restangular.one("agent/sec/log/"+id+"/Application/"+time+"/"+type).get().then(function(entries) {
	
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
		
		//Ostaje
		retVal.getAgentAll = function(id) {
			var link = 'agent/all/day/'+id;
			return Restangular.all(link).getList().then(function(data) {
				logovi = data;
				return logovi;
			})
		}
		

		
		
		return retVal;
	}])