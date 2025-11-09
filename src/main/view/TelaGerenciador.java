package main.view;

import main.gerenciador.GerenciadorCategoria;
import main.gerenciador.GerenciadorIdioma;
import main.view.categoria.TelaCategoria;
import main.view.idioma.TelaIdioma;
import main.view.midias.TelaMidia;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TelaGerenciador {
    private TelaPrincipal telaPrincipal;

    private List<JComboBox> boxes;

    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;
    private TelaIdioma telaIdioma;

    public TelaGerenciador(GerenciadorCategoria gerenciadorCategoria, GerenciadorIdioma gerenciadorIdioma) {
        this.telaMidia = new TelaMidia(adicionarAcoesMidia(), gerenciadorCategoria);
        this.telaCategoria = new TelaCategoria(adicionarAcoes(),gerenciadorCategoria);
        this.telaIdioma = new TelaIdioma(adicionarAcoes(),gerenciadorIdioma);

        this.telaPrincipal = new TelaPrincipal(telaMidia, telaCategoria, telaIdioma);
    }

    private List<String> adicionarAcoesMidia() {
        return Arrays.asList(
                "Adicionar",
                "Listar Todos",
                "Listar um",
                "Atualizar",
                "Remover"
        );
    }

    private List<String> adicionarAcoes() {
        return Arrays.asList(
                "Adicionar",
                "Listar"
        );
    }


}
