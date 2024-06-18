package up.edu.br.daos;

import java.util.List;

/**
 * Interface para os DAOs
 * @param <T> Tipo de objeto
 */
public interface DaoInterface<T> {
    List<T> lerArquivo();
    void salvarArquivo(List<T> lista);
    String FormatacaoString(T t);
}
