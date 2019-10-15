package codigo; 
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
WHITE=[" "|"\t"|"\r"|"\n"]
%{
    public String lexeme;
%}
%%
numero |palabras | decir
{lexeme=yytext(); return Reservadas;}
{WHITE} {/*Ignore*/}
"$".*."$" { return Cadena;}
"=" {return Igual;}
{L}({L}|{D})* {lexeme=yytext(); return Variable;}
({D}{L})* {lexeme=yytext(); return Variable;}
("(-"{D}+")")|{D} {lexeme=yytext(); return Numero;}
 . {return ERROR;}
