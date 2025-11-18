package main.view.idioma.cadastro;

import main.controller.IdiomaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Laura Barauna
 */

public class TelaCadastroIdioma {
    private JTextField textFieldIdioma;
    private JPanel jPanelPrincipal;
    private JButton btnAdicionar;
    private JLabel idioma;

    private IdiomaController controller;
    
    /**
     * Construtor da tela de cadastro de Idioma. Inicializa o Controller e configura os Listeners para o botão de cadastro
     * e para a tecla ENTER.
     * @param controller: A instância do {@code IdiomaController} a ser usada.
     */

    public TelaCadastroIdioma(IdiomaController controller) {
        this.controller = controller;
        cadastrarIdioma();
        enterIdioma();
    }
    
    /**
     * Configura um KeyListener no campo de texto textFieldIdioma. Permite que o usuário acione o botão "Adicionar" pressionando a tecla ENTER.
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
     * Configura o ActionListener para o botão "Adicionar". Coleta o texto do campo, delega o cadastro ao Controller, e exibe uma mensagem
     * de sucesso ou um erro em caso de exceção.
     */

    private void cadastrarIdioma() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idioma = textFieldIdioma.getText();
                try {
                    controller.cadastrarIdioma(idioma);
                    JOptionPane.showMessageDialog(null, "Idioma " + idioma + " cadastrado com sucesso!");
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
