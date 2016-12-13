// app.js
var processoApp = angular.module('processoApp', ['ui.router']);

processoApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        .state('home', {
            url: '/home',
            controller: 'ProcessoController',
            controllerAs: 'vm',
            templateUrl: 'pages/processo-home.html'
        })
        .state('processo_get', {
			url: "/processo",
			views: {
				'@': {
					templateUrl: "pages/processo-detail.html",
					controller: 'ProcessoDetailController',
					controllerAs: 'vm'
				}
			}
        })
//        .state('processo_new', {
//			url: "/processo",
//			views: {
//				'@': {
//					templateUrl: "pages/processo-form.html",
//					controller: 'ProcessoFormController',
//					controllerAs: 'vm',
//				}
//			}
//        })
        
    
}); 
