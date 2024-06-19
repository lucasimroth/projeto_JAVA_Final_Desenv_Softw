package up.edu.br.validar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidaCrud {
    static Scanner scan = new Scanner (System.in);
    private static final Logger logger = LogManager.getLogger(ValidaCrud.class);


    /**
     * Método responsável por validar o ID
     * @return o ID validado
     */
    public static int validaId(){
        logger.info("Iniciando validação do ID");
        while(true) {
            try {
                int id = scan.nextInt();
                scan.nextLine();
                if(id > 0){
                    return id;
                }
                System.out.println("ID inválido, digite novamente: ");
            } catch (InputMismatchException e) {
                logger.error("Erro ao validar ID", e);
                System.out.println("ID inválido, digite novamente: ");
            }
        }
    }

    /**
     * Método responsável por confirmar se o ID é o desejado
     * @return true se o ID for confirmado, false se não for
     */
    public static boolean confirmarId() {
        System.out.println("é o usuário que deseja? (1 - sim, 2 - não)");
        int confirmacao = scan.nextInt();
        scan.nextLine();
        return confirmacao != 1;
    }

    /**
     * Método responsável por confirmar o CPF
     * @return true se o CPF for confirmado, false se não for
     */
    public static boolean confirmarCpf() {
        System.out.println("\né o Cliente que deseja? (1 - sim, 2 - não)");
        int confirmacao = scan.nextInt();
        scan.nextLine();
        return confirmacao != 1;
    }

    /**
     * Método responsável por validar o nome
     * @return o nome validado
     */
    public static String validaNome(){
        String nome = scan.nextLine();
        while (!nome.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Nome inválido, digite novamente: ");
            nome = scan.nextLine();
        }
        return nome;
    }

    /**
     * Método responsável por validar o CPF
     * @return o CPF validado
     */
    public static String validaCpf(){
        String cpf = scan.nextLine().replaceAll("\\D", "");
        while(!validaCpf2(cpf)){
            cpf = scan.nextLine().replaceAll("\\D", "");
        }
        return cpf;
    }

    /**
     * Metodo auxiliar que faz a validação do CPF atraves dos calculos
     * @param cpf o cpf a ser validado
     * @return true se o cpf for válido, false se não for
     */
    public static boolean validaCpf2(String cpf){

        if(cpf.length() != 11 || !cpf.matches("\\d+") || cpf.matches("(\\d)\\1*"))
        {
            System.out.println("CPF inválido, digite novamente: ");
            return false;
        }
        for(int j = 0; j < 2; j++){
            int soma = 0, resto;
            for(int i = 10 + j; i >= 2; i--){
                soma += i * Integer.parseInt(cpf.charAt(j + 10 - i) + "");
            }
            resto = (soma % 11);

            if(resto < 2) {
                resto = 0;
            } else {
                resto = 11 - resto;
            }

            if(resto != Integer.parseInt(cpf.charAt(j + 9) + "")){
                System.out.println("CPF inválido, digite novamente: ");
                return false;
            }
        }
        return true;
    }

    /**
     * Método responsável por validar o email
     * @return o email validado
     */
    public static String validaEmail(){
        String email = scan.nextLine();
        while (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            System.out.println("Email inválido, digite novamente: ");
            email = scan.nextLine();
        }
        return email;
    }

    /**
     * Método responsável por validar o telefone
     * @return o telefone validado
     */
    public static String validaTelefone(){
        String telefone = scan.nextLine();
        while (!telefone.matches("\\d+") || (telefone.length() < 8 || telefone.length() > 11) || telefone.matches("(\\d)\\1*")){
            System.out.println("Telefone inválido, digite novamente: ");
            telefone = scan.nextLine();
        }
        return telefone;
    }

    /**
     * Método responsável por validar a placa
     * @return a placa validada
     */
    public static String validaPlaca(){
        String placa = scan.nextLine().replaceAll("[^a-zA-Z0-9]", "");
        while (!placa.matches("[a-zA-Z]{3}\\d{4}")){
            System.out.println("Placa inválida, digite novamente: ");
            placa = scan.nextLine().replaceAll("[^a-zA-Z0-9]", "");
        }
        return placa;
    }

    /**
     * Método responsável por validar o modelo
     * @return o modelo validado
     */
    public static String validaModelo(){
        String modelo = scan.nextLine();
        while (!modelo.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Modelo inválido, digite novamente: ");
            modelo = scan.nextLine();
        }
        return modelo;
    }

    /**
     * Método responsável por validar a cor
     * @return a cor validada
     */
    public static String validaCor(){
        String cor = scan.nextLine();
        while (!cor.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Cor inválida, digite novamente: ");
            cor = scan.nextLine();
        }
        return cor;
    }

}
