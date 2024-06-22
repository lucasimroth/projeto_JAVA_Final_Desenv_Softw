package up.edu.br;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.views.ClienteView;
import up.edu.br.views.FuncionarioView;
import up.edu.br.views.HistoricoView;
import up.edu.br.views.ValletView;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("Iniciando o sistema");

        int opcao;
        do {
            System.out.println("----------Menu Principal-----------\n");
            System.out.println("Digite a opção desejada: \n");
            System.out.println("1 - Vallet");
            System.out.println("2 - Funcionário");
            System.out.println("3 - Cliente"); 
            System.out.println("4 - Historico");
            System.out.println("5 - Sair");
            System.out.println("---------------------------------------\n\n");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    ValletView.menu();
                    continue;
                case 2:
                    FuncionarioView.menu();
                    continue;
                case 3:
                    ClienteView.menu();
                    continue;
                case 4:
                    HistoricoView.menu();
                    continue;
                case 5:
                    logger.info("Finalizando o sistema");
                    System.out.println("Finalizando o sistema");
                    break;
                default:
                    logger.error("Opção inválida");
                    System.out.println("Opção inválida");
                    continue;
            }
            break;
        } while (true);
    }
}