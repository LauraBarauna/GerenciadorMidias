package main.model.midias;

public class Categoria {
    private String categoria;

    public Categoria(String categoria) {
        setCategoria(categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws IllegalArgumentException {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazia ou nula.");
        }
        this.categoria = categoria;
    }

    @Override
    public String toString() {
       return getCategoria();
    }
}
