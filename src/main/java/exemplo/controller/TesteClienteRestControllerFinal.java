package exemplo.controller;

import exemplo.core.ClienteRestCrudBase;

public class TesteClienteRestControllerFinal<T> extends ClienteRestCrudBase<T>{
	private static final String URI_CAMINHOBASE = "http://localhost:8080/restJaxRsExemplo/crudFinal";
	
	public String requisicaoHttpGenerica(TipoRequisicao tipoRequisicao) {
		return super.requisicaoHttpGenerica(tipoRequisicao, URI_CAMINHOBASE, null, null);
	}
	
	public String requisicaoHttpGenerica(TipoRequisicao tipoRequisicao, T t) {
		return super.requisicaoHttpGenerica(tipoRequisicao, URI_CAMINHOBASE, t, null);
	}
	
	public String requisicaoHttpGenerica(TipoRequisicao tipoRequisicao, Integer id) {
		return super.requisicaoHttpGenerica(tipoRequisicao, URI_CAMINHOBASE, null, id);
	}
}
