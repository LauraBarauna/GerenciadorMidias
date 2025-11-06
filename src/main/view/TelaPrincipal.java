package main.view;

import main.view.categoria.TelaCategoria;
import main.view.midias.TelaMidia;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Classe principal da interface gráfica do sistema.
 *
 * <p>Responsável por gerenciar a tela principal e exibir as abas
 * (Mídias, Categorias, Idiomas e Pessoa) através de um {@link JTabbedPane}.</p>
 *
 * <p>Ela também se comunica com as telas específicas (como {@link TelaMidia}
 * e {@link TelaCategoria}) para renderizar seus respectivos painéis quando
 * as abas são selecionadas.</p>
 */
public class TelaPrincipal extends JFrame {

    /** Painel principal da tela */
    private JPanel jPanelPrincipal;

    /** Componente que controla as abas do menu */
    private JTabbedPane menu;

    /** Painéis correspondentes a cada aba */
    private JPanel midias;
    private JPanel categorias;
    private JPanel idiomas;
    private JPanel pessoa;
    private JPanel inicio;

    /** Telas específicas para cada tipo de dado */
    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;

    /**
     * Construtor da TelaPrincipal.
     *
     * @param telaMidia     Tela responsável pela gestão de mídias
     * @param telaCategoria Tela responsável pela gestão de categorias
     */
    public TelaPrincipal(TelaMidia telaMidia, TelaCategoria telaCategoria) {
        this.telaMidia = telaMidia;
        this.telaCategoria = telaCategoria;

        configurarTela();    // Configura o JFrame
        adicionarLayouts();  // Define o layout dos painéis
        selecionarMenu();    // Configura o listener das abas
    }

    /**
     * Define o layout de cada painel de aba usando CardLayout,
     * permitindo a troca dinâmica de sub-telas dentro de cada aba.
     */
    public void adicionarLayouts() {
        this.midias.setLayout(new CardLayout());
        this.categorias.setLayout(new CardLayout());
        this.idiomas.setLayout(new CardLayout());
        this.pessoa.setLayout(new CardLayout());
    }

    /**
     * Configura as propriedades básicas da janela principal.
     */
    public void configurarTela() {
        setTitle("Gerenciador de Mídias");
        setContentPane(this.jPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack(); // Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(null); // Centraliza na tela
        setVisible(true);
    }

    /**
     * Adiciona um listener que detecta a troca de abas no menu principal.
     * Quando uma aba é selecionada, a tela correspondente é atualizada.
     */
    public void selecionarMenu() {
        menu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = menu.getSelectedIndex();
                String aba = menu.getTitleAt(index).toUpperCase();

                switch (aba) {
                    case "MÍDIAS":
                        atualizarTelaMidia(telaMidia);
                        break;
                    case "CATEGORIAS":
                        atualizarTelaCategoria(telaCategoria);
                        break;
                    case "IDIOMA":
                        // futura integração com TelaIdioma
                        break;
                    case "PESSOA":
                        // futura integração com TelaPessoa
                        break;
                }
            }
        });
    }

    /**
     * Atualiza o conteúdo da aba de Mídias.
     *
     * @param telaMidia Tela de Mídia que será exibida no painel.
     */
    public void atualizarTelaMidia(TelaMidia telaMidia) {
        midias.removeAll();
        midias.add(telaMidia.getjPanelPrincipal());
        midias.revalidate();
        midias.repaint();
    }

    /**
     * Atualiza o conteúdo da aba de Categorias.
     *
     * @param telaCategoria Tela de Categoria que será exibida no painel.
     */
    private void atualizarTelaCategoria(TelaCategoria telaCategoria) {
        categorias.removeAll();
        categorias.add(telaCategoria.getjPanelPrincipal());
        categorias.revalidate();
        categorias.repaint();
    }

    /**
     * Método principal da aplicação.
     * Cria uma instância do {@link TelaGerenciador}, que inicializa todas as telas.
     */
    public static void main(String[] args) {
        new TelaGerenciador();
    }
}
