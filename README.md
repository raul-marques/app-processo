app-processo
==============================================

Tecnologias requeridas: 
- Java 8
- Tomcat7
- PostgreSQL 9.3

==============================================
Configuração:
1 - Importa o projeto como Maven Project no Eclipse .
2 - Add o projeto no Tomcat7 pelo Eclipse.
3 - Restaurar backup no PostgreSQL, o arquivo se encontra na pasta app-processo\src\main\resources\db.

==============================================
Testes do serviços disponibilizados:

GET http://localhost:8080/app-processo/rest/processo/1 (usando Número Unico)
POST localhost:8080/app-processo/rest/processo/
No body vai em JSON:

{"classeProcessual":{"id":1,"competencias":[{"competencia":{"id":1, "descricao": "Familia"}},{"competencia":{"id":2, "descricao":"Fazenda"}}]},
							"comarca":{"id":1,"descricao":"Cuiaba"} }

```