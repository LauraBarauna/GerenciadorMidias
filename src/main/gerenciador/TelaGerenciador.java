package main.gerenciador;

import main.view.categoria.principal.TelaCategoria;
import main.view.idioma.principal.TelaIdioma;
import main.view.midias.TelaMidia;
import main.view.pessoa.principal.TelaPessoa;
import main.view.principal.TelaPrincipal;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TelaGerenciador {
    private TelaPrincipal telaPrincipal;

    private List<JComboBox> boxes;

    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;
    private TelaIdioma telaIdioma;
    private TelaPessoa telaPessoa;

    public TelaGerenciador(GerenciadorCategoria gerenciadorCategoria, GerenciadorIdioma gerenciadorIdioma,
                           GerenciadorPessoa gerenciadorPessoa) {
        this.telaMidia = new TelaMidia(adicionarAcoesMidia(), gerenciadorCategoria);
        this.telaCategoria = new TelaCategoria(adicionarAcoes(),gerenciadorCategoria);
        this.telaIdioma = new TelaIdioma(adicionarAcoes(),gerenciadorIdioma);
        this.telaPessoa = new TelaPessoa(adicionarAcoes(), gerenciadorPessoa);

        this.telaPrincipal = new TelaPrincipal(telaMidia, telaCategoria, telaIdioma, telaPessoa);
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
