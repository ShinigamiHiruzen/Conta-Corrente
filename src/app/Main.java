package app;

import conta.ContaCorrente;
import controller.Agencia;
import exception.EContaNaoExisteException;
import exception.ESaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Agencia agencia = null;
        ContaCorrente contaCorrente;
        int opcao, buscarCpf;
        boolean validacaoNumeroAgencia = false, validacaoConta = false;
        String numeroAgencia = null, numeroAgenciaCliente, identificadorCpf;
        List<Agencia> agenciaList = new ArrayList<>();

        do {
            System.out.println("""
                    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                              SISTEMA DE AGÊNCIAS
                    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                    | 1 - Gerente
                    | 2 - Cliente
                    | 9 - Sair
                    """);
            opcao = sc.nextInt();

            switch (opcao) {

                case 1 -> {
                    int opcaoGer;
                    do {
                        System.out.println("""
                                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                                           BEM-VINDO GERENTE
                                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                                | 1 - Criar Agência
                                | 9 - Sair
                                """);

                        opcaoGer = sc.nextInt();

                        switch (opcaoGer) {

                            case 1 -> {
                                do {
                                    System.out.println("Digite um número para a agência: ");
                                    numeroAgencia = sc.next();
                                    for (Agencia agenciaSearch : agenciaList) {
                                        if (numeroAgencia.equals(agenciaSearch.getNumeroAgencia())) {
                                            System.out.println("Já existe uma agência com esse número.");
                                            validacaoNumeroAgencia = true;
                                        } else {
                                            validacaoNumeroAgencia = false;
                                        }
                                    }
                                } while (validacaoNumeroAgencia);

                                System.out.println("Digite o local da sua agência: ");
                                String localAgencia = sc.next();
                                System.out.println("Digite o nome do gerente: ");
                                String nomeGerente = sc.next();
                                agencia = new Agencia(numeroAgencia, localAgencia, nomeGerente);
                                agenciaList.add(agencia);
                                validacaoConta = false;
                                System.out.println("AGÊNCIA CRIADA COM SUCESSO!!!");
                            }

                            case 9 -> System.out.println("Saindo...");

                            default -> System.out.println("Opção Inválida!");
                        }
                    } while (opcaoGer != 9);
                }

                case 2 -> {
                    if (agencia != null) {
                        int opcaoCliente;

                        do {
                            System.out.println("""
                                    \n
                                                                                
                                    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                                               BEM-VINDO CLIENTE
                                    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                                    | 1 - Abrir Conta Corrente
                                    | 2 - Abrir Conta Corrente Especial
                                    | 3 - Sacar
                                    | 4 - Depositar
                                    | 5 - Lista de Agências Disponíveis
                                    | 6 - Alterar Dados
                                    | 7 - Consultar Saldo
                                    | 9 - Sair
                                    """);
                            opcaoCliente = sc.nextInt();

                            switch (opcaoCliente) {

                                case 1 -> {
                                    do {
                                        System.out.println("Digite o numero da agencia que deseja abrir a sua" +
                                                " conta corrente: ");
                                        numeroAgenciaCliente = sc.next();
                                        for (Agencia agenciaSearch : agenciaList) {
                                            if (numeroAgencia.equals(agenciaSearch.getNumeroAgencia())) {
                                                System.out.println("Digite o numero da conta: ");
                                                int numeroConta = sc.nextInt();
                                                System.out.println("Digite o seu nome: ");
                                                String nome = sc.next();
                                                System.out.println("Digite o seu cpf: ");
                                                String cpf = sc.next();
                                                agencia.cadastrarContaCorrente(numeroAgenciaCliente, numeroConta,
                                                        nome, cpf);
                                                validacaoConta = true;
                                            }
                                        }
                                    } while (!validacaoConta);
                                    validacaoConta = false;
                                    System.out.println("CONTA CRIADA COM SUCESSO!!!");
                                }

                                case 2 -> {
                                    do {
                                        System.out.println("Digite o numero da agencia que deseja abrir a sua" +
                                                " conta corrente: ");
                                        numeroAgenciaCliente = sc.next();
                                        for (Agencia agenciaSearch : agenciaList) {
                                            if (agenciaSearch.getNumeroAgencia().equals(numeroAgenciaCliente)) {
                                                System.out.println("Digite o numero da conta: ");
                                                int numeroConta = sc.nextInt();
                                                System.out.println("Digite o seu nome: ");
                                                String nome = sc.next();
                                                System.out.println("Digite o seu cpf: ");
                                                String cpf = sc.next();
                                                System.out.println("Digite o limite de crédito: ");
                                                float limiteCredito = sc.nextFloat();
                                                agencia.cadastrarContaCorrenteEspecial(numeroAgenciaCliente, numeroConta,
                                                        nome, cpf, limiteCredito);
                                                validacaoConta = true;
                                            }
                                        }
                                    } while (!validacaoConta);
                                    validacaoConta = false;
                                    System.out.println("CONTA CRIADA COM SUCESSO!!!");
                                }

                                case 3 -> {
                                    do {
                                        System.out.println("Digite o numero da sua conta");
                                        buscarCpf = sc.nextInt();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                System.out.println("Digite o valor que deseja sacar: ");
                                                float valor = sc.nextFloat();
                                                contaCorrente.saque(valor);
                                                System.out.println("SAQUE REALIZADO COM SUCESSO!!!");
                                                System.out.println("VOCÊ SACOU R$" + valor);
                                                System.out.println("O SEU SALDO ATUALMENTE É R$" + contaCorrente.getSaldo());
                                                validacaoConta = true;
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            } catch (ESaldoInsuficienteException e) {
                                                System.out.println("Error:  " + e);
                                                validacaoConta = false;
                                            }
                                        } else {
                                            validacaoConta = true;
                                        }
                                    } while (!validacaoConta);
                                }

                                case 4 -> {
                                    do {
                                        System.out.println("Digite o numero da sua conta");
                                        buscarCpf = sc.nextInt();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                System.out.println("Digite o valor que deseja depositar: ");
                                                float valor = sc.nextFloat();
                                                contaCorrente.deposito(valor);
                                                System.out.println("DEPOSITO REALIZADO COM SUCESSO!!!");
                                                System.out.println("VOCÊ DEPOSITOU R$" + valor);
                                                System.out.println("O SEU SALDO ATUALMENTE É R$" + contaCorrente.getSaldo());
                                                validacaoConta = true;
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
                                        } else {
                                            validacaoConta = true;
                                        }
                                    } while (!validacaoConta);
                                }

                                case 5 -> {
                                    for (Agencia list : agenciaList) {
                                        if (list != null) {
                                            System.out.println("Número da agência: " + list.getNumeroAgencia());
                                            System.out.println("Local da agência: " + list.getLocal());
                                            System.out.println("Nome do gerente: " + list.getNomeGerente());
                                            System.out.println(
                                                    "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                                        } else {
                                            System.out.println("Não tem nenhuma agência disponível.");
                                        }
                                    }
                                }

                                case 6 -> {
                                    do {
                                        System.out.println("Digite o numero da sua conta");
                                        buscarCpf = sc.nextInt();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                System.out.println("Digite o seu nome: ");
                                                String nome = sc.next();
                                                System.out.println("Digite o seu cpf: ");
                                                String cpf = sc.next();
                                                contaCorrente.alterarDados(nome, cpf);
                                                System.out.println("DADOS ALTERADOS COM SUCESSO");
                                                validacaoConta = true;
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
                                        } else {
                                            validacaoConta = true;
                                        }
                                    } while (!validacaoConta);
                                }

                                case 7 -> {
                                    do {
                                        System.out.println("Digite o numero da sua conta");
                                        buscarCpf = sc.nextInt();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                System.out.println("O seu saldo é R$" + contaCorrente.getSaldo());
                                                validacaoConta = true;
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
                                        } else {
                                            validacaoConta = true;
                                        }
                                    } while (!validacaoConta);
                                }

                                case 9 -> System.out.println("Saindo...");

                                default -> System.out.println("Opção Inválida!");
                            }
                        } while (opcaoCliente != 9);
                    }
                }

                case 9 -> System.out.println("Saindo...");

                default -> System.out.println("Opção Inválida!");
            }
        } while (opcao != 9);
    }
}