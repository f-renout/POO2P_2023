package td2.morpion;

public enum Type {
    X, O;

    public Type adversaire() {
        return this == X ? O : X;
    }
}
