package ludo.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import ludo.model.Color;

public class BoardView extends JPanel {
    private BufferedImage backgroundImage;

    private SquareView getSquare(int x, int y) {
        return (SquareView) this.getComponent(x * 15 + y);
    }

    public BoardView(File imageFile) {
        // Setando background
        try {
            backgroundImage = ImageIO.read(imageFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Criando quadrados
        // Insert the squares into the 15x15 grid
        int top, left, bottom, right;
        SquareView square;
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                square = new SquareView();

                top =  1;
                left = 1;
                bottom = 0;
                right = 0;
                // Set borders
                if(i == 14) {
                    bottom = 1;
                }

                if(j == 14) {
                    right = 1;
                }

                square.setBorder(top, left, bottom, right);
                this.add(square);
            }
        }

        // Loop through the 9 squares in the middle
        for(int i = 6; i <= 8; i++) {
            for(int j = 6; j <= 8; j++) {
                square = getSquare(i, j);
                top = 0;
                left = 0;
                // if it's the top of the square, it has a top border
                if(i == 6) top = 1;
                // if it's the left of the square, it has a left border
                if(j == 6) left = 1;
                square.setBorder(top, left, 0, 0);
            }
        }

        // Quadrados superiores esquerdos das casas
        int[] valores = {2, 11};
        int c = 0; // contador para Enum da cor
        Color color;
        // casas são (2, 2), (2, 11), (11, 2), (11, 11)
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                // Cor para colocar os peões
                color = Color.values()[c++];
                square = getSquare(valores[i], valores[j]);
                // adiciona peão
                square.addPawn(color);
                square = getSquare(valores[i] + 1, valores[j]);
                square.setBorder(0, 1, 0, 0);
                // adiciona peão, remove bordas
                square.addPawn(color);
                square = getSquare(valores[i], valores[j] + 1);
                square.setBorder(1, 0, 0, 0);
                square.addPawn(color);
                square = getSquare(valores[i] + 1, valores[j] + 1);
                square.setBorder(0, 0, 0, 0);
                square.addPawn(color);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage != null) {
            return new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
        }
        return super.getPreferredSize();
    }
}
