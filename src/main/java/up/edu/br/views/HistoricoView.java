package up.edu.br.views;

import up.edu.br.controllers.HistoricoController;

public class HistoricoView {
    public static void menu(){
        System.out.println("------------------------Historico-----------------------");
        System.out.println("Tipo - Funcionario - Cliente - CPF - Carro - Entrada - Saida\n");
        HistoricoController.listarHistorico();
        System.out.println("\n\n-----------------------------------------------------\n");
    }


}
