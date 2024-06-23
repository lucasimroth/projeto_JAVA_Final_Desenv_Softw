package up.edu.br.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.controllers.CarroController;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.controllers.ValletController;
import up.edu.br.validar.ValidaCrud;
import up.edu.br.validar.ValidaMenu;
import up.edu.br.validar.ValidaVallet;

public class ValletView {
    private static final Logger logger = LogManager.getLogger(ValletView.class);

    /**
     * Método responsável por exibir o menu de Vallet
     */
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
            System.out.println("5 - Voltar");
            System.out.println("---------------------------------------\n\n");

            opcao = ValidaMenu.validaOpcao();

            switch(opcao) {
                case 1:
                    estacionarCarro();
                    ValidaMenu.rodape();
                    continue;
                case 2:
                    retirarCarro();
                    ValidaMenu.rodape();
                    continue;
                case 3:
                    listarEstacionamento();
                    ValidaMenu.rodape();
                    continue;
                case 4:
                    mostrarVallet();
                    ValidaMenu.rodape();
                    continue;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }while(true);
    }

    /**
     * Método responsável por estacionar um veiculo no estacionamento
     * e chamar o metodo no controler para realizar a operação
     */
    public static void estacionarCarro(){
        logger.info("iniciando estacionamento de veiculo");
        System.out.println("---------------------------------------Estacionar Veiculo------------------------------------\n");
        try {
            FuncionarioController.listarFuncionarios();
            System.out.println("Digite o id do funcionario:");
            int idFuncionario = ValidaVallet.validaIdFuncionario();
            System.out.println("Digite o cpf do cliente:");
            String cpfCliente = ValidaVallet.validaCpfCliente();
            CarroController.ListarCarro(cpfCliente);
            System.out.println("Digite o id do Carro");
            int idCarro = ValidaVallet.validaIdCarro(cpfCliente);
            ValletController.estacionarCarro(idFuncionario, cpfCliente, idCarro);
        }catch (NotFoundException e){
            logger.error("Erro ao estacionar veiculo", e);
            System.out.println("Erro ao estacionar veiculo");
        }
    }

    /**
     * Método responsável por retirar um veiculo do estacionamento
     * e chamar o metodo no controler para realizar a operação
     */
    public static void retirarCarro(){
        logger.info("iniciando retirada de veiculo");
        System.out.println("---------------------------------------Retirar Veiculo------------------------------------\n");
        try {
            String cpfCliente;
            do {
                System.out.println("Digite o cpf do cliente:");
                cpfCliente = ValidaCrud.validaCpf();
                ValletController.mostrarVallet(cpfCliente);
            }while(ValidaCrud.confirmarCpf());
            ValletController.retirarCarro(cpfCliente);
        }catch (NotFoundException e){
            logger.error("Cliente não localizado", e);
            System.out.println("Cliente não localizado");
        }
    }

    /**
     * Método responsável por listar os veiculos no estacionamento
     * e chamar o metodo no controler para realizar a operação
     */
    public static void listarEstacionamento(){
        logger.info("iniciando listagem de estacionamento");
        System.out.println("---------------------------------------Listar Estacionamento------------------------------------\n");
        ValletController.listarEstacionamento();
    }

    /**
     * Método responsável por mostrar os dados de um veiculo
     * e chamar o metodo no controler para realizar a operação
     */
    public static void mostrarVallet(){
        logger.info("iniciando mostrar dados de veiculo");
        System.out.println("---------------------------------------Mostrar Dados Veiculo------------------------------------\n");
        try {
            System.out.println("Digite o cpf do cliente:");
            String cpfCliente = ValidaCrud.validaCpf();
            ValletController.mostrarVallet(cpfCliente);
        }catch (NotFoundException e){
            logger.error("Erro ao mostrar dados de veiculo", e);
            System.out.println("Erro ao mostrar dados de veiculo");
        }
    }
}
