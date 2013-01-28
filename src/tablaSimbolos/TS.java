package tablaSimbolos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TS {
	/**
	 * Nivel [0, 1, 2, 3, 4, 5, 6, 7]
	 * 				 i 
	 * 		[ID | <clase, tipo, dir>]
	 * 		[ID | <clase, tipo, dir>]
	 * 		[ID | <clase, tipo, dir>]
	 */
	private class Tupla {
		public Clase clase; // var - proc
		public Tipo tipo;
		public int dir;

		public Tupla(Clase clase, Tipo tipo, int dir) {
			this.clase = clase;
			this.tipo = tipo;
			this.dir = dir;
		}

		public String toString() {
			return  "> [" + this.clase + " | " +  this.tipo + " | " + "Dir =>" + this.dir + "] ";

		}
	}

	// -----------------------------------------
	private ArrayList<HashMap<String, Tupla>> tabla;

	public TS() {
		tabla = new ArrayList<HashMap<String, Tupla>>();
		tabla.add(new HashMap<String, Tupla>());
	}

	public TS(ArrayList<HashMap<String, Tupla>> tabla) {
		this.tabla = tabla;
	}

	private void crearHastaNivel(int hasta_nivel)
	{
		int nivel_actual = this.ultimoNivel() + 1;
		while(nivel_actual <= hasta_nivel)
		{	
			this.tabla.add(nivel_actual, new HashMap<String, Tupla>());
			nivel_actual ++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public TS duplicarTS() {
		TS nuevaTS = new TS();
		int nivel_actual = this.ultimoNivel();
		while(nivel_actual >= 0)
		{
			HashMap<String, Tupla> hash = (HashMap<String, Tupla>) this.tabla.get(nivel_actual).clone();
			if (nuevaTS.ultimoNivel() < nivel_actual )
			{
				nuevaTS.crearHastaNivel(nivel_actual);
			}
			nuevaTS.tabla.set(nivel_actual, hash);
			nivel_actual--;
		}
		return nuevaTS;
	}

	public String toString() {
		int nivelActual = 0;
		Iterator<HashMap<String, Tupla>> it = tabla.iterator();
		String s = "";
		while (it.hasNext()) {
			HashMap<String, Tupla> h = it.next();
			s += "Nivel:" + nivelActual + " ===> " + h.toString() + "\n";
			nivelActual++;
		}
		return s;
	}

	private void crearNivel(int nivel)
	{
		this.tabla.add(nivel, new HashMap<String, Tupla>());
	}
	
	public int ultimoNivel()
	{
		return tabla.size() - 1;
	}
	
	/* metodos */
	
	public void addNivel()
	{
		int ultimoNivel = ultimoNivel();
		this.crearNivel(ultimoNivel + 1);
	}
	
	public void addElement_inLevel(String iden, Clase clase, Tipo tipo, int dir, int nivel)
	{
		// si es un nivel nuevo, hay que crearlo
		if (nivel > ultimoNivel())
		{
			tabla.add(nivel, new HashMap<String, Tupla>());
		}
		HashMap<String, Tupla> hash = tabla.get(nivel);
		hash.put(iden, new Tupla(clase, tipo, dir));
		tabla.set(nivel, hash);
	}

	public boolean existeSimbEnUltimoNivel(String id) {
		HashMap<String, Tupla> ultimoNivel = tabla.get(ultimoNivel());
		return ultimoNivel.containsKey(id);
	}

	public Tipo tipoDeIden(String iden) {
		// Recorrer la Tabla desde el ultimo nivel hasta el ultimo
		int nivel_actual = ultimoNivel();
		Tupla tupla;
		while (nivel_actual >= 0) {
			HashMap<String, Tupla> hash = tabla.get(nivel_actual);
			tupla = hash.get(iden);
			if (tupla != null)
			{
				return tupla.tipo;
			} 
			else
			{
				nivel_actual--;
			}
		}
		return new Tipo(EnumTipos.ERROR);
	}

	public Clase claseDeIden(String iden)
	{
		int nivel_actual = ultimoNivel();
		Tupla tupla;
		while (nivel_actual >= 0)
		{
			HashMap<String, Tupla> hash = tabla.get(nivel_actual);
			tupla = hash.get(iden);
			if(tupla != null)
			{
				return tupla.clase;
			}
			else
			{
				nivel_actual--;
			}
		}
		return null;
	}
	
	public Integer direccionDeIden(String iden)
	{
		int nivel_actual = ultimoNivel();
		Tupla tupla;
		while (nivel_actual >= 0)
		{
			HashMap<String, Tupla> hash = tabla.get(nivel_actual);
			tupla = hash.get(iden);
			if(tupla != null)
			{
				return tupla.dir;
			}
			else
			{
				nivel_actual--;
			}
		}
		return -1;
	}
	
	public Integer nivelDeIden(String iden)
	{
		int nivel_actual = ultimoNivel();
		Tupla tupla;
		while (nivel_actual >= 0)
		{
			HashMap<String, Tupla> hash = tabla.get(nivel_actual);
			tupla = hash.get(iden);
			if(tupla != null)
			{
				return nivel_actual;
			}
			else
			{
				nivel_actual--;
			}
		}
		return -1;
	}
	
	/* Es un proc con numParametros correcto */
	public boolean encontrarProcedimiento(String nombre, int numParametros)
	{

		int nivel = ultimoNivel();
		while(nivel >= 0)
		{
			HashMap<String, Tupla> hash = tabla.get(nivel);
			Tupla tuplaEvaluada = hash.get(nombre);
			
			if(tuplaEvaluada != null)
			{
				if(	!tuplaEvaluada.tipo.es_num && !tuplaEvaluada.tipo.es_error)
				{
					if (tuplaEvaluada.tipo.params.size() == numParametros)
					{
						return true;
					}
				}
			}
			nivel--;
		}
		return false;
	}
	
	// ---------------------------------------
	public static void test(String args[]) {
		/* Construccion de una tabla para pruebas */
		TS ts = new TS();
		/* addElement_inLevel(id,  Var?, 					tipo,                   dir, nivel) */
		ts.addElement_inLevel("x", new Clase(EnumClases.VAR), new Tipo(EnumTipos.NUM),  0,  0);
		ts.addElement_inLevel("x", new Clase(EnumClases.PVAR), new Tipo(EnumTipos.PROC), 0,  1);
		ts.addElement_inLevel("c", new Clase(EnumClases.VAR), new Tipo(EnumTipos.NUM),  2,  0);
		ts.addElement_inLevel("y", new Clase(EnumClases.VAR), new Tipo(EnumTipos.NUM),  1,  0);
		System.out.println(ts.toString());
		
		/* Test para tipoDeIden() */
		System.out.println("¿Tipo de y? (NUM) => " + ts.tipoDeIden("y"));
		System.out.println("¿Tipo de x? (PROC) => " + ts.tipoDeIden("x"));
		System.out.println("¿Tipo de o? (NO) => " + ts.tipoDeIden("o"));
		
		/* Test para existeSimbEnUltimoNivel */
		System.out.println("¿Existe y? (NO) => " + ts.existeSimbEnUltimoNivel("y"));
		System.out.println("¿Existe x? (SI) => " + ts.existeSimbEnUltimoNivel("x"));
		
		/* Test para duplicar TS */
		TS nuevaTS = ts.duplicarTS();
		nuevaTS.addElement_inLevel("UUU", new Clase(EnumClases.PVAR), new Tipo(EnumTipos.NUM), 3, 2);
		System.out.println("\n Nueva Tabla \n" + nuevaTS.toString());
		System.out.println("\n Tabla anterior \n" + ts.toString());
		
		/* Test para nivelDe()*/
		System.out.println("¿nivel y? (0) => " + ts.nivelDeIden("y"));
		System.out.println("¿nivel x? (1) => " + ts.nivelDeIden("x"));
		
		/* Test para direccionDe()*/
		System.out.println("¿Direccion y? (1) => " + ts.direccionDeIden("y"));
		System.out.println("¿Direccion x? (0) => " + ts.direccionDeIden("x"));
		
		System.out.println("FIN TEST");
		System.exit(0);
	}

}
