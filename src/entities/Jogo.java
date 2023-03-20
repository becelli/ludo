package entities;

import entities.*;
public class Jogo {
    private Peao [] peoesAzuis = new Peao[4];
    private Peao [] peoesVermelhos = new Peao[4];
    private Board board;

    public Jogo() {
        peoesAzuis[0] = new Peao(Color.BLUE, 0);
        peoesAzuis[1] = new Peao(Color.BLUE, 1);
        peoesAzuis[2] = new Peao(Color.BLUE, 2);
        peoesAzuis[3] = new Peao(Color.BLUE, 3);
        peoesVermelhos[0] = new Peao(Color.RED, 4);
        peoesVermelhos[1] = new Peao(Color.RED, 5);
        peoesVermelhos[2] = new Peao(Color.RED, 6);
        peoesVermelhos[3] = new Peao(Color.RED, 7);
        board = new Board(peoesAzuis, peoesVermelhos);
    }

    public void testar() {
        System.out.println(board.toString());
        // colocamos +7 para ignorar as casas de origem
        board.movePawn(0, 12);
        System.out.println(board.toString());
        board.movePawn(2, 15);
        System.out.println(board.toString());
        // Mandando o peão de outra cor pra mesma casa do azul
        // O azul tem que voltar
        board.movePawn(5, 12);
        System.out.println(board.toString());
    }
}