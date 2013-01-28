package arbol;

import java.io.IOException;

import lexico.CatLexica;
import lexico.AnalizadorLexico;
import lexico.Token;
import gramatica.GramaticaAtributos;
import gramatica.GramaticaAtributos.*;

public class ConstructorArboles {

	private Token tokenActual;
	private AnalizadorLexico analizadorLexico;
	private GramaticaAtributos gramaticaAtributos = new GramaticaAtributos();
	private static final boolean DEBUG_ACTIVADO = false;
	
	public ConstructorArboles(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
	}

	public Programa parse() throws IOException {
		tokenActual = analizadorLexico.nextToken();
		Programa a_Programa = recPrograma();
		rec(CatLexica.EOF);
		return a_Programa;
	}


	/* GRAMATICA */

	/** Programa
	 *    Programa ::= Declaraciones Cuerpo
	 * 		Programa.a = programaR1(Declaraciones.a, Cuerpo.a) 
	 */
	private Programa recPrograma() throws IOException
	{	
		return programaR1(recDeclaraciones(), recCuerpo());
	}

	
	/** Declaraciones 
	 *   Declaraciones ::= decs ListaDeclaraciones end decs
	 * 		Declaraciones.a = declaracionesR1(ListaDeclaraciones.a)
	 *   Declaraciones ::=  ¬
	 * 		Declaraciones.a = declaracionesR2()
	 */
	private Declaraciones recDeclaraciones()throws IOException
	{
		if (tokenActual(CatLexica.DECS))
		{
			rec(CatLexica.DECS);
			Declaraciones a_Declaraciones = declaracionesR1(recListaDeclaraciones());
			rec(CatLexica.END);
			rec(CatLexica.DECS);
			return a_Declaraciones;
		}
		else
		{
			return declaracionesR2();
		}	
	}
	
	/** ListaDeclaraciones
	 *    ListaDeclaraciones ::= Declaracion RListaDeclaraciones
	 *    	RListaDeclaraciones.ah = listaDeclaracionesR2(Declaracion.a)
	 *    	ListaDeclaraciones.a = RListaDeclaraciones.a
	 *    RListaDeclaraciones ::= , Declaracion RListaDeclaraciones
	 *    	RListaDeclaraciones(1).ah = listaDeclaracionesR1(RListaDeclaraciones(0).ah, Declaracion.a)
	 *    	RListaDeclaraciones(0).a = RListaDeclaraciones(1).a
	 *    RListaDeclaraciones ::= ¬
	 *    	RListaDeclaraciones.a = RListaDeclaraciones.ah
	 */
	private ListaDeclaraciones recListaDeclaraciones()throws IOException
	{
		return recRListaDeclaraciones(listaDeclaracionesR2(recDeclaracion()));
	}
	private ListaDeclaraciones recRListaDeclaraciones(ListaDeclaraciones ah_RListaDeclaraciones)throws IOException
	{
		while (tokenActual(CatLexica.COMA))
		{
			rec(CatLexica.COMA);
			Declaracion a_Declaracion = recDeclaracion();
			ah_RListaDeclaraciones = listaDeclaracionesR1(ah_RListaDeclaraciones, a_Declaracion);
		}
		return ah_RListaDeclaraciones;
	}
	
	/** Declaracion
	 *    Declaracion ::= IDEN
	 * 		Declaracion.a = declaracionR1(IDEN.lex)
	 * 	  Declaracion ::=  proc IDEN ( ParametrosFormales ) Declaraciones Cuerpo
	 * 		Declaracion.a = declaracionR2(IDEN.lex, ParametrosFormales.a, Declaraciones.a, Cuerpo.a)
	 */
	private Declaracion recDeclaracion() throws IOException
	{
		if(tokenActual(CatLexica.IDEN))
		{
			Token tokenIDEN = tokenActual;
			rec(CatLexica.IDEN);
			return declaracionR1(tokenIDEN);
		}
		else if(tokenActual(CatLexica.PROC))
		{
			rec(CatLexica.PROC);
			Token tokenIDEN = tokenActual;
			rec(CatLexica.IDEN);
			rec(CatLexica.PARENTESIS_ABIERTO);
			ParametrosFormales a_ParametrosFormales = recParametrosFormales();
			rec(CatLexica.PARENTESIS_CERRADO);
			Declaraciones a_Declaraciones = recDeclaraciones();
			Cuerpo a_Cuerpo = recCuerpo();
			return declaracionR2(tokenIDEN, a_ParametrosFormales, a_Declaraciones, a_Cuerpo);
		}
		else
		{
			errorSintactico(CatLexica.IDEN, CatLexica.PROC);
			return null;
		}			
	}
	
