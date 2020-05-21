package br.com.zup.pgg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zup.pgg.model.Cliente;
import br.com.zup.pgg.service.ClienteService;

@WebServlet(urlPatterns = "/clientes")
public class ClienteController extends HttpServlet {

	private static final String CLIENTE_INSERIDO = "\nCliente inserido com sucesso!";
	private static final String CLIENTE_JA_EXISTE = "Cliente ja existe";
	private static final String CPF_NULO = "cpf nulo";
	private static final long serialVersionUID = 1L;
	public static Map<String, Cliente> listaDeCliente = new HashMap<String, Cliente>();
	public static ClienteService clienteService = new ClienteService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String cpf = req.getParameter("cpf");

		if (cpf != null) {

			buscaCliente(req, resp);

		} else {

			for (Cliente cliente : clienteService.buscaListaClientes()) {
				writer.println("Nome: " + cliente.getNome());
				writer.println("Idade: " + cliente.getIdade());
				writer.println("Email: " + cliente.getEmail());
				writer.println("Cpf: " + cliente.getCpf());
				writer.println("Telefone: " + cliente.getTelefone());
				writer.println("Endereco: " + cliente.getEndereco());
				writer.println("\n");

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String cpf = request.getParameter("cpf");

		Cliente cliente = new Cliente();
		if (cpf != "") {

			cliente.setNome(request.getParameter("nome"));
			cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setTelefone(request.getParameter("telefone"));
			cliente.setEndereco(request.getParameter("endereco"));

			if (!listaDeCliente.containsKey(cpf)) {

				clienteService.insereCliente(cliente);

				response.getWriter()
						.print("nome :" + cliente.getNome() + "\nidade :" + cliente.getIdade() + "\ncpf :"
								+ cliente.getCpf() + "\nemail :" + cliente.getEmail() + "\ntelefone :"
								+ cliente.getTelefone() + "\nendereco :" + cliente.getEndereco());
				writer.print(CLIENTE_INSERIDO);

			}

			writer.print(CLIENTE_JA_EXISTE);

		} else {

			writer.print(CPF_NULO);
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		PrintWriter writer = response.getWriter();

		if (cpf != null) {

			Cliente cliente = new Cliente();
			cliente.setNome(request.getParameter("nome"));
			cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
			cliente.setEmail(request.getParameter("email"));
			cliente.setTelefone(request.getParameter("telefone"));
			cliente.setEndereco(request.getParameter("endereco"));
			clienteService.alteraCliente(cliente, cpf);
			response.getWriter();
			writer.print(
					"nome :" + cliente.getNome() + "\nidade :" + cliente.getIdade()  +"\nemail :" + cliente.getEmail()
							+ "\ntelefone :" + cliente.getTelefone() + "\nendereco :" + cliente.getEndereco());

		} else {

			writer.print("CPF NAO ENCONTRADO!");
		}
		writer.print("\nCliente Atualizado com sucesso!");
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		clienteService.deleteCliente(cpf);

	}

	public static void buscaCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		String cpf = req.getParameter("cpf");

		Cliente clienteBuscado = clienteService.clientePorCpf(cpf);
		writer.println("Nome: " + clienteBuscado.getNome());
		writer.println("Idade: " + clienteBuscado.getIdade());
		writer.println("Email: " + clienteBuscado.getEmail());
		writer.println("Cpf: " + clienteBuscado.getCpf());
		writer.println("Telefone: " + clienteBuscado.getTelefone());
		writer.println("Endereco: " + clienteBuscado.getEndereco());
	}

}
