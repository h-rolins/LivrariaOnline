package rolins.bilbiotecaOnline.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class buscaBD {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void listarLivros() { // Alterado para 'public'
        String sql = "SELECT * FROM livros";
        try {
            List<Map<String, Object>> livros = jdbcTemplate.queryForList(sql);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado no banco de dados.");
            } else {
                System.out.println("Lista de livros:");
                for (Map<String, Object> livro : livros) {
                    System.out.println("Título: " + livro.get("titulo"));
                    System.out.println("Autor: " + livro.get("autor"));
                    System.out.println("Idioma: " + livro.get("idioma"));
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar os livros.");
            e.printStackTrace();
        }
    }

    public void listarAutores() {
        String sql = "SELECT DISTINCT autor FROM livros";
        try {
            List<String> autores = jdbcTemplate.queryForList(sql, String.class);

            if (autores.isEmpty()) {
                System.out.println("Nenhum autor encontrado no banco de dados.");
            } else {
                System.out.println("Lista de autores:");
                for (String autor : autores) {
                    System.out.println(autor);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar os autores.");
            e.printStackTrace();
        }
    }

    public void listarAutoresVivos(int ano) {
        String sql = """
            SELECT nome FROM autores  WHERE ano_nascimento <= ? AND (ano_falecimento IS NULL OR ano_falecimento > ?)
        """;
        try {
            List<String> autoresVivos = jdbcTemplate.queryForList(sql, new Object[]{ano, ano}, String.class);

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado no ano " + ano + ".");
            } else {
                System.out.println("Autores vivos no ano " + ano + ":");
                for (String autor : autoresVivos) {
                    System.out.println(autor);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar os autores vivos.");
            e.printStackTrace();
        }
    }

    public void listarLivrosPorIdioma(String idioma) {
        String sql = "SELECT * FROM livros WHERE idioma = ?";
        try {
            // Realizando a consulta no banco de dados para listar livros do idioma escolhido
            List<Map<String, Object>> livros = jdbcTemplate.queryForList(sql, idioma);
            if (!livros.isEmpty()) {
                for (Map<String, Object> livro : livros) {
                    System.out.println("Título: " + livro.get("titulo"));
                    System.out.println("Autor: " + livro.get("autor"));
                    System.out.println("Idioma: " + livro.get("idioma"));
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("Nenhum livro encontrado para o idioma " + idioma);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
