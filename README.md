# AnalizadorLexico
GRAMATICA DEL LENGUAJE EASY

<variable>::= {<Letra>} [<Digito>]
<Letra>::= a|b|...|y|z 
<Digito>::= 0|1|...|8|9
<WhiteSpace>::= \n | \t | “ ”
<Declarar entero>::= numero <variable>
<Declarar cadena>::= palabras <variable>
<DefinirCadena> ::=  <variable> = ${ <Letra> }$
<Definir entero> ::= <variable> = { <Digito> }
<operador>::= + |- |* | / |=
<SentenciaDeimpresion> ::= decir <variable> 

::= de definicion (el esquema de la derecha desarrolla el elemento de la izquierda)
| de alternativa (se puede elegir unicamente uno de los elementos que separa)
{} de repeticion (los elementos que incluyen pueden repetirse cero o mas veces)
[] de opcion (los elementos que incluyen pueden utilizarse o no)
() de agrupacion (sirven para agrupar los elementos que incluyen)

COMPILACION LINUX (cd usr/codigo/)
 1.- Compilar JFLEX
   $: jflex Lexer.flex
 2.- Compilar Easy
   $: javac codigo/Easy.java
   $: java codigo.Easy archivo.es
