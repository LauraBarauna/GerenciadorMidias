package main.excecoes.midia;

/**
 * @author Laura Barauna e Yasmin Darlen Schneider
 */

public class MidiaNaoEncontradaException extends MidiaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor para exceção de Mídia Não Encontrada baseada no ID.
	 * @param id: O identificador único (ID) da Mídia que foi procurada e não existe.
	 */

	public MidiaNaoEncontradaException(int id) {
        super("Midia com id " + id + " não foi  encontrada na lista.");
    }
	
	/**
     * Construtor para exceção de Mídia Não Encontrada baseada na Categoria.
     * @param categoria: O nome da Categoria da Mídia que foi procurada e não existe.
     */

    public MidiaNaoEncontradaException(String categoria) {
        super("Midia com categoria " + categoria + " não foi  encontrada na lista.");
    }
    
    /**
     * Construtor para exceção de Mídia Não Encontrada baseada no Título. O parâmetro booleano c é usado apenas para diferenciar a assinatura do
     * construtor de MidiaNaoEncontradaException(String categoria).
     * @param titulo: O Título da Mídia que foi procurada e não existe.
     * @param c: Um parâmetro booleano, não utilizado na mensagem para sobrecarga.
     */

    public MidiaNaoEncontradaException(String titulo, boolean c) {
        super("Midia com título " + titulo + " não foi  encontrada na lista.");
    }
}
