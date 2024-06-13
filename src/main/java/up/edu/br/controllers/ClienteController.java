package up.edu.br.controllers;

import up.edu.br.Exception.NotFoundException;
import up.edu.br.daos.ClienteDao;
import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;
import up.edu.br.models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    static ClienteDao clienteDao = new ClienteDao();
    private static final List<Cliente> clientes = new ArrayList<Cliente>();

    /**
     * Método responsável por cadastrar um cliente
     * @param nome o nome do cliente
     * @param cpf o cpf do cliente
     * @param email o email do cliente
     * @param telefone o telefone do cliente
     * @param carros a lista de carros do cliente
     */
    public static void CadastrarCliente(String nome, String cpf, String email, String telefone, List<Carro> carros){
        Cliente cliente = new Cliente(MaiorID() + 1, nome, cpf, email, telefone, carros);
        clientes.add(cliente);
    }

    /**
     * Método responsável por adicionar um carro de um cliente
     * @param idCliente o id do cliente
     * @param placa a placa do carro a ser adicionado
     * @param modelo o modelo do carro a ser adicionado
     * @param cor a cor do carro a ser adicionado
     */
    public static void AdicionarCarro(int idCliente, String placa, String modelo, String cor){
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                Carro Carro = new Carro(MaiorIDCarro(c)+1, placa, modelo, cor);
                c.getCarros().add(Carro);
            }
        }
    }

    /**
     * Método responsável por alterar os dados de um carro de um cliente
     * @param idCliente o id do cliente
     * @param idCarro o id do carro a ser alterado
     * @param modelo o novo modelo do carro
     * @param cor a nova cor do carro
     */
    public static void AlterarClienteCarro(int idCliente, int idCarro, String modelo, String cor){
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                CarroController.AlterarCarro(c.getCarros(), idCarro, modelo, cor);
            }
        }
    }

    /**
     * Método responsável por alterar os dados de um cliente
     * @param idCliente o id do cliente
     * @param nome o nome do cliente
     * @param email o email do cliente
     * @param telefone o telefone do cliente
     */
    public static void AlterarCliente(int idCliente, String nome, String email, String telefone){
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                c.setNome(nome);
                c.setEmail(email);
                c.setTelefone(telefone);
                return;
            }
        }
    }

    /**
     * Método responsável por excluir um cliente da lista de clientes
     * @param id o id do cliente a ser excluído
     *
     */
    public static void ExcluirCliente(int id){
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                clientes.remove(c);
                return;
            }
        }
    }

    /**
     * Método responsável por listar os clientes de maneira formatada
     */
    public static void ListarClientes(){
        System.out.printf("%-10s %-20s %-15s %-30s %-15s\n", "ID", "Nome", "CPF", "Email", "Telefone");
        for (Cliente c : clientes) {
            imprimirClientes(c);
        }
    }

    /**
     * Método responsável por listar os carros de um cliente de maneira formatada
     * @param id o id do cliente a ser listado
     *
     */
    public static void ListarCarrosCliente(int id){
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                imprimirClientes(c);
                CarroController.ListarCarros(c.getCarros());
                return;
            }
        }
    }

    /**
     * Método responsável por listar os clientes de maneira formatada
     * @param c o cliente a ser impresso
     */
    public static void imprimirClientes(Cliente c)
    {
        System.out.printf("%-10d %-20s %-15s %-30s %-15s\n",
                c.getId(), c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone());
    }

    /**
     * Método responsável por buscar um cliente pelo id e lançar uma exceção caso não encontre
     * @param id o id do cliente a ser buscado
     * @return Cliente o cliente encontrado
     */
    public static Cliente buscarPorId(int id) throws NotFoundException
    {
        for (Cliente c : clientes)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }
        throw new NotFoundException("Funcionário não encontrado com ID: " + id + "\n");
    }

    /**
     * Método responsável por retornar o maior id entre os clientes
     * @return maior o maior id dos clientes
     */
    public static int MaiorID() {
        int maior = 0;

        for (Cliente c : clientes) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior;
    }

    /**
     * Método responsável por retornar o maior id dos carros de um cliente
     * @param c o cliente a ser verificado
     * @return maior o maior id dos carros do cliente
     */
        public static int MaiorIDCarro(Cliente c)
        {
            int maior = 0;
            List<Carro> carros = c.getCarros();

            for (Carro carro :  carros)
            {
                if (carro.getId() > maior)
                {
                    maior = carro.getId();
                }
            }
            return maior;
        }

    /**
     * Método responsável por verificar se um cpf já existe na lista de clientes
     * @param cpf o cpf a ser verificado
     * @return boolean true se o cpf já existe, false se não existe
     */
    public static boolean jaExisteCpf(String cpf)
    {
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                return true;
            }
        }
        return false;
    }
}
