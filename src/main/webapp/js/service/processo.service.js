angular.module('processoApp')
       .service('processoService', processoService);
	   
	processoService.$inject = ['$http'];
	function processoService($http){
		this.getProcessoByNumeroUnico = function(numeroUnico){
			return $http.get("/app-processo/rest/processo/"+numeroUnico);
//			return $http.get("/app-processo/rest/processo", { params: {numprocesso: numeroUnico}});
		};
	};