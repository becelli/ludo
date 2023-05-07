package ludo.view;

import ludo.model.Color;

import java.util.EnumMap;

public class SpecialSquareView extends SquareView {
    // HashMap de cores
    private final EnumMap<Color, Integer> pawnCount = new EnumMap<>(Color.class);
    public SpecialSquareView() {
        super();
        // Esses são sempre quadrados "normais", então dá pra definir as bordas aqui
        this.setBorder(1, 1, 0, 0);
        // Seta tudo para 0
        for(Color color : Color.values()) this.pawnCount.put(color, 0);
        // Align text to center
        this.setHorizontalAlignment(CENTER);
        this.redraw();
    }

    @Override
    public void addPawn(Color color) {
        this.pawnCount.put(color, this.pawnCount.get(color) + 1);
        this.redraw();
    }

    public void removePawn(Color color) {
        this.pawnCount.put(color, this.pawnCount.get(color) - 1);
        this.redraw();
    }

    @Override
    protected void redraw() {
        EnumMap<Color, String> colorMap = new EnumMap<>(Color.class);
        colorMap.put(Color.BLUE, "82bbec");
        colorMap.put(Color.RED, "ec3535");
        colorMap.put(Color.YELLOW, "ffdd36");
        colorMap.put(Color.GREEN, "06c258");
        // Escrevendo no label
        StringBuilder text = new StringBuilder("<html><b>");
        for(Color color : Color.values()) {
            int count = this.pawnCount.get(color);
            if(count == 0) continue;
            text.append("<font color='#").append(colorMap.get(color)).append("'>").append(count).append("</font> ");
            if(color.ordinal() == 1) text.append("<br>");
        }
        text.append("</b></html>");

        this.setText(text.toString());

        this.setFont(this.getFont().deriveFont(20.0f));
    }
}
