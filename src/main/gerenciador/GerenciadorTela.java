package main.gerenciador;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.PessoaController;
import main.view.categoria.principal.TelaCategoria;
import main.view.idioma.principal.TelaIdioma;
import main.view.midias.principal.TelaMidia;
import main.view.pessoa.principal.TelaPessoa;
import main.view.principal.TelaPrincipal;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class GerenciadorTela {
    private TelaPrincipal telaPrincipal;

    private List<JComboBox> boxes;

    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;
    private TelaIdioma telaIdioma;
    private TelaPessoa telaPessoa;

    public GerenciadorTela(CategoriaController categoriaController, IdiomaController idiomaController,
                           PessoaController pessoaController) {
        this.telaMidia = new TelaMidia(adicionarAcoesMidia(), categoriaController, idiomaController, pessoaController);
        this.telaCategoria = new TelaCategoria(adicionarAcoes(), categoriaController);
        this.telaIdioma = new TelaIdioma(adicionarAcoes(),idiomaController);
        this.telaPessoa = new TelaPessoa(adicionarAcoes(), pessoaController);

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