	/** ParametrosFormales 
	 *    ParametrosFormales ::= ListaParametrosFormales 
	 *    	ParametrosFormales.a = parametrosFormalesR1(ListaParametrosFormales.a)
	 *    ParametrosFormales ::= ¬
	 *    	ParametrosFormales.a = parametrosFormalesR2()
	 */
	private ParametrosFormales recParametrosFormales() throws IOException
	{
		if(tokenActual(CatLexica.IDEN, CatLexica.VAR))
		{
			return parametrosFormalesR1(recListaParametrosFormales());
		}
		else
		{
			return parametrosFormalesR2();
		}
	}
	
	/** ListaParametrosFormales
	 *    ListaParametrosFormales ::= ParametroFormal RListaParametrosFormales
	 *  	RListaParametrosFormales.ah = listaParametrosFormalesR2(ParametroFormal.a)
	 *  	ListaParametrosFormales.a = RListaParametrosFormales.a
	 *    RListaParametrosFormales ::= , ParametroFormal RListaParametrosFormales
	 *  	RListaParametrosFormales(1).ah = listaParametrosFormalesR1(RListaParametroFormal(0).ah, ParametroFormal.a)
	 *  	RListaParametrosFormales(0).a = RListaParametrosFormales(1).a
	 *    RListaParametrosFormales ::= ¬
	 *  	RListaParametrosFormales.a = RlistaParametrosFormales.a
	 */
	private ListaParametrosFormales recListaParametrosFormales()throws IOException
	{
		return recRListaParametrosFormales(listaParametrosFormalesR2(recParametroFormal()));
	}
	private ListaParametrosFormales recRListaParametrosFormales(ListaParametrosFormales ah_RListaParametrosFormales) throws IOException
	{
		while (tokenActual(CatLexica.COMA))
		{
			rec(CatLexica.COMA);
			ParametroFormal a_ParametroFormal = recParametroFormal();
			ah_RListaParametrosFormales = listaParametrosFormalesR1(ah_RListaParametrosFormales, a_ParametroFormal);
		}
		return ah_RListaParametrosFormales;
	}
	
	
	/** ParametroFormal
	 *   ParametroFormal ::= IDEN
	 *   	ParametroFormal.a = parametroFormalR1(IDEN.lex)
	 *   ParametroFormal ::= var IDEN
	 *   	ParametroFormal.a = parametroFormalR2(IDEN.lex)
	 */
	private ParametroFormal recParametroFormal() throws IOException
	{
		if (tokenActual(CatLexica.IDEN))
		{
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			return parametroFormalR1(token_IDEN);
		}
		else if (tokenActual(CatLexica.VAR))
		{
			rec(CatLexica.VAR);
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			return parametroFormalR2(token_IDEN);
		}
		else
		{
			errorSintactico(CatLexica.IDEN, CatLexica.VAR);
			return null;
		}
	}
	
	/** Cuerpo
	 *    Cuerpo ::= body Instrucciones end body
	 *    	Cuerpo.a = cuerpoR1(Instrucciones.a)
	 */
	private Cuerpo recCuerpo() throws IOException
	{
		if (tokenActual(CatLexica.BODY))
		{
			rec(CatLexica.BODY);
			Cuerpo a_Instrucciones = cuerpoR1(recInstrucciones());
			rec(CatLexica.END);
			rec(CatLexica.BODY);
			return a_Instrucciones;
		}
		else
		{
			errorSintactico(CatLexica.BODY);
			return null;
		}
	}
	
	/** Instrucciones 
	 *    Instrucciones ::= Instruccion RInstrucciones
	 *  		RInstrucciones.ah = instruccionesR2(Instruccion.a)
	 *  		Instrucciones.a = RInstrucciones.a
	 *    RInstrucciones ::= , Instruccion RInstrucciones
	 * 	 		RInstrucciones(1).ah = instruccionesR1(RInstrucciones(0).ah, Instruccion.a)
	 *  		RInstrucciones(0).a = RInstrucciones(1).a
	 *    RInstrucciones ::= ¬
	 *  		RInstrucciones.a = RInstrucciones.ah
	 */
	private Instrucciones recInstrucciones() throws IOException
	{
		return recRInstrucciones(instruccionesR2(recInstruccion()));
	}
	private Instrucciones recRInstrucciones(Instrucciones ah_RInstrucciones) throws IOException
	{
		while (tokenActual(CatLexica.COMA))
		{
			rec(CatLexica.COMA);
			Instruccion a_Instruccion = recInstruccion();
			ah_RInstrucciones = instruccionesR1(ah_RInstrucciones, a_Instruccion);
		}
		return ah_RInstrucciones;
	}
		
