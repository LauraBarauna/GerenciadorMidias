package main.view.pessoa.cadastro;

import main.controller.PessoaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Laura Barauna
 */

public class TelaCadastroPessoa {
    private JTextField textFieldNome;
    private JPanel jPanelPrincipal;
    private JButton btnAdicionar;
    private JLabel nome;

    private PessoaController controller;
    
    /**
     * Construtor da tela de cadastro de Pessoa. Inicializa o Controller e configura os ouvintes de eventos (listeners) para o botão de adicionar e para o evento da tecla Enter no campo de texto.
     * @param controller: A instância do PessoaController a ser usada.
     */

    public TelaCadastroPessoa(PessoaController controller) {
        this.controller = controller;
        adicionarPessoa();
        enterPessoa();
    }
    
    /**
     * Configura um KeyListener para o campo de texto do nome. Permite que o usuário submeta o formulário (simulando um clique no botão "Adicionar") ao pressionar a tecla ENTER.
     */

    private void enterPessoa() {
        textFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdicionar.doClick();
                }
            }
        });
    }
    
    /**
     * Configura o ActionListener para o botão "Adicionar". Coleta o nome, chama o PessoaController.cadastrarPessoa() e exibe mensagens de sucesso ou de erro (provenientes das exceções de negócio).
     */

    private void adicionarPessoa() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                try {
                    controller.cadastrarPessoa(nome);
                    JOptionPane.showMessageDialog(null, "Pessoa " + nome + " cadastrada com sucesso.");
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
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
    
    /**
     * Método de inicialização de componentes customizados (gerado automaticamente pelo GUI builder).
     */

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
