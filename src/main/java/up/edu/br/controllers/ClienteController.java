package up.edu.br.controllers;

import up.edu.br.Exception.NotFoundException;
import up.edu.br.daos.ClienteDao;
import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;

import java.util.List;

public class ClienteController {
    static ClienteDao clienteDao = new ClienteDao();

    /**
     * Método responsável por cadastrar um cliente
     * @param nome o nome do cliente
     * @param cpf o cpf do cliente
     * @param email o email do cliente
     * @param telefone o telefone do cliente
     * @param carros a lista de carros do cliente
     */
    public static void CadastrarCliente(String nome, String cpf, String email, String telefone, List<Carro> carros){

        try{
            if (jaExisteCpf(cpf))
            {
                System.out.println("\nCPF já cadastrado\n");
                return;
            }
        } catch (NotFoundException e) {
            System.out.println("Não há clientes cadastrados\n");
            return;
        }
        List<Cliente> clientes = clienteDao.lerArquivo();
        Cliente cliente = new Cliente(MaiorID(clientes) + 1, nome, cpf, email, telefone, carros);
        clientes.add(cliente);
        clienteDao.salvarArquivo(clientes);
    }

    /**
     * Método responsável por alterar os dados de um cliente
     * @param cpf o cpf do cliente a ser alterado
     * @param nome o nome do cliente
     * @param email o email do cliente
     * @param telefone o telefone do cliente
     */
    public static void AlterarCliente(String cpf, String nome, String email, String telefone){
        List<Cliente> clientes = clienteDao.lerArquivo();
        if (clientes.isEmpty())
        {
            System.out.println("Não há clientes cadastrados\n");
            return;
        }
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                c.setNome(nome);
                c.setEmail(email);
                c.setTelefone(telefone);
                clienteDao.salvarArquivo(clientes);
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    /**
     * Método responsável por excluir um cliente da lista de clientes
     * @param cpf o cpf do cliente a ser excluído
     *
     */
    public static void ExcluirCliente(String cpf){
        List<Cliente> clientes = clienteDao.lerArquivo();
        if (clientes.isEmpty())
        {
            System.out.println("Não há clientes cadastrados\n");
            return;
        }
        if(ValletController.clienteEstaVinculado(cpf)){
            System.out.println("Cliente está vinculado a um carro no vallet\n");
            return;
        }
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                clientes.remove(c);
                clienteDao.salvarArquivo(clientes);
                return;
            }
        }
        System.out.println("Cliente não encontrado\n");
    }

    /**
     * Método responsável por listar os clientes de maneira formatada
     */
    public static void ListarClientes(){
        List<Cliente> clientes = clienteDao.lerArquivo();
        if (clientes.isEmpty())
        {
            System.out.println("Não há clientes cadastrados\n");
            return;
        }
        System.out.printf("%-10s %-20s %-15s %-30s %-15s\n", "ID", "Nome", "CPF", "Email", "Telefone");
        for (Cliente c : clientes) {
            imprimirClientes(c);
        }
        System.out.println();
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
        List<Cliente> clientes = clienteDao.lerArquivo();
        for (Cliente c : clientes)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }
        throw new NotFoundException("Funcionário não encontrado com ID: " + id + "\n");
    }

    public static Cliente buscarPorCpf(String cpf) throws NotFoundException
    {
        List<Cliente> clientes = clienteDao.lerArquivo();
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                return c;
            }
        }
        throw new NotFoundException("Cliente não encontrado com CPF: " + cpf + "\n");
    }

    /**
     * Método responsável por retornar o maior id entre os clientes
     * @return maior o maior id dos clientes
     */
    public static int MaiorID(List<Cliente> clientes) {
        int maior = 0;

        for (Cliente c : clientes) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior;
    }

    /**
     * Método responsável por verificar se um cpf já existe na lista de clientes
     * @param cpf o cpf a ser verificado
     * @return boolean true se o cpf já existe, false se não existe
     */
    public static boolean jaExisteCpf(String cpf) throws NotFoundException {
        List<Cliente> clientes = clienteDao.lerArquivo();
        if(clientes.isEmpty())
        {
            throw new NotFoundException("Não há clientes cadastrados\n");
        }
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * metodo para verificar se o arquivo contem algo ou nao
     */
    public static boolean verificarArquivo()
    {
        List<Cliente> clientes = clienteDao.lerArquivo();
        return clientes.isEmpty();
    }

    /**
     * Método responsável por printar o carro no vallet
     * @param cpf o cpf do cliente
     * @param idCarro o id do carro
     * @return string formatada em stringBuider com o nome do cliente
     */
    public static String printToListVallet(String cpf, int idCarro)
    {
        List<Cliente> clientes = clienteDao.lerArquivo();
        StringBuilder lista = new StringBuilder();
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                lista.append(c.getNome()).append(" - ").append(c.getCpf()).append("\n");
                for (Carro carro : c.getCarros())
                {
                    if (carro.getId() == idCarro)
                    {
                        lista.append(" | ").append(carro.getPlaca()).append(" - ").append(carro.getModelo()).append(" - ").append(carro.getCor()).append("\n");
                    }
                }
            }
        }
        String listaString = lista.toString();
        return listaString.replace("\n", " ");
    }

    /**
     * Método responsável por printar o nome do cliente no vallet
     * @param cpf o cpf do cliente
     * @return string formatada em stringBuider com o nome do cliente
     */
    public static String printToVallet(String cpf)
    {
        List<Cliente> clientes = clienteDao.lerArquivo();
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                return c.getNome();
            }
        }
        return null;
    }
}