package br.com.fiap.bean;

public class Contato {
	
	private Email email;
	private Logradouro logradouro;
	private Telefone telefone;
		
	public Contato() {
		super();
	}
	
	public Contato(Email email, Logradouro logradouro, Telefone telefone) {
		super();
		this.email = email;
		this.logradouro = logradouro;
		this.telefone = telefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Logradouro getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
}