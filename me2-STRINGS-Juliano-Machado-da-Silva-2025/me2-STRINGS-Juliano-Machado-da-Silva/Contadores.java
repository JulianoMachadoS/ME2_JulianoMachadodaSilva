/*
Nome: Juliano Machado da Silva
Numero de Matrícula: 25108646-8
*/

public class Contadores {
     Verificadores vr = new Verificadores();
    
    public String contaLetras (String texto){
        String contador = "0";
        for (int i = 0; i < texto.length(); i++){
            if (vr.ehLetradoAlfabetoAZ(texto.charAt(i))){
                contador = somaString(contador, "1");
            }
        }
        return contador;
    }
    
    public String contaVogais (String texto){
        String contador = "0";
        for (int i = 0; i < texto.length(); i++){
            if (vr.ehVogal(texto.charAt(i))){
                contador = somaString(contador, "1");
            }
        }
        return contador;
    }
    
    public String contaConsoantes (String texto){
        String contador = "0";
        for (int i = 0; i < texto.length(); i++){
            if (vr.ehConsoante(texto.charAt(i))){
                contador = somaString(contador, "1");
            }
        }
        return contador;
    }
    
    public String contaNumeros (String texto){
        String contador = "0";
        for (int i = 0; i < texto.length(); i++){
            if (vr.ehNumero(texto.charAt(i))){
                contador = somaString(contador, "1");
            }
        }
        return contador;
    }
    
    private String somaString(String num1, String num2){
        
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        
        return String.valueOf(n1 + n2);
    }
}