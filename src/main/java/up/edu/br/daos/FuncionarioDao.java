package up.edu.br.daos;

import up.edu.br.models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao implements CrudInterface<Funcionario> {
    private List<Funcionario> funcionarios = CarregarFuncionarios();

    private List<Funcionario> CarregarFuncionarios() {
        return new ArrayList<>();
    }

    @Override
    public Funcionario buscarPorCpf(String cpf) {
        return null;
    }

    @Override
    public void salvarArquivo() {

    }

    @Override
    public void lerArquivo() {

    }

    @Override
    public void cadastrar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    @Override
    public void alterar() {

    }

    @Override
    public void excluir() {

    }

    @Override
    public void listar() {

    }
}
