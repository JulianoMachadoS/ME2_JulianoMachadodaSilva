/*
Nome: Juliano Machado da Silva
Numero de Matricula: 25108646-8
*/

import java.text.Normalizer;
import java.util.Date;
import java.util.Scanner;

public class Construtor {
    
    public String formataOpcaoMenu (String opcao){
        Removedores r = new Removedores();
        if(opcao.length() > 0){
            opcao = r.removeEspacos(opcao);
            opcao = r.removeSimbulos(opcao);
            opcao = r.removeLetras(opcao);
            opcao = r.removeEspacosInicioFim(opcao);
            opcao = numeroDeMaiorEvidencia(opcao);
        }
        return opcao;
    }
    
    public String numeroDeMaiorEvidencia(String numero){
        String contagem = "0";
        String numeroMaisEvidente = " ";

        for (int i = 0; i < numero.length(); i++){
            String novaContagem = "0";
            String numeroTeste = " ";

            for (int j = 0; j < numero.length(); j++){
                if ( numero.charAt(i) == numero.charAt(j) ){
                    novaContagem = somaStringNumerica(novaContagem, "1");
                    numeroTeste = numero.substring(i,i+1);
                }
            }
            if(comparaStringNumerica(contagem, novaContagem) < 0){
                contagem = novaContagem;
                numeroMaisEvidente = numeroTeste.substring(0);
            }
        }
        return numeroMaisEvidente;
    }

    public String formataNomeUsuario(){
        Contadores c = new Contadores();
        Removedores r = new Removedores();
        Scanner teclado  = new Scanner (System.in);
        String nome = " ";
        String contador = "0";
        
        do {
            if (contador.equals("0")){
                System.out.print("Digite seu nome ");
            }else if (comparaStringNumerica(contador, "2") < 0){
                System.out.print("\nDigite um nome valido ");
            }else {
                System.out.print("\nDeve conter no mínimo 3 caracteres" +
                        "\nEx: Ana\nDigite um nome valido ");
            }
            
            
            nome = teclado.nextLine();
            nome = nome.toLowerCase();
            
            if(comparaStringNumerica(c.contaLetras(nome), "3") >= 0){
                nome = r.removeSimbulos(nome);
                nome = r.removeEspacosDuplos(nome);
                nome = r.removeNumeros(nome);
                nome = r.removeEspacosInicioFim(nome);
                nome = primeiraLetraMaiuscula(nome);
                nome = maiusculaDepoisDoespaco(nome);
            }
            contador = somaStringNumerica(contador, "1");
        }while (comparaStringNumerica(c.contaLetras(nome), "3") < 0);

        return nome;
    }
    
    public String contaletras(String nome){
        Contadores c = new Contadores();
        return c.contaLetras(nome);
    }
    
    public String contavogais(String nome){
        Contadores c = new Contadores();
        return c.contaVogais(nome);
    }
    
    public String contaconsoantes (String nome){
        Contadores c = new Contadores();
        return c.contaConsoantes(nome);
    }
    
    public String pegaPrimeiroNome (String nome){
        for (int i = 0; i < nome.length(); i++){
            if ( nome.charAt(i) == ' ') {
                return nome.substring(0, i);
            }
        }
        return nome;
    }
    
    public String primeiraLetraMaiuscula(String texto){
        texto = texto.substring(0,1).toUpperCase() + texto.substring(1);
        return texto;
    }
    
    public String maiusculaDepoisDoespaco (String texto){
        Verificadores v = new Verificadores();
        for (int i = 0; i < texto.length() - 1; i++){
            if (texto.charAt(i) == ' ' && v.ehLetradoAlfabetoAZ(texto.charAt(i+1)) ){
                texto = texto.substring(0,i+1) + Character.toUpperCase(texto.charAt(i+1)) + texto.substring(i+2, texto.length());
            }
        }
        return texto;
    }
    
