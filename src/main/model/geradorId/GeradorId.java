package main.model.geradorId;

/**
 * @author Laura
 */
public class GeradorId {
    
    private int id;

    /**
     * Construtor da classe GeradorId. Inicializa o contador de ID com o valor zero (0).
     */
    public GeradorId() {
        this.id = 0;
    }

    /**
     * Retorna o próximo ID disponível e o incrementa para a próxima chamada. Este método é a principal funcionalidade da classe, sendo responsável por garantir que IDs únicos sejam fornecidos sequencialmente.
     * @return O próximo ID sequencial disponível será o valor do ID atual + 1.
     */
    public int getId() {
        return id += 1;
    }

    /**
     * Permite redefinir o valor base do contador de ID. Este método é útil para sincronizar o GeradorId com o maior ID encontrado na persistência (durante a desserialização de dados).
     * @param id: O novo valor base do ID. O próximo ID gerado será id + 1.
     */
    public void setId(int id) {
        this.id = id;
    }
}