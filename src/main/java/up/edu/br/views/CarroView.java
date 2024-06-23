package up.edu.br.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.controllers.CarroController;
import up.edu.br.controllers.ClienteController;
import up.edu.br.models.Cliente;
import up.edu.br.validar.ValidaCrud;
import up.edu.br.validar.ValidaMenu;

public class CarroView {
    private static final Logger logger = LogManager.getLogger(CarroView.class);
    public static String cpf;


    /**
     * Método responsável por exibir o menu de Carro
     * o menu do carro exclusivamente guarda o cpf dado do cliente para que as operações sejam feitas no cliente correto
     */
    public static void menu()
    {
        logger.info("Iniciando menu de carro");
        System.out.println("Digite o cpf do cliente: ");
        cpf = ValidaCrud.validaCpf();
        Cliente cliente;

        try {
            cliente = ClienteController.buscarPorCpf(cpf);
        } catch (NotFoundException e) {
            logger.error("Cliente não encontrado", e);
            System.out.println("Cliente não encontrado");
            return;
        }

        int opcao;
        do {
            System.out.println("-------------------------------------Menu Carro-------------------------------------\n");
            System.out.print("Cliente: ");

            ClienteController.imprimirClientes(cliente);

            System.out.println("\nDigite a opção desejada: \n");
            System.out.println("1 - Adicionar Carro");
            System.out.println("2 - Alterar Carro");
            System.out.println("3 - Excluir Carro");
            System.out.println("4 - Listar Carro");
            System.out.println("5 - Voltar");
            ValidaMenu.rodape();

            opcao = ValidaMenu.validaOpcao();

            switch (opcao) {
                case 1:
                    AdicionarCarro(cpf);
                    ValidaMenu.rodape();
                    continue;
                case 2:
                    AlterarCarro(cpf);
                    ValidaMenu.rodape();
                    continue;
                case 3:
                    ExcluirCarro(cpf);
                    ValidaMenu.rodape();
                    continue;
                case 4:
                    ListarCarro(cpf);
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
     * O metodo AdicionarCarro interage com o usuario, valida as entradas e chama o metodo AdicionarCarro no controller
     * @param cpf cpf do cliente dono do carro
     */
    public static void AdicionarCarro(String cpf){
        logger.info("Iniciando Adicao de carro");
        System.out.println("-------------------------------------Cadastro de Carro-------------------------------------\n");

        System.out.println("Digite a placa do novo carro: ");
        String placa = ValidaCrud.validaPlaca();
        System.out.println("Digite o modelo do novo carro: ");
        String modelo = ValidaCrud.validaNome();
        System.out.println("Digite a cor do novo carro: ");
        String cor = ValidaCrud.validaNome();

        CarroController.AdicionarCarro(cpf, placa, modelo, cor);
    }

    /**
     * O metodo AlterarCarro interage com o usuario, valida as entradas e chama o metodo AlterarCarro no controller
     * @param cpf cpf do cliente dono do carro
     */
    public static void AlterarCarro(String cpf){
        logger.info("Iniciando Alteracao de carro");
        System.out.println("-------------------------------------Alterar Carro-------------------------------------\n");
        ListarCarro(cpf);

        int idCarro;

        do {
            System.out.println("Digite o ID do Carro que deseja alterar: ");
            idCarro = ValidaCrud.validaId();

            if(CarroController.MostrarCarro(cpf, idCarro)){
                System.out.println("Carro não encontrado!!!\n");
                return;
            }

        } while (ValidaCrud.confirmarId());

        System.out.println("Digite o novo modelo do carro: ");
        String modelo = ValidaCrud.validaModelo();
        System.out.println("Digite a nova cor do carro: ");
        String cor = ValidaCrud.validaCor();

        CarroController.AlterarCarro(cpf, idCarro, modelo, cor);
    }

    /**
     * O metodo ExcluirCarro interage com o usuario, valida as entradas e chama o metodo ExcluirCarro no controller
     * @param cpf cpf do cliente dono do carro
     */
    public static void ExcluirCarro(String cpf){
        logger.info("Iniciando Exclusao de carro");
        System.out.println("-------------------------------------Excluir Carro-------------------------------------\n");
        ListarCarro(cpf);
        int idCarro;
        do {
            System.out.println("Digite o ID do Carro que deseja excluir: ");
            idCarro = ValidaCrud.validaId();

            if(CarroController.MostrarCarro(cpf, idCarro)){
                System.out.println("Carro não encontrado!!!\n");
                return;
            }

        } while (ValidaCrud.confirmarId());

        CarroController.ExcluirCarro(cpf, idCarro);
    }

    /**
     * O metodo ListarCarro chama o metodo ListarCarro no controller
     * @param cpf cpf do cliente dono do carro
     */
    public static void ListarCarro(String cpf){
        logger.info("Iniciando Listagem de carro");
        System.out.println("-------------------------------------Listar Carro-------------------------------------\n");
        CarroController.ListarCarro(cpf);
    }
}
