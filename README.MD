Exemplo de implementa��o de cliente rest usando JaxRS.

Nesse hello world, foi implementada uma p�gina usando JSF, no intuito de servir de cliente dos endpoints providos pelo projeto
"restJaxRsExemplo". Os bot�es na p�gina s�o mapeados de modo a requisitar chamadas http nos endpoints especificados:

	http://localhost:8080/restJaxRsExemplo/crudFinal/consultarTodos (GET)
	http://localhost:8080/restJaxRsExemplo/crudFinal/consultarId/{Id} (GET)
	http://localhost:8080/restJaxRsExemplo/crudFinal/cadastrar (POST)
	http://localhost:8080/restJaxRsExemplo/crudFinal/editar (PUT)
	http://localhost:8080/restJaxRsExemplo/crudFinal/excluir/{Id} (DELETE)

A p�gina � acessada no endere�o: 

	http://localhost:8081/restJaxRsExemploView/hello.xhtml

Este projeto deve funcionar em conjunto com "restJaxRsExemplo" e, para funcionar,
devem existir duas instancias do servidor wildfly funcionando simultaneamente.

Duas pastas 'standalone' foram criadas no diret�rio de instala��o do wildfly. E no
standalone.xml da view (este projeto), foi alterado o offset da porta para n�o haver conflito
no deploy. Assim, a aplica��o que atua como servidor funciona na porta 8080, enquanto a aplica��o
cliente atua na 8081. 
