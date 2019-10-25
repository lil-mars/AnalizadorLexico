package codigo; 
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
WHITE=[" "|"\t"|"\r"]
%{
    public String lexeme;
%}
%%
numero {lexeme=yytext(); return DeclararE;}
palabras {lexeme=yytext(); return DeclararP;}
decir {lexeme=yytext(); return Decir;}
{WHITE} {/*Ignore*/}
"$".*."$" { return Cadena;}
/*
    Operarores de  numeros
*/
"\n"    { return Linea; } 
"igual" {lexeme=yytext(); return Igual;}
"mas"   {lexeme=yytext(); return Suma;}
"menos" {lexeme=yytext(); return Resta;}
"por"   {lexeme=yytext(); return Multiplicacion;}
"entre" {lexeme=yytext(); return Division;}
/**/
{L}({L}|{D})* {lexeme=yytext(); return Variable;}
({D}{L})* {lexeme=yytext(); return Variable;}
("(-"{D}+")")|{D} {lexeme=yytext(); return Numero;}
 . {return ERROR;}
