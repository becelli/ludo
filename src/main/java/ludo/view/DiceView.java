package ludo.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class DiceView extends JLabel {
    public DiceView() {
        super("");
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(112, 112));
        setIcon(new ImageIcon("img/dice/Alea_5.png"));
    }

    public void setValue(int value) {
        setIcon(new ImageIcon("img/dice/Alea_" + value + ".png"));
    }
}
