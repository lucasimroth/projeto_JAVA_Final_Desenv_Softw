package up.edu.br.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.validar.ValidaCrud;
import up.edu.br.validar.ValidaMenu;


public class FuncionarioView {
    private static final Logger logger = LogManager.getLogger(FuncionarioView.class);

    public static void menu(){
        int opcao;
        logger.info("Iniciando o Menu Funcionário");
        do {
            System.out.println("----------Menu Funcionário-----------\n");
            System.out.println("Digite a opção desejada: \n");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Alterar funcionário");
            System.out.println("3 - Excluir funcionário");
            System.out.println("4 - Listar funcionários");
            System.out.println("5 - Voltar");
            System.out.println("--------------------------------------\n\n");

            opcao = ValidaMenu.validaOpcao();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    continue;
                case 2:
                    alterarFuncionario();
                    continue;
                case 3:
                    excluirFuncionario();
                    continue;
                case 4:
                    listarFuncionarios();
                    continue;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        } while (true);
    }

    /**
     * Os metodos do view funcionario interagem com o usuario,
     * validam as entradas e chamam seus repsectivos metodos no controller
     */

    public static void cadastrarFuncionario() {
        logger.info("Iniciando cadastro de funcionário");
        System.out.println("----------Cadastro de Funcionário-----------\n");
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

    public static void alterarFuncionario() {
        logger.info("Iniciando alteração de funcionário");
        if(FuncionarioController.verificarArquivo()){
            logger.info("Não há funcionários cadastrados para alterar");
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        listarFuncionarios();
        int id;
        System.out.println("----------Alteração de Funcionário-----------\n");
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

    public static void excluirFuncionario() {
        logger.info("Iniciando exclusão de funcionário");
        int id;
        System.out.println("----------Exclusão de Funcionário-----------\n");
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

    public static void listarFuncionarios() {
        FuncionarioController.listarFuncionarios();
    }

}
