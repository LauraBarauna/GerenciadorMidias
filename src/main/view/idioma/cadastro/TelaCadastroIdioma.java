package main.view.idioma.cadastro;

import main.controller.IdiomaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Laura Barauna
 */

public class TelaCadastroIdioma {
    
    private JTextField textFieldIdioma;
    private JPanel jPanelPrincipal;
    private JButton btnAdicionar;
    private JLabel idioma;
    private IdiomaController controller;

    /**
     * Construtor da tela de cadastro de idioma. Inicializa o Controller e configura os ouvintes de eventos (listeners) para o botão de adicionar e para o evento da tecla Enter.
     * @param controller: A instância do IdiomaController a ser usada.
     */
    public TelaCadastroIdioma(IdiomaController controller) {
        this.controller = controller;
        cadastrarIdioma();
        enterIdioma();
    }

    /**
     * Configura um KeyListener para o campo de texto do idioma. Permite que o usuário submeta o formulário (simulando um clique no botão "Adicionar") ao pressionar a tecla ENTER.
     */
    private void enterIdioma() {
        textFieldIdioma.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick();
                }
            }
        });
    }

    /**
     * Configura o ActionListener para o botão "Adicionar". Coleta o nome do idioma, chama o IdiomaController.cadastrarIdioma() e exibe mensagens de sucesso ou de erro (provenientes das exceções de negócio).
     */
    private void cadastrarIdioma() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idioma = textFieldIdioma.getText();
                try {
                    controller.cadastrarIdioma(idioma);
                    JOptionPane.showMessageDialog(null, "Idioma " + idioma + " cadastrado com sucesso!");
                    textFieldIdioma.setText("");
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