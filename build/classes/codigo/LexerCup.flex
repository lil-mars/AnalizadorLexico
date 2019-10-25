package codigo; 
import java_cup.runtime.*;
%%
%class LexerCup
%type java_cup.runtime.*;
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
WHITE=[" "|"\t"|"\r"|"\n"]
%{
   private Symbol symbol(int type,object value){
        return new Symbol(type,yyline,yycolumn,value);
  }
   private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
  }
%}
%%
numero {return new Symbol(sym.DeclararE,yychar,yyline,yytext());}
palabras {return new Symbol(sym.DeclararP,yychar,yyline,yytext());}
decir {return new Symbol(sym.Decir,yychar,yyline,yytext());}
{WHITE} {/*Ignore*/}
"$".*."$" { return new Symbol(sym.Cadena,yychar,yyline,yytext());}
/*
    Operarores de  numeros
*/

"igual" {return new Symbol(sym.Igual,yychar,yyline,yytext());}
"mas"   {return new Symbol(sym.Suma,yychar,yyline,yytext());}
"menos" {return new Symbol(sym.Resta,yychar,yyline,yytext());}
"por"   {return new Symbol(sym.Multiplicacion,yychar,yyline,yytext());}
"entre" {return new Symbol(sym.Division,yychar,yyline,yytext());}
/**/
{L}({L}|{D})* {return new Symbol(sym.Variable,yychar,yyline,yytext());}
({D}{L})* {return new Symbol(sym.Variable,yychar,yyline,yytext());}
("(-"{D}+")")|{D} {return new Symbol(sym.Numero,yychar,yyline,yytext());}
 . {return new Symbol(sym.ERROR,yychar,yyline,yytext());}

