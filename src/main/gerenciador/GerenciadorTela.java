package main.gerenciador;

import main.controller.CategoriaController;
import main.controller.IdiomaController;
import main.controller.MidiaController;
import main.controller.PessoaController;
import main.view.categoria.principal.TelaCategoria;
import main.view.idioma.principal.TelaIdioma;
import main.view.midias.principal.TelaMidia;
import main.view.pessoa.principal.TelaPessoa;
import main.view.principal.TelaPrincipal;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Laura Barauna
 */

public class GerenciadorTela {
    private TelaPrincipal telaPrincipal;

    private List<JComboBox> boxes;

    private TelaMidia telaMidia;
    private TelaCategoria telaCategoria;
    private TelaIdioma telaIdioma;
    private TelaPessoa telaPessoa;
    
    /**
    * Construtor do Gerenciador de Tela. Recebe todos os Controllers da camada de negócio e os utiliza para inicializar
    * as telas mestras, que, por sua vez, utilizarão os Controllers para a lógica da View.
    * @param categoriaController: Controller para operações de Categoria.
    * @param idiomaController: Controller para operações de Idioma.
    * @param pessoaController: Controller para operações de Pessoa.
    * @param midiaController: Controller para operações de Mídia.
    */

    public GerenciadorTela(CategoriaController categoriaController, IdiomaController idiomaController,
                           PessoaController pessoaController, MidiaController midiaController) {
        this.telaMidia = new TelaMidia(adicionarAcoes(), categoriaController, idiomaController, pessoaController, midiaController);
        this.telaCategoria = new TelaCategoria(adicionarAcoes(), categoriaController);
        this.telaIdioma = new TelaIdioma(adicionarAcoes(),idiomaController);
        this.telaPessoa = new TelaPessoa(adicionarAcoes(), pessoaController);

        this.telaPrincipal = new TelaPrincipal(telaMidia, telaCategoria, telaIdioma, telaPessoa);
    }
    
    /**
     * Cria e retorna a lista padrão de ações disponíveis para os ComboBoxes de todas as Telas Mestras.
     * @return Uma List<String> contendo as ações padrão "Adicionar", "Listar".
     */


    private List<String> adicionarAcoes() {
        return Arrays.asList(
                "Adicionar",
                "Listar"
        );
    }


}
