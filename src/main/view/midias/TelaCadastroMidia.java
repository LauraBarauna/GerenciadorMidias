package main.view.midias;

import javax.swing.*;

/**
 * Representa a tela de cadastro de mídias no sistema.
 *
 * Essa tela permite que o usuário insira as informações de uma nova mídia,
 * como o título, o caminho do arquivo, e a categoria selecionada.
 *
 * Observação: esta classe contém apenas os componentes visuais.
 * A lógica de interação (como o clique no botão "Adicionar") deve ser feita
 * em um controlador separado ou dentro da classe principal que utiliza essa view.
 */
public class TelaCadastroMidia {

    /** Painel principal que contém todos os componentes da tela */
    private JPanel jPanelPrincipal;

    /** Rótulo (label) que identifica o campo de título da mídia */
    private JLabel titulo;

    /** Campo de texto para o usuário digitar o título da mídia */
    private JTextField textFieldTitulo;

    /** Rótulo (label) que identifica o campo de caminho do arquivo */
    private JLabel caminho;

    /** Campo de texto para o usuário digitar ou mostrar o caminho do arquivo da mídia */
    private JTextField textFieldCaminho;

    /** Botão usado para abrir o seletor de arquivo e buscar a mídia no computador */
    private JButton btnBuscar;

    /** Rótulo (label) indicando o campo de categoria da mídia */
    private JLabel categoria;

    /** ComboBox para seleção da categoria (ex: Filme, Série, Livro, etc.) */
    private JComboBox comboBoxCategoria;

    /** Botão usado para confirmar o cadastro da nova mídia */
    private JButton btnAdicionar;

    /**
     * Retorna o painel principal da tela de cadastro de mídia.
     *
     * Esse método é necessário para que o painel possa ser adicionado
     * a outros layouts ou janelas da aplicação (como em um CardLayout
     * dentro da TelaMidia).
     *
     * @return o painel principal da tela
     */
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}