    public String geraAnagramaDoTipoPalinromoComPrimeiroNome(String primeiroNome){
        Removedores r = new Removedores();
        Verificadores v = new Verificadores();
        String possivelPalindomo = " ";


        for (int i = primeiroNome.length() - 1; i >= 0 ; i--) {
            if (v.ehLetradoAlfabetoAZ(primeiroNome.charAt(i))) {
                possivelPalindomo = possivelPalindomo + primeiroNome.charAt(i);
            }
        }
        possivelPalindomo = r.removeEspacos(possivelPalindomo);
        return possivelPalindomo;
    }
    
    public String ehPalindormo(String primeiroNome, String nomeInvertido){
        Verificadores v = new Verificadores();
        primeiroNome = primeiroNome.toLowerCase();
        nomeInvertido = nomeInvertido.toLowerCase();

        String normalizadoP = (Normalizer.normalize(primeiroNome, Normalizer.Form.NFD));
        String normalizadoS = (Normalizer.normalize(nomeInvertido, Normalizer.Form.NFD));

        return v.ehPalindromo(normalizadoS, normalizadoP);
    }

    public String formataDataDeAniversario(){
        Scanner teclado = new Scanner(System.in);
        Verificadores v = new Verificadores();
        Removedores r = new Removedores();
        Contadores c = new Contadores();
        String dataDeNascimento = " ";
        String dia;
        String mes;
        String ano;
        String contadorWhile = "0";
        String contadorIF = "0";
        
        while (!contadorWhile.equals("8")){
            if (contadorIF.equals("0")){
                System.out.print("Que dia você nasceu?\nDe a data neste formato [ dia/mes/ano ] " +
                        "com zeros inclusos ");
                contadorIF = "1";
            }else{
                System.out.println("\nSua data precisa estar neste formato [ dia/mes/ano ] com zeros inclusos. " +
                        "\nEx: 02/03/2002 , (dois de março de dois mil e dois) \n");
                System.out.print("Digite novamente sua data de nascimento: ");
            }
            dataDeNascimento = teclado.nextLine();
            dataDeNascimento = r.removeEspacos(dataDeNascimento);
            dataDeNascimento = r.removeSimbulos(dataDeNascimento);
            dataDeNascimento = r.removeLetras(dataDeNascimento);
            
            contadorWhile = c.contaNumeros(dataDeNascimento);
            
            if (contadorWhile.equals("8")){
                dia = diaAniversario(dataDeNascimento);
                mes = mesAniversario(dataDeNascimento);
                ano = anoAniversario(dataDeNascimento);
                if (!v.ehDataValida(dia, mes, ano).equals("true")){
                    contadorWhile = "0";
                }
            }
        }
        return dataDeNascimento;
    }
    
    public String diaAniversario (String dataDenascimento){
        return dataDenascimento.substring(0,2);
    }
    
    public String mesAniversario (String dataDenascimento){
        return dataDenascimento.substring(2,4);
    }
    
    public String anoAniversario (String dataDenascimento){
        return dataDenascimento.substring(4);
    }
    
    public String diasFaltantesAniversario(String dia, String mes) {
        Date atual = new Date();
        
        String diaInt = dia;
        String mesInt = mes;

        Date aniversarioDate = new Date(atual.getYear(), stringParaInt(mesInt)-1, stringParaInt(diaInt)+1);

        if (aniversarioDate.before(atual)) {
            aniversarioDate = new Date(atual.getYear() + 1, stringParaInt(mesInt)-1, stringParaInt(diaInt)+1);
        }

        long tempo = (aniversarioDate.getTime() - atual.getTime()) / 86400000;

        return intParaString((int) tempo);
    }
    
    public String descobreIdadeUsuario(String dia, String mes, String ano){
        Date dt = new Date();
        String diaAtual = intParaString(dt.getDate());
        String mesAtual = intParaString(dt.getMonth() + 1);
        String anoAtual = intParaString(dt.getYear() + 1900);
        
        String idade = subtraiStringNumerica(anoAtual, ano);

        if ((comparaStringNumerica(mes, mesAtual) > 0) || 
            (mes.equals(mesAtual) && comparaStringNumerica(dia, diaAtual) > 0)){
            idade = subtraiStringNumerica(idade, "1");
        }
        return idade;
    }

