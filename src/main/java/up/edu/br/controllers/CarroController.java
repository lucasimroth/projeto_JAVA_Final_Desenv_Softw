package up.edu.br.controllers;

import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;

import java.util.List;

public class CarroController {

    public static void AlterarCarro(List<Carro> carros, int idCarro, String modelo, String cor){
        for (Carro carro : carros) {
            if (carro.getId() == idCarro) {
                carro.setModelo(modelo);
                carro.setCor(cor);
                return;
            }
        }
    }

    public static List<Carro> ExcluirCarro(List<Carro> carros, String placa){
        for (Carro carro : carros){
            if(carro.getPlaca().equals(placa)){
                carros.remove(carro);
                return carros;
            }
        }
        System.out.println("Carro não encontrado\n");
        return carros;
    }

    public static void ListarCarros(List<Carro> carros){
        System.out.printf("     %-10s %-20s %-15s\n", "Placa", "Modelo", "Cor");
        for (Carro c : carros) {
            imprimirCarros(c);
        }
    }

    public static void imprimirCarros(Carro c)
    {
        System.out.printf("     %-10s %-20s %-15s\n",
                c.getPlaca(), c.getModelo(), c.getCor());
    }
}
