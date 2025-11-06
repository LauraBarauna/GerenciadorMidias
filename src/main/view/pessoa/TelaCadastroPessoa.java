package main.view.pessoa;

import javax.swing.*;

/**
 * Classe responsável pela tela de cadastro de pessoas.
 *
 * Essa tela permite que o usuário insira o nome de uma pessoa e a adicione
 * ao sistema, por meio de um botão "Adicionar".
 *
 * Observação: esta classe contém apenas os componentes visuais (View).
 * A lógica de ação (ex: o que acontece ao clicar em "Adicionar") deve ser
 * implementada em uma classe controladora ou na tela principal.
 */
public class TelaCadastroPessoa {

    /** Campo de texto onde o usuário digita o nome da pessoa */
    private JTextField textFieldNome;

    /** Painel principal que contém todos os componentes da tela */
    private JPanel jPanelPrincipal;

    /** Botão usado para confirmar e adicionar a nova pessoa */
    private JButton btnAdicionar;

    /** Rótulo (label) que identifica o campo de nome */
    private JLabel nome;

    /**
     * Retorna o painel principal da tela de cadastro de pessoa.
     *
     * Esse método é útil para integrar essa tela em outro painel
     * (por exemplo, dentro de um CardLayout em uma tela maior).
     *
     * @return o painel principal da tela de cadastro de pessoa
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}