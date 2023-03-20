package entities;

import java.util.HashMap;
import java.util.Map;

public class Peao {
    private final Color color;
    private final int origin;

    public Peao(Color color, int origin) {
        this.color = color;
        this.origin = origin;
    }

    public Color getColor() {
        return this.color;
    }

    public int getOrigin() {
        return this.origin;
    }

    public String toString() {
        Map<Color, String> map = new HashMap<Color, String>();
        map.put(Color.RED, "R");
        map.put(Color.GREEN, "G");
        map.put(Color.BLUE, "B");
        map.put(Color.YELLOW, "Y");
        return map.get(color);
    }
}
