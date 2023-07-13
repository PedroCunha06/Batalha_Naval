import java.util.Random;
import java.util.Scanner;

public class TabuleiroDuo {
    private int[][] navios1;
    private int[][] navios2;
    private int[] tiro1;
    private int[] tiro2;
    private int[][] tabuleiro1;
    private int[][] tabuleiro2;
    private int acertos1 = 0, acertos2 = 0, rodada = 0;
    Random random = new Random();
    Scanner entrada = new Scanner(System.in);

    public TabuleiroDuo() {
        navios1 = new int[3][2];
        navios2 = new int[3][2];
        tabuleiro1 = new int[5][5];
        tabuleiro2 = new int[5][5];
        tiro1 = new int[2];
        tiro2 = new int[2];

        inicializaTabuleiro(tabuleiro1);
        iniciaNavio(navios1);
        inicializaTabuleiro(tabuleiro2);
        iniciaNavio(navios2);

        System.out.println("\nQue comecem os jogos!!");

        do {
            System.out.println("\nRodada: " + rodada + "\nJogados numero 1");
            System.out.println();
            mostraTabuleiro(tabuleiro1);
            darTiro(tiro1, tabuleiro1);
            if (acertou(tiro1, navios1)) {
                acertos1++;
            }
            alteraTabuleiro(tiro1, navios1, tabuleiro1);
            System.out.println();
            dica(tiro1, navios1);
            System.out.println("\nRodada: " + rodada + "\nJogador numero 2");
            System.out.println();
            mostraTabuleiro(tabuleiro2);
            darTiro(tiro2, tabuleiro2);
            if (acertou(tiro2, navios2)) {
                acertos2++;
            }
            alteraTabuleiro(tiro2, navios2, tabuleiro2);
            dica(tiro2, navios2);
            System.out.println();
            rodada++;
        } while ((acertos1 != 3) && (acertos2 != 3));
        vitoria(acertos1, acertos2);
    }

    public void mostraTabuleiro(int[][] tabuleiro) {
        System.out.println("\nLinhas");
        for (int linha = 0; linha < 5; linha++) {
            System.out.print("     " + (linha + 1) + "  ");

            for (int coluna = 0; coluna < 5; coluna++) {
                if (tabuleiro[linha][coluna] == -1) {
                    System.out.print("   ~");
                } else if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("   *");
                } else {
                    System.out.print("   X");
                }

            }

            System.out.println();
        }
        System.out.println("\n           1   2   3   4   5 ");
        System.out.println("                Colunas");
    }

    public void inicializaTabuleiro(int[][] tabuleiro) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = -1;
            }
        }
    }

    public void iniciaNavio(int[][] navios) {
        for (int i = 0; i < 3; i++) {
            navios[i][0] = random.nextInt(5);
            navios[i][1] = random.nextInt(5);

            for (int anterior = 0; anterior < i; anterior++) {
                if ((navios[i][0] == navios[anterior][0]) && (navios[i][1] == navios[anterior][1])) {
                    do {
                        navios[i][0] = random.nextInt(5);
                        navios[i][1] = random.nextInt(5);
                    } while ((navios[i][0] == navios[anterior][0]) && (navios[i][1] == navios[anterior][1]));
                }
            }
        }
    }

    public void darTiro(int[] tiro, int[][] tabuleiro) {
        do {
            System.out.println("\n Linha: ");
            tiro[0] = entrada.nextInt();
            tiro[0]--;
            System.out.println("\n Coluna: ");
            tiro[1] = entrada.nextInt();
            tiro[1]--;

            if ((tiro[0] < 0 || tiro[0] >= 5) || (tiro[1] < 0 || tiro[1] >= 5)) {
                System.out.println("Escolha uma linha de 1-5 ou coluna de 1-5");
            } else if ((tabuleiro[tiro[0]][tiro[1]] != -1)) {
                System.out.println("Esse campo ja esta preenchido!!");
            }
        } while ((tiro[0] < 0 || tiro[0] >= 5) || (tiro[1] < 0 || tiro[1] >= 5) || (tabuleiro[tiro[0]][tiro[1]] != -1));

    }

    public boolean acertou(int[] tiro, int[][] navio) {
        for (int i = 0; i < navio.length; i++) {
            if (tiro[0] == navio[i][0] && tiro[1] == navio[i][1]) {
                System.out.printf("Voce acertou o tiro (%d, %d)!!\n", tiro[0] + 1, tiro[1] + 1);
                return true;
            }
        }
        return false;
    }

    public void dica(int[] tiro, int[][] navios) {
        int linha = 0, coluna = 0;
        for (int i = 0; i < navios.length; i++) {
            if (navios[i][0] == tiro[0]) {
                linha++;
            }
            if (navios[i][1] == tiro[1]) {
                coluna++;
            }
        }

        System.out.printf("Na sua linha tem %d navio(s) e na sua coluna tem %d navio(s)",
                linha, coluna);
    }

    public void alteraTabuleiro(int[] tiro, int[][] navios, int[][] tabuleiro) {
        if (acertou(tiro, navios)) {
            tabuleiro[tiro[0]][tiro[1]] = 0;
        } else {
            tabuleiro[tiro[0]][tiro[1]] = 1;
        }
    }

    public void vitoria(int acertos1, int acertos2) {
        if (acertos1 == 3) {
            System.out.println("Parabens Jogador Numero 1!!!\nVOCE GANHOU! \nTava torcendo por vc!");
        } else if (acertos2 == 3) {
            System.out.println("Parabens Jogador Numero 2!!!\nVOCE GANHOU! \nTava torcendo por vc!");
        }
    }

}
