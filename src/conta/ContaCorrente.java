package conta;

import Check.ValidationFieldsBank;
import exception.ECampoInvalidoException;
import exception.ESaldoInsuficienteException;
import exception.EValorInvalidoException;

public class ContaCorrente implements ValidationFieldsBank {

    private int agencia;
    private int numeroConta;
    protected float saldo;
    private String cpf;
    private String nome;

    public ContaCorrente(int agencia, int numeroConta, String nome, String cpf){
        if (validarNomes(nome) && validarCpf(cpf)){
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

    public String getCpf() {
        return cpf;
    }
    public void alterarDados(String nome, String cpf){
        if (validarNomes(nome) && validarCpf(cpf)){
            this.nome = nome;
            this.cpf = cpf;
        }
    }

    public void saque(float valor) throws ESaldoInsuficienteException{
        if (valor <= 0){
            throw new EValorInvalidoException("O valor do saque deve ser positivo.");
        }
        if (saldo>0 && valor <= saldo){
            saldo -= valor;
        }else {
            throw new ESaldoInsuficienteException("Saldo insuficiente para efetuar o saque.");
        }
    }
    public void deposito(float valor){
        if (valor <= 0){
            throw new EValorInvalidoException("O valor do depósito deve ser positivo.");
        }
        saldo += valor;
    }

}