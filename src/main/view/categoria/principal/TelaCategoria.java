package main.view.categoria.principal;

import main.controller.CategoriaController;
import main.gerenciador.GerenciadorCategoria;
import main.view.categoria.cadastro.TelaCadastroCategoria;
import main.view.categoria.listar.TelaListarCategoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaCategoria {

    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesCategoria;
    private JPanel jPanelAcaoCategoria;
    private TelaCadastroCategoria cadastro;
    private TelaListarCategoria listarCategoria;

    /**
     * Construtor da tela principal de Categoria. Inicializa as sub-telas, configura o layout do painel de ação e preenche o ComboBox de ações.
     * @param acoes: A lista de strings que representa as ações a serem exibidas no ComboBox (e.g., ["ADICIONAR", "LISTAR"]).
     * @param controller: A instância do CategoriaController compartilhada entre todas as sub-telas.
     */
    public TelaCategoria(List<String> acoes, CategoriaController controller) {
        this.cadastro = new TelaCadastroCategoria(controller);
        this.listarCategoria = new TelaListarCategoria(controller);

        adicionarLayout();
        adicionarAcoes(acoes);
        trocarTela();
    }

    /**
     * Configura o jPanelAcaoCategoria para usar um CardLayout, o que é ideal para gerenciar múltiplos painéis em um único espaço.
     */
    private void adicionarLayout() {
        this.jPanelAcaoCategoria.setLayout(new CardLayout());
    }

    /**
     * Adiciona as ações fornecidas (como strings) ao JComboBox de seleção.
     * @param acoes: A lista de strings com os nomes das ações.
     */
    private void adicionarAcoes(List<String> acoes) {
        for (String a : acoes) {
            if (acoesCategoria == null){
                break;
            }
            this.acoesCategoria.addItem(a);
        }
    }

    /**
     * Configura o ActionListener para o ComboBox de ações. Este método é responsável por limpar o painel de ação e carregar a sub-tela (Cadastro ou Listagem) correspondente à ação selecionada.
     */
    private void trocarTela() {
        acoesCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acao = acoesCategoria.getSelectedItem().toString().toUpperCase();
                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcaoCategoria.removeAll();
                        jPanelAcaoCategoria.add(cadastro.getjPanelPrincipal());
                        jPanelAcaoCategoria.revalidate();
                        jPanelAcaoCategoria.repaint();
                        break;
                    case "LISTAR":
                        listarCategoria.atualizarLista(); 
                        jPanelAcaoCategoria.removeAll();
                        jPanelAcaoCategoria.add(listarCategoria.getjPanelPrincipal());
                        jPanelAcaoCategoria.revalidate();
                        jPanelAcaoCategoria.repaint();
                        break;
                }
                trocarTela();
            }
        });
    }

    /**
     * Retorna o painel principal desta tela.
     * @return O JPanel que contém a interface de gerenciamento de categorias.
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}