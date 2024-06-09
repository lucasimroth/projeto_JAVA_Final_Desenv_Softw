package up.edu.br.daos;

import up.edu.br.models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao implements CrudInterface<Funcionario> {
    private final static List<Funcionario> funcionarios = CarregarFuncionarios();

    private static List<Funcionario> CarregarFuncionarios() {
        return new ArrayList<>();
    }

    public Funcionario buscarPorId(int id)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                return f;
            }
        }
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
    public void alterar(int id, Funcionario funcionario)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                f.setNome(funcionario.getNome());
                f.setCpf(funcionario.getCpf());
                f.setEmail(funcionario.getEmail());
                f.setTelefone(funcionario.getTelefone());
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    @Override
    public void excluir(int id)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                funcionarios.remove(f);
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    @Override
    public void listar()
    {
        for (Funcionario f : funcionarios)
        {
            System.out.println(f);
        }
    }
}
