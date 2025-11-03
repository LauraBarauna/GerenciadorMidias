package main.principal;

import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;

public class Main {
    public static void main(String[] args) throws NomeInvalido, ListagemDeAutoresInvalida, LocalDoArquivoInvalido {
        Midia filme1 = new Filme(1, "c:/dados", 450, "Filme",
                120, new Categoria("Ação"), new Idioma("Inglês"));

        System.out.println(filme1.exibirAtributos());

    }
}
