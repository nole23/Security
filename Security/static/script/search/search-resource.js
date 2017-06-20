//(function () {
//    angular
//        .module('simeCenterApp')
//        .factory('SearchR', Service);
//
//    function Service($http, $localStorage, $log, $window) {
//        var service = {};
//
//        service.search = search;
//        
//        
//        function search(slova, callBack) {
//        	$http.post('/api/log/my/search/'+slova).success(function(data, status, headers, config) {
//				//console.log(data);
//        		callBack(data);
//			})
//		}
//
//        return service;
//
//        
//    }
//})();

angular.module('simeCenterApp')
	.factory('SearchR', ['Restangular', '_', function(Restangular, _) {

		
		var retVal = {};
		
		var lista = [];
		
		retVal.search = function(slova) {
			var link = '/log/my/search/'+slova;
			return Restangular.all(link).post().then(function(success) {
				lista = success;
				return lista;
			})
		}
		
		return retVal;
	}])