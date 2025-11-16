package main.view.idioma.principal;

import main.controller.IdiomaController;
import main.gerenciador.GerenciadorIdioma;
import main.view.idioma.cadastro.TelaCadastroIdioma;
import main.view.idioma.listar.TelaListarIdioma;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaIdioma {

    private JComboBox<String> acoesIdioma;
    private JPanel jPanelPrincipal;
    private JPanel jPanelAcoesIdioma;
    private TelaCadastroIdioma cadastro;
    private TelaListarIdioma listar;

    /**
     * Construtor da tela principal de Idioma. Inicializa as sub-telas, configura o layout do painel de ação e preenche o ComboBox de ações.
     * @param acoes: A lista de strings que representa as ações a serem exibidas no ComboBox (e.g., ["ADICIONAR", "LISTAR"]).
     * @param controller: A instância do IdiomaController compartilhada entre todas as sub-telas.
     */
    public TelaIdioma(List<String> acoes, IdiomaController controller) {
        this.cadastro = new TelaCadastroIdioma(controller);
        this.listar = new TelaListarIdioma(controller);
        adicionarLayout();
        adicionarAcoesIdioma(acoes);
        trocarTela();
    }

    /**
     * Configura o jPanelAcoesIdioma para usar um CardLayout, que é ideal para gerenciar múltiplos painéis em um único espaço.
     */
    private void adicionarLayout() {
        this.jPanelAcoesIdioma.setLayout(new CardLayout());
    }

    /**
     * Adiciona as ações fornecidas (como strings) ao JComboBox de seleção.
     * @param acoes: A lista de strings com os nomes das ações.
     */
    private void adicionarAcoesIdioma(List<String> acoes) {
        for (String a : acoes) {
            this.acoesIdioma.addItem(a);
        }
    }

    /**
     * Configura o {@code ActionListener} para o ComboBox de ações. Este método é responsável por limpar o painel de ação e carregar a sub-tela (Cadastro ou Listagem) correspondente à ação selecionada.
     */
    private void trocarTela() {
        this.acoesIdioma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String acao = acoesIdioma.getSelectedItem().toString().toUpperCase();

                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcoesIdioma.removeAll();
                        jPanelAcoesIdioma.add(cadastro.getjPanelPrincipal());
                        jPanelAcoesIdioma.revalidate();
                        jPanelAcoesIdioma.repaint();
                        break;
                    case "LISTAR":
                        listar.atualizarLista();
                        jPanelAcoesIdioma.removeAll();
                        jPanelAcoesIdioma.add(listar.getjPanelPrincipal());
                        jPanelAcoesIdioma.revalidate();
                        jPanelAcoesIdioma.repaint();
                    break;
                }

            }
        });
    }

    /**
     * Retorna o painel principal desta tela.
     * @return O JPanel que contém a interface de gerenciamento de idiomas.
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}