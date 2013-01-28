package lexico;

import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStream;

enum EstadoLexico
{
	INICIAL,
	REC_IDEN, REC_NUM, REC_ZERO,
//  , ( ) { }
	REC_COMA, REC_PARENTESIS_ABIERTO, REC_PARENTESIS_CERRADO, REC_LLAVE_ABIERTA, REC_LLAVE_CERRADA,
//  = + *
	REC_DISTINTO, REC_MAS, REC_POR,
//	- / > <
	REC_MENOS, REC_DIV, REC_MAYOR, REC_MENOR,
	INICIAL_MENOR, INICIAL_MAYOR, INICIAL_DISTINTO, CASI_DISTINTO,
	REC_EOF
};

public class AnalizadorLexico
{
	private static final int EOF = -1;

	private EstadoLexico estado;
	private String lexema;
	private int caracter;
	//private InputStream input;	
	private InputStream input;
	
	private int filaInicio;
	private int fila;
	private int colInicio;
	private int col;
	
	public AnalizadorLexico(InputStream input) throws IOException
	{
		this.input = input;
		fila = 0;
		col = -1;
		caracter = this.sigCar();	
	}

	public Token nextToken() throws IOException
	{
		estado = EstadoLexico.INICIAL;
		filaInicio = fila;
		colInicio = col;
		lexema = "";

		while (true) 
		{
			switch (estado)
			{
				case INICIAL:
					if (caracter == '0')
						transita(EstadoLexico.REC_ZERO);
					else if (esNumPos(caracter)) 
						transita(EstadoLexico.REC_NUM);
					else if (esComienzoIden(caracter))
						transita(EstadoLexico.REC_IDEN);
					// ---------------------------- //
					else if (caracter == ',')
						transita(EstadoLexico.REC_COMA);
					else if (caracter == '(')
						transita(EstadoLexico.REC_PARENTESIS_ABIERTO);
					else if (caracter == ')')
						transita(EstadoLexico.REC_PARENTESIS_CERRADO);
					else if (caracter == '{')
						transita(EstadoLexico.REC_LLAVE_ABIERTA);
					else if (caracter == '}')
						transita(EstadoLexico.REC_LLAVE_CERRADA);
					else if (caracter == '=')
						transita(EstadoLexico.INICIAL_DISTINTO);
					else if (caracter == '+')
						transita(EstadoLexico.REC_MAS);
					else if (caracter == '*')
						transita(EstadoLexico.REC_POR);
					// ---------------------------- //
					else if (caracter == '/')
						transita(EstadoLexico.REC_DIV);
					else if (caracter == '<')
						transita(EstadoLexico.INICIAL_MENOR);
					else if (caracter == '>')
						transita(EstadoLexico.INICIAL_MAYOR);
					else if (caracter == '-')
						transita(EstadoLexico.REC_MENOS);
					// ---------------------------- //
					else if (caracter == EOF)
						transita(EstadoLexico.REC_EOF);
					else if (esSeparador(caracter))
						transitaIgnorando(EstadoLexico.INICIAL);
					else
						errorLexico();
					break;
				
				case REC_ZERO: 
					return new Token(filaInicio, colInicio, CatLexica.NUM, lexema);
					
				case REC_NUM:
					if (esDigito(caracter))
					  transita(EstadoLexico.REC_NUM);
					else 
					  return new Token(filaInicio, colInicio, CatLexica.NUM, lexema);
					break;
				
				case REC_IDEN:
					if (esComienzoIden(caracter) || esDigito(caracter))
					  transita(EstadoLexico.REC_IDEN);
					else
					  return reservadaOlexema();
					break;
				
				case REC_COMA: 
					return new Token(filaInicio, colInicio, CatLexica.COMA);
					
				case REC_PARENTESIS_ABIERTO:
					return new Token(filaInicio, colInicio, CatLexica.PARENTESIS_ABIERTO);
				
				case REC_PARENTESIS_CERRADO:
					return new Token(filaInicio, colInicio, CatLexica.PARENTESIS_CERRADO);
					
				case REC_LLAVE_ABIERTA:
					return new Token(filaInicio, colInicio, CatLexica.LLAVE_ABIERTA);
					
				case REC_LLAVE_CERRADA:
					return new Token(filaInicio, colInicio, CatLexica.LLAVE_CERRADA);
					
				case REC_DISTINTO:
					return new Token(filaInicio, colInicio, CatLexica.DISTINTO);
					
				case INICIAL_DISTINTO:
					if(caracter == '/')
					{
						transita(EstadoLexico.CASI_DISTINTO);
					}
					else
					{
						return new Token(filaInicio, colInicio, CatLexica.IGUAL);
					}
				case REC_MAS:
					return new Token(filaInicio, colInicio, CatLexica.MAS);
				
				case REC_MENOS:
					return new Token(filaInicio, colInicio, CatLexica.MENOS);
					
				case REC_DIV:
					return new Token(filaInicio, colInicio, CatLexica.DIV);
				
				case INICIAL_MAYOR:
					if (caracter == '=')
					{
						return new Token(filaInicio, colInicio, CatLexica.MAYOR_IGUAL);
					}
					else
					{
						return new Token(filaInicio, colInicio, CatLexica.MAYOR);
					}
					
				case INICIAL_MENOR:
					if(caracter == '=')
					{
						return new Token(filaInicio, colInicio, CatLexica.MENOR_IGUAL);
					}
					else
					{
						return new Token(filaInicio, colInicio, CatLexica.MENOR);
					}
				case CASI_DISTINTO:	
					if(caracter == '=')
					{
						return new Token(filaInicio, colInicio, CatLexica.DISTINTO);
					}
					else
					{
						errorLexico();
					}
				case REC_POR:
					return new Token(filaInicio, colInicio, CatLexica.POR);
					
				case REC_EOF:
					return new Token(filaInicio, colInicio, CatLexica.EOF);
			
			}
		}
	}

