
(function () {
	angular
		.module('simeCenterApp')
		.factory('LoginResources', Service);
	
	function Service($http, $localStorage, $log, $window) {
		
		var service = {};
		
		service.login = login;
		service.getCurrentUser = getCurrentUser;
		service.logout = logout;
		service.register = register;
		service.searchLogs = searchLogs;
		
		return service;
		
		function login(user, callBack) {
			$http.post('https://localhost:8080/api/user/login', user)
			.success(function (response) {
				 
					
					var currentUser = {
							username: user.username,
							token: response.jwt,
							rola: response.rola
					};
					
					$localStorage.currentUser = currentUser;
					$http.defaults.headers.common.Authorization = response.jwt;
					
					callBack("login")
				
			})
			.error(function (response){
		        
		        window.alert(response.error);

		    });
		}
		
		function getCurrentUser() {
            return $localStorage.currentUser;
        }
		
		function logout() {
            // uklonimo korisnika iz lokalnog skladišta
			console.log("Logout");
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
            $window.location = '#/login';
        }
		
		
		
		/*registration */
		function register(user){
			$http.post('https://localhost:8080/api/user/registration/operator', user)
			.success(function (response) {
				 
					
					window.alert("User added");
					
					$localStorage.currentUser = currentUser;
					$http.defaults.headers.common.Authorization = response.jwt;
					
					$window.location = "#/"
				
			})
			.error(function (response){
		        
		        window.alert("Error occured");

		    });
		}
		
		function searchLogs(log) {
            
			$http.post('https://localhost:8080/api/log/search', log)
			.success(function (response) {
				 
					
			//take response
			window.alert(response);
					
					
				
			});
        }
		
	}
})();