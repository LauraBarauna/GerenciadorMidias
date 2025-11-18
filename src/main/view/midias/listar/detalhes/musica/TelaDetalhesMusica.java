package main.view.midias.listar.detalhes.musica;

import main.controller.PessoaController;
import main.model.midias.Midia;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;

import javax.swing.*;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaDetalhesMusica {
    private JPanel jPanelPrincipal;
    private JComboBox<String> artistas;

    private PessoaController pessoaController;
    
    /**
     * Construtor do painel de detalhes de Música. Recebe a dependência do Controller necessária para buscar dados de Artistas.
     * @param pessoaController: A instância do PessoaController para acesso à lista de Pessoas.
     */
    
    public TelaDetalhesMusica(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
    }
    
    /**
     * Carrega a lista de Pessoas cadastradas e configura o ComboBox. Garante que o artista atual da música seja pré-selecionado para visualização/edição.
     * Esta é a função de inicialização de dados da View específica de Música.
     * @param midia: A instância de Midia, que deve ser uma Musica, cujos detalhes serão exibidos.
     */

    public void atualizarLista(Midia midia) {
        List<String> artistas = this.pessoaController.listarPessoas();

        Musica musica = (Musica) midia;
        listarArtistas(artistas, musica);
    }
    
    /**
     * Preenche o JComboBox artistas com a lista de nomes e define o artista atualmente associado à Música como o item selecionado.
     * @param artistas: A lista de nomes de artistas (String) obtida do Controller.
     * @param musica: A instância de Musica sendo detalhada, usada para obter o nome do artista atual.
     */

    private void listarArtistas(List<String> artistas, Musica musica) {
        this.artistas.removeAllItems();

        for (String artista : artistas) {
            this.artistas.addItem(artista);
        }
        this.artistas.setSelectedItem(musica.getArtista().getNome());
    }
    
    /**
     * Retorna o objeto Pessoa (Artista) baseado no nome atualmente selecionado no ComboBox.
     * Este método é crucial para a atualização. A tela principal de detalhes de Mídia chama este
     * método para obter o novo valor do artista, se alterado, e incorporá-lo na atualização final.
     * @return Uma nova instância de Pessoa contendo o nome do artista selecionado.
     */

    public Pessoa getArtista() {
        return new Pessoa(artistas.getSelectedItem().toString());
    }
    
    /**
     * Retorna o painel principal (container) desta sub-tela.
     * @return O JPanel principal da interface, pronto para ser incorporado.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