	/** Instruccion 
	 *    Instruccion ::= set IDEN to Exp0
	 *  	Instruccion.a = instruccionR1(IDEN.lex, Exp0.a)
	 *    Instruccion ::= call IDEN ParametrosReales end call
	 *    	Instruccion.a = instruccionR2(IDEN.lex, ParametrosReales.a)
	 *    Instruccion ::= if Exp0 then {Instrucciones} ParteElse
	 *  	Instruccion.a = instruccionR3(Exp0.a, Instrucciones.a, ParteElse.a)
	 *    Instruccion ::= out IDEN
	 *    	Instruccion.a = instruccionR4(IDEN.lex)
	 */
	private Instruccion recInstruccion() throws IOException
	{
		if (tokenActual(CatLexica.SET))
		{
			rec(CatLexica.SET);
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			rec(CatLexica.TO);
			return instruccionR1(token_IDEN, recExp0());
		}
		else if(tokenActual(CatLexica.CALL))
		{
			rec(CatLexica.CALL);
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			ParametrosReales a_ParametrosReales = recParametrosReales();
			rec(CatLexica.END);
			rec(CatLexica.CALL);
			return instruccionR2(token_IDEN, a_ParametrosReales);
		}
		else if (tokenActual(CatLexica.IF))
		{
			rec(CatLexica.IF);
			Exp0 a_Exp0 = recExp0();
			rec(CatLexica.THEN);
			rec(CatLexica.LLAVE_ABIERTA);
			Instrucciones a_Instrucciones = recInstrucciones();
			rec(CatLexica.LLAVE_CERRADA);
			return instruccionR3(a_Exp0, a_Instrucciones, recParteElse());
		}
		else if (tokenActual(CatLexica.OUT))
		{
			rec(CatLexica.OUT);
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			return instruccionR4(token_IDEN);
		}
		else
		{
			errorSintactico(CatLexica.SET, CatLexica.IF, CatLexica.OUT, CatLexica.CALL);
			return null;
		}
	}
	
	/** ParametrosReales
	 *    ParametrosReales ::= with ListaParametrosReales
	 *    	ParametrosReales.a = parametrosRealesR1(ListaParametrosReales.a)
	 *    ParametrosReales ::= ¬
	 *    	ParametrosReales.a = parametrosRealesR2()
	 */
	private ParametrosReales recParametrosReales() throws IOException
	{
		if(tokenActual(CatLexica.WITH))
		{
			rec(CatLexica.WITH);
			ListaParametrosReales a_ListaParametrosReales = recListaParametrosReales();
			return parametrosRealesR1(a_ListaParametrosReales);
		}
		else
		{
			return parametrosRealesR2();
		}
	}
	
	/** ListaParametrosReales
	 *    ListaParametrosReales ::= Exp0 RListaParametrosReales
	 *  	RListaParametrosReales.ah = listaParametrosRealesR2(Exp0.a)
	 *  	ListaParametrosReales.a = RListaParametrosReales.a
	 *    RListaParametrosReales ::= , Exp0 RListaParametrosReales
	 *  	RListaParametrosReales(1).ah = listaParametrosRealesR1(RListaParametrosReales(0).ah, Exp0.a)
	 *  	RListaParametrosReales(0).a = RListaParametrosReales(1).a
	 *    RListaParametrosRealies ::= ¬
	 *  	RListaParametrosReales.a = RListaParametrosReales.ah
	 */
	private ListaParametrosReales recListaParametrosReales() throws IOException
	{
		return recRListaParametrosReales(listaParametrosRealesR2(recExp0()));
	}
	private ListaParametrosReales recRListaParametrosReales(ListaParametrosReales ah_RListaParametrosReales) throws IOException
	{
		while (tokenActual(CatLexica.COMA))
		{
			rec(CatLexica.COMA);
			Exp0 a_Exp0 = recExp0();
			ah_RListaParametrosReales = listaParametrosRealesR1(ah_RListaParametrosReales, a_Exp0);
		}
		return ah_RListaParametrosReales;
	}

	
	/** ParteElse
	 *    ParteElse ::= else {Instrucciones}
	 *  	ParteElse.a = parteElseR1(Instrucciones.a)
	 *    ParteElse ::= ¬
	 *  	ParteElse.a = parteElseR2()
	 */
	private ParteElse recParteElse() throws IOException
	{
		if (tokenActual(CatLexica.ELSE))
		{
			rec(CatLexica.ELSE);
			rec(CatLexica.LLAVE_ABIERTA);
			Instrucciones a_Instrucciones = recInstrucciones();
			rec(CatLexica.LLAVE_CERRADA);
			return parteElseR1(a_Instrucciones);
		}
		else return parteElseR2();
	}
	
