angular.module('processoApp')
       .service('processoService', processoService);
	   
	processoService.$inject = ['$http'];
	function processoService($http){
		this.getProcessoByNumeroUnico = function(numeroUnico){
			return $http.get("/distribuicaoprocesso/rest/processo", { params: {NumeroUnico: numeroUnico}});
		};
	};