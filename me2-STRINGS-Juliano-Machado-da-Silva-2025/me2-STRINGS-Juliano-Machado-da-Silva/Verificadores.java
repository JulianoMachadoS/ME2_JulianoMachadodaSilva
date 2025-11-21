/*
Nome: Juliano Machado da Silva
Numero de Matrícula: 25108646-8
*/

import java.util.*;
import java.util.Date;

public class Verificadores {

    public boolean ehLetradoAlfabetoAZ(char letra){
        letra = Character.toLowerCase(letra);
        if (letra == 'a' || letra == 'b' || letra == 'c' || letra == 'd' || letra == 'e' || letra == 'f' ||
                letra == 'g' || letra == 'h' || letra == 'i' || letra == 'j' || letra == 'k' || letra == 'l' ||
                letra == 'm' || letra == 'n' || letra == 'o' || letra == 'p' || letra == 'q' || letra == 'r' ||
                letra == 's' || letra == 't' || letra == 'u' || letra == 'v' || letra == 'w' || letra == 'x' ||
                letra == 'y' || letra == 'z'){
            return true;
        }
        return false;
    }
    
    public boolean ehVogal(char letra){
        letra = Character.toLowerCase(letra);
        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u'){
            return true;
        }
        return false;
    }
    
    public boolean ehConsoante(char letra){
        if (ehLetradoAlfabetoAZ(letra) && !ehVogal(letra)) {
            return true;
        }
        return false;
    }
    
    public String ehPalindromo(String nome, String nomeInvertido) {
        if (nomeInvertido.equals(nome)) {
            return "true";
        }
        return "false";
    }
    
    public boolean ehNumero(char letra){
        letra = Character.toLowerCase(letra);
        if (letra == '1' || letra == '2' || letra == '3' || letra == '4' || letra == '5' ||
                letra == '6' || letra == '7' || letra == '8' || letra == '9' || letra == '0' ){
            return true;
        }
        return false;
    }
    
    public boolean ehSimbulo (char letra){
        letra = Character.toLowerCase(letra);
        if (ehLetradoAlfabetoAZ(letra) || letra == ' ' || ehNumero(letra)){
            return false;
        }
        return true;
    }
    
    public String ehDataValida (String dia, String mes, String ano){
        Date dt = new Date();
        String diaAtual = String.valueOf(dt.getDate());
        String mesAtual = String.valueOf(dt.getMonth() + 1);
        String anoAtual = String.valueOf(dt.getYear() + 1900);
        
        int diaInt = converteStringParaInt(dia);
        int mesInt = converteStringParaInt(mes);
        int anoInt = converteStringParaInt(ano);
        int diaAtualInt = converteStringParaInt(diaAtual);
        int mesAtualInt = converteStringParaInt(mesAtual);
        int anoAtualInt = converteStringParaInt(anoAtual);

        if (anoInt < 1900){
            return "false";
        }
        if(diaInt >= 32){
            return "false";
        }
        if (mesInt >= 13){
            return "false";
        }
        if (anoInt > anoAtualInt) {
            return "false";
        }

        if(!(mesInt == 1 || mesInt == 3 || mesInt == 5 || mesInt == 7 ||
                mesInt == 8 || mesInt == 10 || mesInt == 12) && diaInt == 31){
            return "false";
        }

        if (mesInt == 2){
            if (!ehAnoBissexto(ano).equals("true") && diaInt == 29){
                return "false";
            }
        }

        if (anoInt == anoAtualInt){
            if (mesInt == mesAtualInt){
                if (diaInt >= diaAtualInt){
                    return "false";
                }
            } else if (mesInt > mesAtualInt) {
                return "false";
            }
        }
        return "true";
    }
    
    public String ehAnoBissexto (String ano){
        int anoInt = converteStringParaInt(ano);
        if (anoInt % 400 == 0){
            return "true";
        }
        return "false";
    }
    

    private int converteStringParaInt(String num){
        return Integer.parseInt(num);
    }
}