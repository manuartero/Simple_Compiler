package maquinaPila;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MaquinaVirtual {
	
	private static final int TAM_MEMORIA = 2048;
 	private static final int DIR_COMIENZO_DATOS = 500; // 512
	
	private Stack<Integer> pilaEvaluacion;
	private int[] memoria;
	private int ultimaPosMemoria; 
	
	private InstruccionesMaquina[] programaEntrada;
	private int pc;
	
	/* CONSTRUCTORAS */
	
	public MaquinaVirtual(InstruccionesMaquina[] programa)
	{
		pilaEvaluacion = new Stack<Integer>();
		memoria = new int[TAM_MEMORIA];
		ultimaPosMemoria = DIR_COMIENZO_DATOS;
		programaEntrada = programa;
		pc = 0;
		
		rellenaMemoria(-1);
	}
	
	public MaquinaVirtual(List<InstruccionesMaquina> listaEntrada)
	{
		pilaEvaluacion = new Stack<Integer>();
		memoria = new int[TAM_MEMORIA];
		ultimaPosMemoria = DIR_COMIENZO_DATOS;
		programaEntrada = new InstruccionesMaquina[listaEntrada.size()];
		int j = 0;
		for (InstruccionesMaquina i : listaEntrada)
		{
			programaEntrada[j++] = i;
		}
		this.pc = 0;
		
		rellenaMemoria(-1);
	}

	@SuppressWarnings("unchecked")
	public MaquinaVirtual(String rutaArchivo) throws Exception
	{
		this.pilaEvaluacion = new Stack<Integer>();
		this.memoria = new int[TAM_MEMORIA];
		this.ultimaPosMemoria = DIR_COMIENZO_DATOS;
		try
		{
			List<InstruccionesMaquina> listaEntrada;
			listaEntrada = (List<InstruccionesMaquina>) new ObjectInputStream(new FileInputStream(rutaArchivo)).readObject();
			programaEntrada = new InstruccionesMaquina[listaEntrada.size()];
			int j = 0;
			for (InstruccionesMaquina i : listaEntrada)
			{
				programaEntrada[j++] = i;
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.pc = 0;
		
		//
		rellenaMemoria(-1);
	}
	
	// puede que no sea necesario
	private void rellenaMemoria(int v)
	{
		for(int i = 0; i < this.memoria.length; i++)
		{
			memoria[i] = v;
		}
	}
	
	/* METODOS */
	public void run()
	{
		while (pc < programaEntrada.length)
		{
			
			InstruccionesMaquina debug_actual = programaEntrada[pc];
			debug_actual.getClass();
			
			programaEntrada[pc].ejecuta(this);
		}
	}

	
	public void run(boolean traza) {

		if (traza)
			System.out.println(pilaEvaluacion + "\n");

		while (pc < programaEntrada.length)
		{
			if (traza)
			{
				System.out.print(programaEntrada[pc] + "=>");
			}
			programaEntrada[pc].ejecuta(this);
			if (traza)
			{
				System.out.println(pilaEvaluacion);
				System.out.println(this.toString());
				System.out.println();
			}
		}
	}

	public void pila_apilar(int value)
	{
		pilaEvaluacion.push(value);
	}

	public int pila_desapilar()
	{
		return pilaEvaluacion.pop();
	}
	
	public int pila_consultarCima()
	{
		return pilaEvaluacion.lastElement();
	}
	
	public void pila_flip()
	{
		int cima = pilaEvaluacion.pop();
		int subcima = pilaEvaluacion.pop();
		pilaEvaluacion.push(cima);
		pilaEvaluacion.push(subcima);
	}

	public int getUltimaPosMemoria()
	{
		return ultimaPosMemoria;
	}
	
	public void setUltimaPosMemoria(int ultimaPosMemoria)
	{
		this.ultimaPosMemoria = ultimaPosMemoria;
	}
	
	public int getPC()
	{
		return pc;
	}
	
	public void incrementarPC()
	{
		pc++;
	}

	public void setPC(int pc)
	{
		this.pc = pc;
	}

	public void memoria_guardarValor(int posicion, int valor)
	{
		memoria[posicion + DIR_COMIENZO_DATOS] = valor;
		actualizarUltimaPosMemoria(posicion + DIR_COMIENZO_DATOS);
	}

	public int memoria_leerValor(int posicion) 
	{
		return memoria[posicion + DIR_COMIENZO_DATOS];
	}

	public String toString()
	{
		String s = " ------------------------- ";
		s += "\n MEMORIA \n";
		for (int i = DIR_COMIENZO_DATOS; i <= ultimaPosMemoria; i++)
		{
			s += "[" + i + "] => " + memoria[i] + " | ";			
		}
		
		s += "\n PILA \n";
		Iterator<Integer> it = pilaEvaluacion.iterator();
		while(it.hasNext())
		{
			s += "<" + it.next().toString() +">\n" ;
		}
		
		return s; 
	}

	private void actualizarUltimaPosMemoria(int m)
	{
		if (ultimaPosMemoria < m)
			ultimaPosMemoria = m;
	}

	public static void test(String[] args) {
		InstruccionesMaquina i0 = InstruccionesMaquina.nueva_apila(0);
		InstruccionesMaquina i1 = InstruccionesMaquina.nueva_apila(1);
		InstruccionesMaquina i2 = InstruccionesMaquina.nueva_apila(3);
		InstruccionesMaquina i3 = InstruccionesMaquina.nueva_desapila_dir(0);
		InstruccionesMaquina i4 = InstruccionesMaquina.nueva_desapila_dir(1);
		InstruccionesMaquina i5 = InstruccionesMaquina.nueva_apila_dir(0);
		InstruccionesMaquina i6 = InstruccionesMaquina.nueva_apila_dir(0);
		InstruccionesMaquina i7 = InstruccionesMaquina.nueva_desapila_ind();
		InstruccionesMaquina d2 = InstruccionesMaquina.nueva_debug(".");
		InstruccionesMaquina i8 = InstruccionesMaquina.nueva_apila_dir(1);
		InstruccionesMaquina i9 = InstruccionesMaquina.nueva_eq();
		InstruccionesMaquina i10 = InstruccionesMaquina.nueva_not();
		InstruccionesMaquina i11 = InstruccionesMaquina.nueva_apila(5);
		InstruccionesMaquina i12 = InstruccionesMaquina.nueva_suma();
		InstruccionesMaquina i13 = InstruccionesMaquina.nueva_apila_dir(0);
		InstruccionesMaquina d1 = InstruccionesMaquina.nueva_debug(".");
		InstruccionesMaquina i14 = InstruccionesMaquina.nueva_apila_dir(1);
		InstruccionesMaquina i15 = InstruccionesMaquina.nueva_desapila_ind();
		InstruccionesMaquina i16 = InstruccionesMaquina.nueva_apila(6);
		InstruccionesMaquina i17 = InstruccionesMaquina.nueva_resta();
		InstruccionesMaquina i18 = InstruccionesMaquina.nueva_apila_ind();
		InstruccionesMaquina i19 = InstruccionesMaquina.nueva_escribe();
		
		List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
		lista.add(i0);  lista.add(i1);  lista.add(i2);  lista.add(i3);  lista.add(i4);
		lista.add(d1);
		lista.add(i5);  lista.add(i6);  lista.add(i7);  lista.add(i8);  lista.add(i9);
		lista.add(i10); lista.add(i11); lista.add(i12); lista.add(i13); lista.add(i14);
		lista.add(d2);
		lista.add(i15); lista.add(i16); lista.add(i17); lista.add(i18); lista.add(i19);
		
		MaquinaVirtual mv = new MaquinaVirtual(lista);
		
		/**
		 * System => 1
		 * 
		 * Memoria
		 *   0  1  2  3
		 * [ 3  1  0  1 ]
		 * 
		 * Pila <>
		 */
		
		mv.run(false);
		System.out.println(mv.toString());
	}

}
