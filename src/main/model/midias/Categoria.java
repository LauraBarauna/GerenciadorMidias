package main.model.midias;
// Pacote onde a classe está — dentro de "model.midias", ou seja, parte do modelo relacionado às mídias.

public class Categoria {
    private String categoria;
    // Atributo que armazena o nome da categoria (por exemplo, "Aventura", "Romance", "Terror"...)

    //==============================
    // CONSTRUTOR
    //==============================
    public Categoria(String categoria) {
        setCategoria(categoria);
        // O construtor já usa o setter — isso é bom porque reutiliza a validação do método setCategoria().
    }

    //==============================
    // GETTER
    //==============================
    public String getCategoria() {
        return categoria;
    }

    //==============================
    // SETTER (com validação)
    //==============================
    public void setCategoria(String categoria) throws IllegalArgumentException {
        // Garante que o nome da categoria não é nulo nem vazio.
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazia ou nula.");
        }
        this.categoria = categoria;
    }

    //==============================
    // SOBRESCRITA DO MÉTODO toString()
    //==============================
    @Override
    public String toString() {
        return getCategoria();
        // Ao converter uma Categoria para texto (por exemplo, em System.out.println(categoria)), ela exibirá apenas o nome — e não algo como "Categoria@1a2b3c".
    }
}