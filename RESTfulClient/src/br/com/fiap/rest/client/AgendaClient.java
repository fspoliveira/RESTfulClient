package br.com.fiap.rest.client;

import java.util.Scanner;

import br.com.fiap.bean.Contato;
import br.com.fiap.rest.AgendaRestClient;

/** 
 * Interface de Usuario da Agenda de Contatos via REST
 */
public class AgendaClient {

	private final int OPCAO_INSERIR   = 1;
	private final int OPCAO_CONSULTAR = 2;
	private final int OPCAO_LISTAR    = 3;
	private final int OPCAO_EXCLUIR   = 4;	
	private final int OPCAO_QUIT      = 0;

	private AgendaRestClient agendaRestClient = new AgendaRestClient();
	
	/**
	 * Executa a Interface de Usuario da Agenda
	 */
	public void execute() {

		Scanner input = new Scanner(System.in);
		input.useDelimiter(System.getProperty("line.separator"));
		
		String urlServer = null;

		System.out.println("Digite a URL do servidor da Agenda");
		System.out.println("(default: 'http://localhost:9000/agenda'): ");
		urlServer = getStringFromInput(input);
		
		if (urlServer != null && !urlServer.isEmpty()) {
			agendaRestClient.setServerURL(urlServer);
		}
		
		int opcao = 0;
		
		do {
			
			opcao = promptMenuPrincipal(input);
			
		} while (opcao != OPCAO_QUIT);

		System.out.println("Tchau!!");
		
	}

	/**
	 * Obtem um numero inteiro a partir do console
	 * @param input Objeto da classe {@link Scanner}
	 * @return Numero inteiro obtido ou -1 se houve erro.
	 */
	private int getIntFromInput(Scanner input) {

		int retorno = -1;
		
		try {
			retorno = input.nextInt();
		} catch (Exception ex) {
			input.nextLine();
			retorno = -1;
		}
		
		return retorno;
	}

	/**
	 * Obtem uma string a partir do console
	 * @param input Objeto da classe {@link Scanner}
	 * @return String obtida ou "" se houve erro.
	 */
	private String getStringFromInput(Scanner input) {
		
		String retorno = ""; 
		
		try {
			retorno = input.next();
		} catch (Exception ex) {
			input.nextLine();
			retorno = "";
		}
		
		return retorno;
	}

	/**
	 * Exibe um prompt para o usuario pressionar ENTER para continuar
	 * @param input Objeto da classe {@link Scanner}
	 */
	private void promptEnterToContinue(Scanner input) {

		System.out.print("Pressione <ENTER> para continuar...");
		getStringFromInput(input);
	}

	/**
	 * Exibe um prompt para o usuario confirmar uma operacao (Sim ou Nao)
	 * @param input Objeto da classe {@link Scanner}
	 * @return True se houve confirmacao, false se nao.
	 */
	private boolean promptConfirma(Scanner input) {

		String opcao = null;
		
		do {
		
			System.out.print("Confirma (S/N)? ");
			opcao = getStringFromInput(input).trim().toUpperCase();
			
		} while (!"S".equals(opcao) && !"N".equals(opcao));
		
		return ("S".equals(opcao));
	}

	/**
	 * Exibe o Menu Principal e avalia a opcao escolhida pelo usuario
	 * @param input Objeto da classe {@link Scanner}
	 * @return Resultado da opcao escolhida.
	 */
	private int promptMenuPrincipal(Scanner input) {

		int opcao = -1;
		
		do {
		
			System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println("Agenda de Contatos - Exercicio REST - 17SCJ");
			System.out.println("-------------------------------------------");
			System.out.println("Fernando Santiago - RM: 42320");			
			System.out.println("-------------------------------------------");
			System.out.println("Servidor: " + agendaRestClient.getServerURL());
			System.out.println("-------------------------------------------");
			System.out.println("1 - Adicionar Contato");
			System.out.println("2 - Consultar Contato");
			System.out.println("3 - Listar Contatos");
			System.out.println("4 - Remover Contato");	
			System.out.println("-------------------------------------------");
			System.out.println("0 - Sair do Programa");
			System.out.println("-------------------------------------------");
			
			opcao = getIntFromInput(input); 
				
			switch (opcao) {
			case OPCAO_INSERIR:
				inserirContato(input);
				opcao = -1;
				break;
			case OPCAO_EXCLUIR:
				excluirContato(input);
				opcao = -1;
				break;
			case OPCAO_CONSULTAR:
				consultarContato(input);
				opcao = -1;
				break;
			case OPCAO_LISTAR:
				listarContatos(input);
				opcao = -1;
				break;
			case OPCAO_QUIT:
				break;
			default:
				opcao = -1;
				break;
			}
		
		} while (opcao == -1);

		return opcao;
	}

