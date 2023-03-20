package entities;

import java.util.HashMap;
import java.util.Map;

public class Peao {
    private final Cor cor;
    private final int origin;

    public Peao(Cor cor, int origin) {
        this.cor = cor;
        this.origin = origin;
    }

    public Cor getColor() {
        return this.cor;
    }

    public int getOrigin() {
        return this.origin;
    }

    public String toString() {
        Map<Cor, String> map = new HashMap<Cor, String>();
        map.put(Cor.RED, "R");
        map.put(Cor.GREEN, "G");
        map.put(Cor.BLUE, "B");
        map.put(Cor.YELLOW, "Y");
        return map.get(cor);
    }
}
