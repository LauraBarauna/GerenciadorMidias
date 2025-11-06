package main.model.idioma;
// Pacote onde a classe está — dentro de "model", o que indica que ela representa um objeto de domínio (um dado do sistema): neste caso, um idioma.

public class Idioma {
    private String idioma;
    // Atributo que armazena o nome do idioma (ex: "Português", "Inglês", "Espanhol").

    //==============================
    // CONSTRUTOR
    //==============================
    public Idioma(String idioma) {
        setIdioma(idioma);
        // Ao criar o objeto, o construtor já chama o setter para validar o valor.
        // Isso garante que nenhum objeto "Idioma" será criado com texto vazio ou nulo.
    }

    //==============================
    // GETTER
    //==============================
    public String getIdioma() {
        return idioma;
        // Retorna o valor atual do idioma armazenado.
    }

    //==============================
    // SETTER com validação
    //==============================
    public void setIdioma(String idioma) throws IllegalArgumentException {
        // Antes de definir o atributo, o método valida se o valor é aceitável:
        if (idioma == null || idioma.isBlank()) {
            // "isBlank()" verifica se a String está vazia ou só contém espaços.
            throw new IllegalArgumentException("Idioma não pode ser vazio ou nulo.");
            // Se o valor for inválido, é lançada uma exceção informando o problema.
        }
        this.idioma = idioma;
    }

    //==============================
    // MÉTODO DE EXIBIÇÃO
    //==============================
    public String exibirIdioma() {
        return getIdioma();
        // Apenas retorna o idioma (poderia ser útil se futuramente quiser formatar a saída, ex: deixar em maiúsculas, adicionar prefixos, etc.)
    }
}