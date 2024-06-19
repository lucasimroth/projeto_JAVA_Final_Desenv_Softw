package up.edu.br.controllers;

import up.edu.br.Exception.NotFoundException;
import up.edu.br.daos.FuncionarioDao;
import up.edu.br.models.Funcionario;

import java.util.List;

public class FuncionarioController{
    static FuncionarioDao funcionarioDao = new FuncionarioDao();

    /**
     * Método responsável por alterar um funcionário usando a lista e salvar no arquivo
     * @param id o id do funcionário a ser alterado
     * @param nome o novo nome do funcionário
     * @param email o novo email do funcionário
     * @param telefone o novo telefone do funcionário
     */
    public static void alterarFuncionario(int id,String nome, String email, String telefone)
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        if (funcionarios.isEmpty())
        {
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                f.setNome(nome);
                f.setEmail(email);
                f.setTelefone(telefone);
                funcionarioDao.salvarArquivo(funcionarios);
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    /**
     * Método responsável por excluir um funcionário da lista e salvar no arquivo a lista atualizada
     * @param id o id do funcionário a ser excluído
     */
    public static void excluirFuncionario(int id)
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        if (funcionarios.isEmpty())
        {
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                funcionarios.remove(f);
                funcionarioDao.salvarArquivo(funcionarios);
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    /**
     * Método responsável por listar os funcionários de maneira formatada
     */
    public static void listarFuncionarios()
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        if (funcionarios.isEmpty())
        {
            System.out.println("Não há funcionários cadastrados\n");
            return;
        }
        System.out.printf("%-10s %-20s %-15s %-30s %-15s\n", "ID", "Nome", "CPF", "Email", "Telefone");
        for (Funcionario f : funcionarios)
        {
            imprimirFuncionario(f);
        }
        System.out.println();
    }

    /**
     * Método responsável por buscar um funcionário pelo id, lanca uma exceção caso não encontre
     * @param id o id do do funcionario a ser buscado
     * @return Funcionario O funcionário encontrado, ou NotFoundException caso não encontre
     */
    public static Funcionario buscarPorId(int id) throws NotFoundException
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                return f;
            }
        }
        throw new NotFoundException("Funcionário não encontrado com ID: " + id + "\n");
    }

    /**
     * Método responsável por cadastrar um funcionário usando a lista e salvar no arquivo
     * @param nome o nome do funcionario
     * @param cpf o cpf do funcionario
     * @param email o email do funcionario
     * @param telefone o telefone do funcionario
     */
    public static void cadastrarFuncionario(String nome, String cpf, String email, String telefone)
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        if (jaExisteCpf(cpf, funcionarios))
        {
            System.out.println("\nCPF já cadastrado\n");
            return;
        }
        Funcionario f = new Funcionario(MaiorID() + 1, nome, cpf, email, telefone);
        funcionarios.add(f);
        funcionarioDao.salvarArquivo(funcionarios);
    }

    /**
     * Método responsável por verificar se um cpf já existe na lista de funcionários
     * @param cpf o cpf a ser verificado
     * @return boolean true se o cpf já existe, false se não existe
     */
    public static boolean jaExisteCpf(String cpf, List<Funcionario> funcionarios)
    {
        if(funcionarios.isEmpty())
        {
            return false;
        }
        for (Funcionario f : funcionarios)
        {
            if (f.getCpf().equals(cpf))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável por retornar o maior id da lista de funcionários
     * @return int o maior id da lista de funcionários
     */
    public static int MaiorID()
    {
        int maior = 0;
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        if (funcionarios.isEmpty())
        {
            System.out.println("Não há funcionários cadastrados\n");
            return 0;
        }
        for (Funcionario f : funcionarios)
        {
            if (f.getId() > maior)
            {
                maior = f.getId();
            }
        }
        return maior;
    }

    /**
     * Método responsável por imprimir UM funcionário de maneira formatada
     * @param f o funcionário a ser impresso
     */
    public static void imprimirFuncionario(Funcionario f)
    {
        System.out.printf("%-10d %-20s %-15s %-30s %-15s\n",
                f.getId(), f.getNome(), f.getCpf(), f.getEmail(), f.getTelefone());
    }

    /**
     * Método responsável por verificar se o arquivo de funcionários está vazio
     * @return boolean true se o arquivo está vazio, false se não está
     */
    public static boolean verificarArquivo()
    {
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        return funcionarios.isEmpty();
    }

    /**
     * Método responsável por retornar o nome de um funcionário
     * @param ifFuncionario o id do funcionário
     * @return String o nome do funcionário
     */
    public static String printToVallet(int ifFuncionario){
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        for (Funcionario f : funcionarios){
            if (f.getId() == ifFuncionario){
                return f.getNome();
            }
        }
        return null;
    }

    /**
     * Método responsável por verificar se um funcionário existe
     * @param id o id do funcionário
     * @return boolean true se o funcionário existe, false se não
     */
    public static boolean ExistsFuncionario(int id){
        List<Funcionario> funcionarios = funcionarioDao.lerArquivo();
        for (Funcionario f : funcionarios){
            if (f.getId() == id){
                return true;
            }
        }
        return false;
    }

}
