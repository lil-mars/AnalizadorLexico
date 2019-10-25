/*//
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codigo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
//import codigo.Lexer;
import java_cup.runtime.Symbol;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java_cup.runtime.*;
/**

 * @author Mars
 */


public class Easy {
    
    public static void main(String[] args) {
        LexicalAnalize(args);
        SintaxAnalize(args);
    }
    
 
    private static void SintaxAnalize(String args[]) {
        //Scanner in = new Scanner(new FileReader(args[0]));
        //System.out.println(in);
        String content = "";
        String result = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(args[0]) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }       
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(content)));
        
        try {
            s.parse();
        } catch (Exception ex) {
            System.out.println("Bye");
        }
    }
   
    private static void LexicalAnalize(String args[]){
        
        int linea = 1;    
        try {
            String text;
            if (args.length == 0) {
                text = "archivo.es";
            }
            else {
            text = args[0];
            }
          
            Reader lector = new BufferedReader(new FileReader(text));
            //Debemos pasar por parametro un Reader a nuestro Lexer
            Lexer lexer = new Lexer(lector);
            String resultado = "INICIO DE PROGRAMA\n\n"
                    + "Linea " + linea + "\n";
            while (true) {
                //Busca las expresiones y las va leyendo
                //Va pasando de uno en uno
                
                Tokens tokens = lexer.yylex();
                //Cuando estemos al Final
                if (tokens == null) {
                    resultado += "\n\nFIN DEL PROGRAMA";
                    System.out.println(resultado);
                    return;
                }
                //Para comparar cada uno de las epresiones usamos una switch con la que compararemos con  los simbolos
                switch (tokens) {
                    //Caso de que el token sea un Error  se aanade al a resutado
                    case ERROR:
                        resultado += lexer.yytext() + "Error de sintaxis\n";
                        break;
                    //Caso de que se un identificador o palabra reservada
                    case Variable: case Numero: case DeclararE: case DeclararP:
                        resultado += lexer.lexeme + ": Token: " + tokens + "\n";
                        break;
                    //Caso de que sea un linea
                    case Linea:
                        resultado += lexer.yytext() + "Linea " + (++linea) + "\n";
                        break;
                    //Caso de que es un Token solo se mostrara el token
                    //Caso de que sea una Suma nos dira Suma
                    default:
                        resultado +=  lexer.yytext() + ": Token: " + tokens + "\n";
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
