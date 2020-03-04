package exemplo.model;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String uri;
	private String nome;
	private String sobrenome;
	
	public Usuario() {
	}
	
	public Usuario(Integer id, String uri, String nome, String sobrenome) {
		this.id = id;
		this.uri = uri;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Override
	public String toString() {
		return "Usuario{"
				+ "id="+id+
				", uri='"+uri+"\'"+
				", nome='"+nome+"\'"+
				", sobrenome='"+sobrenome+"\'"
				+"}";
	}
}
