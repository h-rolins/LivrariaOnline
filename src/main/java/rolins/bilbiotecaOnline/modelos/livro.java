package rolins.bilbiotecaOnline.modelos;
import jakarta.persistence.*;

@Entity
@Table(name ="livros")
public class livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String idioma;


    // Getter e Setter para id
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    // Getter e Setter para titulo
    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    // Getter e Setter para autor
    public String getAutor() {
        return autor;
    }


    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String editora) {
        this.idioma = editora;
    }
}