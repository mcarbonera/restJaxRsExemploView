package exemplo.controller.resolveRequisicaoHttp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exemplo.model.Usuario;

public class exemploSemArgumento {
	public enum TipoRequisicao{
		GETALL,
		GETID,
		POST,
		PUT,
		DELETE
	}
	public static final Map<exemploSemArgumento.TipoRequisicao,Callable<Response>> resolveRequisicao = new HashMap<>();
	//private static TesteClienteRestControllerFinal testeClienteRestControllerFinal;
	static {
		resolveRequisicao.put(TipoRequisicao.GETALL, exemploSemArgumento::respondeBuscarTodos);
		resolveRequisicao.put(TipoRequisicao.GETID, exemploSemArgumento::respondeBuscarPorId);
		resolveRequisicao.put(TipoRequisicao.POST, exemploSemArgumento::respondeCadastrar);
		resolveRequisicao.put(TipoRequisicao.PUT, exemploSemArgumento::respondeEditar);
		resolveRequisicao.put(TipoRequisicao.DELETE, exemploSemArgumento::respondeRemover);
	}
	
	private static final String URI_CONSULTARTODOS = "http://localhost:8080/restJaxRsExemplo/crudFinal/consultarTodos";
	private static final String URI_CONSULTARID = "http://localhost:8080/restJaxRsExemplo/crudFinal/consultarId";
	private static final String URI_CADASTRAR = "http://localhost:8080/restJaxRsExemplo/crudFinal/cadastrar";
	private static final String URI_EDITAR = "http://localhost:8080/restJaxRsExemplo/crudFinal/editar";
	private static final String URI_EXCLUIR = "http://localhost:8080/restJaxRsExemplo/crudFinal/excluir";

	public void TesteClienteRestController() {

	}	
			
	public String requisicaoHttpGenerica(TipoRequisicao tipoRequisicao, Usuario usuario, Integer id) {
		Response response = null;
		try {
			response = resolveRequisicao.get(tipoRequisicao).call();
			return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
		} catch (Exception e) {
			return "Requisição não efetuada. ";
		} finally {
			if(null != response) {
				response.close();
			}
		}
	}
	
	/* Função Auxiliar */
	private String prepararResposta(Response response) {
		int statusCode = response.getStatus();
        String resposta = response.readEntity(String.class);
        
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object jsonObject = mapper.readValue(resposta, Object.class);
			String respostaFormatada = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
			
			return "Status: "+statusCode+"\n\n"+respostaFormatada;
		} catch (JsonMappingException e) {
			return "Status: "+statusCode;
		} catch (JsonProcessingException e) {
			return "Status: "+statusCode;
		} catch (NullPointerException e) {
			return "Status: "+statusCode;
		}
	}
	
	/* getResponse */	
	public static Response respondeBuscarTodos() {
		return new ResteasyClientBuilder().build()
				.target(URI_CONSULTARTODOS)
				.request()
				.get();
	}
	
	public static Response respondeBuscarPorId() {
		return new ResteasyClientBuilder().build()
				.target(URI_CONSULTARID)
				.path(String.valueOf(1))
				.request()
				.get();
	}
	
	public static Response respondeCadastrar() {
		Usuario usuario = new Usuario();
		return new ResteasyClientBuilder().build()
				.target(URI_CADASTRAR)
				.request()
				.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
	}
	
	public static Response respondeEditar() {
		Usuario usuario = new Usuario();
		return new ResteasyClientBuilder().build()
				.target(URI_EDITAR)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(usuario, MediaType.APPLICATION_JSON));
	}
	
	public static Response respondeRemover() {
		return new ResteasyClientBuilder().build()
				.target(URI_EXCLUIR)
				.path(String.valueOf(1))
				.request()
				.delete();
	}
}
