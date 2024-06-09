package up.edu.br.views;

import up.edu.br.controllers.FuncionarioController;

import java.util.Scanner;

public class FuncionarioView {
    Scanner scanner = new Scanner(System.in);
    public void cadastrarFuncionario(Scanner scanner) {
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine(); // colocar verificar de cpf
        System.out.println("Digite o email do funcionário: ");
        String email = scanner.nextLine();// verificador de email
        System.out.println("Digite o telefone do funcionário: ");
        String telefone = scanner.nextLine(); // verificador de telefone

        FuncionarioController.cadastrarFuncionario(nome, cpf, email, telefone);
    }

    public void alterarFuncionario() {
        FuncionarioController.listarFuncionarios();
        int id;

        do {
            System.out.println("Digite o ID do funcionário que deseja alterar: ");
            id = scanner.nextInt();
            scanner.nextLine();

        } while (!confirmarId(id));

        System.out.println("Digite o novo nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo CPF do funcionário: ");
        String cpf = scanner.nextLine(); // colocar verificar de cpf
        System.out.println("Digite o novo email do funcionário: ");
        String email = scanner.nextLine();// verificador de email
        System.out.println("Digite o novo telefone do funcionário: ");
        String telefone = scanner.nextLine(); // verificador de telefone

        FuncionarioController.alterarFuncionario(id, nome, cpf, email, telefone);
    }

    public void excluirFuncionario() {
        int id;
        do {
            System.out.println("Digite o ID do funcionário que deseja excluir: ");
            id = scanner.nextInt();
            scanner.nextLine();

        } while (!confirmarId(id));

        FuncionarioController.excluirFuncionario(id);
    }

    public void listarFuncionarios() {
        FuncionarioController.listarFuncionarios();
    }

    public void buscarFuncionarioPorId() {
        System.out.println("Digite o ID do funcionário que deseja buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        FuncionarioController.buscarFuncionarioPorId(id);
    }

    public boolean confirmarId(int id) {
        FuncionarioController.buscarFuncionarioPorId(id);
        System.out.println("é o usuário que deseja alterar? (1 - sim, 2 - não)");
        int confirmacao = scanner.nextInt();
        scanner.nextLine();
        return confirmacao == 1;
    }
}
