package up.edu.br.views;

import up.edu.br.controllers.FuncionarioController;
import up.edu.br.validar.ValidaCrud;


public class FuncionarioView {

    public static void cadastrarFuncionario() {
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
        FuncionarioController.listarFuncionarios();
        int id;

        do {
            System.out.println("Digite o ID do funcionário que deseja alterar: ");
            id = ValidaCrud.validaId();

        } while (ValidaCrud.confirmarId(id));

        System.out.println("Digite o novo nome do funcionário: ");
        String nome = ValidaCrud.validaNome();
        System.out.println("Digite o novo email do funcionário: ");
        String email = ValidaCrud.validaEmail();
        System.out.println("Digite o novo telefone do funcionário: ");
        String telefone = ValidaCrud.validaTelefone();

        FuncionarioController.alterarFuncionario(id, nome, email, telefone);
    }

    public static void excluirFuncionario() {
        int id;
        do {
            System.out.println("Digite o ID do funcionário que deseja excluir: ");
            id = ValidaCrud.validaId();

        } while (ValidaCrud.confirmarId(id));

        FuncionarioController.excluirFuncionario(id);
    }

    public static void listarFuncionarios() {
        FuncionarioController.listarFuncionarios();
    }

}
