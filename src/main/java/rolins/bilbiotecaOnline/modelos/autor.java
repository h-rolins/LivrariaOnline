package rolins.bilbiotecaOnline.modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int ano_nascimento;
    private int ano_falecimento;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDataNascimento() {
        return ano_nascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.ano_nascimento = ano_nascimento;
    }

    public int getDataFalecimento() {
        return ano_falecimento;
    }

    public void setDataFalecimento(String dataFalecimento) {
        this.ano_falecimento = ano_falecimento;
    }
}
