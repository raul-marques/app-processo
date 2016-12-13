angular.module('processoApp')
       .controller('ProcessoController', ProcessoController);
	   
	ProcessoController.$inject = ['$scope', 'processoService'];
	function ProcessoController($scope, processoService){
		var vm = this;
		
		vm.mensagem = "Controller inject!!";
		
		vm.buscarInfo = _buscarInfo();
		
		function _buscarInfo(){
			processoService.getProcessoByNumeroUnico(vm.numeroUnicoBusca);
			
		}
	}