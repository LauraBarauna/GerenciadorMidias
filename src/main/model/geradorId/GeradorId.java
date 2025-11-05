package main.model.geradorId;

public class GeradorId {
    private int id;

    public GeradorId() {
        this.id = 0;
    }

    public int getId() {
        return id += 1;
    }

    public void setId(int id) {
        this.id = id;
    }
}