    public String diaDaSemanaDeNascimento(String dia, String mes, String ano){
        String diaInt = dia;
        String mesInt = mes;
        String anoInt = ano;
        
        if (stringParaInt(mesInt) <= 2) {
            mesInt = intParaString(stringParaInt(mesInt) + 12);
            anoInt = intParaString(stringParaInt(anoInt) - 1);
        }
        
        String j = intParaString(stringParaInt(anoInt) / 100);
        String k = intParaString(stringParaInt(anoInt) % 100);
        String div1 = intParaString(((stringParaInt(mesInt) + 1) * 26) / 10);
        String div2 = intParaString(stringParaInt(k) / 4);
        String div3 = intParaString(stringParaInt(j) / 4);

        String diaDaSemana = intParaString((stringParaInt(diaInt) + stringParaInt(k) +
                (5 * stringParaInt(j)) + stringParaInt(div1) + stringParaInt(div2) + stringParaInt(div3)) % 7);

        if (diaDaSemana.equals("0")){
            return "Sábado";
        } else if (diaDaSemana.equals("1")) {
            return "Domingo";
        }else if (diaDaSemana.equals("2")) {
            return "Segunda-feira";
        }else if (diaDaSemana.equals("3")) {
            return "Terça-feira";
        }else if (diaDaSemana.equals("4")) {
            return "Quarta-feira";
        }else if (diaDaSemana.equals("5")){
            return "Quinta-feira";
        }
        return "Sexta-feira";
    }
    
    public String signodoUsuario (String dia, String mes){
        String diaInt = dia;
        String mesInt = mes;
        
        if ((stringParaInt(mesInt) == 1 && stringParaInt(diaInt) >= 21) || (stringParaInt(mesInt) == 2 && stringParaInt(diaInt) <= 19)) {
            return "Aquário";
            
        } else if (stringParaInt(mesInt) == 2 || (stringParaInt(mesInt) == 3 && stringParaInt(diaInt) <= 20)) {
            return "Peixes";
            
        }else if (stringParaInt(mesInt) == 3 || (stringParaInt(mesInt) == 4 && stringParaInt(diaInt) <= 20)){
            return "Áries";
            
        }else if (stringParaInt(mesInt) == 4 || (stringParaInt(mesInt) == 5 && stringParaInt(diaInt) <= 20)){
            return "Touro";
            
        }else if (stringParaInt(mesInt) == 5 || (stringParaInt(mesInt) == 6 && stringParaInt(diaInt) <= 20)){
            return "Gêmeos";
            
        }else if (stringParaInt(mesInt) == 6 || (stringParaInt(mesInt) == 7 && stringParaInt(diaInt) <= 22)){
            return "Câncer";
            
        }else if (stringParaInt(mesInt) == 7 || (stringParaInt(mesInt) == 8 && stringParaInt(diaInt) <= 22)){
            return "Leão";

        }else if (stringParaInt(mesInt) == 8 || (stringParaInt(mesInt) == 9 && stringParaInt(diaInt) <= 22)){
            return "Virgem";
            
        }else if (stringParaInt(mesInt) == 9 || (stringParaInt(mesInt) == 10 && stringParaInt(diaInt) <= 20)){
            return "Libra";
        }else if (stringParaInt(mesInt) == 10 || (stringParaInt(mesInt) == 11 && stringParaInt(diaInt) <= 20)){
            return "Escorpião";
        }else if (stringParaInt(mesInt) == 11 || (stringParaInt(mesInt) == 12 && stringParaInt(diaInt) <= 21)){
            return "Sagitário";
            
        }
        return "Capricórnio";
    }
    
    public String somaStringNumerica(String num1, String num2){
        return intParaString(stringParaInt(num1) + stringParaInt(num2));
    }
    
