(function (angular) {
	'use strict';
	
	angular.module('App')
		.service('LoginService', ['$http', '$window', '$state', '$localStorage', '$rootScope', LoginService]);
	
	function LoginService($http, $window, $state, $localStorage, $rootScope) {
		return {
			login: login,
			getCurrentUser: getCurrentUser,
			logout:logout,
			saveAdmin:saveAdmin,
			saveAgent:saveAgent
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
		
		function saveAdmin(userDTO, callback) {
			
			$http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
			$http.post('/api/user/registration/OPERATOR', userDTO)
				.success(function (response) {
					console.log(response);
					callback(response);
				})
		}
		
		function saveAgent(userDTO, callback) {
			$http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
			$http.post('/api/user/registration/AGENT', userDTO)
				.success(function (response) {
					console.log(response);
					callback(response);
				})
		}
		
	}
	
})(angular);