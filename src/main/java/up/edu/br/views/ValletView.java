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
            System.out.println("5 - Voltar");
            System.out.println("---------------------------------------\n\n");

            opcao = ValidaMenu.validaOpcao();

            switch(opcao) {
                case 1:
                    estacionarCarro();
                    continue;
                case 2:
                    retirarCarro();
                    continue;
                case 3:
                    listarEstacionamento();
                    continue;
                case 4:
                    mostrarVallet();
                    continue;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }while(true);
    }

    public static void estacionarCarro(){
        logger.info("iniciando estacionamento de veiculo");
        System.out.println("----------Estacionar Veiculo-----------\n");
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

    public static void retirarCarro(){
        logger.info("iniciando retirada de veiculo");
        System.out.println("----------Retirar Veiculo-----------\n");
        try {
            System.out.println("Digite o cpf do cliente:");
            String cpfCliente = ValidaCrud.validaCpf();
            ValletController.retirarCarro(cpfCliente);
        }catch (NotFoundException e){
            logger.error("Veiculo não localizado", e);
            System.out.println("Veiculo não localizado");
        }
    }

    public static void listarEstacionamento(){
        logger.info("iniciando listagem de estacionamento");
        System.out.println("----------Listar Estacionamento-----------\n");
        ValletController.listarEstacionamento();
    }

    public static void mostrarVallet(){
        logger.info("iniciando mostrar dados de veiculo");
        System.out.println("----------Mostrar Dados Veiculo-----------\n");
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
