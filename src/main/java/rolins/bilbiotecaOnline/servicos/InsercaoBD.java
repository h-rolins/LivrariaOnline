package rolins.bilbiotecaOnline.servicos;

import rolins.bilbiotecaOnline.api.acessando_valores;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InsercaoBD {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void buscarEInserirLivros(String nome) {
        String dadosAPI = acessando_valores.buscarDadosDaAPI(nome);

        if (dadosAPI != null) {
            processarEInserir(dadosAPI);
        } else {
            System.out.println("Nenhum dado foi retornado pela API.");
        }
    }

    private void processarEInserir(String responseBody) {
        try {
            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonArray resultados = jsonResponse.getAsJsonArray("results");

            if (resultados.size() > 0) {
                JsonObject livro = resultados.get(0).getAsJsonObject();

                String titulo = livro.get("title").getAsString();
                JsonArray autoresArray = livro.getAsJsonArray("authors");

                for (int j = 0; j < autoresArray.size(); j++) {
                    JsonObject autor = autoresArray.get(j).getAsJsonObject();
                    String nomeAutor = autor.get("name").getAsString();
                    int anoNascimento = autor.get("birth_year").getAsInt();
                    Integer anoFalecimento = autor.has("death_year") ? autor.get("death_year").getAsInt() : null;

                    // Inserir o autor no banco, se não existir
                    inserirAutor(nomeAutor, anoNascimento, anoFalecimento);

                    // Inserir o livro com o nome do autor e o idioma diretamente
                    JsonArray idiomasArray = livro.getAsJsonArray("languages");
                    StringBuilder idiomasBuilder = new StringBuilder();
                    for (int i = 0; i < idiomasArray.size(); i++) {
                        idiomasBuilder.append(idiomasArray.get(i).getAsString());
                        if (i < idiomasArray.size() - 1) {
                            idiomasBuilder.append(", ");
                        }
                    }
                    String idiomas = idiomasBuilder.toString();

                    // Inserir livro com título, autor e idioma
                    inserirLivro(titulo, nomeAutor, idiomas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirAutor(String nomeAutor, int anoNascimento, Integer anoFalecimento) {
        // Verificar se o autor já existe no banco
        String sqlVerificarAutor = "SELECT COUNT(*) FROM autores WHERE nome = ?";
        Integer count = jdbcTemplate.queryForObject(sqlVerificarAutor, new Object[]{nomeAutor}, Integer.class);

        // Se o autor não existe, inserir
        if (count == 0) {
            String sql = "INSERT INTO autores (nome, ano_nascimento, ano_falecimento) VALUES (?, ?, ?)";
            try {
                jdbcTemplate.update(sql, nomeAutor, anoNascimento, anoFalecimento);
                System.out.println("Autor inserido com sucesso: " + nomeAutor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Autor já existe: " + nomeAutor);
        }
    }

    private void inserirLivro(String titulo, String nomeAutor, String idiomas) {
        // Inserir o livro com título, autor e idioma diretamente na tabela livros
        String sql = "INSERT INTO livros (titulo, autor, idioma) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, titulo, nomeAutor, idiomas);
            System.out.println("Livro inserido com sucesso: " + titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
