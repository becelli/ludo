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

    // WARNING: Não vou fazer validção pra mais de 4 peões -- é só não fazer cagada (não é pra isso acontecer, nunca)
    public void addPawn(Color color) {
        // Se veio cor diferente, apaga a anterior (zera o count)
        if(this.currentColor != color) {
            this.currentColor = color;
            this.pawnCount = 0;
        }

        this.pawnCount++;

        // Adiciona peça
        this.redraw();
    }

    public void removePawn() {
        if(this.pawnCount == 0) return;
        this.pawnCount--;
        this.redraw();
    }

    protected void redraw() {
        // Se não tem peça, seta imagem vazia
        if(this.pawnCount == 0) {
            setIcon(null);
            return;
        }

        ImageIcon pawn = new ImageIcon("img/pawns/pawn_" + this.currentColor.toString().toLowerCase() + "_" + this.pawnCount + ".png");
        setIcon(pawn);
    }
}
