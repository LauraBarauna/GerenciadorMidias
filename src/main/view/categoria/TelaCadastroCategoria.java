package main.view.categoria;

import javax.swing.*;

/**
 * Representa a tela de cadastro de categorias no sistema.
 *
 * Essa classe define os componentes visuais da interface Swing
 * usados para cadastrar uma nova categoria, como nome e tipo de mídia.
 *
 * Observação: esta classe geralmente é utilizada junto a um controller,
 * que tratará os eventos e lógica de negócio.
 */
public class TelaCadastroCategoria {

    // --- Componentes da interface gráfica ---

    /** ComboBox para seleção do tipo de mídia (ex: Filme, Livro, etc.) */
    private JComboBox comboBoxTipo;

    /** Painel principal da tela que contém todos os componentes */
    private JPanel jPanelPrincipal;

    /** Campo de texto para digitar o nome da categoria */
    private JTextField textFieldNome;

    /** Botão usado para adicionar uma nova categoria */
    private JButton btnAdicionar;

    /** Rótulo (label) indicando o campo de nome da categoria */
    private JLabel nomeCategoria;

    /** Rótulo (label) indicando o tipo de mídia selecionado */
    private JLabel tipoMidia;

    /**
     * Retorna o painel principal da tela.
     *
     * Esse método é útil para quando a tela precisa ser adicionada
     * a um JFrame ou exibida dentro de outro container.
     *
     * @return o painel principal da tela de cadastro de categoria.
     */
    public JPanel getjPanelPrincipal() {

        return jPanelPrincipal;
    }
}
