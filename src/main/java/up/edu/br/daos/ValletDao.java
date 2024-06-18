package up.edu.br.daos;

import up.edu.br.models.Vallet;

import java.util.List;

public class ValletDao implements DaoInterface<Vallet>{


    @Override
    public List<Vallet> lerArquivo() {
        return List.of();
    }

    @Override
    public void salvarArquivo(List<Vallet> lista) {

    }

    @Override
    public String FormatacaoString(Vallet vallet) {
        return "";
    }
}