    public String subtraiStringNumerica(String num1, String num2){
        return intParaString(stringParaInt(num1) - stringParaInt(num2));
    }
    
    public int comparaStringNumerica(String num1, String num2){
        int n1 = stringParaInt(num1);
        int n2 = stringParaInt(num2);
        
        if (n1 < n2) return -1;
        if (n1 > n2) return 1;
        return 0;
    }
    
    public int stringParaInt(String num){
        return Integer.parseInt(num);
    }
    
    public String intParaString(int num){
        return String.valueOf(num);
    }
    
    // ===== METODOS PARA GERENCIAR HISTORICO =====
    
    public String adicionaPessoaNoHistorico(String historico, String nome, String data, String letras, String vogais,
                                            String consoantes, String signo, String diaSemana, String idade, String diasAniversario){

        String novoRegistro = nome + "|" + data + "|" + letras + "|" + vogais + "|" + consoantes + "|" +
                signo + "|" + diaSemana + "|" + idade + "|" + diasAniversario + "||";

        return historico + novoRegistro;
    }

    public String salvarPessoaNoHistorico(String historico, String nome, String data, String letras, String vogais,
                                          String consoantes, String signo, String diaSemana, String idade, String diasAniversario){

        historico = removePessoaPorNome(historico, nome);// Remove registro antigo se existir
        

        return adicionaPessoaNoHistorico(historico, nome, data, letras, vogais, 
                                        consoantes, signo, diaSemana, idade, diasAniversario);
    }

    public String atualizaPessoaNoHistorico(String historico, String nome, String data, 
                                            String letras, String vogais, String consoantes, 
                                            String signo, String diaSemana, String idade, 
                                            String diasAniversario){
        // remove registro antigo se existir
        historico = removePessoaPorNome(historico, nome);
        
        // Adiciona novo registro
        return adicionaPessoaNoHistorico(historico, nome, data, letras, vogais, 
                                        consoantes, signo, diaSemana, idade, diasAniversario);
    }

    // apaga a pessoa que ja existe no historico, evita clones, serve paara atualizar
    private String removePessoaPorNome(String historico, String nome){
        if (historico.length() == 0){
            return historico;
        }
        
        String novoHistorico = "";
        String registroAtual = "";
        
        for (int i = 0; i < historico.length(); i++){
            if (i < historico.length() - 1 && 
                historico.charAt(i) == '|' && historico.charAt(i+1) == '|'){ // Fim d0 registro
                String nomeRegistro = extraiCampo(registroAtual, 0);
                if (!nomeRegistro.equals(nome)){
                    novoHistorico += registroAtual + "||";
                }
                registroAtual = "";
                i++; // pula o barra |
            } else {
                registroAtual += historico.charAt(i);
            }
        }
        
        return novoHistorico;
    }
    
    public String removerPessoaDoHistorico(String historico){
        
        if (historico.length() == 0){
            System.out.println("\nHistórico vazio! Nenhuma pessoa para remover.\n");
            return historico;
        }

        exibeHistorico(historico);
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("\nDigite o ID da pessoa que deseja remover: ");
        String idRemover = teclado.nextLine();
        Removedores r = new Removedores();
        idRemover = r.removeEspacos(idRemover);
        idRemover = r.removeSimbulos(idRemover);
        idRemover = r.removeLetras(idRemover);
        
        if (idRemover.length() == 0){
            System.out.println("\nID inválido!\n");
            return historico;
        }
        
        String totalPessoas = contaPessoasNoHistorico(historico);
        if (comparaStringNumerica(idRemover, "1") < 0 || 
            comparaStringNumerica(idRemover, totalPessoas) > 0){
            System.out.println("\nID não encontrado no histórico!\n");
            return historico;
        }
        
        // remove a pessoa pelo ID
        String novoHistorico = "";
        String registroAtual = "";
        String idAtual = "1";
        
        for (int i = 0; i < historico.length(); i++){
            if (i < historico.length() - 1 && 
                historico.charAt(i) == '|' && historico.charAt(i+1) == '|'){
                if (!idAtual.equals(idRemover)){
                    novoHistorico += registroAtual + "||";
                } else {
                    String nomeRemovido = extraiCampo(registroAtual, 0);
                    System.out.println("\nPessoa '" + nomeRemovido + "' removida do histórico!\n");
                }
                registroAtual = "";
                idAtual = somaStringNumerica(idAtual, "1");
                i++; // pula a barra |
            } else {
                registroAtual += historico.charAt(i);
            }
        }
        
        return novoHistorico;
    }
    
