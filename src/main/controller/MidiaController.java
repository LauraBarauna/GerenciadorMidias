package main.controller;

import main.excecoes.idioma.IdiomaDuplicadoException;
import main.excecoes.idioma.IdiomaNaoEncontradoException;
import main.gerenciador.GerenciadorIdioma;
import main.gerenciador.GerenciadorMidia;
import main.model.idioma.Idioma;
import main.model.midias.Categoria;
import main.model.midias.Midia;
import main.model.midias.filme.Filme;
import main.model.midias.livro.Livro;
import main.model.midias.musica.Musica;
import main.model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MidiaController {

    private GerenciadorMidia gerenciadorMidia;

    public MidiaController() {
        this.gerenciadorMidia = new GerenciadorMidia();
    }

    public void cadastrarMidia(Midia midia) {

        if(midia == null){
            throw new IllegalArgumentException("Midia não pode ser nulo");
        }
        gerenciadorMidia.incluirMidia(midia);
        }
        public List<Midia> listarTodas(){
        return gerenciadorMidia.listarTodasMidias();
        }
}



