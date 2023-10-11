package exception;

public class EContaNaoExisteException extends Exception{
    public EContaNaoExisteException(String mensagem){
        super(mensagem);
    }
}
