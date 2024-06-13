package up.edu.br.daos;

import java.util.List;

public interface DaoInterface<T> {
    List<T> lerArquivo();
    void salvarArquivo(List<T> lista);
    String FormatacaoString(T t);
}
