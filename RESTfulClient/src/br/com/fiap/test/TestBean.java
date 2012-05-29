package br.com.fiap.test;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.bean.Agenda;
import br.com.fiap.bean.Contato;
import br.com.fiap.bean.Email;
import br.com.fiap.bean.Logradouro;
import br.com.fiap.bean.Telefone;

public class TestBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Email e1 = new Email("f@yahoo.com.br");
		Logradouro l1 = new Logradouro("Rua Campo Redondo, 900 casa 100",
				"São Paulo", "SP", "Brasil");

		Telefone t1 = new Telefone("11 7070 0001");

		Contato c1 = new Contato(e1, l1, t1);

		List<Contato> contatos = new ArrayList<Contato>();

		contatos.add(c1);

		/*********************** contato2 ******************************/

		Email e2 = new Email("cma@hotmail.com");
		Logradouro l2 = new Logradouro("Rua Dom Antonio Barney, 277 casa 52",
				"Campinas", "SP", "Brasil");

		Telefone t2 = new Telefone("11 7070 0002");

		Contato c2 = new Contato(e2, l2, t2);

		contatos.add(c2);

		Agenda a = new Agenda();
		a.setContatos(contatos);

		for (int i = 0; i < a.getContatos().size(); i++) {

			System.out.println(a.getContatos().get(i).getEmail().getEmail());
			System.out.println(a.getContatos().get(i).getLogradouro()
					.getCidade());

		}

	}

}
