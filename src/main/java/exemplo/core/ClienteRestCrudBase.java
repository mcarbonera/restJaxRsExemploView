package exemplo.core;

import static exemplo.core.ClienteRestCrudBase.TipoRequisicao.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//private static final String URI_CAMINHOBASE = "http://localhost:8080/restJaxRsExemplo/crudFinal";
public class ClienteRestCrudBase<T> {
	private static final String URI_CONSULTARTODOS = "/consultarTodos";
	private static final String URI_CONSULTARID = "/consultarId";
	private static final String URI_CADASTRAR = "/cadastrar";
	private static final String URI_EDITAR = "/editar";
	private static final String URI_EXCLUIR = "/excluir";
	
	public enum TipoRequisicao{
		GETALL,
		GETID,
		POST,
		PUT,
		DELETE
	}
	private static final Map<TipoRequisicao,Requisicao<Object,Integer>> resolveRequisicao = new HashMap<>();
	static {
		resolveRequisicao.put(GETALL, ClienteRestCrudBase::respondeBuscarTodos);
		resolveRequisicao.put(GETID, ClienteRestCrudBase::respondeBuscarPorId);
		resolveRequisicao.put(POST, ClienteRestCrudBase::respondeCadastrar);
		resolveRequisicao.put(PUT, ClienteRestCrudBase::respondeEditar);
		resolveRequisicao.put(DELETE, ClienteRestCrudBase::respondeRemover);
	}
	
	public interface Requisicao<T,I> {
		public Response exec(String caminho,T t, I i);
	}
	
	public String requisicaoHttpGenerica(TipoRequisicao tipoRequisicao, String caminho, T t, Integer id) {
		Response response = null;
		try {
			response = resolveRequisicao.get(tipoRequisicao).exec(caminho, t, id);
			return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
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
	public static <T> Response respondeBuscarTodos(String caminho, T t, Integer id) {
		return new ResteasyClientBuilder().build()
				.target(caminho+URI_CONSULTARTODOS)
				.request()
				.get();
	}
	
	public static <T> Response respondeBuscarPorId(String caminho, T t, Integer id) {
		return new ResteasyClientBuilder().build()
				.target(caminho+URI_CONSULTARID)
				.path(String.valueOf(id))
				.request()
				.get();
	}
	
	public static <T> Response respondeCadastrar(String caminho, T t, Integer id) {
		return new ResteasyClientBuilder().build()
				.target(caminho+URI_CADASTRAR)
				.request()
				.post(Entity.entity(t, MediaType.APPLICATION_JSON));
	}
	
	public static <T> Response respondeEditar(String caminho, T t, Integer id) {
		return new ResteasyClientBuilder().build()
				.target(caminho+URI_EDITAR)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(t, MediaType.APPLICATION_JSON));
	}
	
	public static <T> Response respondeRemover(String caminho, T t, Integer id) {
		return new ResteasyClientBuilder().build()
				.target(caminho+URI_EXCLUIR)
				.path(String.valueOf(id))
				.request()
				.delete();
	}
}
