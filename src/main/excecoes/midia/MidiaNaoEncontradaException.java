package main.excecoes.midia;

public class MidiaNaoEncontradaException extends MidiaException {
    public MidiaNaoEncontradaException(int id) {
        super("Midia com id " + id + " não foi  encontrada na lista.");
    }
}
