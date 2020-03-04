Exemplo de implementação de cliente rest usando JaxRS.

Nesse hello world, foi implementada uma página usando JSF, no intuito de servir de cliente dos endpoints providos pelo projeto
"restJaxRsExemplo". Os botões na página são mapeados de modo a requisitar chamadas http nos endpoints especificados:

	http://localhost:8080/restJaxRsExemplo/crudFinal/consultarTodos (GET)
	http://localhost:8080/restJaxRsExemplo/crudFinal/consultarId/{Id} (GET)
	http://localhost:8080/restJaxRsExemplo/crudFinal/cadastrar (POST)
	http://localhost:8080/restJaxRsExemplo/crudFinal/editar (PUT)
	http://localhost:8080/restJaxRsExemplo/crudFinal/excluir/{Id} (DELETE)

A página é acessada no endereço: 

	http://localhost:8081/restJaxRsExemploView/hello.xhtml

Este projeto deve funcionar em conjunto com "restJaxRsExemplo" e, para funcionar,
devem existir duas instancias do servidor wildfly funcionando simultaneamente.

Duas pastas 'standalone' foram criadas no diretório de instalação do wildfly. E no
standalone.xml da view (este projeto), foi alterado o offset da porta para não haver conflito
no deploy. Assim, a aplicação que atua como servidor funciona na porta 8080, enquanto a aplicação
cliente atua na 8081. 
