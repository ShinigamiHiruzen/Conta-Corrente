package exception;

public class EAgenciaNaoExisteException extends Exception{

    public EAgenciaNaoExisteException(String mensagem){
        super(mensagem);
    }
}