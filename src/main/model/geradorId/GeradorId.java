package main.model.geradorId;
// Pacote onde a classe está. "model" geralmente guarda classes de dados ou utilitários do sistema.

public class GeradorId {
    private int id;
    // Atributo privado que guarda o valor atual do identificador (ID).
    // Ele vai sendo incrementado toda vez que alguém pede um novo ID.

    public GeradorId() {
        this.id = 0;
        // Construtor: começa com o valor 0.
        // Ou seja, o primeiro ID gerado será 1 (pois é incrementado depois).
    }

    public int getId() {
        return id += 1;
        // Cada vez que esse método é chamado, ele soma +1 ao valor atua e retorna o novo número.
        // Exemplo: se id = 0, retorna 1; se chamar de novo, retorna 2, e assim por diante.
    }

    public void setId(int id) {
        this.id = id;
        // Permite alterar o contador manualmente.
        // (Em sistemas reais, às vezes é usado para restaurar o último ID salvo em arquivo ou banco de dados.)
    }
}