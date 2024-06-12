package up.edu.br.daos;

public interface CrudInterface<O> {

    void salvarArquivo();

    void cadastrar(O o);
    void alterar(int identificador, O o);
    void excluir(int identificador);
    void listar();
}
