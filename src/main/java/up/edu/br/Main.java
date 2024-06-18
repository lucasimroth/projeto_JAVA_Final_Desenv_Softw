package up.edu.br;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.views.ClienteView;
import up.edu.br.views.FuncionarioView;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("Iniciando o sistema");

        int opcao;
        do {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Funcionário");
            System.out.println("2 - Cliente");
            System.out.println("3 - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    FuncionarioView.menu();
                    continue;
                case 2:
                    ClienteView.menu();
                    continue;
                case 3:
                    logger.info("Finalizando o sistema");
                    System.out.println("Finalizando o sistema");
                    break;
                default:
                    logger.error("Opção inválida");
                    System.out.println("Opção inválida");
            }
        } while (opcao != 3);
    }
}