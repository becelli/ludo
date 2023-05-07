package ludo.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class DiceView extends JLabel {
    private File audioFile;
    public DiceView() {
        super("");
        // Seta tamanho do quadrado (45px)
        setPreferredSize(new java.awt.Dimension(112, 112));
        setIcon(new ImageIcon("img/dice/Alea_5.png"));
        this.audioFile = new File("sound/dice_roll.wav");
    }

    public void setValue(int value) {
        setIcon(new ImageIcon("img/dice/Alea_" + value + ".png"));
        this.playSound();
    }

    private synchronized void playSound() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                // File to AudioInputStream
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(audioFile);
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