	/**
	 * Insere um contato na agenda
	 * @param input Objeto da classe {@link Scanner}
	 */
	private void inserirContato(Scanner input) {

		String email = null;
		Contato contato = null;

		System.out.println("Digite o email do contato a ser adicionado: ");
		email = getStringFromInput(input);
		
		contato = agendaRestClient.consultar(email);
		
		if (contato != null && contato.getEmail() != null) {
			
			System.out.println("Ja' existe um contato cadastrado com esse email:");
			System.out.println();
			imprimeContato(contato);
			
		} else {
			
			contato = new Contato();
			contato.setEmail(email);
			
			System.out.println("Digite o nome do contato: ");
			contato.setNome(getStringFromInput(input));

			System.out.println("Digite o telefone: ");
			contato.setTelefone(getStringFromInput(input));

			System.out.println("Digite o endereco (logradouro, numero): ");
			contato.setEndereco(getStringFromInput(input));
			
			System.out.println("Digite a cidade: ");
			contato.setCidade(getStringFromInput(input));

			System.out.println("Digite o estado (UF): ");
			contato.setEstado(getStringFromInput(input));

			System.out.println("Digite o pais: ");
			contato.setPais(getStringFromInput(input));
			
			if (agendaRestClient.inserir(contato)) {
				
				System.out.println("Contato adicionado com sucesso!");
				
			} else {
				
				System.out.println("Erro ao adicionar contato.");
			}
		}
		
		promptEnterToContinue(input);
	}

	/**
	 * Exclui um contato da agenda
	 * @param input Objeto da classe {@link Scanner}
	 */
	private void excluirContato(Scanner input) {

		String email = null;
		Contato contato = null;

		System.out.println("Digite o email do contato a ser removido: ");
		email = getStringFromInput(input);

		contato = agendaRestClient.consultar(email);

		if (contato == null || contato.getEmail() == null) {
			
			System.out.println("Nao ha' contato cadastrado com esse email.");
			
		} else {
			
			System.out.println("Remover Contato:");
			System.out.println();
			imprimeContato(contato);

			if (promptConfirma(input)) {

				if (agendaRestClient.excluir(email)) {
					
					System.out.println("Contato excluido com sucesso!");
					
				} else {
					
					System.out.println("Erro ao excluir contato.");
				}
				
			}
		}
		
		promptEnterToContinue(input);
	}

	/**
	 * Consulta um contato na agenda, pelo email
	 * @param input Objeto da classe {@link Scanner}
	 */
	private void consultarContato(Scanner input) {

		String email = null;
		Contato contato = null;

		System.out.println("Digite o email do Contato: ");
		email = getStringFromInput(input);

		contato = agendaRestClient.consultar(email);

		if (contato != null && contato.getEmail() != null) {
			
			System.out.println("Contato encontrado:");
			System.out.println();
			imprimeContato(contato);
			
		} else {
			
			System.out.println("Nao ha' contato cadastrado com esse email.");
		}
		
		promptEnterToContinue(input);
	}

	/**
	 * Lista todos contatos da agenda
	 * @param input Objeto da classe {@link Scanner}
	 */
	private void listarContatos(Scanner input) {
		
		Contato[] listContatos = agendaRestClient.listar();
		
		if (listContatos == null || listContatos.length == 0) {
			
			System.out.println("Nao ha' nenhum contato cadastrado.");
			
		} else {
			
			System.out.println("Lista de contatos cadastrados:");
			System.out.println();
			for (Contato contato : listContatos) {
				imprimeContato(contato);
			}
		}
		
		promptEnterToContinue(input);
		
	}
	
	/**
	 * Imprime na tela as informacoes de um contato
	 * @param contato Objeto da classe {@link Contato}
	 */
	private void imprimeContato(Contato contato) {

		System.out.println(String.format("Nome: %s", contato.getNome()));
		System.out.println(String.format("Email: %s", contato.getEmail()));
		System.out.println(String.format("Telefone: %s", contato.getTelefone()));
		System.out.println(String.format("End: %s", contato.getEndereco()));
		System.out.println(String.format("Cidade: %s",	contato.getCidade()));
		System.out.println(String.format("Estado: %s", contato.getEstado()));
		System.out.println(String.format("Pais: %s", contato.getPais()));
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		AgendaClient agenda = new AgendaClient();
		agenda.execute();
	}
	
}