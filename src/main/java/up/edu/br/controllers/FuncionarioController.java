package up.edu.br.controllers;

import up.edu.br.daos.CrudInterface;
import up.edu.br.models.Funcionario;

public class FuncionarioController {
    public void cadastrarFuncionario(String nome, String cpf, String email, String telefone) {
        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone);

    }

    public void alterarFuncionario() {
    }

    public void excluirFuncionario() {
    }

    public void listarFuncionarios() {
    }

    public void buscarFuncionarioPorCpf() {
    }
}
