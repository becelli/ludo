package ludo.view;

import javax.swing.*;
// Pequena escapada do MVC
import ludo.model.Color;

public class SquareView extends JLabel {
    private int pawnCount;
    public SquareView() {
        super("");
        this.pawnCount = 0;
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(45, 45));
    }

    public void setBorder(int top, int left, int bottom, int right) {
        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, java.awt.Color.BLACK));
    }

    public void addPawn(Color color) {
        System.out.println("pawn_" + color.toString().toLowerCase() + ".png");
        // Adiciona peça
        //ImageIcon pawn = new ImageIcon("pawn_" + color.toString().toLowerCase() + ".png");
        //setIcon(pawn);
        this.pawnCount++;
    }

    private void redraw() {
        System.out.println("redraw");
    }
}
