package main.view.midias.listar.detalhes.filme;

import main.controller.IdiomaController;
import main.model.idioma.Idioma;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;

import javax.swing.*;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaDetalhesFilme {
    private JPanel jPanelPrincipal;
    private JComboBox<String> idiomas;

    private IdiomaController idiomaController;
    
    /**
     * Construtor do painel de detalhes de Filme. Recebe a dependência do Controller necessária para buscar a lista de idiomas.
     * @param idiomaController: A instância do IdiomaController para buscar a lista de idiomas.
     */

    public TelaDetalhesFilme(IdiomaController idiomaController) {
        this.idiomaController = idiomaController;
    }
    
    /**
     * Carrega a lista de Idiomas cadastrados no ComboBox e pré-seleciona o idioma atual do filme. Esta é a função de inicialização de dados 
     * da View específica de Filme.     *
     * @param midia A instância de  Midia, que deve ser um Filme, cujos detalhes serão exibidos.
     * @throws ClassCastException Se a {@code midia} fornecida não for uma instância de Filme.
     */

    public void atualizarLista(Midia midia) {
        List<String> idiomas = idiomaController.listarNomeIdioma();

        Filme filme = (Filme) midia;

        this.idiomas.setSelectedItem(filme.getIdioma().exibirIdioma());
        listarIdiomas(idiomas);
    }
    
    /**
     * Preenche o JComboBox idiomas com a lista de nomes de idiomas fornecida.
     * @param idiomas A lista de nomes de idiomas (String) obtida do Controller.
     */

    private void listarIdiomas(List<String> idiomas) {
        this.idiomas.removeAll();
        for (String idioma : idiomas) {
            this.idiomas.addItem(idioma);
        }
    }
    
    /**
     * Retorna o painel principal desta tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
    
    /**
     * Retorna o objeto {@code Idioma} baseado no item selecionado no ComboBox. Este método é chamado pela TelaDetalhesMidia quando o
     * usuário clica em "Salvar" para obter o novo valor do idioma e incorporá-lo na atualização do objeto Filme.
     * @return Uma nova instância de Idioma com o nome selecionado.
     */

    public Idioma getIdioma() {
        return new Idioma((String) this.idiomas.getSelectedItem());
    }
}
