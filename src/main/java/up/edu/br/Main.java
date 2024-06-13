package up.edu.br;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.models.Cliente;
import up.edu.br.views.ClienteView;
import up.edu.br.views.FuncionarioView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {


        logger.info("Iniciando sistema");
        ClienteView.CadastrarCliente();
        ClienteView.CadastrarCliente();

        ClienteView.ListarClientes();

        ClienteView.AlterarCliente();

        ClienteView.ExcluirCliente();

        ClienteView.ListarCarrosCliente();
    }
}