	/** Exp0
	 *    Exp0 ::= Exp1 RExp0
	 *  	RExp0.ah = Exp1.a
	 *    	Exp0.a = RExp0.a
	 *    RExp0 ::= OpComparacion Exp1
	 *    	RExp0.a = exp0R1(RExp0.ah, OpComparacion.a, Exp1.a)
	 *    RExp0 ::= ¬
	 *    	RExp0.a = exp0R2(RExp0.ah)
	 */
	private Exp0 recExp0() throws IOException
	{
		return recRExp0(recExp1());
	}
	private Exp0 recRExp0(Exp1 ah_RExp0) throws IOException
	{
		if (esOpComparacion())
		{
			return exp0R1(ah_RExp0, recOpComparacion(), recExp1());
		}
		else
		{
			return exp0R2(ah_RExp0);
		}
	}

	/** Exp1
	 *    Exp1 ::= Exp2 RExp1
	 *  	RExp1.ah = exp1R2(Exp2.a)
	 *  	Exp1.a = RExp1.a
	 *    RExp1 ::= OpAditivo Exp2 RExp1
	 *  	RExp1(1).ah = exp1R1(RExp1(0).ah, OpAditivo.a, Exp2.a)
	 *  	RExp1(0).a = RExp1(1).a
	 *    RExp1 ::= ¬
	 *  	RExp1.a = RExp1.ah
	 */
	private Exp1 recExp1() throws IOException
	{
		return recRExp1(exp1R2(recExp2()));
	}
	private Exp1 recRExp1(Exp1 ah_RExp1) throws IOException
	{
		if (esOpAditivo())
		{
			return recRExp1(exp1R1(ah_RExp1, recOpAditivo(), recExp2()));
		}
		else
		{
			return ah_RExp1;
		}
	}
	
	/** Exp2
	 *    Exp2 ::= Exp3 RExp2
	 *  	RExp2.ah = exp2R2(Exp3.a)
	 *    	Exp2.a = RExp2.a
	 *    RExp2 ::= OpMultiplicativo Exp3 RExp2
	 *  	RExp2(1).ah = exp2R1(RExp2(0).ah, OpMultiplicativo.a, Exp3.a)
	 *  	RExp2(0).a = RExp2(1).a
	 *    RExp2 ::= ¬
	 *  	RExp2.a = RExp2.ah
	 */
	private Exp2 recExp2() throws IOException
	{
		return recRExp2(exp2R2(recExp3()));
	}
	private Exp2 recRExp2(Exp2 ah_RExp2) throws IOException
	{
		if (esOpMultiplicativo())
		{
			return recRExp2(exp2R1(ah_RExp2, recOpMultiplicativo(), recExp3()));
		}
		else
		{
			return ah_RExp2;
		}
	}

	/** Exp3
	 * 	  Exp3 ::= OpUnario Exp3
	 * 		Exp3(0).a = exp3R1(OpUnario.a, Exp3(1).a)
	 * 	  Exp3 ::= Exp4
	 * 		Exp3.a = exp3R2(Exp4.a)
	 */
	private Exp3 recExp3() throws IOException
	{
		if (esOpUnario())
		{
			return exp3R1(recOpUnario(), recExp3());
		}
		else
		{
			return exp3R2(recExp4());
		}
	}
	
