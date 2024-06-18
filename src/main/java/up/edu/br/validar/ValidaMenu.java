package up.edu.br.validar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ValidaMenu {
    private static final Scanner scan = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(ValidaMenu.class);

    /**
     * Método responsável por validar a opção do menu
     * @return a opção validada
     */
    public static int validaOpcao() {

        int opcao = 0;
        boolean valida = false;
        do {
            try {
                opcao = scan.nextInt();
                scan.nextLine();
                valida = true;
            } catch (NumberFormatException e) {
                logger.error("Formato inesperado", e);
                System.out.println("Opção inválida, digite novamente");
            }
        } while (!valida);
        return opcao;
    }

}
