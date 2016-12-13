angular.module('processoApp')
       .controller('ProcessoController', ProcessoController);
	   
	ProcessoController.$inject = ['$scope', 'processoService', '$state'];
	function ProcessoController($scope, processoService, $state){
		var vm = this;
		
		vm.mensagem = "Controller inject!!";
		
		vm.findProcessoByNumUnico = _findProcessoByNumUnico;
		
		function _findProcessoByNumUnico(){
			var processoBusca;
			processoService.getProcessoByNumeroUnico(vm.numeroUnicoBusca)
				.success(function(data){
					processoBusca = data;
					$state.go('processo_get',{processo:processoBusca});
				})
				.error(function(){
					console.log("Ocorreu um erro ao se comunicar com app.")
				});
			
		}
	}