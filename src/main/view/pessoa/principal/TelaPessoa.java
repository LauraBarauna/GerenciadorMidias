package main.view.pessoa.principal;

import main.controller.PessoaController;
import main.gerenciador.GerenciadorPessoa;
import main.view.pessoa.cadastro.TelaCadastroPessoa;
import main.view.pessoa.listar.TelaListarPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPessoa {
    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesPessoas;
    private JPanel jPanelAcoesPessoa;

    private TelaCadastroPessoa cadastro;
    private TelaListarPessoa listar;
    
    /**
     * Construtor da tela principal de Pessoa. Inicializa as sub-telas, configura o layout do painel de ação e preenche o ComboBox de ações.
     * @param acoes: A lista de strings que representa as ações a serem exibidas no ComboBox (e.g., ["ADICIONAR", "LISTAR"]).
     * @param controller: A instância do {@code PessoaController} compartilhada entre todas as sub-telas.
     */

    public TelaPessoa(List<String> acoes, PessoaController controller) {
        this.cadastro = new TelaCadastroPessoa(controller);
        this.listar = new TelaListarPessoa(controller);
        adicionarLayout();
        adicionarAcoes(acoes);
        trocarTela();
    }
    
    /**
     * Configura o ActionListener para o ComboBox de ações. Este método é responsável por limpar o painel de ação e carregar a sub-tela (Cadastro ou Listagem) correspondente à ação selecionada.
     */

    private void trocarTela() {
        this.acoesPessoas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String acao = acoesPessoas.getSelectedItem().toString().toUpperCase();
                switch (acao) {
                    case "ADICIONAR":
                        jPanelAcoesPessoa.removeAll();
                        jPanelAcoesPessoa.add(cadastro.getjPanelPrincipal());
                        jPanelAcoesPessoa.revalidate();
                        jPanelAcoesPessoa.repaint();
                        break;
                    case "LISTAR":
                        listar.atualizarLista();
                        jPanelAcoesPessoa.removeAll();
                        jPanelAcoesPessoa.add(listar.getjPanelPrincipal());
                        jPanelAcoesPessoa.revalidate();
                        jPanelAcoesPessoa.repaint();
                        break;
                }
            }
        });
    }

    /**
     * Configura o jPanelAcoesPessoa para usar um CardLayout, o que é ideal para gerenciar múltiplos painéis em um único espaço.
     */
    
    private void adicionarLayout() {
        this.jPanelAcoesPessoa.setLayout(new CardLayout());
    }
    
    /**
     * Adiciona as ações fornecidas (como strings) ao JComboBox de seleção.
     * @param acoes: A lista de strings com os nomes das ações.
     */

    private void adicionarAcoes(List<String> acoes) {
        for (String a : acoes) {
            acoesPessoas.addItem(a);
        }
    }
    
    /**
     * Retorna o painel principal desta tela.
     * @return O JPanel que contém a interface de gerenciamento de pessoas.
     */

    public JPanel getJPanelPrincipal() {
        return jPanelPrincipal;
    }
}
