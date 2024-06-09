package up.edu.br.daos;

import up.edu.br.models.Funcionario;

public interface CrudInterface<O> {

    void salvarArquivo();
    void lerArquivo();
    void cadastrar(O o);
    void alterar(int identificador, O o);
    void excluir(int identificador);
    void listar();
}
