package entities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testando o jogo.\nPerceba como o peão azul na " +
                "18ª posição volta para trás, sendo substituído pelo vermelho.");
        Jogo jogo = new Jogo();
        jogo.testar();
        System.out.println("\nTestando o dado.");
        Dado d = new Dado();
        System.out.println(d.rolar());
    }
}
