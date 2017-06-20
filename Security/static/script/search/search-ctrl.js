
'use strict';

angular.module('simeCenterApp')
	.controller('SearchCtrl', ['$scope', '$uibModal', 'SearchR',
		function($scope, $uibModal, SearchR) {
		
		$scope.liste = [];
		var data;
		
		$scope.search = function() {
			SearchR.search($scope.keywords).then(function(item) {
				
				$scope.liste = item;
			})
		};
		
		console.log($scope.liste);
//		var odgovor = [];
//	function callBack(succe) {
//  	  //console.log(succe)
//  	  odgovor = "novica";
//  	  
//  	  console.log($scope.response);
//	}
//	
//	$scope.liste = odgovor;
		
	}])
	
	