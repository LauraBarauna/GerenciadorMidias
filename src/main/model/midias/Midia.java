package main.model.midias;

import java.io.Serializable;

/**
 * @author Yasmin Darlen Schneider e Laura Barauna
 */
public abstract class Midia implements Serializable {

    private static final long serialVersionUID = 1L;
    protected int id;
    private String local;
    private double tamanhoEmDisco;
    protected String titulo;
    protected int duracao;
    protected Categoria categoria;

    /**
     * Construtor da classe Midia. Inicializa e valida todos os atributos comuns a todas as mídias.
     * @param id: O identificador único da mídia.
     * @param local: O caminho para o arquivo no sistema operacional.
     * @param tamanhoEmDisco: O tamanho do arquivo em disco (em MB, por exemplo).
     * @param titulo: O título da mídia.
     * @param duracao: A duração/tamanho da mídia (unidade varia por tipo: minutos, segundos ou páginas).
     * @param categoria: O objeto Categoria da mídia.
     */

    public Midia(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria) {
        setId(id);
        setLocal(local);
        setTamanhoEmDisco(tamanhoEmDisco);
        setTitulo(titulo);
        setDuracao(duracao);
        setCategoria(categoria);
    }

    /**
     * Retorna uma String formatada com os atributos básicos da mídia. Este método é projetado para ser estendido pelas classes filhas para incluir seus atributos específicos.
     * @return Uma String contendo o ID, Local, Tamanho, Título, Duração e Categoria.
     */
    public String exibirAtributos() {
        String atributos = "";
        atributos += "Id: " + getId() + "\n" +
                    "Local do arquivo: " + getLocal() + "\n" +
                    "Tamanho do arquivo: " + getTamanhoEmDisco() + "\n" +
                    "Título: " + getTitulo() + "\n" +
                    "Duração: " + getDuracao() + "\n" +
                    "Categoria: " + getCategoria().getCategoria() + "\n";
        return atributos;
    }

    /**
     * @return O ID único da mídia.
     */
    public int getId() {
        return id;
    }

    /**
     * @return O local (caminho) do arquivo no sistema operacional.
     */
    public String getLocal() {
        return local;
    }

    /**
     * @return O tamanho do arquivo em disco (double).
     */
    public double getTamanhoEmDisco() {
        return tamanhoEmDisco;
    }

    /**
     * @return O título da mídia.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return A duração ou quantidade de páginas (int) da mídia.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @return a Categoria associado à mídia.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define o ID da mídia.
     * @param id: O novo ID.
     * @throws IllegalArgumentException Se o ID for menor ou igual a zero.
     */
    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id não pode ser igual ou menor que 0.");
        }
        this.id = id;
    }

    /**
     * Define o caminho (local) do arquivo.
     * @param local: O caminho do arquivo.
     * @throws IllegalArgumentException Se o local for nulo ou uma string vazia.
     */
    public void setLocal(String local) {
        if(local == null || local.isBlank()) {
            throw new IllegalArgumentException("Local do arquivo não pode ser vazio.");
        }
        this.local = local;
    }

    /**
     * Define o tamanho do arquivo em disco.
     * @param tamanhoEmDisco: O tamanho em disco (double).
     * @throws IllegalArgumentException Se o tamanho em disco for menor ou igual a zero.
     */
    public void setTamanhoEmDisco(double tamanhoEmDisco) {
        if (tamanhoEmDisco <= 0) {
            throw new IllegalArgumentException("Tamanho em disco não pode ser menor ou igual a zero.");
        }
        this.tamanhoEmDisco = tamanhoEmDisco;
    }

    /**
     * Define o título da mídia.
     * @param titulo: O título da mídia.
     * @throws IllegalArgumentException Se o título for nulo ou uma string vazia.
     */
    public void setTitulo(String titulo)  {
        if (titulo == null  || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    /**
     * Define o objeto Categoria da mídia.
     * @param categoria: O objeto Categoria a ser associado.
     * @throws IllegalArgumentException Se o objeto categoria for nulo.
     */
    public void setCategoria(Categoria categoria) throws IllegalArgumentException {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser vazio.");
        }
        this.categoria = categoria;
    }

    /**
     * Define a duração da mídia. A unidade desta duração é interpretada pela subclasse (Filme=minutos, Musica=segundos, Livro=páginas).
     * @param duracao: O valor da duração.
     * @throws IllegalArgumentException Se a duração for menor ou igual a zero.
     */
    public void setDuracao(int duracao) throws IllegalArgumentException {
        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração não pode ser menor ou igual a zero.");
        }
        this.duracao = duracao;
    }
}