package up.edu.br.views;

import up.edu.br.controllers.HistoricoController;

public class HistoricoView {
    /**
     * Método responsável por exibir o menu de Historico
     * como o historico só tem uma função ele só a realiza e finaliza
     */
    public static void menu(){
        System.out.println("---------------------------------------Historico------------------------------------\n");
        System.out.println("Tipo - Funcionario - Cliente - CPF - Carro - Entrada - Saida\n");
        HistoricoController.listarHistorico();
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
    }


}
