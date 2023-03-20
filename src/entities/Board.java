package entities;

public class Board {
    private final int size = 52;
    private Peao[] casas = new Peao[size];

    public Board(Peao[] azuis, Peao[] vermelhos) {
        casas[0] = azuis[0];
        casas[1] = azuis[1];
        casas[2] = azuis[2];
        casas[3] = azuis[3];
        casas[4] = vermelhos[0];
        casas[5] = vermelhos[1];
        casas[6] = vermelhos[2];
        casas[7] = vermelhos[3];
        for(int i = 8; i < 52; i++)
            casas[i] = null;
    }

    public void movePawn(int origin, int distance) {
        if (casas[origin + distance] != null) {
            if(casas[origin + distance].getColor() != casas[origin].getColor()) {
                casas[casas[origin + distance].getOrigin()] = casas[origin + distance];
            }
        }
        casas[origin + distance] = casas[origin];
        casas[origin] = null;
    }

    public String toString() {
        String res = (casas[0] == null ? "0" : casas[0].toString());

        for(int i = 1; i < 52; i++) {
            if(casas[i] == null) {
                res += "0";
                continue;
            }
            res += casas[i].toString();
        }

        return res;
    }
}
