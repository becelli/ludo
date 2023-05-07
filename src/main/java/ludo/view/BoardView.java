package ludo.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;

import ludo.model.Color;

public class BoardView extends JPanel {
    private BufferedImage backgroundImage;

    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    // lookup tables -- programação dinâmica :sunglasses:
    private SquareView[][] baseSquares = new SquareView[4][4];
    private SquareView[] normalSquares = new SquareView[52];
    private SquareView[][] finalSquares = new SquareView[4][6];

    private int coordToIndex(int x, int y) {
        return x * 15 + y;
    }

    private SquareView getSquare(int x, int y) {
        return (SquareView) this.getComponent(coordToIndex(x, y));
    }

    private void buildLookupTables() {
        // não precisa constuir os da base
        // passei essa responsabilidade para um for loop no construtor desta
        // classe que já visita as casas da base
        int line = 0, col = 8;
        int[] turningCounts = {
                5, 6, 2,
                5, 6, 2,
                5, 6, 2,
                5, 6, 2
        };
        Direction[] directions = {
                Direction.DOWN, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.DOWN, Direction.LEFT,
                Direction.UP, Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.UP, Direction.RIGHT,
                Direction.UP, Direction.RIGHT
        };
        int j = 0;
        int nextDiagonal = 1;
        for(int i = 0; i < 52; i++) {
            normalSquares[i] = getSquare(line, col);

            if(j == nextDiagonal) {
                // Hora de virar a diagonal! Isso custará 1 movimento.
                turningCounts[j]--;
                // Incrementar e/ou decrementar de acordo com a combinação [direção atual, direção anterior]
                if (directions[j] == Direction.RIGHT && directions[j - 1] == Direction.DOWN) {
                    line++;
                    col++;
                }
                else if (directions[j] == Direction.DOWN && directions[j - 1] == Direction.LEFT) {
                    line++;
                    col--;
                }
                else if (directions[j] == Direction.LEFT && directions[j - 1] == Direction.UP) {
                    line--;
                    col--;
                }
                else if (directions[j] == Direction.UP && directions[j - 1] == Direction.RIGHT) {
                    line--;
                    col++;
                }
                nextDiagonal += 3;
                continue;
            }

            switch (directions[j]) {
                case DOWN -> line++;
                case LEFT -> col--;
                case UP -> line--;
                case RIGHT -> col++;
            }

            turningCounts[j]--;
            if(turningCounts[j] == 0) j++;
        }
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
                baseSquares[color.ordinal()][0] = square;
                // adiciona peão
                square.addPawn(color);

                square = getSquare(valores[i] + 1, valores[j]);
                square.setBorder(0, 1, 0, 0);
                baseSquares[color.ordinal()][1] = square;
                // adiciona peão, remove bordas
                square.addPawn(color);

                square = getSquare(valores[i], valores[j] + 1);
                square.setBorder(1, 0, 0, 0);
                square.addPawn(color);
                baseSquares[color.ordinal()][2] = square;

                square = getSquare(valores[i] + 1, valores[j] + 1);
                square.setBorder(0, 0, 0, 0);
                square.addPawn(color);
                baseSquares[color.ordinal()][3] = square;
            }
        }

        // Teste: adiciona peão vermelho na casa  (é pra zerar e colocar um, e apenas um, novo peão vermelho)
        square = getSquare(2, 2);
        square.addPawn(Color.RED);

        // Teste: adiciona peão vermelho na casa (2, 11) (é pra ficar dois)
        square = getSquare(7, 6);
        square.addPawn(Color.RED);
        square.addPawn(Color.RED);
        square.addPawn(Color.RED);
        square.addPawn(Color.RED);

        // Casas especiais (estrelas e casas de segurança)
        valores = new int[]{8, 2, 6, 12, 6, 13, 1, 8};
        int[] valores2 = new int[]{2, 6, 12, 8, 1, 6, 8, 13};
        int index;
        for(int i = 0; i < valores.length; i++) {
            // remove o que está lá
            index = coordToIndex(valores[i], valores2[i]);
            this.remove(index);
            // adiciona a casa especial
            this.add(new SpecialSquareView(), index);
        }

        SpecialSquareView squareSpecial = (SpecialSquareView) getSquare(8, 2);
        // enum map
        EnumMap<Color, Integer> map = new EnumMap<>(Color.class);
        map.put(Color.RED, 3);
        map.put(Color.GREEN, 2);
        map.put(Color.YELLOW, 1);
        map.put(Color.BLUE, 4);
        //squareSpecial.setPawns(map);

        squareSpecial = (SpecialSquareView) getSquare(8, 13);
        squareSpecial.setPawns(map);

        // Number all squares
        for(int i = 0; i < 225; i++) {
            square = (SquareView) getComponent(i);
            square.setText(Integer.toString(i));
        }

        buildLookupTables();
    }

    public void addPawn(int x, int y, Color color) {
        SquareView square = getSquare(x, y);
        square.addPawn(color);
    }

    // Muito cuidado com o removePawn.
    // Quando um peão de cor diferentee for  até uma casa normal, basta adicionar o peão que vai ser removido
    // na sua casa original, e adicionar o novo peão na casa de destino.
    // removePawn é apenas para tirar um peão, de uma mesma cor, de uma casa e colocar em outra
    // Ex.: para adicionar um peão vermelho na casa (x, y), onde já há um peão verde, você dá dois addPawns: um do vermelho em (x,y) e um do verde na base
    public void removePawn(int x, int y) {
        SquareView square = getSquare(x, y);
        square.removePawn();
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
