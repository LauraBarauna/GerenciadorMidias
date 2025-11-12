package main.view.principal;

import main.view.categoria.principal.TelaCategoria;
import main.view.idioma.principal.TelaIdioma;
import main.view.midias.principal.TelaMidia;
import main.view.pessoa.principal.TelaPessoa;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

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

    public TelaPrincipal(TelaMidia telaMidia, TelaCategoria telaCategoria, TelaIdioma telaIdioma, TelaPessoa telaPessoa) {
        this.telaMidia = telaMidia;
        this.telaCategoria = telaCategoria;
        this.telaIdioma = telaIdioma;
        this.telaPessoa = telaPessoa;
        configurarTela();
        adicionarLayouts();

        selecionarMenu();
    }

    public void adicionarLayouts() {
        this.midias.setLayout(new CardLayout());
        this.categorias.setLayout(new CardLayout());
        this.idiomas.setLayout(new CardLayout());
        this.pessoa.setLayout(new CardLayout());
    }

    public void configurarTela() {
        setTitle("Gerenciador de Midias");
        setContentPane(this.jPanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

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

    public void atualizarTelaMidia(TelaMidia telaMidia) {
        midias.removeAll();
        midias.add(telaMidia.getjPanelPrincipal());
        midias.revalidate();
        midias.repaint();
    }

    private void atualizarTelaCategoria(TelaCategoria telaCategoria) {
        categorias.removeAll();
        categorias.add(telaCategoria.getjPanelPrincipal());
        categorias.revalidate();
        categorias.repaint();
    }

    private void atualizarTelaIdioma(TelaIdioma telaIdioma) {
        idiomas.removeAll();
        idiomas.add(telaIdioma.getjPanelPrincipal());
        idiomas.revalidate();
        idiomas.repaint();
    }

    private void atualizarTelaPessoa(TelaPessoa telaPessoa) {
        pessoa.removeAll();
        pessoa.add(telaPessoa.getJPanelPrincipal());
        pessoa.revalidate();
        pessoa.repaint();
    }

}
