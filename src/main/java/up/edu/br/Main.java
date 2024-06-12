package up.edu.br;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.views.FuncionarioView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        FuncionarioView.alterarFuncionario();
        FuncionarioView.cadastrarFuncionario();

        FuncionarioView.listarFuncionarios();

        FuncionarioView.excluirFuncionario();

        FuncionarioView.listarFuncionarios();

        FuncionarioController.SalvarArquivo();
    }
}