package up.edu.br.Exception;

/**
 * Classe de exceção para quando um objeto não é encontrado
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
