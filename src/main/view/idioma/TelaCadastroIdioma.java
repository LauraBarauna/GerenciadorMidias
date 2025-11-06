package main.view.idioma;

import javax.swing.*;

/**
 * Classe responsável pela tela de cadastro de Idioma.
 *
 * Essa tela deve permitir ao usuário digitar o nome de um idioma
 * (como "Português", "Inglês", "Espanhol", etc.) e adicioná-lo
 * através de um botão "Adicionar".
 *
 * Observação: Essa classe contém apenas os componentes gráficos,
 * e provavelmente será usada dentro de outra tela (ex: TelaIdioma)
 * que controla a lógica de troca e ações.
 */
public class TelaCadastroIdioma {

    /** Campo de texto onde o usuário digita o nome do idioma */
    private JTextField textFieldIdioma;

    /** Painel principal que contém todos os componentes da tela */
    private JPanel jPanelPrincipal;

    /** Botão para confirmar a adição do idioma */
    private JButton btnAdicionar;

    /** Rótulo (label) que indica o campo de idioma */
    private JLabel idioma;

    /**
     * Getter público para o painel principal.
     *
     * Esse método é importante para permitir que outras telas,
     * como TelaIdioma ou TelaPrincipal, adicionem esse painel
     * ao layout principal da aplicação.
     *
     * @return o painel principal desta tela
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}