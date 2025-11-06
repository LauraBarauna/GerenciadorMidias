package main.model.midias;
// Define o pacote onde essa classe faz parte — dentro do modelo de mídias.

public abstract class Midia {
    //==============================
    // ATRIBUTOS
    //==============================
    protected int id;               // Identificador único da mídia (geralmente gerado por um GeradorId)
    private String local;           // Caminho/localização do arquivo da mídia (ex: "C:/Filmes/Matrix.mp4")
    private double tamanhoEmDisco;  // Tamanho do arquivo em MB ou GB
    protected String titulo;        // Título da mídia
    protected int duracao;          // Duração em minutos, páginas, etc. (depende do tipo da mídia)
    protected Categoria categoria;  // Categoria associada à mídia (como "Ação", "Drama", etc.)

    //==============================
    // CONSTRUTOR
    //==============================
    public Midia(int id, String local, double tamanhoEmDisco,
                 String titulo, int duracao, Categoria categoria) {
        setId(id);
        setLocal(local);
        setTamanhoEmDisco(tamanhoEmDisco);
        setTitulo(titulo);
        setDuracao(duracao);
        setCategoria(categoria);
        // Usa os setters dentro do construtor — excelente prática, porque garante que
        // toda validação seja aplicada na criação do objeto.
    }

    //==============================
    // MÉTODO PADRÃO DE EXIBIÇÃO
    //==============================
    public String exibirAtributos() {
        // Método base que exibe os atributos comuns a todas as mídias.
        String atributos = "";
        atributos += "Id: " + getId() + "\n" +
                "Local do arquivo: " + getLocal() + "\n" +
                "Tamanho do arquivo: " + getTamanhoEmDisco() + "\n" +
                "Título: " + getTitulo() + "\n" +
                "Duração: " + getDuracao() + "\n" +
                "Categoria: " + getCategoria() + "\n";
        return atributos;
    }

    //==============================
    // GETTERS
    //==============================
    public int getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public double getTamanhoEmDisco() {
        return tamanhoEmDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    //==============================
    // SETTERS (com validação)
    //==============================
    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id não pode ser igual ou menor que 0");
        }
        this.id = id;
    }

    public void setLocal(String local) {
        if(local == null || local.isBlank()) {
            throw new IllegalArgumentException("Local do arquivo não pode ser nulo ou vazio.");
        }
        this.local = local;
    }

    public void setTamanhoEmDisco(double tamanhoEmDisco) {
        if (tamanhoEmDisco <= 0) {
            throw new IllegalArgumentException("Tamanho em disco não pode ser menor ou igual a zero.");
        }
        this.tamanhoEmDisco = tamanhoEmDisco;
    }

    public void setTitulo(String titulo)  {
        if (titulo == null  || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio ou nulo");
        }
        this.titulo = titulo;
    }

    public void setCategoria(Categoria categoria) throws IllegalArgumentException {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser nulo");
        }
        this.categoria = categoria;
    }

    public void setDuracao(int duracao) throws IllegalArgumentException {
        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração não pode ser menor ou igual a zero.");
        }
        this.duracao = duracao;
    }
}