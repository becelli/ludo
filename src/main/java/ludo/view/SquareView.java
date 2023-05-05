package ludo.view;

import javax.swing.*;

public class SquareView extends JLabel {
    public SquareView() {
        super("");
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(45, 45));
    }

    public void setBorder(int top, int left, int bottom, int right) {
        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, java.awt.Color.BLACK));
    }
}
