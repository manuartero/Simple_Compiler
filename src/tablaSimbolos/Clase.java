package tablaSimbolos;

public class Clase {
	public boolean es_var; // !es_pvar
	public boolean es_proc;
	
	public Clase(EnumClases clase)
	{
		if (clase == EnumClases.VAR)
		{
			this.es_var = true;
			this.es_proc = false;
		}
		else if (clase == EnumClases.PVAR)
		{
			this.es_var = false;
			this.es_proc = false;
		}
		else // t == Enumclases.PROC
		{
			this.es_proc = true;
		}
	}

	
	public String toString()
	{
		if (es_proc) return "CLASE PROC";
		else if (es_var) return "CLASE VAR";
		else return "CLASE PVAR";
	}

}