	/** Exp4
	 * 	  Exp4 ::= NUM
	 * 		Exp4.a = exp4R1(NUM.lex)
	 * 	  Exp4 ::= IDEN
	 * 		Exp4.a = exp4R2(IDEN.lex)
	 * 	  Exp4 ::= ( Exp0 )
	 * 		Exp4.a = exp4R3(Exp0.a)
	 */
	private Exp4 recExp4() throws IOException
	{
		if (tokenActual(CatLexica.NUM))
		{
			Token token_NUM = tokenActual;
			rec(CatLexica.NUM);
			return exp4R1(token_NUM);
		}
		else if(tokenActual(CatLexica.IDEN))
		{
			Token token_IDEN = tokenActual;
			rec(CatLexica.IDEN);
			return exp4R2(token_IDEN);
		}
		else if(tokenActual(CatLexica.PARENTESIS_ABIERTO))
		{
			rec(CatLexica.PARENTESIS_ABIERTO);
			Exp0 a_Exp0 = recExp0();
			rec(CatLexica.PARENTESIS_CERRADO);
			return exp4R3(a_Exp0);
		}
		else 
		{
			errorSintactico(CatLexica.NUM, CatLexica.IDEN, CatLexica.PARENTESIS_ABIERTO);
			return null;
		}
	}

	/** OpComparacion
	 *   OpComparacion ::= =
	 * 		OpComparacion.a = opComparacionR1()
	 *   OpComparacion ::= =/=
	 *   	OpComparacion.a = opComparacionR2()
	 *   OpComparacion ::= >
	 *   	OpComparacion.a = opComparacionR3()
	 *   OpComparacion ::= >=
	 *   	OpComparacion.a = opComparacionR4()
	 *   OpComparacion ::= <
	 *   	OpComparacion.a = opComparacionR5()
	 *   OpComparacion ::= <=
	 *   	OpComparacion.a = opComparacionR6()
	 */
	private OpComparacion recOpComparacion() throws IOException
	{
		if (tokenActual(CatLexica.IGUAL))
		{
			rec(CatLexica.IGUAL);
			return opComparacionR1();
		}
		else if(tokenActual(CatLexica.DISTINTO))
		{
			rec(CatLexica.DISTINTO);
			return opComparacionR2();
		}
		else if(tokenActual(CatLexica.MAYOR))
		{
			rec(CatLexica.MAYOR);
			return opComparacionR3();
		}
		else if(tokenActual(CatLexica.MAYOR_IGUAL))
		{
			rec(CatLexica.MAYOR_IGUAL);
			return opComparacionR4();
		}
		else if(tokenActual(CatLexica.MENOR))
		{
			rec(CatLexica.MENOR);
			return opComparacionR5();
		}
		else if(tokenActual(CatLexica.MENOR_IGUAL))
		{
			rec(CatLexica.MENOR_IGUAL);
			return opComparacionR6();
		}
		else
		{
			errorSintactico(CatLexica.IGUAL, CatLexica.MAYOR, CatLexica.MENOR, CatLexica.DISTINTO, CatLexica.MAYOR_IGUAL, CatLexica.MENOR_IGUAL);
			return null;
		}
	}
	
	/** OpAditivo
	 *    OpAditivo ::= +
	 * 		opAditivo.a = opAditivoR1()
	 * 	  OpAditivo ::= -
	 * 		opAditivo.a = opAditivoR2()
	 *    OpAditivo ::= or
	 *    	opAditivo.a = opAditivoR3()
	 */
	private OpAditivo recOpAditivo() throws IOException
	{
		if (tokenActual(CatLexica.MAS))
		{
			rec(CatLexica.MAS);
			return opAditivoR1();
		}
		else if(tokenActual(CatLexica.MENOS))
		{
			rec(CatLexica.MENOS);
			return opAditivoR2();
		}
		else if(tokenActual(CatLexica.OR))
		{
			rec(CatLexica.OR);
			return opAditivoR3();
		}
		else
		{
			errorSintactico(CatLexica.MAS, CatLexica.MENOS, CatLexica.OR);
			return null;
		}
	}
		
	/** OpMultiplicativo
	 *    OpMultiplicativo ::= *
	 *  	opMultiplicativo.a = opMultiplicativoR1()
	 *    OpMultiplicativo ::= /
	 *     	opMultiplicativo.a = opMultiplicativoR2()
	 *    OpMultiplicativo ::= and
	 *    	opMultiplciativo.a = opMultiplciativoR3()
	 */
	private OpMultiplicativo recOpMultiplicativo() throws IOException
	{
		if (tokenActual(CatLexica.POR))
		{
			rec(CatLexica.POR);
			return opMultiplicativoR1();
		}
		else if(tokenActual(CatLexica.DIV))
		{
			rec(CatLexica.DIV);
			return opMultiplicativoR2();
		}
		else if(tokenActual(CatLexica.AND))
		{
			rec(CatLexica.AND);
			return opMultiplicativoR3();
		}
		else
		{
			errorSintactico(CatLexica.POR, CatLexica.DIV, CatLexica.AND);
			return null;
		}
	}
	
