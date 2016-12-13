angular.module('processoApp')
       .controller('ProcessoDetailController', ProcessoDetailController);
	   
ProcessoDetailController.$inject = ['$scope', '$stateParams'];
	function ProcessoDetailController($scope, $stateParams){
		var vm = this;
		
		console.log($stateParams.processo);
	}