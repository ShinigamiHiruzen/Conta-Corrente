package controller;

import check.ValidationFieldsBank;
import conta.ContaCorrente;
import conta.ContaCorrenteEspecial;
import exception.EContaNaoExisteException;

import java.util.ArrayList;
import java.util.List;

public class Agencia implements ValidationFieldsBank {

    private String numeroAgencia;
    private String local;
    private String nomeGerente;

    private List<ContaCorrente> contaCorrenteList;

    public Agencia(String numeroAgencia, String local, String nomeGerente) {
        if (validarAgencia(numeroAgencia) && validarNomes(nomeGerente) && validarLocal(local)){
            this.numeroAgencia = numeroAgencia;
            this.local = local;
            this.nomeGerente = nomeGerente;
            contaCorrenteList = new ArrayList<>();
        }
    }

    public void cadastrarContaCorrente(int agencia, int numeroConta, String nome, String cpf){
        ContaCorrente contaCorrente = new ContaCorrente(agencia, numeroConta, nome, cpf);
        contaCorrenteList.add(contaCorrente);
    }

    public void cadastrarContaCorrenteEspecial(int agencia, int numeroConta, String nome, String cpf, float credito) {
        ContaCorrente contaCorrente = new ContaCorrenteEspecial(agencia, numeroConta, nome, cpf, credito);
        contaCorrenteList.add(contaCorrente);
    }

    public ContaCorrente buscarConta(String cpf) throws EContaNaoExisteException {
        for (ContaCorrente contaCorrente : contaCorrenteList){
            if (contaCorrente.getCpf().equals(cpf)){
                return contaCorrente;
            }
        }
        throw new EContaNaoExisteException("Esse cpf não está associado a nenhuma conta.");
    }

    public String buscarCpf(String cpf) throws EContaNaoExisteException {
        for (ContaCorrente contaCorrente : contaCorrenteList){
            if (contaCorrente.getCpf().equals(cpf)){
                return cpf;
            }
        }
        throw new EContaNaoExisteException("Esse cpf não está associado a nenhuma conta.");
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public String getLocal() {
        return local;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }
}