	/** OpUnario
	 *    OpUnario ::= -
	 *    	opUnario.a = opUnarioR1()
	 *    OpUnario ::= not
	 *  	opUnario.a = opUnarioR2()
	 */
	private OpUnario recOpUnario() throws IOException
	{
		if(tokenActual(CatLexica.MENOS))
		{
			rec(CatLexica.MENOS);
			return opUnarioR1();
		}
		else if (tokenActual(CatLexica.NOT))
		{
			rec(CatLexica.NOT);
			return opUnarioR2();
		}
		else
		{
			errorSintactico(CatLexica.MENOS, CatLexica.NOT);
			return null;
		}
	}
	
	/* FUNCIONES IMPORTANTES */
	
	private void rec(CatLexica c) throws IOException
	{
		if (tokenActual(c)) 
		{
			tokenActual = analizadorLexico.nextToken();
		} 
		else 
		{
			errorSintactico(c);
		}
	}
	private void errorSintactico(CatLexica... esperadas)
	{
		System.err.print("(" + tokenActual.getFila() + "," + tokenActual.getCol() + ")"
				+ "ERROR SINTACTICO: Encontrado " + tokenActual
				+ ". Se esperaba alguno de los siguientes elementos: ");
		for (CatLexica catEsperada : esperadas) 
		{
			System.err.print(catEsperada + " ");
		}
		System.err.println();
		System.exit(1);
	}
	private boolean tokenActual(CatLexica... c) 
	{
		boolean encontrada = false;
		int i = 0;
		while (i < c.length && !encontrada)
		{
			encontrada = tokenActual.getCategoria() == c[i];
			i++;
		}
		return encontrada;
	}
	@SuppressWarnings("unused")
	private Token nextToken() throws IOException
	{
		return analizadorLexico.nextToken();
	}
	
	/* AUXILIARES */
	private boolean esOpComparacion(){ return (tokenActual(CatLexica.IGUAL) || tokenActual(CatLexica.MAYOR) || tokenActual(CatLexica.MENOR) || tokenActual(CatLexica.MAYOR_IGUAL) || tokenActual(CatLexica.MENOR_IGUAL) || tokenActual(CatLexica.DISTINTO)); }
	private boolean esOpMultiplicativo(){ return (tokenActual(CatLexica.POR) || tokenActual(CatLexica.DIV) || tokenActual(CatLexica.AND)); }
	private boolean esOpAditivo(){ return (tokenActual(CatLexica.MAS) || tokenActual(CatLexica.MENOS) || tokenActual(CatLexica.OR)); }
	private boolean esOpUnario() { return (tokenActual(CatLexica.NOT)); }
	
	/* REGLAS */
	
