package ludo.view;

import javax.swing.*;
// Pequena escapada do MVC
import ludo.model.Color;

public class SquareView extends JLabel {
    private int pawnCount = 0;
    private Color currentColor = null;
    public SquareView() {
        super("");
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(45, 45));
    }

    public void setBorder(int top, int left, int bottom, int right) {
        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, java.awt.Color.BLACK));
    }

    public void addPawn(Color color) {
        // Se veio cor diferente, apaga a anterior (zera o count)
        if(this.currentColor != color) {
            this.currentColor = color;
            this.pawnCount = 0;
        }
        // Adiciona peça
        //ImageIcon pawn = new ImageIcon("pawn_" + color.toString().toLowerCase() + ".png");
        //setIcon(pawn);
        this.pawnCount++;
        this.redraw();
    }

    protected void redraw() {
        System.out.println("redraw");
        System.out.println("pawn_" + this.currentColor.toString().toLowerCase() + "_" + this.pawnCount + ".png");
    }
}
