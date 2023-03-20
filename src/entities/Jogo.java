package entities;

public class Jogo {
    private Peao [] peoesAzuis = new Peao[4];
    private Peao [] peoesVermelhos = new Peao[4];
    private Tabuleiro tabuleiro;

    public Jogo() {
        peoesAzuis[0] = new Peao(Cor.BLUE, 0);
        peoesAzuis[1] = new Peao(Cor.BLUE, 1);
        peoesAzuis[2] = new Peao(Cor.BLUE, 2);
        peoesAzuis[3] = new Peao(Cor.BLUE, 3);
        peoesVermelhos[0] = new Peao(Cor.RED, 4);
        peoesVermelhos[1] = new Peao(Cor.RED, 5);
        peoesVermelhos[2] = new Peao(Cor.RED, 6);
        peoesVermelhos[3] = new Peao(Cor.RED, 7);
        tabuleiro = new Tabuleiro(peoesAzuis, peoesVermelhos);
    }

    public void testar() {
        System.out.println(tabuleiro.toString());
        // colocamos +7 para ignorar as casas de origem
        tabuleiro.movePawn(0, 12);
        System.out.println(tabuleiro.toString());
        tabuleiro.movePawn(2, 15);
        System.out.println(tabuleiro.toString());
        // Mandando o peão de outra cor pra mesma casa do azul
        // O azul tem que voltar
        tabuleiro.movePawn(5, 12);
        System.out.println(tabuleiro.toString());
    }
}