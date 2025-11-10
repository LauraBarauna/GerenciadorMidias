package main.model.midias;

import java.io.Serializable;

public abstract class Midia implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int id;
    private String local;
    private double tamanhoEmDisco;
    protected String titulo;
    protected int duracao;
    protected Categoria categoria;

    public Midia(int id, String local, double tamanhoEmDisco, String titulo, int duracao, Categoria categoria) {
        setId(id);
        setLocal(local);
        setTamanhoEmDisco(tamanhoEmDisco);
        setTitulo(titulo);
        setDuracao(duracao);
        setCategoria(categoria);
    }

    public String exibirAtributos() {
        String atributos = "";
        atributos += "Id: " + getId() + "\n" +
                    "Local do arquivo: " + getLocal() + "\n" +
                    "Tamanho do arquivo: " + getTamanhoEmDisco() + "\n" +
                    "Título: " + getTitulo() + "\n" +
                    "Duração: " + getDuracao() + "\n" +
                    "Categoria: " + getCategoria() + "\n";
        return atributos;
    }

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

    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id não pode ser igual ou menor que 0.");
        }
        this.id = id;
    }

    public void setLocal(String local) {
        if(local == null || local.isBlank()) {
            throw new IllegalArgumentException("Local do arquivo não pode ser vazio.");
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
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public void setCategoria(Categoria categoria) throws IllegalArgumentException {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser vazio.");
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
