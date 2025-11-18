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
     * Construtor da Tela de Categoria. Inicializa as sub-telas (cadastro e listar), configura o CardLayout,
     * preenche o ComboBox de ações e anexa o Listener para troca de tela.
     * @param acoes: A lista de nomes de ações (Strings) a serem exibidas no ComboBox.
     * @param controller: A instância do CategoriaController a ser passada para as sub-telas.
     */

    public TelaCategoria(List<String> acoes, CategoriaController controller) {
        this.cadastro = new TelaCadastroCategoria(controller);
        this.listarCategoria = new TelaListarCategoria(controller);

        adicionarLayout();
        adicionarAcoes(acoes);
        trocarTela();
    }
    
    /**
     * Configura o jPanelAcaoCategoria para usar o CardLayout.
     */

    private void adicionarLayout() {
        this.jPanelAcaoCategoria.setLayout(new CardLayout());
    }
    
    /**
     * Preenche o JComboBox acoesCategoria com as ações disponíveis. Inclui uma verificação de segurança para o JComboBox não ser nulo.
     * @param acoes: A lista de Strings com os nomes das ações.
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
     * Configura o ActionListener para o JComboBox de ações. Quando o usuário seleciona uma ação, este método troca o painel exibido usando o CardLayout.
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
                        listarCategoria.atualizarLista(); // recarrega categorias
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
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
