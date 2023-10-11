package controller;

import Check.ValidationFieldsBank;
import conta.ContaCorrente;
import conta.ContaCorrenteEspecial;

import java.util.ArrayList;
import java.util.List;

public class Agencia implements ValidationFieldsBank {

    private String numeroAgencia;
    private String local;
    private String nomeGerente;
    private List<ContaCorrente> contaCorrenteList;
    public Agencia(String numeroAgencia, String local, String nomeGerente){
        if (validarAgencia(numeroAgencia) && validarNomes(nomeGerente) && validarLocal(local)){
            this.numeroAgencia = numeroAgencia;
            this.local = local;
            this.nomeGerente = nomeGerente;
            contaCorrenteList = new ArrayList<>();
        }
    }

    public void cadastrarContaCorrente(int agencia, int numeroConta, String nome, String cpf, float credito){
        ContaCorrente contaCorrente = new ContaCorrenteEspecial(agencia, numeroConta, nome, cpf, credito);
        contaCorrenteList.add(contaCorrente);
    }


}