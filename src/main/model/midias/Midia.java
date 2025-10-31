package main.model.midias;

import main.excecoes.LocalDoArquivoInvalido;
import main.excecoes.NomeInvalido;

public abstract class Midia {
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
            throw new IllegalArgumentException("Id não pode ser igual ou menor que 0");
        }
        this.id = id;
    }

    public void setLocal(String local) throws LocalDoArquivoInvalido {
        if(local == null || local.isBlank()) {
            throw new LocalDoArquivoInvalido("Local do arquivo não pode ser nulo ou vazio.");
        }
        this.local = local;
    }

    public void setTamanhoEmDisco(double tamanhoEmDisco) {
        if (tamanhoEmDisco <= 0) {
            throw new IllegalArgumentException("Tamanho em disco não pode ser menor ou igual a zero.");
        }
        this.tamanhoEmDisco = tamanhoEmDisco;
    }

    public void setTitulo(String titulo) throws NomeInvalido {
        if (titulo == null  || titulo.isBlank()) {
            throw new NomeInvalido("Título não pode ser vazio ou nulo");
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
