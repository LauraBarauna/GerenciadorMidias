package main.view.categoria.cadastro;

import main.controller.CategoriaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Representa a tela de interface gráfica (GUI) responsável pelo cadastro de novas Categorias. Esta classe é a Visão (View) no padrão MVC. Ela coleta a entrada do usuário (nome da categoria e tipo de mídia) e delega a lógica de negócio para o CategoriaController.
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
     * Construtor da tela de cadastro de categoria. Inicializa o Controller e configura os ouvintes de eventos (listeners) para o botão de  adicionar e para o evento da tecla Enter.
     * @param controller A instância do CategoriaController a ser usada.
     */
    public TelaCadastroCategoria(CategoriaController controller) {
        this.controller = controller;
        adicionarCategoria();
        adicionarCategoriaEnter();
    }

    /**
     * Configura um KeyListener para o campo de texto do nome da categoria. Permite que o usuário submeta o formulário (simulando um clique no botão "Adicionar") ao pressionar a tecla ENTER.
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
     * Configura o ActionListener para o botão "Adicionar". Esta é a principal lógica de submissão do formulário:
     * Verifica se o campo nome está vazio. Chama o CategoriaController.adicionarCategoria() com os dados da tela. Exibe mensagens de sucesso ou de erro (provenientes das exceções da camada Controller).
     */
    public void adicionarCategoria() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldNome.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "O campo nome deve ser preenchido.");
                    return;
                }

                try {
                    controller.adicionarCategoria(textFieldNome.getText(), comboBoxTipo.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso.");
                    textFieldNome.setText(""); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    /**
     * Retorna o painel principal da tela. É usado pela classe principal da aplicação para exibir esta tela em uma janela ou frame.
     * @return O JPanel principal da interface.
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}