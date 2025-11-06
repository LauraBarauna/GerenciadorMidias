package main.view.categoria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Classe responsável por exibir a tela principal de gerenciamento de categorias.
 *
 * Essa tela possui um JComboBox para selecionar ações (ex: "Adicionar", "Editar", "Remover")
 * e um painel dinâmico (CardLayout) que troca o conteúdo de acordo com a ação escolhida.
 */
public class TelaCategoria {
    // --- Componentes da interface ---

    /** Painel principal que contém todos os elementos da tela */
    private JPanel jPanelPrincipal;

    /** ComboBox que lista as ações disponíveis (ex: "Adicionar", "Remover") */
    private JComboBox<String> acoesCategoria;

    /** Painel onde serão trocadas as telas internas de acordo com a ação */
    private JPanel jPanelAcaoCategoria;

    /** Tela de cadastro de categoria (usada quando o usuário seleciona "Adicionar") */
    private TelaCadastroCategoria cadastro;

    /**
     * Construtor da tela de categoria.
     *
     * @param acoes Lista de ações que serão exibidas no JComboBox (ex: ["Adicionar", "Remover"])
     */
    public TelaCategoria(List<String> acoes) {
        this.cadastro = new TelaCadastroCategoria(); // cria a tela de cadastro
        adicionarLayout();  // define o tipo de layout (CardLayout)
        adicionarAcoes(acoes); // adiciona as ações no comboBox
        trocarTela();  // exibe o painel correspondente à ação atual
    }

    /**
     * Define o layout do painel de ações como CardLayout.
     *
     * Esse layout permite "trocar" painéis dentro do mesmo espaço,
     * simulando telas diferentes dentro da mesma janela.
     */
    private void adicionarLayout() {
        this.jPanelAcaoCategoria.setLayout(new CardLayout());
    }

    /**
     * Carrega as ações passadas na lista dentro do JComboBox.
     *
     * @param acoes Lista de ações disponíveis.
     */
    private void adicionarAcoes(List<String> acoes) {
        for (String a : acoes) {
            // Garante que o ComboBox foi inicializado antes de usar
            if (acoesCategoria == null) {
                break;
            }
            this.acoesCategoria.addItem(a);
        }
    }

    /**
     * Atualiza o painel interno de acordo com a ação selecionada no ComboBox.
     *
     * No momento, apenas a opção "ADICIONAR" está implementada.
     */
    private void trocarTela() {
        // Obtém a ação atual selecionada no ComboBox
        String acao = this.acoesCategoria.getSelectedItem().toString().toUpperCase();

        switch (acao) {
            case "ADICIONAR":
                // Remove o conteúdo atual e adiciona a tela de cadastro
                jPanelAcaoCategoria.removeAll();
                jPanelAcaoCategoria.add(cadastro.getjPanelPrincipal());
                jPanelAcaoCategoria.revalidate(); // atualiza o layout
                jPanelAcaoCategoria.repaint();    // força a repintura visual
                break;
        }
    }

    /**
     * Retorna o painel principal da tela de categoria.
     *
     * @return o JPanel principal da tela.
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}