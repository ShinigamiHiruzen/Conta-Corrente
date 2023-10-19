package app;

import conta.ContaCorrente;
import conta.ContaCorrenteEspecial;
import controller.Agencia;
import exception.EAgenciaNaoExisteException;
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
        ContaCorrenteEspecial contaCorrenteEspecial;
        int opcao, buscarCpf;
        boolean validacaoNumeroAgencia = false, validacaoConta = false, validarNumero = false;
        String numeroAgencia = null, numeroAgenciaCliente, identificadorCpf, identificadorAgencia;
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
                                | 2 - Buscar Conta
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

                            case 2 -> {
                                if (agencia != null) {
                                    do {
                                        System.out.println("Digite o número da sua conta: ");
                                        buscarCpf = sc.nextInt();
                                        System.out.println("Digite o número da sua agência: ");
                                        identificadorAgencia = sc.next();
                                        if (buscarCpf != 0) {
                                            try {
                                                for (Agencia buscarAgencia : agenciaList
                                                ) {
                                                    if (buscarAgencia.getNumeroAgencia().equals(identificadorAgencia)) {
                                                        identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                        contaCorrente = agencia.buscarConta(identificadorCpf);
                                                        System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                                                        System.out.println("Nome: " + contaCorrente.getNome());
                                                        System.out.println("CPF: " + contaCorrente.getCpf());
                                                        System.out.println("Número da conta: " + contaCorrente.getNumeroConta());
                                                        System.out.println("Saldo: " + contaCorrente.getSaldo());
                                                        System.out.println("Agência: " + contaCorrente.getAgencia());
                                                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
                                                        validacaoConta = true;
                                                    }
                                                }
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                            }
                                        } else {
                                            validacaoConta = true;
                                        }
                                    } while (!validacaoConta);
                                    validacaoConta = false;
                                } else {
                                    System.out.println("Nenhuma agência disponível!");
                                }
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
                                        System.out.println("Digite o numero da agencia que deseja abrir a sua" + " conta corrente: ");
                                        numeroAgenciaCliente = sc.next();
                                        for (Agencia agenciaSearch : agenciaList) {
                                            if (numeroAgencia.equals(agenciaSearch.getNumeroAgencia())) {
                                                do {
                                                    System.out.println("Digite o numero da conta: ");
                                                    int numeroConta = sc.nextInt();
                                                    try {
                                                        agencia.buscarNumeroConta(numeroConta);
                                                        System.out.println("Digite o seu nome: ");
                                                        String nome = sc.next();
                                                        System.out.println("Digite o seu cpf: ");
                                                        String cpf = sc.next();
                                                        agencia.cadastrarContaCorrente(numeroAgenciaCliente, numeroConta, nome, cpf);
                                                        validacaoConta = true;
                                                        System.out.println("CONTA CRIADA COM SUCESSO!!!");
                                                        validarNumero = true;
                                                    } catch (EContaNaoExisteException e) {
                                                        System.out.println("Error: " + e);
                                                    }
                                                }while (!validarNumero);
                                            }
                                        }
                                    } while (!validacaoConta);
                                    validacaoConta = false;
                                }

                                case 2 -> {
                                    do {
                                        System.out.println("Digite o numero da agencia que deseja abrir a sua" + " conta corrente: ");
                                        numeroAgenciaCliente = sc.next();
                                        for (Agencia agenciaSearch : agenciaList) {
                                            if (agenciaSearch.getNumeroAgencia().equals(numeroAgenciaCliente)) {
                                                do{
                                                    System.out.println("Digite o numero da conta: ");
                                                    int numeroConta = sc.nextInt();
                                                    try {
                                                        agencia.buscarNumeroConta(numeroConta);
                                                        System.out.println("Digite o seu nome: ");
                                                        String nome = sc.next();
                                                        System.out.println("Digite o seu cpf: ");
                                                        String cpf = sc.next();
                                                        System.out.println("Digite o limite de crédito: ");
                                                        float limiteCredito = sc.nextFloat();
                                                        agencia.cadastrarContaCorrenteEspecial(numeroAgenciaCliente, numeroConta, nome, cpf, limiteCredito);
                                                        validacaoConta = true;
                                                        System.out.println("CONTA CRIADA COM SUCESSO!!!");
                                                        validarNumero = true;
                                                    } catch (EContaNaoExisteException e) {
                                                        System.out.println("Error: " + e);
                                                    }
                                                }while (!validarNumero);
                                            }
                                        }
                                    } while (!validacaoConta);
                                    validacaoConta = false;
                                }

                                case 3 -> {
                                    do {
                                        System.out.println("Digite o número da sua conta: ");
                                        buscarCpf = sc.nextInt();
                                        System.out.println("Digite o número da sua agência: ");
                                        identificadorAgencia = sc.next();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                String buscarAgencia = agencia.buscarAgencia(identificadorAgencia);
                                                System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                if (contaCorrente != null && buscarAgencia != null) {
                                                    System.out.println("Digite o valor que deseja sacar: ");
                                                    float valor = sc.nextFloat();
                                                    contaCorrente.saque(valor);
                                                    System.out.println("SAQUE REALIZADO COM SUCESSO!!!");
                                                    System.out.println("VOCÊ SACOU R$" + valor);
                                                    System.out.println("O SEU SALDO ATUALMENTE É R$" + contaCorrente.getSaldo());
                                                    validacaoConta = true;
                                                }
                                            } catch (EContaNaoExisteException | ESaldoInsuficienteException | EAgenciaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
                                        }
                                    } while (!validacaoConta);
                                }

                                case 4 -> {
                                    do {
                                        System.out.println("Digite o número da sua conta: ");
                                        buscarCpf = sc.nextInt();
                                        System.out.println("Digite o número da sua agência: ");
                                        identificadorAgencia = sc.next();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                String buscarAgencia = agencia.buscarAgencia(identificadorAgencia);
                                                if (contaCorrente != null && buscarAgencia != null) {
                                                    System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                    System.out.println("Digite o valor que deseja depositar: ");
                                                    float valor = sc.nextFloat();
                                                    contaCorrente.deposito(valor);
                                                    System.out.println("DEPOSITO REALIZADO COM SUCESSO!!!");
                                                    System.out.println("VOCÊ DEPOSITOU R$" + valor);
                                                    System.out.println("O SEU SALDO ATUALMENTE É R$" + contaCorrente.getSaldo());
                                                    validacaoConta = true;
                                                }
                                            } catch (EContaNaoExisteException | EAgenciaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
                                        }
                                    } while (!validacaoConta);
                                }

                                case 5 -> {
                                    for (Agencia list : agenciaList) {
                                        System.out.println("Número da agência: " + list.getNumeroAgencia());
                                        System.out.println("Local da agência: " + list.getLocal());
                                        System.out.println("Nome do gerente: " + list.getNomeGerente());
                                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                                    }
                                }

                                case 6 -> {
                                    do {
                                        System.out.println("Digite o número da sua conta: ");
                                        buscarCpf = sc.nextInt();
                                        System.out.println("Digite o número da sua agência: ");
                                        identificadorAgencia = sc.next();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrente = agencia.buscarConta(identificadorCpf);
                                                String buscarAgencia = agencia.buscarAgencia(identificadorAgencia);
                                                if (contaCorrente != null && buscarAgencia != null) {
                                                    System.out.println("CONTA LOCALIZADA COM SUCESSO!");
                                                    System.out.println("Digite o seu nome: ");
                                                    String nome = sc.next();
                                                    System.out.println("Digite o seu cpf: ");
                                                    String cpf = sc.next();
                                                    contaCorrente.alterarDados(nome, cpf);
                                                    System.out.println("DADOS ALTERADOS COM SUCESSO");
                                                    validacaoConta = true;
                                                }
                                            } catch (EContaNaoExisteException | EAgenciaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                                validacaoConta = false;
                                            }
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
                                            }
                                        }
                                    } while (!validacaoConta);
                                }

                                case 8 -> {
                                    do {
                                        System.out.println("Digite o numero da sua conta");
                                        buscarCpf = sc.nextInt();
                                        if (buscarCpf != 0) {
                                            try {
                                                identificadorCpf = agencia.buscarCpf(buscarCpf);
                                                contaCorrenteEspecial = (ContaCorrenteEspecial) agencia.buscarConta(identificadorCpf);
                                                System.out.println("O seu limite de crédito é R$" + contaCorrenteEspecial.getLimiteCredito());
                                                validacaoConta = true;
                                            } catch (EContaNaoExisteException e) {
                                                System.out.println("Error: " + e);
                                            }
                                        }
                                    } while (!validacaoConta);
                                }

                                case 9 -> System.out.println("Saindo...");

                                default -> System.out.println("Opção Inválida!");
                            }
                        } while (opcaoCliente != 9);
                    } else {
                        System.out.println("Não existe agência disponível!");
                    }
                }

                case 9 -> System.out.println("Saindo...");

                default -> System.out.println("Opção Inválida!");
            }
        } while (opcao != 9);
    }
}