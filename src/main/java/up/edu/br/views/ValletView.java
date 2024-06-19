package up.edu.br.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.controllers.ValletController;
import up.edu.br.validar.ValidaMenu;

import java.util.Scanner;

public class ValletView {
    private static final Logger logger = LogManager.getLogger(ValletView.class);
    static Scanner scan = new Scanner (System.in);

    public static void menu(){
        int opcao;
        logger.info("Iniciando Menu Vallet.");
        do{
            System.out.println("-----------Menu Vallet --------------\n");
            System.out.println("Digite a opcao desejada: \n");
            System.out.println("1 - Estacionar Veiculo");
            System.out.println("2 - Retirar Veiculo");
            System.out.println("3 - Listar Estacionamento");
            System.out.println("4 - Mostrar Dados Veiculo");
            System.out.println("---------------------------------------\n\n");

            opcao = ValidaMenu.validaOpcao();

            switch(opcao) {
                case 1:
                    estacionarCarro();

            }
        }
    }
}
