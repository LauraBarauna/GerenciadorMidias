package main.model.midias.musica;

import java.io.Serializable;

import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.pessoa.Pessoa;

/**
 * @author Yasmin Darlen Schneider e Laura
 */
public class Musica extends Midia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa artista;

    /**
     * Construtor da classe Musica. Inicializa os atributos herdados de Midia e o atributo específico artista.
     * @param id: O identificador único da mídia.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título da música.
     * @param duracao: A duração da música em segundos.
     * @param categoria: A categoria da música.
     * @param artista: O objeto Pessoa que representa o artista.
     */
    public Musica(int id, String local, double tamanhoEmDisco, 
                  String titulo, int duracao, Categoria categoria, Pessoa artista) {
        super(id, local, tamanhoEmDisco, titulo, duracao, categoria);
        setArtista(artista);
    }
    
    /**
     * Sobrescreve o método da classe pai (Midia.exibirAtributos()) e adiciona o atributo específico artista à exibição.
     * @return Uma String contendo todos os atributos da Música formatados para exibição.
     */
    @Override
    public String exibirAtributos() {
        // Chama a implementação da classe pai e adiciona o atributo específico
        String atributos = super.exibirAtributos();
        atributos += "Artista: " + getArtista().getNome() + "\n";
        return atributos;
    }

    /**
     * Define a duração da música em segundos. Sobrescreve o método para manter a documentação da subclasse, mas chama a implementação da classe pai para validar.
     * @param duracao: O valor da duração em segundos.
     * @throws IllegalArgumentException Se a duração for menor ou igual a zero.
     */
    @Override
    public void setDuracao(int duracao) throws IllegalArgumentException {
        super.setDuracao(duracao);
    }
    
    /**
     * Retorna a Pessoa que representa o artista da música.
     * @return O objeto  Pessoa, no caso o artista.
     */
    public Pessoa getArtista() {
        return artista;
    }

    /**
     * Define o objeto Pessoa que representa o artista da música.
     * @param artista: O objeto Pessoa a ser definido.
     * @throws IllegalArgumentException Se o objeto artista for nulo.
     */
    public void setArtista(Pessoa artista) throws IllegalArgumentException{
        if(artista == null) {
            throw new IllegalArgumentException("Pessoa (Artista) não pode ser nula.");
        }
        this.artista = artista;
    }
}