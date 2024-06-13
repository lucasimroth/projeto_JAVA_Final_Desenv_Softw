package up.edu.br.views;

import up.edu.br.controllers.ClienteController;
import up.edu.br.models.Carro;
import up.edu.br.validar.ValidaCrud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteView {
    static Scanner scan = new Scanner (System.in);
    public static void CadastrarCliente() {
        System.out.println("Digite o nome do cliente: ");
        String nome = ValidaCrud.validaNome();
        System.out.println("Digite o CPF do cliente: ");
        String cpf = ValidaCrud.validaCpf();
        System.out.println("Digite o email do cliente: ");
        String email = ValidaCrud.validaEmail();
        System.out.println("Digite o telefone do cliente: ");
        String telefone = ValidaCrud.validaTelefone();

        List<Carro> carros = new ArrayList<>();

        int cont = 1;
        carros.add(CadastrarClienteCarro(cont));
        while(true){
            System.out.println("Deseja cadastrar mais um veiculo para o cliente? (S/N)");
            if(scan.nextLine().equalsIgnoreCase("S")){
                carros.add(CadastrarClienteCarro(++cont));
            }
            else {break;}
        }

        ClienteController.CadastrarCliente(nome, cpf, email, telefone, carros);
    }

    public static void adicionarCarro(){
        ClienteController.ListarClientes();
        System.out.println("Digite o ID do cliente que deseja adicionar um carro: ");
        int idcliente = ValidaCrud.validaId();
        System.out.println("Digite a placa do carro: ");
        String placa = ValidaCrud.validaPlaca();
        System.out.println("Digite o modelo do carro: ");
        String modelo = ValidaCrud.validaModelo();
        System.out.println("Digite a cor do carro: ");
        String cor = ValidaCrud.validaCor();

        ClienteController.AdicionarCarro(idcliente, placa, modelo, cor);
    }

    public static Carro CadastrarClienteCarro(int id) {
        System.out.println("Digite a placa do carro: ");
        String placa = ValidaCrud.validaPlaca();
        System.out.println("Digite o modelo do carro: ");
        String modelo = ValidaCrud.validaModelo();
        System.out.println("Digite a cor do carro: ");
        String cor = ValidaCrud.validaCor();

        return new Carro(id, placa, modelo, cor);
    }

    public static void AlterarClienteCarro() {
        ClienteController.ListarClientes();
        System.out.println("Digite o ID do cliente que deseja alterar: ");
        int idCliente = ValidaCrud.validaId();
        ClienteController.ListarCarrosCliente(idCliente);
        System.out.println("Digite o ID do carro que deseja alterar: ");
        int idCarro = ValidaCrud.validaId();
        System.out.println("Digite o novo modelo do carro: ");
        String modelo = ValidaCrud.validaModelo();
        System.out.println("Digite a nova cor do carro: ");
        String cor = ValidaCrud.validaCor();

        ClienteController.AlterarClienteCarro(idCliente, idCarro, modelo, cor);
    }

    public static void AlterarCliente() {
        ClienteController.ListarClientes();
        System.out.println("Digite o ID do cliente que deseja alterar: ");
        int id = ValidaCrud.validaId();
        System.out.println("Digite o novo nome do cliente: ");
        String nome = ValidaCrud.validaNome();
        System.out.println("Digite o novo email do cliente: ");
        String email = ValidaCrud.validaEmail();
        System.out.println("Digite o novo telefone do cliente: ");
        String telefone = ValidaCrud.validaTelefone();

        ClienteController.AlterarCliente(id, nome, email, telefone);
    }

    public static void ExcluirCliente() {
        ClienteController.ListarClientes();
        System.out.println("Digite o ID do cliente que deseja excluir: ");
        int id = ValidaCrud.validaId();
        ClienteController.ExcluirCliente(id);
    }

    public static void ListarClientes() {
        ClienteController.ListarClientes();
    }

    public static void ListarCarrosCliente() {
        ClienteController.ListarClientes();
        System.out.println("Digite o ID do cliente que deseja listar os carros: ");
        int id = ValidaCrud.validaId();
        ClienteController.ListarCarrosCliente(id);
    }


}