    public void exibeHistorico(String historico){
        if (historico.length() == 0){
            System.out.println("\n========== HISTÓRICO DE PESSOAS ==========");
            System.out.println("Nenhuma pessoa cadastrada no histórico.");
            System.out.println("==========================================\n");
            return;
        }
        
        System.out.println("\n========== HISTÓRICO DE PESSOAS ==========");
        String registroAtual = "";
        String id = "1";
        
        for (int i = 0; i < historico.length(); i++){  // fim de um registro
            if (i < historico.length() - 1 && historico.charAt(i) == '|' && historico.charAt(i+1) == '|'){

                exibeRegistro(id, registroAtual);
                registroAtual = "";
                id = somaStringNumerica(id, "1");
                i++; // pula |
            } else {
                registroAtual += historico.charAt(i);
            }
        }
        System.out.println("==========================================\n");
    }
    

    // exibe dados individual da pessoa.

    private void exibeRegistro(String id, String registro){
        
        String nome = extraiCampo(registro, 0);
        String data = extraiCampo(registro, 1);
        String letras = extraiCampo(registro, 2);
        String vogais = extraiCampo(registro, 3);
        String consoantes = extraiCampo(registro, 4);
        String signo = extraiCampo(registro, 5);
        String diaSemana = extraiCampo(registro, 6);
        String idade = extraiCampo(registro, 7);
        String diasAniv = extraiCampo(registro, 8);
        
        String dataFormatada = data.substring(0,2) + "/" + data.substring(2,4) + "/" + data.substring(4);
        
        System.out.println("\n[ID " + id + "] " + nome );
        System.out.println("  Data nascimento: " + dataFormatada);
        System.out.println("  Nome: " + letras + " letras (" + vogais + " vogais, " + consoantes + " consoantes)");
        System.out.println("  Signo: " + signo);
        System.out.println("  Nasceu em: " + diaSemana);
        System.out.println("  Idade: " + idade + " anos");
        
        if (diasAniv.equals("0")) {
            System.out.println("  ===== " + (nome).toUpperCase() + " esta de ANIVERSÁRIO HOJE! =======  ");
            
        } else if(diasAniv.equals("1")) {
            
            System.out.println("  Falta apenas um dia para o aniversário");
        
        } else {
            System.out.println("  Dias até aniversário: " + diasAniv);
            
        }
    }
    

    //Extrai campo específico do registro
    private String extraiCampo(String registro, int indiceCampo){
        String campo = "";
        String indiceAtual = "0";
        
        for (int i = 0; i < registro.length(); i++){
            
            if (registro.charAt(i) == '|'){
                
                if (indiceAtual.equals(String.valueOf(indiceCampo))){
                    return campo;
                }
                campo = "";
                indiceAtual = somaStringNumerica(indiceAtual, "1");
            } else {
                campo += registro.charAt(i);
            }
            
        }
        
        if (indiceAtual.equals(String.valueOf(indiceCampo))){
            return campo;
        }
        return "";
    }
    

    //conta pelo numero de "barras duplas" || encontradas
    private String contaPessoasNoHistorico(String historico){
        String contador = "0";

        if (historico.length() == 0) return "0";

        for (int i = 0; i < historico.length() - 1; i++){
            if (historico.charAt(i) == '|' && historico.charAt(i+1) == '|'){
                contador = somaStringNumerica(contador, "1");
            }
        }
        return contador;
    }
}