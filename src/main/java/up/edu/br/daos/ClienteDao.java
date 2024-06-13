package up.edu.br.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.models.Cliente;

import java.util.List;

public class ClienteDao implements DaoInterface<Cliente> {
    public static final Logger logger = LogManager.getLogger(ClienteDao.class);
    public static final String FILE_NAME_CLIENTE = "clientes.txt";

    @Override
    public List<Cliente> lerArquivo() {
        logger.info("Iniciando leitura do arquivo clientes");
        return null;
    }

    @Override
    public void salvarArquivo(List<Cliente> clientes) {
        logger.info("Iniciando save do arquivo clientes");
    }

    @Override
    public String FormatacaoString(Cliente cliente) {
        return cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getCpf() + ";" + cliente.getTelefone() + ";" + cliente.getEmail();
    }
}
