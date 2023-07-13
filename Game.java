import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcao;
        System.out.println("Bem vindo ao Batalha Naval PH!\nEscolha as opcoes abaixo:\n    0 => Sair\n    1 => Homem X Maquina \n    2 => Homem X Homem");
        opcao = entrada.nextInt();
        switch(opcao) {
            case 0: 
            System.out.println("Tchau!!");
            break;
            case 1: 
            Tabuleiro jogo1 = new Tabuleiro();
            break;
            case 2: 
            TabuleiroDuo jogo2 = new TabuleiroDuo();
            break;
        }
        
    }
}