	private Token reservadaOlexema()
	{
		if (lexema.equals("decs"))
			return new Token(filaInicio, colInicio, CatLexica.DECS);
		else if (lexema.equals("end"))
			return new Token(filaInicio, colInicio, CatLexica.END);
		else if (lexema.equals("body"))
			return new Token(filaInicio, colInicio, CatLexica.BODY);
		else if (lexema.equals("set"))
			return new Token(filaInicio, colInicio, CatLexica.SET);
		else if (lexema.equals("to"))
			return new Token(filaInicio, colInicio, CatLexica.TO);
		else if (lexema.equals("if"))
			return new Token(filaInicio, colInicio, CatLexica.IF);
		else if (lexema.equals("then"))
			return new Token(filaInicio, colInicio, CatLexica.THEN);
		else if (lexema.equals("else"))
			return new Token(filaInicio, colInicio, CatLexica.ELSE);
		else if (lexema.equals("out"))
			return new Token(filaInicio, colInicio, CatLexica.OUT);
		else if (lexema.equals("call"))
			return new Token(filaInicio, colInicio, CatLexica.CALL);
		else if (lexema.equals("with"))
			return new Token(filaInicio, colInicio, CatLexica.WITH);
		else if (lexema.equals("proc"))
			return new Token(filaInicio, colInicio, CatLexica.PROC);
		else if (lexema.equals("var"))
			return new Token(filaInicio, colInicio, CatLexica.VAR);
		else if (lexema.equals("or"))
			return new Token(filaInicio, colInicio, CatLexica.OR);
		else if (lexema.equals("and"))
			return new Token(filaInicio, colInicio, CatLexica.AND);
		else if (lexema.equals("not"))
			return new Token(filaInicio, colInicio, CatLexica.NOT);
		// ---------------------------- //
		else
			return new Token(filaInicio, colInicio, CatLexica.IDEN, lexema);
	}

		private void transita(EstadoLexico aEstado) throws IOException {
			lexema = lexema + (char) caracter;
			transitaIgnorando(aEstado);
		}

		private void transitaIgnorando(EstadoLexico aEstado) throws IOException {
			estado = aEstado;
			filaInicio = fila;
			colInicio = col;
			caracter = sigCar();

		}

		
		 private int sigCar() throws IOException 
		 {
		        caracter = input.read();
		        if (caracter == '\n') {
		            col = -1;
		            fila++;
		        } else {
		            col++;
		        }
		        return caracter;
		    }
		

		private boolean esComienzoIden(int c) {
			return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == '_'));
		}

		private boolean esSeparador(int c) {
			return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
		}

		private boolean esNumPos(int c) {
			return (c >= '1' && c <= '9');
		}

		private boolean esDigito(int c) {
			return (esNumPos(c) || c == '0');
		}

		private void errorLexico() {
			System.err.println("(" + fila + "," + col + ")"
					+ "ERROR LEXICO: caracter desconocido: " + (char) caracter);
			System.exit(1);

		}

}

























