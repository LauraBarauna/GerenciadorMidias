package main.model.midias;

import main.excecoes.NomeInvalido;

public class Categoria {
    private String categoria;

    public Categoria(String categoria) {
        setCategoria(categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws NomeInvalido {
        if (categoria == null || categoria.isBlank()) {
            throw new NomeInvalido("Nome da categoria não pode ser vazia ou nula.");
        }
        this.categoria = categoria;
    }
}
