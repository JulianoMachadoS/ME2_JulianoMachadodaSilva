/*
Nome: Juliano Machado da Silva
Numero de Matrícula: 25108646-8
*/

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Construtor ct = new Construtor();
        System.out.println("\f");
        boolean loop = true;

        // atributos - TODOS EM STRING

        String nome = "!";
        String primeiroNome;
        String primeiroNomeInvertido;
        String ehpalindromo;
        String vogais = "";
        String consoantes = "";
        String letras = "";

        String dataAniversarioUsuario = "!";
        String diaDaSemana = "";
        String dia;
        String mes;
        String ano;
        String diasParaAniversario = "";
        String idadeUsuario = "";
        String signo = "";

        String opcao;
        String historico = ""; // Historico de pessoas (vetor-string)

        while (loop){

            opcao = imprimeMenu(nome, dataAniversarioUsuario);
            System.out.println();

            switch (opcao) {
                case "1":

                    nome = ct.formataNomeUsuario();
                    letras = ct.contaletras(nome);
                    consoantes = ct.contaconsoantes(nome);
                    vogais = ct.contavogais(nome);

                    primeiroNome = ct.pegaPrimeiroNome(nome);
                    primeiroNomeInvertido = ct.geraAnagramaDoTipoPalinromoComPrimeiroNome(primeiroNome);
                    ehpalindromo = ct.ehPalindormo(primeiroNome, primeiroNomeInvertido);

                    System.out.println("\nO seu nome (" + nome + ") possue "+ letras + " letras, sendo " +
                            consoantes + " consoantes e " + vogais + " vogais\n");

                    if (ehpalindromo.equals("true")){
                        System.out.println("O seu nome É UM PALINDROMO, o anagrama "+ primeiroNomeInvertido + " lido de traz para frente é igual " +
                                "\na ele mesmo em sua forma normal! " + primeiroNome);
                    }else {
                        System.out.println("O seu nome NÃO É UM PALINDROMO, o anagrama "+ primeiroNomeInvertido + " lido de traz para frente é diferente " +
                                "\na ele mesmo em sua forma normal! " + primeiroNome);
                    }
                    break;

                case "2":

                    dataAniversarioUsuario = ct.formataDataDeAniversario();

                    System.out.println("\nSua data de nascimento é " + dataAniversarioUsuario.substring(0,2)+
                            "/"+ dataAniversarioUsuario.substring(2,4)+ "/" + dataAniversarioUsuario.substring(4));

                    dia = ct.diaAniversario(dataAniversarioUsuario);
                    mes = ct.mesAniversario(dataAniversarioUsuario);
                    ano = ct.anoAniversario(dataAniversarioUsuario);

                    // Congruência zeller.
                    diaDaSemana = ct.diaDaSemanaDeNascimento(dia, mes, ano);

                    // SOP com o dia da semana de nascimento.
                    if (diaDaSemana.equals("Sábado") || diaDaSemana.equals("Domingo")) {
                        System.out.println("Você nasceu em um " + diaDaSemana);
                    } else {
                        System.out.println("Você nasceu em uma " + diaDaSemana);
                    }

                    // calcula e da um SOP do signo.
                    signo = ct.signodoUsuario(dia, mes);
                    System.out.println("O seu signo é " + signo);

                    diasParaAniversario = ct.diasFaltantesAniversario(dia, mes);
                    idadeUsuario = ct.descobreIdadeUsuario(dia, mes, ano);

                    if (diasParaAniversario.equals("0")) {
                        if (nome.equals("!") ){
                            System.out.println("\n ---- PARABENS HOJE É SEU ANIVERSARIO!  ----" +
                                    "\n ---- HOJE VOCÊ FAZ " + idadeUsuario + " ANOS! ----\n");
                        }else {
                            System.out.println("\n ---- PARABENS " + nome.toUpperCase() + " HOJE É SEU ANIVERSARIO!  ----" +
                                    "\n ---- HOJE VOCÊ FAZ "+ idadeUsuario + " ANOS! ----");
                        }
                    } else {
                        System.out.println( "Você tem " + idadeUsuario + " anos completos,"+
                                " e faltam " + diasParaAniversario + " dias para o seu aniversario. ");
                    }

                    // Salva no histórico se ambos nome e data estiverem prenchidos
                    if (!nome.equals("!") && !dataAniversarioUsuario.equals("!")){
                        letras = ct.contaletras(nome);
                        vogais = ct.contavogais(nome);
                        consoantes = ct.contaconsoantes(nome);
                        historico = ct.salvarPessoaNoHistorico(historico, nome, dataAniversarioUsuario,
                                letras, vogais, consoantes, signo,
                                diaDaSemana, idadeUsuario, diasParaAniversario);
                        System.out.println("\n✓ Pessoa adicionada ao histórico com sucesso!");

                        // reseta as variáveis para adicionar nova pessoa
                        nome = "!";
                        dataAniversarioUsuario = "!";
                    }

                    break;

                case "3":
                    System.out.println("\f");
                    break;

                case "4":
                    // Salva pessoa atual antes de exibir histórico
                    if (!nome.equals("!") && !dataAniversarioUsuario.equals("!")){
                        letras = ct.contaletras(nome);
                        vogais = ct.contavogais(nome);
                        consoantes = ct.contaconsoantes(nome);
                        historico = ct.atualizaPessoaNoHistorico(historico, nome, dataAniversarioUsuario,
                                letras, vogais, consoantes, signo,
                                diaDaSemana, idadeUsuario, diasParaAniversario);
                    }
                    ct.exibeHistorico(historico);
                    break;

                case "5":
                    historico = ct.removerPessoaDoHistorico(historico);
                    break;

                case "6":
                    System.out.println("\n--- SISTEMA ENCERRADO ---\n");
                    loop = false;
                    break;
            }
        }
    }

    public static String imprimeMenu (String nome, String dataAniversarioUsuario){
        Construtor ct = new Construtor();
        Scanner teclado = new Scanner(System.in);
        String opcao;
        System.out.print("");
        do {
            if (nome.equals("!" ) && dataAniversarioUsuario.equals("!")){
                System.out.print("\n----------------- MENU -----------------"+
                        "\n1 - para Adicionar nome" +
                        "\n2 - para Adicionar data de nascimento"+
                        "\n3 - para 'Limpar a tela'"+
                        "\n4 - para Ver histórico de pessoas"+
                        "\n5 - para Remover pessoa do histórico"+
                        "\n6 - para Encerrar o sistema" +
                        "\nDigite um numero para navegar entre as opções: ");

            } else if (!nome.equals("!" ) && dataAniversarioUsuario.equals("!")) {
                System.out.print("\n----------------- MENU -----------------"+
                        "\n1 - para Alterar nome" +
                        "\n2 - para Adicionar data de nascimento"+
                        "\n3 - para 'Limpar a tela'"+
                        "\n4 - para Ver histórico de pessoas"+
                        "\n5 - para Remover pessoa do histórico"+
                        "\n6 - para Encerrar o sistema" +
                        "\nDigite um numero para navegar entre as opções: ");
            }else if (nome.equals("!" ) && !dataAniversarioUsuario.equals("!")){
                System.out.print("\n----------------- MENU -----------------"+
                        "\n1 - para Adicionar nome" +
                        "\n2 - para Alterar data de nascimento"+
                        "\n3 - para 'Limpar a tela'"+
                        "\n4 - para Ver histórico de pessoas"+
                        "\n5 - para Remover pessoa do histórico"+
                        "\n6 - para Encerrar o sistema" +
                        "\nDigite um numero para navegar entre as opções: ");
            }else {
                System.out.print("\n----------------- MENU -----------------"+
                        "\n1 - para Alterar nome" +
                        "\n2 - para Alterar data de nascimento"+
                        "\n3 - para 'Limpar a tela'"+
                        "\n4 - para Ver histórico de pessoas"+
                        "\n5 - para Remover pessoa do histórico"+
                        "\n6 - para Encerrar o sistema" +
                        "\nDigite um numero para navegar entre as opções: ");
            }
            opcao = teclado.nextLine();
            opcao = ct.formataOpcaoMenu(opcao);

            if (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") &&
                    !opcao.equals("4") && !opcao.equals("5") && !opcao.equals("6")){
                opcao = " ";
                System.out.println("\n------------ DIGITE UM VALOR VALIDO ----------");
            }

        } while (opcao.equals(" "));

        return opcao;
    }
}