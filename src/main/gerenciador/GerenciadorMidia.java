package main.gerenciador;
// Define o pacote onde esta classe está localizada.
// Isso ajuda a organizar o código por módulos, neste caso "gerenciador" (responsável pela lógica de gerenciamento).

import main.model.midias.Midia;
// Importa a classe "Midia", que representa o objeto que será gerenciado (por exemplo, filmes, músicas, etc.)

import java.util.ArrayList;
import java.util.List;
// Importa as classes necessárias para trabalhar com listas dinâmicas (coleções).

public class GerenciadorMidia {
    // A classe "GerenciadorMidia" é responsável por armazenar e manipular várias mídias.

    private List<Midia> midias;
    // Lista (coleção) onde serão guardadas todas as mídias cadastradas no sistema.
    // Usa o tipo genérico <Midia> para garantir que só objetos desse tipo sejam adicionados.

    public GerenciadorMidia() {
        this.midias = new ArrayList<>();
        // Construtor: toda vez que um GerenciadorMidia for criado, ele inicia com uma lista vazia de mídias.
    }

    public void incluirMidia(Midia midia) {
        this.midias.add(midia);
        // Método público para adicionar uma nova mídia à lista.
        // Recebe um objeto do tipo Midia como parâmetro e o adiciona à coleção.
    }
}