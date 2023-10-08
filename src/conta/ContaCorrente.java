package conta;

public class ContaCorrente {

    private int agencia;
    private int numero;
    protected float saldo;
    private String cpf;
    private String nome;

    public ContaCorrente(int agencia, int numero, String nome, String cpf){
        this.agencia = agencia;
        this.numero = numero;
        this.nome = nome;
        this.cpf = cpf;
        saldo = 0;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void alterarDados(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public void saque(float valor){
        if (valor <= saldo){
            saldo -= valor;
        }
    }




}