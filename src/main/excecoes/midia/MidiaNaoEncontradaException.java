package main.excecoes.midia;

public class MidiaNaoEncontradaException extends MidiaException {
    public MidiaNaoEncontradaException(int id) {
        super("Midia com id " + id + " não foi  encontrada na lista.");
    }

    public MidiaNaoEncontradaException(String categoria) {
        super("Midia com categoria " + categoria + " não foi  encontrada na lista.");
    }

    public MidiaNaoEncontradaException(String titulo, boolean c) {
        super("Midia com título " + titulo + " não foi  encontrada na lista.");
    }
}
