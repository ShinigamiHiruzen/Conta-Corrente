package conta;

import exception.ECampoInvalidoException;
import exception.ESaldoInsuficienteException;
import exception.EValorInvalidoException;

public class ContaCorrente {

    private int agencia;
    private int numeroConta;
    protected float saldo;
    private String cpf;
    private String nome;

    public ContaCorrente(int agencia, int numeroConta, String nome, String cpf){
        if (validarCampos(nome, cpf)){
            this.agencia = agencia;
            this.numeroConta = numeroConta;
            this.nome = nome;
            this.cpf = cpf;
            saldo = 0;
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    private boolean validarCampos(String nome, String cpf){
        if (cpf.length()!=12){
            throw new ECampoInvalidoException("CPF inválido.");
        }
        return nome.matches("^[a-zA-Z\\s']+");
    }
    public void alterarDados(String nome, String cpf){
        if (validarCampos(nome, cpf)){
            this.nome = nome;
            this.cpf = cpf;
        }
    }

    public void saque(float valor) throws ESaldoInsuficienteException{
        if (valor <= 0){
            throw new EValorInvalidoException("O valor do saque deve ser positivo.");
        }
        if (valor <= saldo){
            saldo -= valor;
        }else {
            throw new ESaldoInsuficienteException("Saldo insuficiente para efetuar o saque.");
        }
    }
    public void deposito(float valor){
        if (valor <= 0){
            throw new EValorInvalidoException("O valor do saque deve ser positivo.");
        }
        saldo += valor;
    }


}