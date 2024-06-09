package up.edu.br.controllers;

import up.edu.br.daos.FuncionarioDao;
import up.edu.br.models.Funcionario;

public class FuncionarioController {

    private static final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public static void cadastrarFuncionario(String nome, String cpf, String email, String telefone)
    {
        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone);
        funcionarioDao.cadastrar(funcionario);
    }

    public static void alterarFuncionario(int id, String nome, String cpf, String email, String telefone)
    {
        Funcionario funcionario = new Funcionario(id, nome, cpf, email, telefone);
        funcionarioDao.alterar(id, funcionario);
    }

    public static void excluirFuncionario(int id) {
        funcionarioDao.excluir(id);
    }

    public static void listarFuncionarios() {
        funcionarioDao.listar();
    }

    public static void buscarFuncionarioPorId(int id) {
        Funcionario f = funcionarioDao.buscarPorId(id);
        System.out.println(f);
    }
}
