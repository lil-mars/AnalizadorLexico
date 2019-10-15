/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import codigo.Lexer;
/**
 *
 * @author Mars
 */
public class Easy {
    public static void main(String[] args) {

        try {
            Reader lector = new BufferedReader(new FileReader(args[0]));
            //Debemos pasar por parametro un Reader a nuestro Lexer
            Lexer lexer = new Lexer(lector);
            String resultado = "INICIO DE PROGRAMA\n\n";
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
                    case Variable: case Numero: case Reservadas:
                        resultado += lexer.lexeme + ": Token: " + tokens + "\n";
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
