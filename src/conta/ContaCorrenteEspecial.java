package conta;

import Check.ValidationFieldsBank;
import exception.ESaldoInsuficienteException;
import exception.EValorInvalidoException;

public class ContaCorrenteEspecial extends ContaCorrente implements ValidationFieldsBank {

    private float limiteCredito;

    public ContaCorrenteEspecial(int agencia, int numeroConta, String nome, String cpf, float limiteCredito) {
        super(agencia, numeroConta, nome, cpf);
        if (validacaoLimiteCredito(limiteCredito)){
            this.limiteCredito = limiteCredito;
        }
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    @Override
    public void saque(float valor) throws ESaldoInsuficienteException {
        if (valor <= 0){
            throw new EValorInvalidoException("O valor do saque deve ser positivo.");
        }
       if (limiteCredito > 0 && valor <= limiteCredito) {
            limiteCredito -= valor;
        }else  {
            throw new ESaldoInsuficienteException("Saldo e limite de crédito insuficiente para o saque.");
        }
    }

}