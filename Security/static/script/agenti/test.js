(function () {
    angular
        .module('simeCenterApp')
        .factory('AgentResource11', Service);

    function Service($http, $localStorage, $log, $window) {
        var service = {};

        service.getLogBySystem = getLogBySystem;
        service.getMessages = getMessages;
        
        
        
        function getLogBySystem(id, source, time, callBack) {
			
        	$http.get('api/agent/day/log/'+id+'/' + source +'/' + time).success(function (data, status, headers, config) {
				
        		console.log(data);
        		var mess = {
        				messag: data.idAgenta
        		}
        		$localStorage.mess = mess;
        		
        		callBack(data);
			})
		}
        
        
        
        
        
        function getMessages() {
            return $localStorage.mess;
        }
		

        return service;

        
    }
})();