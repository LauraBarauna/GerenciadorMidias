package main.view.categoria.listar;

import main.controller.CategoriaController;
import main.model.midias.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class TelaListarCategoria {

    private CategoriaController controller;

    private JPanel jPanelPrincipal;
    private JComboBox tipoMidia;
    private JPanel jPanelCategorias;
    private JButton btnRemoverTudo;
    
    /**
     * Construtor da tela de listagem de Categoria. Inicializa o Controller, configura o layout do painel de listagem, e configura os listeners
     * para o filtro de tipo de Mídia e para o botão "Remover Tudo".
     * @param controller: A instância do CategoriaController a ser usada.
     */

    public TelaListarCategoria(CategoriaController controller) {
        this.controller = controller;
        jPanelCategorias.setLayout(new BoxLayout(jPanelCategorias, BoxLayout.Y_AXIS));
        jPanelCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
        pegarTipoMidia();
        removerTudo();

        String tipoSelecionado = (String) tipoMidia.getSelectedItem();
        if (tipoSelecionado != null) {
            listarCategorias(tipoSelecionado);
        }
    }
    
    /**
     * Configura o ActionListener para o botão "Remover Tudo". Obtém o tipo de Mídia selecionado, verifica se há categorias, solicita confirmação
     * e delega a remoção em massa ao Controller.
     */

    public void removerTudo() {
        btnRemoverTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSelecionado = (String) tipoMidia.getSelectedItem();

                List<Categoria> categoria = controller.listarCategorias(tipoSelecionado);

                if (!categoria.isEmpty()) {
                    int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                            "Tem certeza que quer remover tudo?\n" +
                                    "1 - Sim\n" +
                                    "2 - Não\n"
                    ));

                    switch (opcao) {
                        case 1:
                            controller.removerTudo(tipoSelecionado);
                            listarCategorias(tipoSelecionado);
                            break;
                        case 2:
                            listarCategorias(tipoSelecionado);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem nada para remover");
                    listarCategorias(tipoSelecionado);
                }
            }
        });
    }
    
    /**
     * Método público para forçar a atualização da lista, usado tipicamente pela Tela Mestra após retornar para esta View.
     */

    public void atualizarLista() {
        String tipo = (String) tipoMidia.getSelectedItem();
        if (tipo != null) {
            listarCategorias(tipo);
        }
    }
    
    /**
     * Configura o ActionListener para o JComboBox de tipos de Mídia. Sempre que o tipo selecionado muda, a lista de 
     * categorias é recarregada para refletir o novo filtro.
     */

    private void pegarTipoMidia() {
        tipoMidia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = tipoMidia.getSelectedItem().toString();
                listarCategorias(tipo);
                pegarTipoMidia();
            }
        });
    }
    
    /**
     * Renderiza a lista de categorias no {@code jPanelCategorias} para o tipo de Mídia fornecido.
     * Para cada categoria, cria uma linha contendo o nome e um botão "Remover", configurando o Listener para remoção individual.
     * @param tipo: O tipo de Mídia selecionado (ex. "Filme").
     */

    public void listarCategorias(String tipo) {
        List<String> categorias = controller.listarCategoriasString(tipo);

        jPanelCategorias.removeAll();

        if (!categorias.isEmpty()) {
            for (String c : categorias) {
                JPanel linha = new JPanel(new BorderLayout());
                linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
                JLabel nome = new JLabel(c, JLabel.LEFT);

                JButton btnRemover = new JButton("Remover");

                btnRemover.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.removerCategoria(c, tipo);
                        JOptionPane.showMessageDialog(jPanelPrincipal, "Categoria " + c + " removida com sucesso!");
                        listarCategorias(tipo);
                    }
                });

                linha.add(nome);

                linha.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
                linha.add(btnRemover);

                linha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // borda pequena
                jPanelCategorias.add(linha);
            }

            jPanelCategorias.revalidate();
            jPanelCategorias.repaint();
        } else {
            JPanel linha = new JPanel(new BorderLayout());
            JLabel semCategoria = new JLabel(tipo + " não tem nenhuma categoria cadastrada!", JLabel.CENTER);
            linha.add(semCategoria, BorderLayout.CENTER);
            linha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            jPanelCategorias.add(linha);
            jPanelCategorias.revalidate();
            jPanelCategorias.repaint();
        }
    }
    
    /**
     * Retorna o painel principal da tela.
     * @return O JPanel principal da interface.
     */

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }
}
