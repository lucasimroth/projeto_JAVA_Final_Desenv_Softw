package up.edu.br.validar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.controllers.CarroController;
import up.edu.br.controllers.ClienteController;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.controllers.ValletController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidaVallet {
    static Scanner scan = new Scanner (System.in);
    private static final Logger logger = LogManager.getLogger(ValidaVallet.class);


    /**
     * Método responsável por validar o ID do funcionário no valletView, para garantir pegar o dado
     * corretamente e que o funcionario exista
     * @return id do funcionário
     */
    public static int validaIdFuncionario() throws NotFoundException {
        logger.info("Iniciando validação do ID do funcionario");
        while(true) {
            int id = ValidaCrud.validaId();
            try{
                if(FuncionarioController.ExistsFuncionario(id)){
                    return id;
                }
                System.out.println("Funcionário não encontrado, digite novamente: ");
            } catch (NotFoundException e) {
                logger.error("Não há funcionários cadastrados", e);
                throw new NotFoundException("Cadastros de funcionário vazio");
            }
        }
    }

    /**
     * Método responsável por validar o CPF do cliente no valletView, para garantir pegar o dado
     * corretamente e que o cliente exista
     * @return cpf do cliente
     */
    public static String validaCpfCliente() throws NotFoundException {
        logger.info("Iniciando validação do CPF");
        while(true) {
            try {
                String cpf = ValidaCrud.validaCpf();
                if(ClienteController.jaExisteCpf(cpf)){
                    if (ValletController.jaEstacionou(cpf)){
                        return cpf;
                    }
                    System.out.println("Cliente ja estacionou um carro, digite novamente: ");
                    continue;
                }
                System.out.println("Cliente não encontrado, digite novamente: ");
            } catch (InputMismatchException e) {
                logger.error("Erro ao validar CPF", e);
                System.out.println("CPF inválido, digite novamente: ");
            } catch (NotFoundException e) {
                logger.error("Cadastros de cliente vazio");
                throw new NotFoundException("Cadastros de cliente vazio");
            }
        }
    }

    /**
     * Método responsável por validar o ID do carro no valletView, para garantir pegar o dado
     * corretamente e que o carro exista
     * @param cpf o cpf do cliente dono do carro
     * @return id do carro
     */
    public static int validaIdCarro(String cpf) throws NotFoundException {
        logger.info("Iniciando validação do ID do carro");
        while(true) {
            int id = ValidaCrud.validaId();
            try {
                if (CarroController.ExistsCarro(cpf, id)) {
                    return id;
                }
                System.out.println("Carro não encontrado");
            }catch (NotFoundException e){
                logger.error("Não há carros cadastrados");
                throw new NotFoundException("Cadastros de carro vazio");
            }
        }
    }


}
