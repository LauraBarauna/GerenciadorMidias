package main.view.categoria.cadastro;

import main.controller.CategoriaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Laura Barauna
 */

public class TelaCadastroCategoria {

    private CategoriaController controller;

    private JComboBox comboBoxTipo;
    private JPanel jPanelPrincipal;
    private JTextField textFieldNome;
    private JButton btnAdicionar;
    private JLabel nomeCategoria;
    private JLabel tipoMidia;
    
    /**
     * Construtor da tela de cadastro de Categoria. Inicializa o Controller e configura os Listeners para o botão de cadastro
     * e para a tecla ENTER.
     * @param controller: A instância do CategoriaController a ser usada.
     */

    public TelaCadastroCategoria(CategoriaController controller) {
        this.controller = controller;
        adicionarCategoria();
        adicionarCategoriaEnter();
    }
    
    /**
     * Configura um KeyListener no campo de texto textFieldNome. Permite que o usuário acione o botão "Adicionar" pressionando a tecla ENTER.
     */

    public void adicionarCategoriaEnter() {
        textFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick(); // simula o clique no botão
                }
            }
        });
    }
    
    /**
     * Configura o ActionListener para o botão "Adicionar". Realiza a validação básica do nome, coleta os dados (nome e tipo), delega o
     * cadastro ao Controller, e exibe um feedback ao usuário.
     */

    public void adicionarCategoria() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldNome.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "O campo nome deve ser preenchido.");
                }

                try {
                    controller.adicionarCategoria(textFieldNome.getText(), comboBoxTipo.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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
