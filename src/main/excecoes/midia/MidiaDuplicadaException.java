package main.excecoes.midia;

public class MidiaDuplicadaException extends MidiaException {
    public MidiaDuplicadaException(int id) {
        super("Midia com o id " + id + " já está cadastrada na lista.");
    }
}
