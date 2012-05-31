package br.com.fiap.rest.client;


import java.io.IOException;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import br.com.fiap.bean.Contato;

public class MeuCliente extends ClientResource {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ResourceException
	 */

	public static void main(String[] args) throws ResourceException,
			IOException {
		// Create the client resource
		// ClientResource resource = new
		// ClientResource("http://www.restlet.org");
		ClientResource resource = new ClientResource("http://localhost:8182");

		// Write the response entity on the console
		resource.get().write(System.out);
		// Representation rep = new StringRepresentation("PERGUNTA");

		Contato contato = new Contato();
		contato.setEmail("fspo@msn.com.br");
		contato.setCidade("São Paulo");
		contato.setEndereco("");
		contato.setEstado("");
		contato.setId(0);
		contato.setNome("Fernando Santiago");
		contato.setPais("");
		contato.setTelefone("");

		Representation rep = new JsonRepresentation(contato);
		CharacterSet ch = new CharacterSet("ISO_8859_1");		
		rep.setCharacterSet(ch);
		
		resource.post(rep);
	}
}