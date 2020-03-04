package exemplo.controller;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exemplo.model.Usuario;

public class TesteClienteRestController{
	private static final String URI_CONSULTARTODOS = "http://localhost:8080/restJaxRsExemplo/crudFinal/consultarTodos";
	private static final String URI_CONSULTARID = "http://localhost:8080/restJaxRsExemplo/crudFinal/consultarId";
	private static final String URI_CADASTRAR = "http://localhost:8080/restJaxRsExemplo/crudFinal/cadastrar";
	private static final String URI_EDITAR = "http://localhost:8080/restJaxRsExemplo/crudFinal/editar";
	private static final String URI_EXCLUIR = "http://localhost:8080/restJaxRsExemplo/crudFinal/excluir";
	
	public TesteClienteRestController() {
		
	}
	
	public String getBuscarTodos() {
		Response response = null;
		try {
			response = new ResteasyClientBuilder().build()
					.target(URI_CONSULTARTODOS)
					.request()
					.get();
			return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
		} finally {
			if(null != response) {
				response.close();
			}
		} 
	}
	
	public String getBuscarPorId(Integer id) {
		Response response = null;
		try{
			response = new ResteasyClientBuilder().build()
					.target(URI_CONSULTARID)
					.path(String.valueOf(id))
					.request()
					.get();
	        return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
		} finally {
			if(null != response) {
				response.close();
			}
		}
	}
	
	public String postCadastrar(Usuario usuario) {
		Response response = null;
		try {
			response = new ResteasyClientBuilder().build()
					.target(URI_CADASTRAR)
					.request()
					.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
			return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
		} finally {
			if(null != response) {
				response.close();
			}
		}
	}
	
	public String putEditar(Usuario usuario) {
		Response response = null;
		try {
			response = new ResteasyClientBuilder().build()
					.target(URI_EDITAR)
					.request(MediaType.APPLICATION_JSON)
					.put(Entity.entity(usuario, MediaType.APPLICATION_JSON));
			return prepararResposta(response);
		} catch(ProcessingException e) {
			return "Servidor Fora do Ar";
		} finally {
			if(null != response) {
				response.close();
			}
		}
	}
	
	public String deleteRemover(Integer id) {
		Response response = null;
		try {
			response = new ResteasyClientBuilder().build()
					.target(URI_EXCLUIR)
					.path(String.valueOf(id))
					.request()
					.delete();
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
}
