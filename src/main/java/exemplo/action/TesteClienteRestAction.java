package exemplo.action;

import static exemplo.core.ClienteRestCrudBase.TipoRequisicao.*;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;

import exemplo.controller.TesteClienteRestController;
import exemplo.controller.TesteClienteRestControllerFinal;
import exemplo.model.Usuario;

@Named
@ConversationScoped
@URLBeanName(value = "testeClienteRestAction")
public class TesteClienteRestAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String resposta;
	TesteClienteRestController testeClienteRestController;
	TesteClienteRestControllerFinal<Usuario> testeClienteRestControllerFinal;
	
	@PostConstruct
	public void limpar() {
		resposta = new String();
		testeClienteRestController = new TesteClienteRestController();
		testeClienteRestControllerFinal = new TesteClienteRestControllerFinal<>();
	}
	
	/* Buscar Todos */
	public void buscarTodos() {
		//this.setResposta(testeClienteRestController.getBuscarTodos());
		this.setResposta(testeClienteRestControllerFinal.requisicaoHttpGenerica(GETALL));
	}
	
	/* Buscar Por Id*/
	public void buscarPorId() {
		//this.setResposta(testeClienteRestController.getBuscarPorId(5));
		this.setResposta(testeClienteRestControllerFinal.requisicaoHttpGenerica(GETID, 5));
	}
	
	/* Cadastrar */
	public void cadastrar() {
		Usuario usuario = new Usuario(5,"digidoc-api/usuario/5","nome","sobrenome");
		//this.setResposta(testeClienteRestController.postCadastrar(usuario));
		this.setResposta(testeClienteRestControllerFinal.requisicaoHttpGenerica(POST, usuario));
	}
	
	/* Editar */
	public void editar() {
		Usuario usuario = new Usuario(5,"digidoc-api/usuario/5","nomeEditado","sobrenomeEditado");
		//this.setResposta(testeClienteRestController.putEditar(usuario));
		this.setResposta(testeClienteRestControllerFinal.requisicaoHttpGenerica(PUT, usuario));
	}
	
	/* Remover */
	public void remover() {
		//this.setResposta(testeClienteRestController.deleteRemover(5));
		this.setResposta(testeClienteRestControllerFinal.requisicaoHttpGenerica(DELETE, 5));
	}
	
	
	/* GETTERS AND SETTERS */
	public String getResposta() {
		return resposta;
	}
	
	public void setResposta(String string) {
		resposta = string;
	}
	
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
}
