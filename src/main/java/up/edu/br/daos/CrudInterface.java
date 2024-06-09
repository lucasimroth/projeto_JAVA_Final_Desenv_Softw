package up.edu.br.daos;

import up.edu.br.models.Funcionario;

public interface CrudInterface<O> {

    Funcionario buscarPorCpf(String cpf);

    void salvarArquivo();
    void lerArquivo();
    void cadastrar(O o);
    void cadastrar(Funcionario funcionario);
    void alterar();
    void excluir();
    void listar();
}