	// XXX
	private Programa programaR1(Declaraciones d, Cuerpo c)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ProgramaR1(d, c);
		}
		else
		{
			return gramaticaAtributos.new ProgramaR1Debug(d, c);
		}
	}
	
	private Declaraciones declaracionesR1(ListaDeclaraciones ld)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new DeclaracionesR1(ld); 
		}
		else
		{
			return gramaticaAtributos.new DeclaracionesR1Debug(ld);
		}
	}
	private Declaraciones declaracionesR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new DeclaracionesR2(); 
		}
		else
		{
			return gramaticaAtributos.new DeclaracionesR2Debug();
		}
	}
	
	
	private ListaDeclaraciones listaDeclaracionesR1(ListaDeclaraciones ld, Declaracion d)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaDeclaracionesR1(ld, d);
		}
		else
		{
			return gramaticaAtributos.new ListaDeclaracionesR1Debug(ld, d);	
		}
	}
	private ListaDeclaraciones listaDeclaracionesR2(Declaracion d)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaDeclaracionesR2(d);
		}
		else
		{	
			return gramaticaAtributos.new ListaDeclaracionesR2Debug(d);
		}
	}
	
	
	private Declaracion declaracionR1(Token iden)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new DeclaracionR1(iden);
		}
		else
		{	
			return gramaticaAtributos.new DeclaracionR1Debug(iden);
		}
	}
	private Declaracion declaracionR2(Token iden, ParametrosFormales pfs, Declaraciones d, Cuerpo c)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new DeclaracionR2(iden, pfs, d, c);
		}
		else
		{	return gramaticaAtributos.new DeclaracionR2Debug(iden, pfs, d, c);
	
		}
	}
	
	private ParametrosFormales parametrosFormalesR1(ListaParametrosFormales lpf)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametrosFormalesR1(lpf);
		}
		else
		{
			return gramaticaAtributos.new ParametrosFormalesR1Debug(lpf);
		}
	}
	private ParametrosFormales parametrosFormalesR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametrosFormalesR2();
		}
		else
		{
			return gramaticaAtributos.new ParametrosFormalesR2Debug();
		}
	}
	
	private ListaParametrosFormales listaParametrosFormalesR1(ListaParametrosFormales lpf, ParametroFormal pf)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaParametrosFormalesR1(lpf, pf);
		}
		else
		{
			return gramaticaAtributos.new ListaParametrosFormalesR1Debug(lpf, pf);	
		}
	}
	private ListaParametrosFormales listaParametrosFormalesR2(ParametroFormal pf)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaParametrosFormalesR2(pf);
		}
		else
		{
			return gramaticaAtributos.new ListaParametrosFormalesR2Debug(pf);	
		}
	}
	
	private ParametroFormal parametroFormalR1(Token iden)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametroFormalR1(iden);
		}
		else
		{
			return gramaticaAtributos.new ParametroFormalR1Debug(iden);	
		}
	}
	private ParametroFormal parametroFormalR2(Token iden)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametroFormalR2(iden);
		}
		else
		{
			return gramaticaAtributos.new ParametroFormalR2Debug(iden);	
		}
	}
	
	private Cuerpo cuerpoR1(Instrucciones is)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new CuerpoR1(is);
		}
		else
		{
			return gramaticaAtributos.new CuerpoR1Debug(is); 
		}
	}

	
	private Instrucciones instruccionesR1(Instrucciones is, Instruccion i)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionesR1(is, i);
		}
		else
		{
			return gramaticaAtributos.new InstruccionesR1Debug(is, i);
		}
	}
	private Instrucciones instruccionesR2(Instruccion i)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionesR2(i);
		}
		else
		{
			return gramaticaAtributos.new InstruccionesR2Debug(i);
		}
	}
	
	
	private Instruccion instruccionR1(Token iden, Exp0 e)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionR1(iden, e);
		}
		else
		{
			return gramaticaAtributos.new InstruccionR1Debug(iden, e);
		}
	}
	private Instruccion instruccionR2(Token iden, ParametrosReales pr)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionR2(iden, pr);
		}
		else
		{
			return gramaticaAtributos.new InstruccionR2Debug(iden, pr);
		}
	}
	private Instruccion instruccionR3(Exp0 e, Instrucciones is, ParteElse pe)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionR3(e, is, pe);
		}
		else
		{
			return gramaticaAtributos.new InstruccionR3Debug(e, is, pe);
		}
	}
	
	private Instruccion instruccionR4(Token iden)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new InstruccionR4(iden);
		}
		else
		{
			return gramaticaAtributos.new InstruccionR4Debug(iden);
		}
	}
	
	private ParametrosReales parametrosRealesR1(ListaParametrosReales lpr)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametrosRealesR1(lpr);
		}
		else
		{
			return gramaticaAtributos.new ParametrosRealesR1Debug(lpr);
		}
	}
	private ParametrosReales parametrosRealesR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParametrosRealesR2();
		}
		else
		{
			return gramaticaAtributos.new ParametrosRealesR2Debug();
		}
	}
	
	private ListaParametrosReales listaParametrosRealesR1(ListaParametrosReales lpr, Exp0 e0)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaParametrosRealesR1(lpr, e0);
		}
		else
		{
			return gramaticaAtributos.new ListaParametrosRealesR1Debug(lpr, e0);	
		}
	}
	private ListaParametrosReales listaParametrosRealesR2(Exp0 e0)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ListaParametrosRealesR2(e0);
		}
		else
		{
			return gramaticaAtributos.new ListaParametrosRealesR2Debug(e0);	
		}
	}
	
	private ParteElse parteElseR1(Instrucciones is)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParteElseR1(is);
		}
		else
		{
			return gramaticaAtributos.new ParteElseR1Debug(is);
		}
	}
	private ParteElse parteElseR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new ParteElseR2();
		}
		else
		{
			return gramaticaAtributos.new ParteElseR2Debug();
		}
	}
	
	private Exp0 exp0R1(Exp1 e1_h, OpComparacion o, Exp1 e1) 
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp0R1(e1_h, o, e1);
		}
		else
		{
			return gramaticaAtributos.new Exp0R1Debug(e1_h, o, e1);
		}
	}
	private Exp0 exp0R2(Exp1 e1_h)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp0R2(e1_h);
		}
		else
		{
			return gramaticaAtributos.new Exp0R2Debug(e1_h);
		}
	}

	
	private Exp1 exp1R1(Exp1 e1_h, OpAditivo o, Exp2 e2)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp1R1(e1_h, o, e2);
		}
		else
		{
			return gramaticaAtributos.new Exp1R1Debug(e1_h, o, e2);
		}
	}
	private Exp1 exp1R2(Exp2 e2)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp1R2(e2);
		}
		else
		{
			return gramaticaAtributos.new Exp1R2Debug(e2);
		}
	}

	
	private Exp2 exp2R1(Exp2 e2, OpMultiplicativo o, Exp3 e3)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp2R1(e2, o, e3);
		}
		else
		{
			return gramaticaAtributos.new Exp2R1Debug(e2, o, e3);
		}
	}
	private Exp2 exp2R2(Exp3 e3)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp2R2(e3);
		}
		else
		{
			return gramaticaAtributos.new Exp2R2Debug(e3);
		}
	}
	
	
	private Exp3 exp3R1(OpUnario o, Exp3 e3)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp3R1(o, e3);
		}
		else
		{
			return gramaticaAtributos.new Exp3R1Debug(o, e3);
		}
	}
	private Exp3 exp3R2(Exp4 e4)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp3R2(e4);
		}
		else
		{
			return gramaticaAtributos.new Exp3R2Debug(e4);
		}
	}
	
	
	private Exp4 exp4R1(Token num)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp4R1(num);
		}
		else
		{
			return gramaticaAtributos.new Exp4R1Debug(num);
		}
	}
	private Exp4 exp4R2(Token iden)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp4R2(iden);
		}
		else
		{
			return gramaticaAtributos.new Exp4R2Debug(iden);
		}
	}
	private Exp4 exp4R3(Exp0 e0)
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new Exp4R3(e0);
		}
		else
		{
			return gramaticaAtributos.new Exp4R3Debug(e0);
		}
	}
	
	private OpComparacion opComparacionR1()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR1();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR1Debug();
		}
	}
	private OpComparacion opComparacionR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR2();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR2Debug();
		}
	}
	private OpComparacion opComparacionR3()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR3();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR3Debug();
		}
	}
	private OpComparacion opComparacionR4()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR4();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR4Debug();
		}
	}
	private OpComparacion opComparacionR5()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR5();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR5Debug();
		}
	}
	private OpComparacion opComparacionR6()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpComparacionR6();
		}
		else
		{
			return gramaticaAtributos.new OpComparacionR6Debug();
		}
	}
	private OpAditivo opAditivoR1()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpAditivoR1();
		}
		else
		{
			return gramaticaAtributos.new OpAditivoR1Debug();
		}
	}
	private OpAditivo opAditivoR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpAditivoR2();
		}
		else
		{
			return gramaticaAtributos.new OpAditivoR2Debug();
		}
	}
	private OpAditivo opAditivoR3()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpAditivoR3();
		}
		else
		{
			return gramaticaAtributos.new OpAditivoR3Debug();
		}
	}
	private OpMultiplicativo opMultiplicativoR1()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpMultiplicativoR1();
		}
		else
		{
			return gramaticaAtributos.new OpMultiplicativoR1Debug();
		}
	}
	private OpMultiplicativo opMultiplicativoR2()
	{
		
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpMultiplicativoR2();
		}
		else
		{	
			return gramaticaAtributos.new OpMultiplicativoR2Debug();
		}
	}
	private OpMultiplicativo opMultiplicativoR3()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpMultiplicativoR3();
		}
		else
		{
			return gramaticaAtributos.new OpMultiplicativoR3Debug();
		}
	}
	private OpUnario opUnarioR1()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpUnarioR1Debug();
		}
		else
		{
			return gramaticaAtributos.new OpUnarioR1();
		}
	}
	private OpUnario opUnarioR2()
	{
		if (!DEBUG_ACTIVADO)
		{
			return gramaticaAtributos.new OpUnarioR2();
		}
		else
		{
			return gramaticaAtributos.new OpUnarioR2Debug();
		}
	}
	
	

}