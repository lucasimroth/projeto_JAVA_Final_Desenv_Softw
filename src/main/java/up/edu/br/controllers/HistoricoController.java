package up.edu.br.controllers;

import up.edu.br.daos.HistoricoDao;
import up.edu.br.models.Historico;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HistoricoController {
    public static void salvarHistorico(String tipo, int idFuncionario, String cpf, int idCarro, LocalTime horarioEntrada, LocalTime horarioSaida) {
        Historico historico = new Historico(tipo, idFuncionario, cpf, idCarro, horarioEntrada, horarioSaida);
        List<Historico> lista = new HistoricoDao().lerArquivo();

        lista.add(historico);
        new HistoricoDao().salvarArquivo(lista);
    }

    public static void listarHistorico() {
        List<Historico> lista = new HistoricoDao().lerArquivo();
        if (lista.isEmpty()) {
            System.out.println("Não há histórico\n");
            return;
        }
        for (Historico historico : lista) {
            System.out.printf(historico.getTipo() + " - " +
                    FuncionarioController.printToVallet(historico.getIdFuncionario()) + " - " +
                    ClienteController.printToVallet(historico.getCpf()) + " - " +
                    historico.getCpf() + " - " +
                    CarroController.printToVallet(historico.getIdCarro(), historico.getCpf()) + " - " +
                    historico.getHorarioEntrada() + " - " +
                    historico.getHorarioSaida());
        }
    }
}
