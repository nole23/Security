(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('LoginService', ['$http', '$window', '$state', '$localStorage', '$rootScope', LoginService]);
	
	function LoginService($http, $window, $state, $localStorage, $rootScope) {
		return {
			login: login,
			getCurrentUser: getCurrentUser,
			logout:logout
		};
		
		function login(loginDTO, callback){
			$http.post('/api/user/login', loginDTO)
				.success(function (response) {
					//console.log(response)
					console.log(response);
					if(response.jwt){
						console.log('radi savrseno');
						var currentUser = {
							username: loginDTO.username,
							token: response.jwt,
							csrf: response.csrf.token,
							date: response.date,
							fName: response.fName,
							lName: response.lName,
							role: response.role
						};
						
						$localStorage.currentUser = currentUser;
						
						callback(response);
					} else {
						callback(response);
					}
					
				})
		}
		
		function getCurrentUser() {
            return $localStorage.currentUser;
        }
		
		function logout(username) {
			console.log(username)
			
			
			$http.get('/api/user/logout/'+username)
		    .then(function(response) {
		        if(response){
		        	console.log("Upao");
		        	delete $localStorage.currentUser;
		            $http.defaults.headers.common.Authorization = '';
		            $window.location = '#/login';
		        }
		    });
		    
		}
		
	}
	
})(angular);