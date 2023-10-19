package check;

import exception.ECampoInvalidoException;
import exception.EValorInvalidoException;

public interface ValidationFieldsBank {

    default boolean validarAgencia(String numeroAgencia) {
        if (numeroAgencia != null && numeroAgencia.matches("\\d{4}")){
            return true;
        }else{
            throw new ECampoInvalidoException("Um dos campos inseridos estão inválidos.");
        }
    }

    default boolean validarLocal(String local) {
        if (local != null && local.matches("^[A-Z][a-zA-Z]*$")){
            return true;
        }else {
            throw new ECampoInvalidoException("Um dos campos inseridos estão inválidos.");
        }
    }

    default boolean validarNomes(String nome) {
        if (nome.matches("^[a-zA-Z\\s']+")){
            return true;
        }else{
            throw new ECampoInvalidoException("Um dos campos inseridos estão inválidos.");
        }
    }

    default boolean validarCpf(String cpf) {
        if (cpf.length()!=12){
            throw new ECampoInvalidoException("CPF inválido.");
        }
        return true;
    }
    default boolean validacaoLimiteCredito(float limiteCredito) {
        if (limiteCredito <= 0){
            throw new EValorInvalidoException("O valor do crédito deve ser positivo.");
        }
        return true;
    }
}
