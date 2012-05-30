package br.com.fiap.test;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.bean.Agenda;
import br.com.fiap.bean.Contato;


public class TestBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		Contato c1 = new Contato();

		List<Contato> contatos = new ArrayList<Contato>();
		
		c1.setEmail("fspoliveira@yahoo.com.br");
		contatos.add(c1);
		

		/*********************** contato2 ******************************/


		Contato c2 = new Contato();
		
		c2.setEmail("teste");

		contatos.add(c2);

		Agenda a = new Agenda();
		a.setContatos(contatos);

		for (int i = 0; i < a.getContatos().size(); i++) {

			System.out.println(a.getContatos().get(i).getEmail());
			

		}

	}

}
