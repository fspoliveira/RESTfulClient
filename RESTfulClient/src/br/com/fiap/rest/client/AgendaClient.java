package br.com.fiap.rest.client;


/**
 * Classe Consumidora do REST Agenda
 */
public class AgendaClient {

	/**
	 * Metodo principal da aplicacao.
	 * @param args Argumentos de linha de comando
	 */
	public static void main(String[] args) {
		AgendaConsole agenda = new AgendaConsole();
		agenda.execute();
	}
}