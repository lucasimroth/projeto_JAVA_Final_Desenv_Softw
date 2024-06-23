package up.edu.br.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.validar.ValidaCrud;
import up.edu.br.validar.ValidaMenu;


public class FuncionarioView {
    private static final Logger logger = LogManager.getLogger(FuncionarioView.class);

    /**
     * Método responsável por exibir o menu de Funcionário
     */
    public static void menu(){
        int opcao;
        logger.info("Iniciando o Menu Funcionário");
        do {
            System.out.println("-------------------------------------Menu Funcionário---------------------------------------\n");
            System.out.println("Digite a opção desejada: \n");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Alterar funcionário");
            System.out.println("3 - Excluir funcionário");
            System.out.println("4 - Listar funcionários");
            System.out.println("5 - Voltar");
            ValidaMenu.rodape();

            opcao = ValidaMenu.validaOpcao();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    ValidaMenu.rodape();
                    continue;
                case 2:
                    alterarFuncionario();
                    ValidaMenu.rodape();
                    continue;
                case 3:
                    excluirFuncionario();
                    ValidaMenu.rodape();
                    continue;
                case 4:
                    listarFuncionarios();
                    ValidaMenu.rodape();
                    continue;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        } while (true);
    }

    /**
     * Método responsável por cadastrar um funcionário no sistema e chamar o controller para realizar o cadastro
     */

    public static void cadastrarFuncionario() {
        logger.info("Iniciando cadastro de funcionário");
        System.out.println("-------------------------------------Cadastro de Funcionário---------------------------------------\n");
        System.out.println("Digite o nome do funcionário: ");
        String nome = ValidaCrud.validaNome();
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = ValidaCrud.validaCpf();
        System.out.println("Digite o email do funcionário: ");
        String email = ValidaCrud.validaEmail();
        System.out.println("Digite o telefone do funcionário(numeros): ");
        String telefone = ValidaCrud.validaTelefone();

        FuncionarioController.cadastrarFuncionario(nome, cpf, email, telefone);
    }

    /**
     * Método responsável por alterar um funcionário no sistema e chamar o controller para realizar a alteração
     */
    public static void alterarFuncionario() {
        logger.info("Iniciando alteração de funcionário");
        if(FuncionarioController.verificarArquivo()){
            logger.info("Não há funcionários cadastrados para alterar");
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        listarFuncionarios();
        int id;
        System.out.println("------------------------------------Alteração de Funcionário---------------------------------------\n");
        do {
            System.out.println("Digite o ID do funcionário que deseja alterar: ");
            id = ValidaCrud.validaId();
            try{
                FuncionarioController.imprimirFuncionario(FuncionarioController.buscarPorId(id));

            } catch (NotFoundException e) {
                System.out.println("Funcionário não encontrado\n");
                return;
            }

        } while (ValidaCrud.confirmarId());

        System.out.println("Digite o novo nome do funcionário: ");
        String nome = ValidaCrud.validaNome();
        System.out.println("Digite o novo email do funcionário: ");
        String email = ValidaCrud.validaEmail();
        System.out.println("Digite o novo telefone do funcionário: ");
        String telefone = ValidaCrud.validaTelefone();

        FuncionarioController.alterarFuncionario(id, nome, email, telefone);
    }

    /**
     * Método responsável por excluir um funcionário no sistema e chamar o controller para realizar a exclusão
     */
    public static void excluirFuncionario() {
        logger.info("Iniciando exclusão de funcionário");
        int id;
        System.out.println("------------------------------------Exclusão de Funcionário---------------------------------------\n");
        if(FuncionarioController.verificarArquivo()){
            logger.info("Não há funcionários cadastrados para excluir");
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        listarFuncionarios();
        do {
            System.out.println("Digite o ID do funcionário que deseja excluir: ");
            id = ValidaCrud.validaId();
            try{
                FuncionarioController.imprimirFuncionario(FuncionarioController.buscarPorId(id));
            } catch (NotFoundException e) {
                System.out.println("Funcionário não encontrado\n");
                return;
            }
        } while (ValidaCrud.confirmarId());

        FuncionarioController.excluirFuncionario(id);
    }

    /**
     * Método responsável por listar os funcionários cadastrados no sistema
     */
    public static void listarFuncionarios() {
        System.out.println("------------------------------------Listar Funcionario---------------------------------------\n");
        FuncionarioController.listarFuncionarios();
    }
}
