package br.com.fiap.rest.client;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class MeuCliente extends ClientResource {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ResourceException 
	 */
	public static void main(String[] args) throws ResourceException, IOException {
		//Create the client resource  
		//ClientResource resource = new ClientResource("http://www.restlet.org");
		ClientResource resource = new ClientResource("http://localhost:8182");
		
		// Write the response entity on the console
		resource.get().write(System.out);
		Representation rep = new StringRepresentation("PERGUNTA");
		resource.post(rep);
	}

}
