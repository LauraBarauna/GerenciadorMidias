package main.view.principal;

import main.view.categoria.principal.TelaCategoria;
import main.view.idioma.principal.TelaIdioma;
import main.view.midias.principal.TelaMidia;
import main.view.pessoa.principal.TelaPessoa;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author Laura Barauna
 */

public class TelaPrincipal extends JFrame {
    private JPanel jPanelPrincipal;

    private JTabbedPane menu;

    private JPanel midias;
    private JPanel categorias;
    private JPanel idiomas;
    private JPanel pessoa;
    private JPanel inicio;

    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;
    private TelaIdioma telaIdioma;
    private TelaPessoa telaPessoa;
    
    /**
     * Construtor da tela principal. Recebe as instâncias das telas de navegação de cada módulo, configura a janela,
     * adiciona os layouts e configura o ouvinte de mudança de aba.
     * @param telaMidia: Tela principal do módulo Mídias.
     * @param telaCategoria: Tela principal do módulo Categorias.
     * @param telaIdioma: Tela principal do módulo Idiomas.
     * @param telaPessoa: Tela principal do módulo Pessoa.
     */

    public TelaPrincipal(TelaMidia telaMidia, TelaCategoria telaCategoria, TelaIdioma telaIdioma, TelaPessoa telaPessoa) {
        this.telaMidia = telaMidia;
        this.telaCategoria = telaCategoria;
        this.telaIdioma = telaIdioma;
        this.telaPessoa = telaPessoa;
        configurarTela();
        adicionarLayouts();

        selecionarMenu();
    }
    
    /**
     * Configura os painéis das abas para usar CardLayout.
     * Isso garante que a remoção e adição do painel principal de cada módulo ocorra corretamente na troca de abas.
     */

    public void adicionarLayouts() {
        this.midias.setLayout(new CardLayout());
        this.categorias.setLayout(new CardLayout());
        this.idiomas.setLayout(new CardLayout());
        this.pessoa.setLayout(new CardLayout());
    }
    
    /**
     * Configura as propriedades básicas da janela (JFrame).
     */

    public void configurarTela() {
        setTitle("Gerenciador de Midias");
        setContentPane(this.jPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Configura o {@code ChangeListener} para o JTabbedPane (menu).
     * Quando o usuário troca de aba, este listener é acionado para garantir que o painel
     * correto do módulo seja carregado e que listas de dados em módulos de cadastro (como Mídias) sejam atualizadas.
     */

    public void selecionarMenu() {
        menu.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int index = menu.getSelectedIndex();
                String aba = menu.getTitleAt(index).toUpperCase();

                switch (aba) {
                    case "MÍDIAS":
                        String extensao = telaMidia.getCadastro().getExtensaoArquivo();

                        if (extensao != null) {
                            telaMidia.getCadastro().listarCategorias(extensao);
                        }

                        telaMidia.getCadastro().getFilme().atualizarListaIdioma();
                        telaMidia.getCadastro().getMusica().atualizarListaArtistas();
                        telaMidia.getCadastro().getLivro().atualizarLista();

                        atualizarTelaMidia(telaMidia);
                        break;
                    case "CATEGORIAS":
                        atualizarTelaCategoria(telaCategoria);
                        break;
                    case "IDIOMAS":
                        atualizarTelaIdioma(telaIdioma);
                        break;
                    case "PESSOA":
                        atualizarTelaPessoa(telaPessoa);
                        break;
                }
            }
        });
    }
    
    /**
     * Adiciona o painel principal do módulo Mídia à sua respectiva aba.
     * @param telaMidia: A instância da tela principal de Mídias.
     */

    public void atualizarTelaMidia(TelaMidia telaMidia) {
        midias.removeAll();
        midias.add(telaMidia.getjPanelPrincipal());
        midias.revalidate();
        midias.repaint();
    }
    
    /**
     * Adiciona o painel principal do módulo Categoria à sua respectiva aba.
     * @param telaCategoria: A instância da tela principal de Categoria.
     */

    private void atualizarTelaCategoria(TelaCategoria telaCategoria) {
        categorias.removeAll();
        categorias.add(telaCategoria.getjPanelPrincipal());
        categorias.revalidate();
        categorias.repaint();
    }
    
    /**
     * Adiciona o painel principal do módulo Idioma à sua respectiva aba.
     * @param telaIdioma: A instância da tela principal de Idioma.
     */

    private void atualizarTelaIdioma(TelaIdioma telaIdioma) {
        idiomas.removeAll();
        idiomas.add(telaIdioma.getjPanelPrincipal());
        idiomas.revalidate();
        idiomas.repaint();
    }
    
    /**
     * Adiciona o painel principal do módulo Pessoa à sua respectiva aba.
     * @param telaPessoa: A instância da tela principal de Pessoa.
     */

    private void atualizarTelaPessoa(TelaPessoa telaPessoa) {
        pessoa.removeAll();
        pessoa.add(telaPessoa.getJPanelPrincipal());
        pessoa.revalidate();
        pessoa.repaint();
    }

}
