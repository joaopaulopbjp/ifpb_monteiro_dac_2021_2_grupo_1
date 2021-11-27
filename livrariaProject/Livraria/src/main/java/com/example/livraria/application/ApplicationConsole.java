package com.example.livraria.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.EstoqueService;
import com.example.livraria.service.LivroService;
import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;

/**
 * Classe usada para mostrar o menu da aplicação da livraria no console, contendo todas 
 * as opções que poderão ser escolhidas pelo usuário.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
// @Component
public class ApplicationConsole implements CommandLineRunner {
	
	/*
	 * Atributo do tipo UsuarioService que vai possibilitar o uso da mesma sem precisar
	 * acessar diretamente o model.
	 * Contém a anotação @Autowired para que quando a classe AplicationConsole for usada o 
	 * Spring chame usuarioService.
	 */
    @Autowired
    UsuarioService usuarioService;
    
    /*
	 * Atributo do tipo AutorService que vai possibilitar o uso da mesma sem precisar 
	 * acessar diretamente o model.
	 * Contém a anotação @Autowired para que quando a classe AplicationConsole for usada o 
	 * Spring chame usuarioService.
	 */
    @Autowired
    AutorService autorService;
    
    /*
	 * Atributo do tipo LivroService que vai possibilitar o uso da mesma sem precisar 
	 * acessar diretamente o model.
	 * Contém a anotação @Autowired para que quando a classe AplicationConsole for usada o 
	 * Spring chame autorService.
	 */
    @Autowired
    LivroService livroService;
    
    /*
	 * Atributo do tipo EstoqueService que vai possibilitar o uso da mesma sem precisar 
	 * acessar diretamente o model.
	 * Contém a anotação @Autowired para que quando a classe AplicationConsole for usada o 
	 * Spring chame livroService.
	 */
    @Autowired
    EstoqueService estoqueService;
    
    /*
	 * Atributo do tipo PedidoService que vai possibilitar o uso da mesma sem precisar 
	 * acessar diretamente o model.
	 * Contém a anotação @Autowired para que quando a classe AplicationConsole for usada o 
	 * Spring chame estoqueService.
	 */
    @Autowired
    PedidoService pedidoService;

	private List<Integer> autoresIDs = new ArrayList<Integer>();

	
    @Override
    public void run(String... args) throws Exception {

    	Scanner entrada = new Scanner(System.in);
        
	    boolean continua = true;
    	
	    while(continua) {
	    	System.out.print("\nEscolha o número correspondente a funcionalidade ->"
	    	        + "\n1 - Registrar novo usuário"
	    	        + "\n2 - Consultar usuário pelo email"
	    	        + "\n3 - Cadastrar autor"
	    	        + "\n4 - Alterar autor"
	    	        + "\n5 - Cadastrar livro"
	    	        + "\n6 - Alterar livro"
	    	        + "\n7 - Excluir livro"
	    	        + "\n8 - Cadastrar um livro do catálogo ao estoque"
	    	        + "\n9 - Consultar os 5 livros mais baratos disponíveis no estoque"
	    	        + "\n10 - Consultar todos os livros"
	    	        + "\n11 - Adicionar um livro a um pedido do cliente"
	    	        + "\n0 - Sair"
	    	        + "\nDigite a opção escolhida: ");
	    	int op = entrada.nextInt();
	    	
	    	String email;
	    	String isbn;
	    	Integer autorID;
	    	Integer quantidade;
	    	String ISBN;
	    	Integer idPedido;
	    
	    	
	    	
	    	switch (op) {
	    		//Novo usuário 
	 			case 1:
	 				System.out.print("Digite o nome: ");
	 				entrada.nextLine();
	 		    	String nome = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite o CPF: ");
	 		    	// String cpf = entrada.nextLine();

	 		    	// System.out.print("Digite o email: ");
	 		    	// email = entrada.nextLine();

	 		    	// System.out.print("Digite o senha: ");
	 		    	// String senha = entrada.nextLine();
	 		    	
	 		    	// //Endereço
	 		    	// System.out.print("Digite o CEP: ");
	 		    	// String cep = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite o estado: ");
	 		    	// String estado = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite a cidade: ");
	 		    	// String cidade = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite a rua: ");
	 		    	// String rua = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite o número: ");
	 		    	// Integer numero = entrada.nextInt();
	 		    	
	 		    	// System.out.print("Digite o bairro: ");
	 				// entrada.nextLine();
	 		    	// String bairro = entrada.nextLine();
	 		    	
	 		    	// System.out.print("Digite o complemento (opcional): ");
	 		    	// String complemento = entrada.nextLine();
	 			
	 				//usuarioService.salvar(nome, cpf, email, senha, cep, estado, cidade, rua, numero, bairro, complemento);
	 				break;
	 			//buscar por email
	 			case 2:
	 		    	System.out.print("Digite o email: ");
	 				entrada.nextLine();
	 		    	email = entrada.nextLine();
 
	 		    	System.out.print("\n"+usuarioService.getUsuario(email) +"\n");
	 				break;
	 			//Cadastrar autor
	 			case 3:
	 		    	System.out.print("Digite o nome: ");
	 				entrada.nextLine();
	 		    	nome = entrada.nextLine();
 
	 				autorService.salvar(nome);
	 				break;
	 			//Alterar autor
	 			case 4:
	 					    		
	 				break;
	 			//Cadastrar livro
	 			case 5:
	 				System.out.print("Digite o ISBN do livro: ");
	 				entrada.nextLine();
	 				isbn = entrada.nextLine();
	 		    	
	 				System.out.print("Digite a categoria: ");
	 		    	String categoria = entrada.nextLine();
	 		    	
	 		    	System.out.print("Digite o título: ");
	 		    	String titulo = entrada.nextLine();
	 		    	
	 		    	System.out.print("Digite a descrição: ");
	 		    	String descricao = entrada.nextLine();
	 		    	
	 		    	System.out.print("Digite a edição: ");
	 		    	String edicao = entrada.nextLine();
	 		    	
	 		    	System.out.print("Digite o ano: ");
	 		    	Integer ano = entrada.nextInt();
	 		    	
	 		    	System.out.print("Digite o preço: ");
	 		    	float preco = entrada.nextFloat();
	 		    	
	 		    	System.out.print("Digite o nome da Editora: ");
	 				entrada.nextLine();
	 		    	String nomeEditora = entrada.nextLine();
	 		    	
	 		    	System.out.print("Digite a cidade da Editora: ");
	 		    	String cidadeEditora = entrada.nextLine(); 		
	 		    	
	 		    	System.out.print("Digite a quantidade de autores que este livro possui: ");
	 		    	int num = entrada.nextInt(); 
	 		    					    
	 		    	for(int i = 1; i <= num; i++) {    		
	 		    		System.out.print("Digite o ID do " + i+ "º autor: ");
	 		    		autorID = entrada.nextInt(); 
		 		    	    autoresIDs.add(autorID);
	 		    	}
	 		    	
	 		    	for(int i = 0; i < autoresIDs.size(); i++) { 
	 		    		System.out.println(autoresIDs.get(i));
	 		    	}
	 		    	
	 		    	livroService.salvar(isbn, categoria, titulo, descricao, edicao, ano, preco, nomeEditora, cidadeEditora, autoresIDs);
	 				break;
	 			//Alterar livro
	 			case 6:
	 				System.out.println("Todos os livros: ");
	 				for(int i = 0; i < livroService.getAll().size(); i++) {
	 					System.out.println(livroService.getAll().get(i).toString());
	 				}
	 				System.out.print("Digite o ISBN do livro que quer alterar: ");
	 				entrada.nextLine();
 		    		isbn = entrada.nextLine();
 		    		
	 				System.out.print("1 - Categoria"
	 						+ "\n2 - Título"
	 						+ "\n3 - Descrição"
	 						+ "\n4 - Edição"
	 						+ "\n5 - Ano"
	 						+ "\n6 - Preço"
	 						+ "\nDigite a opção que deseja alterar:");
 		    		int opcao = entrada.nextInt();
	 				
	 				switch(opcao) {
	 					case 1:
	 						System.out.print("Digite a categoria: ");
	 		 				entrada.nextLine();
	 		 		    	categoria = entrada.nextLine();	    
	 		 		    	livroService.updateCategoria(isbn, categoria); 	
	 		 		    	break;
	 					case 2:
	 		 		    	System.out.print("Digite a título: ");
	 		 				entrada.nextLine();
	 		 		    	titulo = entrada.nextLine();
	 		 		    	livroService.updateTitulo(isbn, titulo); 	
	 		 		    	break;
	 					case 3:	
	 		 		    	System.out.print("Digite a descrição: ");
	 		 				entrada.nextLine();
	 		 		    	descricao = entrada.nextLine();
	 		 		    	livroService.updateDescricao(isbn, descricao); 	
	 		 		    	break;
	 					case 4:	
	 		 		    	System.out.print("Digite a edicao: ");
	 		 				entrada.nextLine();
	 		 		    	edicao = entrada.nextLine();
	 		 		    	livroService.updateEdicao(isbn, edicao); 	
	 		 		    	break;
	 					case 5:	
	 		 		    	System.out.print("Digite o ano: ");
	 		 				entrada.nextLine();
	 		 		    	ano = entrada.nextInt();
	 		 		    	livroService.updateAno(isbn, ano); 	
	 		 		    	break;
	 					case 6:	
	 		 		    	System.out.print("Digite o preco: ");
	 		 				entrada.nextLine();
	 		 		    	preco = entrada.nextInt();
	 		 		    	livroService.updatePreco(isbn, preco); 	
	 		 		    	break;
	 				}
	 				break;
	 		    	
	 			//Excluir livro
	 			case 7:
	 		    	System.out.print("Digite o ISBN do livro: ");
	 				entrada.nextLine();
	 		    	isbn = entrada.nextLine();
 
	 				livroService.remover(isbn);
	 				break;
	 			//Cadastrar um livro do catálogo ao estoque
	 			case 8:
	 				System.out.print("Digite o ISBN do livro: ");
	 				entrada.nextLine();
	 		    	isbn = entrada.nextLine();
	 		    	System.out.print("Digite a quantidade do livro: ");
	 		    	quantidade = entrada.nextInt();
	 		    	
	 		    	estoqueService.criarEstoque(isbn, quantidade);

	 				break;
	 			//Consultar os 5 livros mais baratos disponíveis no estoque
	 			case 9:
	 				for(Livro l :estoqueService.consultarLivrosBaratos()) {
	 					System.out.println(l);;
	 				}
	 				break;
	 			//Consultar todos os livros
	 			case 10:
	 				System.out.print("Digite a pagina a ser consultada: ");
	 				Integer pagina = entrada.nextInt();
	 		    	for(Livro l: livroService.consultarPorPagina(pagina)) {
	 					System.out.println(l);
	 		    	}
	 				break;
	 			//Adicionar um livro a um pedido do cliente
	 			case 11:
	 				System.out.print("Digite seu email: ");
	 				entrada.nextLine();
	 				email = entrada.nextLine();
	 				if(usuarioService.verificarEmail(email)) {
	 					
	 					System.out.print("Digite o id do pedido: ");
	 					idPedido = entrada.nextInt();
	 					System.out.print("Digite o ISBN do livro a ser adicionado: ");
	 					entrada.nextLine();
	 					ISBN = entrada.nextLine();
	 					System.out.print("Informe a quantidade do livro a ser adicionado: ");
	 					quantidade =  entrada.nextInt();
						try {
							pedidoService.adicionarLivroNoPedido(email, idPedido, ISBN, quantidade);
						} catch (Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
						}
	 					
	 				}
	 				break;
	 				
	 			case 0:
	 				continua = false;
	 				System.out.print("Você saiu.");
	 				break;
	 			default:
	 				System.out.print("Opção inválida");
	 				break;
	 		}
	    }
	    entrada.close();
		System.exit(0);
    }
    
}
