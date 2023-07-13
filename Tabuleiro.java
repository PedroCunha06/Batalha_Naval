import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    private int[][] navios;
    private int[] tiro;
    private int[][] tabuleiro;
    private int tentativas = 0, acertos = 0;
    Random random = new Random();
    Scanner entrada = new Scanner(System.in);

    public Tabuleiro() {
        navios = new int[3][2];
        tabuleiro = new int[5][5];
        tiro = new int[2];

        inicializaTabuleiro(tabuleiro);
        iniciaNavio(navios);

        do {
            System.out.println("\n\n");
            mostraTabuleiro(tabuleiro);
            darTiro(tiro);
            if (acertou(tiro, navios)) {
                acertos++;
            }
            alteraTabuleiro(tiro, navios, tabuleiro);
            dica(tiro, navios);
            tentativas++;
        } while (acertos != 3);

        System.out.println("\n\n\n Jogo terminado! Voce venceu com " + tentativas + " tentativas");
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

    public void darTiro(int[] tiro) {
        do {
            System.out.println("\n Linha: ");
            tiro[0] = entrada.nextInt();
            tiro[0]--;
            System.out.println("\n Coluna: ");
            tiro[1] = entrada.nextInt();
            tiro[1]--;

            if ((tiro[0] < 0 || tiro[0] >= 5) || (tiro[1] < 0 || tiro[1] >= 5)) {
                System.out.println("Escolha uma linha de 1-5 ou coluna de 1-5");
            }
            else if ((tabuleiro[tiro[0]][tiro[1]] != -1)) {
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

}