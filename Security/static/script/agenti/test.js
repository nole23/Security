(function () {
    angular
        .module('simeCenterApp')
        .factory('AgentResource11', Service);

    function Service($http, $localStorage, $log, $window) {
        var service = {};

        service.getAllAgent = getAllAgent;
        
        function getAllAgent() {
			
        	http.get('agent/all', user).then(function(response){
        		console.log(response);
        	})
		}

        return service;

        
    }
})();