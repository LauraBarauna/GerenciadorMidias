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
     * Construtor da Tela de Idioma. Inicializa as sub-telas (cadastro e listar), configura o CardLayout,
     * preenche o ComboBox de ações e anexa o Listener para troca de tela.
     * @param acoes: A lista de nomes de ações (Strings) a serem exibidas no ComboBox.
     * @param controller: A instância do IdiomaController a ser passada para as sub-telas.
     */

    public TelaIdioma(List<String> acoes, IdiomaController controller) {
        this.cadastro = new TelaCadastroIdioma(controller);
        this.listar = new TelaListarIdioma(controller);
        adicionarLayout();
        adicionarAcoesIdioma(acoes);
        trocarTela();
    }
    
    /**
     * Configura o jPanelAcoesIdioma para usar o CardLayout.
     */

    private void adicionarLayout() {
        this.jPanelAcoesIdioma.setLayout(new CardLayout());
    }
    
    /**
     * Preenche o JComboBox acoesIdioma com as ações disponíveis.
     * @param acoes: A lista de Strings com os nomes das ações.
     */

    private void adicionarAcoesIdioma(List<String> acoes) {
        for (String a : acoes) {
            this.acoesIdioma.addItem(a);
        }
    }
    
    /**
     * Configura o ActionListener para o JComboBox de ações. Quando o usuário seleciona uma ação, este método remove o painel anterior,
     * adiciona a sub-tela correspondente (cadastro ou listar), e força a atualização da GUI.
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
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
