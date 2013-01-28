package maquinaPila;

public abstract class InstruccionesMaquina
{
	public enum CodigosInstruccion
	{
		APILA, APILA_DIR, APILA_IND, DESAPILA, DESAPILA_DIR, DESAPILA_IND,
		NOT, EQ, NEQ, GT, GE, LT, LE,
		SUMA, RESTA, OR, MUL, DIV, AND, 
		SALTO, SALTO_FALSO, SALTO_IND,
		ESCRIBE, COPIA, NEGAR,
		DEBUG
	};
	public static InstruccionesMaquina nueva_apila(int val){ return new InstruccionApila(val); }
	public static InstruccionesMaquina nueva_apila_ind(){ return new InstruccionApila_ind(); }
	public static InstruccionesMaquina nueva_apila_dir(int dir){ return new InstruccionApila_dir(dir); }
	public static InstruccionesMaquina nueva_desapila(){ return new InstruccionDesapila(); }
	public static InstruccionesMaquina nueva_desapila_dir(int dir){ return new InstruccionDesapila_dir(dir); }
	public static InstruccionesMaquina nueva_desapila_ind(){ return new InstruccionDesapila_ind(); }
	
	public static InstruccionesMaquina nueva_not(){ return new InstruccionNot(); }
	public static InstruccionesMaquina nueva_negar(){ return new InstruccionNegar(); }
	public static InstruccionesMaquina nueva_eq(){ return new InstruccionEQ(); }
	public static InstruccionesMaquina nueva_neq(){ return new InstruccionNEQ(); }
	public static InstruccionesMaquina nueva_gt(){ return new InstruccionGT(); }
	public static InstruccionesMaquina nueva_ge(){ return new InstruccionGE(); }
	public static InstruccionesMaquina nueva_lt(){ return new InstruccionLT(); }
	public static InstruccionesMaquina nueva_le(){ return new InstruccionLE(); }
	
	public static InstruccionesMaquina nueva_suma(){ return new InstruccionSuma(); }
	public static InstruccionesMaquina nueva_resta(){ return new InstruccionResta(); }
	public static InstruccionesMaquina nueva_or(){ return new InstruccionOR(); }
	public static InstruccionesMaquina nueva_mul(){ return new InstruccionMul(); }
	public static InstruccionesMaquina nueva_div(){ return new InstruccionDiv(); }
	public static InstruccionesMaquina nueva_and(){ return new InstruccionAND(); }

	public static InstruccionesMaquina nueva_salto(int d){ return new InstruccionSalto(d); }
	public static InstruccionesMaquina nueva_salto_falso(int d){ return new InstruccionSaltoFalso(d); }	
	public static InstruccionesMaquina nueva_salto_ind(){ return new InstruccionSaltoInd(); }
	
	public static InstruccionesMaquina nueva_escribe(){ return new InstruccionEscribe(); }
	public static InstruccionesMaquina nueva_copia(){ return new InstruccionCopia(); }
	public static InstruccionesMaquina nueva_debug(String m){ return new InstruccionDebug(m); }
	
	abstract public CodigosInstruccion codigoInstruccion();
	abstract public void ejecuta(MaquinaVirtual mv);
	
	//---------------------------------------------------
	
	/* APILA - DESAPILA */
	
