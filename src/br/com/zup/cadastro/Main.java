package br.com.zup.cadastro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.zup.desafio2.model.Cliente;

public class Main {

	private static final int DIGITO_MAX_SUBMENU = 2;
	private static final int DIGITO_MIN_SUBMENU = 0;
	private static final String MSG_DE_INSTRUCAO_DO_MENU = "Olá seja bem vindo ao seu sistema de cadastro de clientes!"
			+ " \nDigite :\n 1-[para inserir cadastro]\n 2-[para alterar cadastro]\n 3-[para buscar cadastro ]\n 4-[para deletar cadastro]\n 0-[para ENCERRAR]";
	private static final String INSTRUCOES_SUBMENU = "Digite:\n1-[para buscar por um cliente]\n2-[Para buscar a lista de clientes]\n0-[para SAIR]";
	private static final String DIGITE_O_CPF_PARA_DELETE = "Digite o cpf do cliente para excluir o cadastro :";
	private static final String DIGITE_O_CPF_PARA_ALTERAR_CADASTRO = "Digite o cpf do cliente para alterar o cadastro :";
	private static final String DIGITE_O_ENDERECO = "Digite o endereço :";
	private static final String DIGITE_O_TELEFONE = "Digite o telefone :";
	private static final String DIGITE_EMAIL = "Digite email :";
	private static final String DIGITE_O_CPF = "Digite o cpf :";
	private static final String DIGITE_A_IDADE = "Digite a idade :";
	private static final String DIGITE_O_NOME = "Digite o nome :";
	private static final String DIGITE_UMA_OPERACAO_VALIDA = "\nDigite uma operação válida\n";
	private static final String TELEFONE = "Número de telefone: ";
	private static final String ENDERECO = "Endereço físico: ";
	private static final String E_MAIL = "Endereço de e-mail: ";
	private static final String CPF = "CPF: ";
	private static final String IDADE = "Idade: ";
	private static final String NOME = "Nome do cliente: ";
	private static final int DIGITO_MAX = 4;
	private static final int DIGITO_MIN = 0;
	private static Map<Long, Cliente> listaClientes;

	public static void main(String[] args) {

		listaClientes = new HashMap<Long, Cliente>();

		int operacao;
		Scanner teclado = new Scanner(System.in);

		do {

			System.out.println(MSG_DE_INSTRUCAO_DO_MENU);
			operacao = teclado.nextInt();
			if (operacao < DIGITO_MIN || operacao > DIGITO_MAX) {
				System.out.println(DIGITE_UMA_OPERACAO_VALIDA);

			}

			switch (operacao) {

			case 1:
				adicionaCliente(teclado, listaClientes);
				break;

			case 2:
				alteraCadastro(teclado);
				break;

			case 3:
				subMenuDeBusca(teclado);
				break;

			case 4:
				excluirCadastro(teclado);
				break;

			default:
				break;
			}

		} while (operacao != 0);
		
		teclado.close();

	}

	public static Cliente adicionaCliente(Scanner teclado, Map<Long, Cliente> listaDeClientes) {

		Cliente clienteAdicionado = new Cliente();

		teclado.nextLine();
		System.out.println(DIGITE_O_NOME);
		clienteAdicionado.setNome(teclado.nextLine());
		System.out.println(DIGITE_A_IDADE);
		clienteAdicionado.setIdade(teclado.nextInt());
		System.out.println(DIGITE_O_CPF);
		clienteAdicionado.setCpf(teclado.nextLong());
		System.out.println(DIGITE_EMAIL);
		clienteAdicionado.setEmail(teclado.next());
		System.out.println(DIGITE_O_TELEFONE);
		clienteAdicionado.setTelefone(teclado.nextInt());
		teclado.nextLine();
		System.out.println(DIGITE_O_ENDERECO);
		clienteAdicionado.setEndereco(teclado.nextLine());

		return listaDeClientes.put(clienteAdicionado.getCpf(), clienteAdicionado);
	}

	public static void buscaCliente(Scanner teclado) {

		System.out.println(DIGITE_O_CPF);
		Long cpf = teclado.nextLong();
		Cliente cliente = listaClientes.get(cpf);

		clienteBuscado(cliente);

	}

	public static void buscaListaDeClientes() {
		for (Cliente cliente : listaClientes.values()) {
			clienteBuscado(cliente);

		}

	}

	public static void alteraCadastro(Scanner teclado) {

		System.out.println(DIGITE_O_CPF_PARA_ALTERAR_CADASTRO);
		Long cpf = teclado.nextLong();
		Cliente clienteAlterado = listaClientes.get(cpf);

		teclado.nextLine();
		System.out.println(DIGITE_O_NOME);
		clienteAlterado.setNome(teclado.nextLine());
		System.out.println(DIGITE_A_IDADE);
		clienteAlterado.setIdade(teclado.nextInt());
		
		System.out.println(DIGITE_EMAIL);
		clienteAlterado.setEmail(teclado.next());
		System.out.println(DIGITE_O_TELEFONE);
		clienteAlterado.setTelefone(teclado.nextInt());
		teclado.nextLine();
		System.out.println(DIGITE_O_ENDERECO);
		clienteAlterado.setEndereco(teclado.nextLine());

		listaClientes.put(cpf, clienteAlterado);
		clienteBuscado(clienteAlterado);

	}

	public static void excluirCadastro(Scanner teclado) {
		System.out.println(DIGITE_O_CPF_PARA_DELETE);
		Long cpf = teclado.nextLong();
		listaClientes.remove(cpf);

	}

	public static void clienteBuscado(Cliente clienteBuscado) {

		System.out.println(NOME + clienteBuscado.getNome());
		System.out.println(IDADE + clienteBuscado.getIdade());
		System.out.println(CPF + clienteBuscado.getCpf());
		System.out.println(E_MAIL + clienteBuscado.getEmail());
		System.out.println(ENDERECO + clienteBuscado.getEndereco());
		System.out.println(TELEFONE + clienteBuscado.getTelefone());

	}

	public static void subMenuDeBusca(Scanner teclado) {

		int operacao;

		do {

			System.out.println(INSTRUCOES_SUBMENU);
			operacao = teclado.nextInt();
			if (operacao < DIGITO_MIN_SUBMENU || operacao > DIGITO_MAX_SUBMENU) {
				System.out.println(DIGITE_UMA_OPERACAO_VALIDA);
			}

			switch (operacao) {

			case 1:
				buscaCliente(teclado);
				break;

			case 2:
				buscaListaDeClientes();
				break;

			}

		} while (operacao != 0);

	}

}
