package tablaSimbolos;

import java.util.List;

public class Tipo {

	public boolean es_num; // !es_proc
	public boolean es_error;
	public List<Boolean> params; // ATENCION SOLO PARA PROCEDURES
	
	public Tipo(EnumTipos tipo)
	{
		if (tipo == EnumTipos.NUM)
		{
			this.es_num = true;
			this.es_error = false;
			this.params = null;
		}
		else if (tipo == EnumTipos.PROC)
		{
			this.es_num = false;
			this.es_error = false;
			this.params = null;
		}
		else // t == EnumTipos.ERROR
		{
			this.es_error = true;
			this.params = null;
		}
	}
	
	public Tipo(EnumTipos tipo, List<Boolean> params)
	{
		// SOLO LLAMADA PARA TIPO PROC
		if (tipo == EnumTipos.PROC)
		{
			this.es_num = false;
			this.es_error = false;
			this.params = params;
		}
	}
	
	public String toString()
	{
		if (es_error) return "TIPO ERROR";
		else if (es_num) return "TIPO NUM";
		else return "TIPO PROC (params = " + this.params.toString() + ")";
	}
}