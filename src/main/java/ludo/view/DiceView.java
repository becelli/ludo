package ludo.view;

import javax.swing.*;

public class DiceView extends JLabel {
    public DiceView() {
        super("");
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(112, 112));
        setIcon(new ImageIcon("img/dice/Alea_5.png"));
    }
}
