package rolins.bilbiotecaOnline;
import rolins.bilbiotecaOnline.servicos.InsercaoBD;
import rolins.bilbiotecaOnline.servicos.buscaBD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.Scanner;

@SpringBootApplication
public class BilbiotecaOnlineApplication {

	@Autowired
	private InsercaoBD insercaoBD;

	@Autowired
	private buscaBD buscaBD;

	public static void main(String[] args) {
		SpringApplication.run(BilbiotecaOnlineApplication.class, args);
	}

	@PostConstruct
	public void init() {
		menu();
	}

	private void menu() {
		Scanner scanner = new Scanner(System.in);
		int opcao;
		int ano;
		String idioma;
		String nomeLivro;

		do {
			System.out.println("Menu:");
			System.out.println("1. Buscar livro pelo título");
			System.out.println("2. Listar livros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Listar livros em determinado idioma");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção (1-6): ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.println("Digite o nome do livro: ");
					nomeLivro = scanner.nextLine();
					buscarLivro(nomeLivro);
					break;
				case 2:
					listarlivros();
					break;
				case 3:
					listarautores();
					break;
				case 4:
					System.out.println("Digite o ano em que o autor esteja vivo: ");
					ano = scanner.nextInt();
					scanner.nextLine();
					listarautoresvivosem(ano);
					break;
				case 5:
					System.out.println("Escolha o idioma desejado: ");
					System.out.println("Português (pt) ");
					System.out.println("Inglês (en) ");
					System.out.println("Italiano (it) ");
					System.out.println("Francês (fr) ");
					idioma = scanner.nextLine();
					listarlivrosidioma(idioma);
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
			}
		} while (opcao != 6);

		scanner.close();
	}

	private void buscarLivro(String nomeLivro) {
		insercaoBD.buscarEInserirLivros(nomeLivro);
	}

	private void listarlivros() {
		buscaBD.listarLivros();
	}

	private void listarautores() {
		buscaBD.listarAutores();
	}

	private void listarautoresvivosem(int ano) {
		buscaBD.listarAutoresVivos(ano);
	}

	private void listarlivrosidioma(String idioma) {
		buscaBD.listarLivrosPorIdioma(idioma);
	}

}
