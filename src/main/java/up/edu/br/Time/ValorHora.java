package up.edu.br.Time;

import up.edu.br.Time.Horarios;

import java.time.LocalTime;

public class ValorHora {

    //FUNCAO PARA COBRAR AS HORAS
    public static void CobrarHoras(LocalTime entrada, LocalTime saida){
        //FAZER LOG
        System.out.println("VALOR COBRADO: R$" + (CalcularValor(entrada,saida)));

    }

    //FUNCAO PARA SABER O VALOR RESPECTIVO COM AS HORAS USADAS
    public static float CalcularValor(LocalTime entrada, LocalTime saida){
        int diferenca = Horarios.VerDiferençaHorarios(entrada, saida);

        if(diferenca < 60){

            return (float)(diferenca * 0.20);

        } else if (diferenca == 60) {

            return 10.0f;

        } else if (diferenca < 120){

            return (float)(diferenca * 0.15);

        } else if (diferenca < 240){

            return (float)(diferenca * 0.10);

        } else if (diferenca > 240) {

            return 6.0f + (float)(diferenca * 0.02);

        } else{

            return 0;

        }
    }
}