	/** (valor) => apilar(valor) */
	public static class InstruccionApila extends InstruccionesMaquina
	{
		private int val;
		private InstruccionApila(int val)
		{
			this.val = val;
		}
		
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.APILA; }
		public String toString(){ return "APILA(" + val + ")"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			mv.pila_apilar(val);
			mv.incrementarPC();
		}
	}
	
	/** (dir) => apilar(memoria(dir)) */
	public static class InstruccionApila_dir extends InstruccionesMaquina
	{
		private int dir;
		private InstruccionApila_dir(int dir)
		{
			this.dir = dir;
		}
		
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.APILA_DIR; }
		public String toString(){ return "APILA DIR(" + dir + ")"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.memoria_leerValor(dir);
			mv.pila_apilar(valor);
			mv.incrementarPC();
		}
	}
	
	/** () => apilar(memoria[cima]) */
	public static class InstruccionApila_ind extends InstruccionesMaquina
	{
		private InstruccionApila_ind(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.APILA_IND; }
		public String toString(){ return "APILA IND"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int dir = mv.pila_desapilar();
			int valor = mv.memoria_leerValor(dir);
			mv.pila_apilar(valor);
			mv.incrementarPC();
		}
	}
	
	/** () => desapilar */
	public static class InstruccionDesapila extends InstruccionesMaquina
	{
		private InstruccionDesapila(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.DESAPILA; }
		public String toString(){ return "DESAPILA"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			mv.pila_desapilar();
			mv.incrementarPC();
		}
	}
	
	/** (dir) => memoria[dir] = cima */
	public static class InstruccionDesapila_dir extends InstruccionesMaquina
	{
		private int dir;
		private InstruccionDesapila_dir(int dir)
		{
			this.dir = dir;
		}
		
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.DESAPILA_DIR; }
		public String toString(){ return "DESAPILA DIR(" + dir + ")"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int cima = mv.pila_desapilar();
			mv.memoria_guardarValor(dir, cima);
			mv.incrementarPC();
		}
	}
	
	/** () => memoria[subCima] = cima */
	public static class InstruccionDesapila_ind extends InstruccionesMaquina
	{
		private InstruccionDesapila_ind(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.DESAPILA_IND; }
		public String toString(){ return "DESAPILA IND"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.pila_desapilar();
			int dir = mv.pila_desapilar();
			mv.memoria_guardarValor(dir, valor);
			mv.incrementarPC();
		}
	}
	
	/* COMPARACION */
	
	public static class InstruccionNegar extends InstruccionesMaquina
	{
		private InstruccionNegar(){}
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.NEGAR; }
		public String toString(){ return "-"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.pila_desapilar();
			valor = 0 - valor;			
			mv.pila_apilar(valor);
			mv.incrementarPC();
		}
	}
	
	/** !¿ (valor) => cima = 1 si (valor == 0) */
	/** cima <= 1 si cima == 0 */
	public static class InstruccionNot extends InstruccionesMaquina 
	{
		private InstruccionNot(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.NOT; }
		public String toString(){ return "NOT"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.pila_desapilar();
			if (valor == 0)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}
	
	/** cima <= 1 si cima == subCima ; 0 cc */
	public static class InstruccionEQ extends InstruccionesMaquina 
	{
		private InstruccionEQ(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.EQ; }
		public String toString(){ return "EQ"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 == v2)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}

	/** cima <= 0 si cima == subcima ; 1 cc */
	public static class InstruccionNEQ extends InstruccionesMaquina 
	{
		private InstruccionNEQ(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.NEQ; }
		public String toString(){ return "NEQ"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 == v2)
			{
				mv.pila_apilar(0);
			}
			else
			{
				mv.pila_apilar(1);
			}
			mv.incrementarPC();
		}
	}

	/** cima = 1 si cima > subcima */
	public static class InstruccionGT extends InstruccionesMaquina 
	{
		private InstruccionGT(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.GT; }
		public String toString(){ return "GT"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 <= v2)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}
	
	/** cima = 1 si cima >= subcima */
	public static class InstruccionGE extends InstruccionesMaquina 
	{
		private InstruccionGE(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.GE; }
		public String toString(){ return "GE"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 < v2)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}
	
	/** cima = 1 si cima < subcima */
	public static class InstruccionLT extends InstruccionesMaquina 
	{
		private InstruccionLT(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.LT; }
		public String toString(){ return "LT"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 >= v2)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}
	
	/** cima = 1 si cima <= subcima */
	public static class InstruccionLE extends InstruccionesMaquina 
	{
		private InstruccionLE(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.LE; }
		public String toString(){ return "LE"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 > v2)
			{
				mv.pila_apilar(1);
			}
			else
			{
				mv.pila_apilar(0);
			}
			mv.incrementarPC();
		}
	}

	/* OPERACIONES */
	
	/** cima <= cima + subcima */
	public static class InstruccionSuma extends InstruccionesMaquina
	{
		private InstruccionSuma(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.SUMA; }
		public String toString(){ return "SUMA"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			mv.pila_apilar(v1 + v2);
			mv.incrementarPC();
		}
	}
	
	/** cima <= cima - subcima */
	public static class InstruccionResta extends InstruccionesMaquina
	{
		private InstruccionResta(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.RESTA; }
		public String toString(){ return "RESTA"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			mv.pila_apilar(v2 - v1); //!¡!¡!¡
			mv.incrementarPC();
		}
	}
	
	/** cima <= 0 si cima == 0 ^ cima == 0 ; 1 cc */
	public static class InstruccionOR extends InstruccionesMaquina
	{
		private InstruccionOR(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.OR; }
		public String toString(){ return "OR"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 == 0 && v2 == 0)
			{
				mv.pila_apilar(0);
			}
			else
			{
				mv.pila_apilar(1);
			}
			mv.incrementarPC();
		}
	}
	
	/** cima <= cima * subcima */
	public static class InstruccionMul extends InstruccionesMaquina
	{
		private InstruccionMul(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.MUL; }
		public String toString(){ return "MUL"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			mv.pila_apilar(v1 * v2);
			mv.incrementarPC();
		}
	}

	/** cima <= cima / subcima */
	public static class InstruccionDiv extends InstruccionesMaquina
	{
		private InstruccionDiv(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.DIV; }
		public String toString(){ return "DIV"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			mv.pila_apilar(v1 / v2);
			mv.incrementarPC();
		}
	}
	
	/** cima <= 0 si cima v subcima == 0 ; 1 cc */
	public static class InstruccionAND extends InstruccionesMaquina
	{
		private InstruccionAND(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.AND; }
		public String toString(){ return "AND"; }
		public void ejecuta(MaquinaVirtual mv) 
		{
			int v1 = mv.pila_desapilar();
			int v2 = mv.pila_desapilar();
			if (v1 == 0 || v2 == 0)
			{
				mv.pila_apilar(0);
			}
			else
			{
				mv.pila_apilar(1);
			}
			mv.incrementarPC();
		}
	}
	
	/* SALTO */
	
	/** (dir) => PC = dir*/
	public static class InstruccionSalto extends InstruccionesMaquina
	{
		private int dir;
		private InstruccionSalto(int dir)
		{
			this.dir = dir;
		}
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.SALTO; }
		public String toString(){ return "SALTO => " + dir; }
		public void ejecuta(MaquinaVirtual mv)
		{
			mv.setPC(dir);
		}
	}
	
	/** (dir) => PC = dir si cima == 0 */
	public static class InstruccionSaltoFalso extends InstruccionesMaquina
	{
		private int dir;
		private InstruccionSaltoFalso(int dir)
		{
			this.dir = dir;
		}
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.SALTO; }
		public String toString(){ return "SALTO FALSO => " + dir; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int cima = mv.pila_desapilar();
			if (cima == 0) 
			{
				mv.setPC(dir);
			}
			else
			{
				mv.incrementarPC();
			}
			
		}
	}
	
	/** () => PC = cima */
	public static class InstruccionSaltoInd extends InstruccionesMaquina
	{
		private InstruccionSaltoInd(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.SALTO_IND; }
		public String toString(){ return "SALTO IND"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int cima = mv.pila_desapilar();
			mv.setPC(cima);
		}
	}
	
	/* IO */
	
	/** () => out<memoria[cima]> */
	public static class InstruccionEscribe extends InstruccionesMaquina
	{
		private InstruccionEscribe(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.ESCRIBE; }
		public String toString(){ return "ESCRIBE"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.pila_desapilar();
			System.out.println(valor);
			mv.incrementarPC();
		}
	}
	
	/* OTHER */
	
	/** () => cima <= cima */
	public static class InstruccionCopia extends InstruccionesMaquina
	{
		private InstruccionCopia(){ }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.COPIA; }
		public String toString(){ return "COPIA"; }
		public void ejecuta(MaquinaVirtual mv)
		{
			int valor = mv.pila_desapilar();
			mv.pila_apilar(valor);
			mv.pila_apilar(valor);
			mv.incrementarPC();
		}
	}
	
	/* DEBUG */
	
	public static class InstruccionDebug extends InstruccionesMaquina
	{
		private String msg;
		private InstruccionDebug(String msg){ this.msg = msg; }
		public CodigosInstruccion codigoInstruccion(){ return CodigosInstruccion.DEBUG; }
		public String toString(){ return msg; }
		public void ejecuta(MaquinaVirtual mv)
		{
			mv.incrementarPC();
		}
	}

}
