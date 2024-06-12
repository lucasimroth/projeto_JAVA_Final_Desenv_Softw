package up.edu.br.controllers;

import up.edu.br.daos.FuncionarioDao;
import up.edu.br.models.Funcionario;

public class FuncionarioController {

    private static final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public static void cadastrarFuncionario(String nome, String cpf, String email, String telefone)
    {

        Funcionario funcionario = new Funcionario(FuncionarioDao.MaiorID()+1, nome, cpf, email, telefone);
        funcionarioDao.cadastrar(funcionario);
    }

    public static void alterarFuncionario(int id, String nome, String email, String telefone)
    {
        Funcionario funcionario = new Funcionario(id, nome, email, telefone);
        funcionarioDao.alterar(id, funcionario);
    }

    public static void excluirFuncionario(int id) {
        funcionarioDao.excluir(id);
    }

    public static void listarFuncionarios() {
        funcionarioDao.listar();
    }

    /**
     * Método que busca um funcionário pelo id
     * @param id
     */
    public static void buscarFuncionarioPorId(int id) {
        Funcionario f = funcionarioDao.buscarPorId(id);
        if (f != null)
        {
            funcionarioDao.imprimirFuncionario(f);
        }
        else
        {
            System.out.println("Funcionário não encontrado\n");
        }
    }

    public static boolean jaExisteCpf(String cpf)
    {
        return funcionarioDao.jaExisteCpf(cpf);
    }

    public static void SalvarArquivo()
    {
        funcionarioDao.salvarArquivo();
    }
}
