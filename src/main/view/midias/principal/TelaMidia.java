package main.view.midias.principal;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.view.midias.cadastro.midia.TelaCadastroMidia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaMidia {

    private JPanel jPanelPrincipal;
    private JComboBox<String> acoesMidia;
    private JPanel jPanelAcaoMidia;
    private TelaCadastroMidia cadastro;

    /**
     * Construtor da tela principal de Mídia. Inicializa a tela de cadastro e recebe todos os controllers necessários para a cadeia de sub-telas.
     * @param acoes: A lista de strings que representa as ações a serem exibidas no ComboBox.
     * @param controller: Controller de Categorias.
     * @param idiomaController: Controller de Idiomas.
     * @param pessoaController: Controller de Pessoas (Autores/Artistas).
     * @param midiaController: Controller de Mídia.
     */
    public TelaMidia(List<String> acoes, CategoriaController controller, IdiomaController idiomaController,
                     PessoaController pessoaController, MidiaController midiaController) {
        this.cadastro = new TelaCadastroMidia(controller, idiomaController, pessoaController, midiaController);
        carregarComboBoxMidias(acoes);
        adicionarLayouts();
        atualizarPainel();
    }

    /**
     * Configura o jPanelAcaoMidia para usar um CardLayout, permitindo a troca de painéis.
     */
    public void adicionarLayouts() {
        this.jPanelAcaoMidia.setLayout(new CardLayout());
    }

    /**
     * Retorna o painel principal desta tela.
     * @return O JPanel que contém a interface de gerenciamento de mídias.
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    /**
     * Adiciona as ações fornecidas (como strings) ao JComboBox de seleção.
     * @param acoes: A lista de strings com os nomes das ações.
     */
    private void carregarComboBoxMidias(List<String> acoes) {
        for (String a : acoes) {
            if (acoesMidia == null) {
                break;
            }
            acoesMidia.addItem(a);
        }
    }

    /**
     * Configura o ActionListener para o ComboBox de ações. Quando uma ação é selecionada, o painel é atualizado para exibir a tela correspondente (atualmente apenas "ADICIONAR").
     */
    private void atualizarPainel() {
        String acaoSelecionada = acoesMidia.getSelectedItem().toString().toUpperCase();
        acoesMidia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acaoAtual = acoesMidia.getSelectedItem().toString().toUpperCase();
                switch (acaoAtual) {
                    case "ADICIONAR":
                        cadastro.telaMidia();
                        jPanelAcaoMidia.removeAll();
                        jPanelAcaoMidia.add(cadastro.getjPanelPrincipal());
                        jPanelAcaoMidia.revalidate();
                        jPanelAcaoMidia.repaint();
                        break;
                }
            }
        });


    }

    /**
     * Retorna a instância da tela de cadastro de mídia.
     * @return O elaCadastroMidia instanciado.
     */
    public TelaCadastroMidia getCadastro() {
        return cadastro;
    }
}