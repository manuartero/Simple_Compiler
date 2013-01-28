package gramatica;

import java.util.LinkedList;
import java.util.List;

import lexico.Token;
import maquinaPila.InstruccionesMaquina;
import tablaSimbolos.Clase;
import tablaSimbolos.EnumClases;
import tablaSimbolos.EnumTipos;
import tablaSimbolos.TS;
import tablaSimbolos.Tipo;

public class GramaticaAtributos 
{
	//private static final int INSTRUCCIONES_DEBUG = 0;
	
	/* Programa
	 * 
	 * 	TS => ¬
	 *  Error
	 *  Cod => cod
	 */
	abstract public class Programa
	{
		private Atributo<Error> error;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Programa()
		{
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		public Atributo<Error> error(){return error;}
		public Atributo<List<InstruccionesMaquina>> cod(){ return cod; }
		
		protected abstract Error error_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	
	/* Declaraciones
	 * 
	 *  TS 	=>	tsh		dirh	nivelh 	| 	ts 	dir
	 *  Error
	 *  Cod => etqh  |  etq  anidamiento  cod 
	 */
	abstract public class Declaraciones
	{
		private DeclaracionesCtx ctx;
		public void registraCtx(DeclaracionesCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Integer> dirh;
		private Atributo<Integer> nivelh;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> anidamiento;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected Declaraciones()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			dirh = new Atributo<Integer>();
			dirh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.dirh_exp(); } });
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.nivelh_exp(); } });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			
			ts = new Atributo<TS>();
			ts.fijaExpresion(new ExpSem<TS>() { public TS val() { return ts_exp(); } });
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return dir_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return anidamiento_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Integer> dirh(){return dirh;}
		public Atributo<Integer> nivelh(){return nivelh;}
		public Atributo<TS> ts(){return ts;}
		public Atributo<Integer> dir(){return dir;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<Integer> anidamiento(){return anidamiento;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract TS ts_exp();
		protected abstract Integer dir_exp();
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract Integer anidamiento_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface DeclaracionesCtx
	{
		public TS tsh_exp();
		public Integer dirh_exp();
		public Integer nivelh_exp();
		public Integer etqh_exp();
	}
	
	/* ListaDeclaraciones
	 *  
	 *  TS => tsh 	dirh	nivelh		|	ts 	  	dir
	 *  Error
	 *  Cod => etqh  |  etq  anidamineto  cod
	 */
	abstract public class ListaDeclaraciones
	{
		private ListaDeclaracionesCtx ctx;
		public void registraCtx(ListaDeclaracionesCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Integer> dirh;
		private Atributo<Integer> nivelh;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> anidamiento;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected ListaDeclaraciones()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			dirh = new Atributo<Integer>();
			dirh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.dirh_exp(); } });
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.nivelh_exp(); } });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			
			ts = new Atributo<TS>();
			ts.fijaExpresion(new ExpSem<TS>() { public TS val() { return ts_exp(); } });
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return dir_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return anidamiento_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Integer> dirh(){return dirh;}
		public Atributo<Integer> nivelh(){return nivelh;}
		public Atributo<TS> ts(){return ts;}
		public Atributo<Integer> dir(){return dir;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<Integer> anidamiento(){return anidamiento;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract TS ts_exp();
		protected abstract Integer dir_exp();
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract Integer anidamiento_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface ListaDeclaracionesCtx
	{
		public TS tsh_exp();
		public Integer dirh_exp();
		public Integer nivelh_exp();
		public Integer etqh_exp();
	}
	
	/* Declaracion
	 * 
	 * TS => 	tsh		dirh	nivelh 		| 	iden   clase   tipo   tam   dir 
	 * Error
	 * Cod => etqh  |  etq  anidamiento  cod
	 */
	abstract public class Declaracion
	{
		private DeclaracionCtx ctx;
		public void registraCtx(DeclaracionCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Integer> dirh;
		private Atributo<Integer> nivelh;
		private Atributo<String> iden;
		private Atributo<Clase> clase; 	
		private Atributo<Tipo> tipo; 		
		private Atributo<Integer> tam;
		private Atributo<Integer> dir;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> anidamiento;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected Declaracion()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			dirh = new Atributo<Integer>();
			dirh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.dirh_exp(); } });
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.nivelh_exp(); } });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>() { public String val() { return iden_exp(); } });
			clase = new Atributo<Clase>();
			clase.fijaExpresion(new ExpSem<Clase>() { public Clase val() { return clase_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() { return tipo_exp(); } });
			tam = new Atributo<Integer>();
			tam.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return tam_exp(); } });
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return dir_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return anidamiento_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Integer> dirh(){return dirh;}
		public Atributo<Integer> nivelh(){return nivelh;}
		public Atributo<String> iden(){return iden;}
		public Atributo<Clase> clase(){return clase;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Integer> tam(){return tam;}
		public Atributo<Integer> dir(){return dir;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<Integer> anidamiento(){return anidamiento;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract String iden_exp();
		protected abstract Clase clase_exp();
		protected abstract Tipo tipo_exp();
		protected abstract Integer tam_exp();
		protected abstract Integer dir_exp();
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract Integer anidamiento_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface DeclaracionCtx
	{
		public TS tsh_exp();
		public Integer dirh_exp();
		public Integer nivelh_exp();
		public Integer etqh_exp();
	}
		
	/* ParametrosFormales
	 * 
	 *  TS => tsh  nivelh  |  ts  dir  params
	 *  Error
	 */
	abstract public class ParametrosFormales
	{
		private ParametrosFormalesCtx ctx;
		public void registraCtx(ParametrosFormalesCtx ctx){ this.ctx = ctx; }
		
		private Atributo<TS> tsh;
		private Atributo<Integer> nivelh;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private Atributo<List<Boolean>> params;
		private Atributo<Error> error;
	
		protected ParametrosFormales()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>(){ public Integer val(){ return ctx.nivelh_exp(); } });
			ts = new Atributo<TS>();
			ts.fijaExpresion(new ExpSem<TS>() { public TS val() { return ts_exp(); } });
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return dir_exp(); } });
			params = new Atributo<List<Boolean>>();
			params.fijaExpresion(new ExpSem<List<Boolean>>(){ public List<Boolean> val(){ return params_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
		}
		
		public Atributo<TS> tsh(){ return tsh; };
		public Atributo<Integer> nivelh(){ return nivelh; };
		public Atributo<TS> ts(){ return ts; };
		public Atributo<Integer> dir(){ return dir; };
		public Atributo<List<Boolean>> params(){ return params; };
		public Atributo<Error> error(){ return error; };
		
		protected abstract TS ts_exp();
		protected abstract Integer dir_exp();
		protected abstract List<Boolean> params_exp();
		protected abstract Error error_exp();
	}
	public interface ParametrosFormalesCtx
	{
		public TS tsh_exp();
		public Integer nivelh_exp();
	}
	
	/* ListaParametrosFormales
	 * 
	 * TS => tsh  nivelh  |  ts  dir  params
	 * Error
	 */
	 abstract public class ListaParametrosFormales
	 {
		 private ListaParametrosFormalesCtx ctx;
		 public void registraCtx(ListaParametrosFormalesCtx ctx){ this.ctx = ctx; }
		 
		 private Atributo<TS> tsh;
		 private Atributo<Integer> nivelh;
		 private Atributo<TS> ts;
		 private Atributo<Integer> dir;
		 private Atributo<List<Boolean>> params;
		 private Atributo<Error> error;
		 
		 protected ListaParametrosFormales()
		 {
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>(){ public Integer val(){ return ctx.nivelh_exp(); } });
			ts = new Atributo<TS>();
			ts.fijaExpresion(new ExpSem<TS>() { public TS val() { return ts_exp(); } });
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return dir_exp(); } });
			params = new Atributo<List<Boolean>>();
			params.fijaExpresion(new ExpSem<List<Boolean>>(){ public List<Boolean> val(){ return params_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
		 }
		 
		public Atributo<TS> tsh(){ return tsh; };
		public Atributo<Integer> nivelh(){ return nivelh; };
		public Atributo<TS> ts(){ return ts; };
		public Atributo<Integer> dir(){ return dir; };
		public Atributo<List<Boolean>> params(){ return params; };
		public Atributo<Error> error(){ return error; };
		
		protected abstract TS ts_exp();
		protected abstract Integer dir_exp();
		protected abstract List<Boolean> params_exp();
		protected abstract Error error_exp();
		 
	 }
	 public interface ListaParametrosFormalesCtx
	 {
		public TS tsh_exp();
		public Integer nivelh_exp();
	 }
	 
	 /* ParametroFormal 
	  * 
	  * TS => clase  iden  modo 
	  */
	 abstract public class ParametroFormal
	 { 
		 private Atributo<Clase> clase;
		 private Atributo<String> iden;
		 private Atributo<Boolean> modo;
		 
		 protected ParametroFormal()
		 {
			clase = new Atributo<Clase>();
			clase.fijaExpresion(new ExpSem<Clase>() { public Clase val() {return clase_exp();} });
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>()
			{
				public String val() {return iden_exp();} 
			});
			modo = new Atributo<Boolean>();
			modo.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return modo_exp();} });
		 }
		 
		 public Atributo<Clase> clase(){ return clase; }
		 public Atributo<String> iden(){ return iden; }
		 public Atributo<Boolean> modo(){ return modo; }
		 
		 protected abstract Clase clase_exp();
		 protected abstract String iden_exp();
		 protected abstract Boolean modo_exp();	 
	 }
	 
	/* Cuerpo
	 * 
	 *  TS => tsh
	 *  Error
	 *  Cod => etqh  |  etq  cod 
	 */
	abstract public class Cuerpo
	{
		private CuerpoCtx ctx;
		public void registraCtx(CuerpoCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected Cuerpo()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface CuerpoCtx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Instrucciones
	 * 
	 * TS =>  tsh
	 * Error
	 * Cod => etqh  |  etq  cod
	 */
	abstract public class Instrucciones
	{
		private InstruccionesCtx ctx;
		public void registraCtx(InstruccionesCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected Instrucciones()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface InstruccionesCtx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Instruccion
	 * 
	 * TS  => 	tsh
	 * Error
	 * Cod => etqh  |  etq  cod
	 */
	abstract public class Instruccion
	{
		private InstruccionCtx ctx;
		public void registraCtx(InstruccionCtx ctx) {this.ctx = ctx;}
				
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected Instruccion()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface InstruccionCtx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* ParametrosReales
	 * 
	 * TS => tsh
	 * Error => error  nparams  ¿subProgramah?
	 * Cod => etqh etq  cod
	 */
	abstract public class ParametrosReales
	{
		private ParametrosRealesCtx ctx;
		public void registraCtx(ParametrosRealesCtx ctx){ this.ctx = ctx; }
		
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> nparams;
		private Atributo<String> subProgramah;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected ParametrosReales()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			nparams = new Atributo<Integer>();
			nparams.fijaExpresion(new ExpSem<Integer>() { public Integer val(){ return nParams_exp(); } } );
			subProgramah = new Atributo<String>();
			subProgramah.fijaExpresion(new ExpSem<String>() { public String val() {return ctx.subProgramah_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		
		public Atributo<TS> tsh(){ return tsh; }
		public Atributo<Error> error(){ return error; }
		public Atributo<Integer> nparams(){ return nparams; }
		public Atributo<String> subProgramah(){ return subProgramah; }
		public Atributo<Integer> etq(){ return etq; }
		public Atributo<Integer> etqh(){ return etqh; }
		public Atributo<List<InstruccionesMaquina>> cod(){ return cod; }
		
		protected abstract Error error_exp();
		protected abstract Integer nParams_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface ParametrosRealesCtx
	{
		public TS tsh_exp();
		public String subProgramah_exp(); 
		public Integer etqh_exp();
	}
	
	/* ListaParametrosReales
	 * 
	 * TS => tsh
	 * Error => error  nparams   subProgramah 
	 * Cod => etqh  etq  cod
	 */
	abstract public class ListaParametrosReales
	{
		private ListaParametrosRealesCtx ctx;
		public void registraCtx(ListaParametrosRealesCtx ctx){ this.ctx = ctx; }
		
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> nparams;
		private Atributo<String> subProgramah;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected ListaParametrosReales()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			nparams = new Atributo<Integer>();
			nparams.fijaExpresion(new ExpSem<Integer>() { public Integer val(){ return nParams_exp(); } } );
			subProgramah = new Atributo<String>();
			subProgramah.fijaExpresion(new ExpSem<String>() { public String val() {return ctx.subProgramah_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		
		public Atributo<TS> tsh(){ return tsh; }
		public Atributo<Error> error(){ return error; }
		public Atributo<Integer> nparams(){ return nparams; }
		public Atributo<String> subProgramah(){ return subProgramah; }
		public Atributo<Integer> etqh(){ return etqh; }
		public Atributo<Integer> etq(){ return etq; }
		public Atributo<List<InstruccionesMaquina>> cod(){ return cod; }
		
		protected abstract Error error_exp();
		protected abstract Integer nParams_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface ListaParametrosRealesCtx
	{
		public TS tsh_exp();
		public String subProgramah_exp(); 
		public Integer etqh_exp();
	}
	
	/* ParteElse
	 * 
	 * TS => 	tsh
	 * Error
	 * Cod => etqh  |  etq  ir_f  cod
	 */
	abstract public class ParteElse
	{
		private ParteElseCtx ctx;
		public void registraCtx(ParteElseCtx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Error> error;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> ir_f;
		private Atributo<List<InstruccionesMaquina>> cod;
		
		protected ParteElse()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			error = new Atributo<Error>();
			error.fijaExpresion(new ExpSem<Error>() { public Error val() {return error_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			ir_f = new Atributo<Integer>();
			ir_f.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ir_f_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Error> error(){return error;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}
		public Atributo<Integer> ir_f(){return ir_f;}		
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract Error error_exp();
		protected abstract Integer etq_exp();
		protected abstract Integer ir_f_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface ParteElseCtx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Exp0
	 * 
	 * TS  => tsh
	 * Error => tipo esDesignador
	 * Cod => etqh  |  etq  cod
	 */
	abstract public class Exp0
	{
		private Exp0Ctx ctx;
		public void registraCtx(Exp0Ctx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Tipo> tipo;
		private Atributo<Boolean> esDesignador;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Exp0()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() {return tipo_exp();} });
			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return esDesignador_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });
		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Boolean> esDesignador(){return esDesignador;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}	
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		
		protected abstract Tipo tipo_exp();
		protected abstract boolean esDesignador_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface Exp0Ctx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Exp1 
	 * 
	 * TS  => tsh
	 * Error => tipo esDesignador
	 * Cod => etqh  |  etq  cod
	 */
	abstract public class Exp1
	{
		private Exp1Ctx ctx;
		public void registraCtx(Exp1Ctx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Tipo> tipo;
		private Atributo<Boolean> esDesignador;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Exp1()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() {return tipo_exp();} });
			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return esDesignador_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Boolean> esDesignador(){return esDesignador;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}	
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}

		protected abstract Tipo tipo_exp();
		protected abstract boolean esDesignador_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface Exp1Ctx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Exp2
	 * TS  => tsh
	 * Error => tipo esDesignador
	 * etqh  |  etq  cod
	 */
	abstract public class Exp2
	{
		private Exp2Ctx ctx;
		public void registraCtx(Exp2Ctx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Tipo> tipo;
		private Atributo<Boolean> esDesignador;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Exp2()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() {return tipo_exp();} });
			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return esDesignador_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Boolean> esDesignador(){return esDesignador;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}	
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}

		protected abstract Tipo tipo_exp();
		protected abstract boolean esDesignador_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface Exp2Ctx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Exp3
	 * 
	 * TS  => tsh
	 * Error => tipo esDesignador
	 * etqh  |  etq  cod
	 */
	abstract public class Exp3
	{
		private Exp3Ctx ctx;
		public void registraCtx(Exp3Ctx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Tipo> tipo;
		private Atributo<Boolean> esDesignador;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Exp3()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() {return tipo_exp();} });
			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return esDesignador_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Boolean> esDesignador(){return esDesignador;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}	
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}

		protected abstract Tipo tipo_exp();
		protected abstract boolean esDesignador_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface Exp3Ctx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Exp4
	 * 
	 * TS  => tsh
	 * Error => tipo esDesignador
	 * etqh  |  etq  cod
	 */
	abstract public class Exp4
	{
		private Exp4Ctx ctx;
		public void registraCtx(Exp4Ctx ctx) {this.ctx = ctx;}
		
		private Atributo<TS> tsh;
		private Atributo<Tipo> tipo;
		private Atributo<Boolean> esDesignador;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected Exp4()
		{
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() { public TS val() { return ctx.tsh_exp(); } });
			tipo = new Atributo<Tipo>();
			tipo.fijaExpresion(new ExpSem<Tipo>() { public Tipo val() {return tipo_exp();} });
			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() { public Boolean val() {return esDesignador_exp();} });
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return ctx.etqh_exp(); } });
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() { public Integer val() { return etq_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<TS> tsh(){return tsh;}
		public Atributo<Tipo> tipo(){return tipo;}
		public Atributo<Boolean> esDesignador(){return esDesignador;}
		public Atributo<Integer> etqh(){return etqh;}
		public Atributo<Integer> etq(){return etq;}	
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}

		protected abstract Tipo tipo_exp();
		protected abstract boolean esDesignador_exp();
		protected abstract Integer etq_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	public interface Exp4Ctx
	{
		public TS tsh_exp();
		public Integer etqh_exp();
	}
	
	/* Op
	 * 
	 * Error => op
	 * Cod => cod
	 */
	abstract public class OpComparacion
	{
		private Atributo<String> op;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected OpComparacion()
		{
			op = new Atributo<String>();
			op.fijaExpresion(new ExpSem<String>() { public String val() { return op_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<String> op(){return op;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		protected abstract String op_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	abstract public class OpAditivo
	{	
		private Atributo<String> op;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected OpAditivo()
		{
			op = new Atributo<String>();
			op.fijaExpresion(new ExpSem<String>() { public String val() { return op_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<String> op(){return op;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		protected abstract String op_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	abstract public class OpMultiplicativo
	{	
		private Atributo<String> op;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected OpMultiplicativo()
		{
			op = new Atributo<String>();
			op.fijaExpresion(new ExpSem<String>() { public String val() { return op_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<String> op(){return op;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		protected abstract String op_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	abstract public class OpUnario
	{
		private Atributo<String> op;
		private Atributo<List<InstruccionesMaquina>> cod;
		protected OpUnario()
		{
			op = new Atributo<String>();
			op.fijaExpresion(new ExpSem<String>() { public String val() { return op_exp(); } });
			cod = new Atributo<List<InstruccionesMaquina>>();
			cod.fijaExpresion(new ExpSem<List<InstruccionesMaquina>>() { public List<InstruccionesMaquina> val() {return cod_exp();} });

		}
		public Atributo<String> op(){return op;}
		public Atributo<List<InstruccionesMaquina>> cod(){return cod;}
		protected abstract String op_exp();
		protected abstract List<InstruccionesMaquina> cod_exp();
	}
	
	// -----------------------------------------------------
	//XXX				IMPLEMENTACION
	// -----------------------------------------------------
	
	/** Programa ::= Declaraciones Cuerpo
	 *  
	 *  Construcción de la tabla de símbolos
	 *  	Declaraciones.tsh = creaTS()
	 *  	Declaraciones.dirh = 0
	 *  	Declaraciones.nivelh = 0
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Cuerpo.tsh = Declaraciones.ts
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Programa.error = Declaraciones.error or Cuerpo.error  
	 *  
	 *  Generación de código
	 *  	Declaraciones.etqh = numeroInstruccionesActivacionProgramaPrincipal()
	 *  	Cuerpo.etqh=Declaraciones.etq 
	 *  	Programa.cod = codigoActivacionProgramaPrincipal(Declaraciones.dir, Declaraciones.etq, Declaraciones.anidamiento) || Declaraciones.cod || Cuerpo.cod
	 */
	public class ProgramaR1 extends Programa
	{
		private Declaraciones d;
		private Cuerpo c;
		
		public ProgramaR1(Declaraciones d, Cuerpo c)
		{
			super();
			this.d = d; 
			this.c = c;
			d.registraCtx(new DeclaracionesCtx() 
			{
				public TS tsh_exp() { return creaTS(); }
				public Integer nivelh_exp() { return 0; }
				public Integer dirh_exp() { return 0; }
				public Integer etqh_exp() { return numeroIntruccionesActivacionProgramaPrincipal(); }
			});
			c.registraCtx(new CuerpoCtx()
			{
				public TS tsh_exp() { return ProgramaR1.this.d.ts().val(); }
				public Integer etqh_exp() { return ProgramaR1.this.d.etq().val(); }
			});
			
			//dependencias
			c.tsh().ponDependencias(d.ts());
			this.error().ponDependencias(c.error(), d.error());
			this.c.etqh().ponDependencias(d.etq());
			this.cod().ponDependencias(d.dir(), d.etq(), d.anidamiento(), d.cod(), c.cod());

		}

		protected Error error_exp(){ return new Error(d.error().val(), c.error().val()); }
		protected List<InstruccionesMaquina> cod_exp()
		{ 
			return concatenarInstrucciones(
				codigoActivacionProgramaPrincipal(d.dir.val(), d.etq().val(), d.anidamiento().val()),
				d.cod.val(),
				c.cod.val() );
		}

	}
	public class ProgramaR1Debug extends ProgramaR1 
	{
		private final static String REGLA = "Programa ::= Declaraciones Cuerpo";
		public ProgramaR1Debug(Declaraciones d, Cuerpo c)
		{
			super(d, c);
			d.tsh().fijaDescripcion(REGLA + "\n  		=>  Declaraciones.tsh");
			d.dirh().fijaDescripcion(REGLA + "\n  		=>  Declaraciones.dirh");
			d.nivelh().fijaDescripcion(REGLA + "\n  	=>  Declaraciones.nivelh");
			c.tsh().fijaDescripcion(REGLA + "\n  		=>  Cuerpo.tsh");
			this.error().fijaDescripcion(REGLA + "\n  	=>  Programa.error");
			d.etqh().fijaDescripcion(REGLA + "\n		=>  Declaraciones.etqh");
			c.etqh().fijaDescripcion(REGLA + "\n		=>  Cuerpo.etqh"); 
			this.cod().fijaDescripcion(REGLA + "\n  	=>  Programa.cod");
		}
	}
	
	
	/** Declaraciones ::= decs ListaDeclaraciones end decs 
	 * 
	 *  Construcción de la tabla de símbolos
	 * 		ListaDeclaraciones.nivelh = Declaraciones.nivelh
	 * 		ListaDeclaraciones.dirh = Declaraciones.dirh
	 * 		Declaraciones.ts = ListaDeclaraciones.ts
	 * 		Declaraciones.dir = ListaDeclaraciones.dir         
	 * 
	 *  Propagación de la tabla de símbolos
	 * 		ListaDeclaraciones.tsh = Declaraciones.tsh
	 * 
	 *  Comprobación de las restricciones contextuales
	 * 		Declaraciones.error = ListaDeclaraciones.error 
	 * 
	 *  Generación de código
	 * 		ListaDeclaraciones.etqh = Declaraciones.etqh
	 * 		Declaraciones.etq = ListaDeclaraciones.etq 
	 * 		Declaraciones.anidamiento = ListaDeclaraciones.anidamiento
	 * 		Declaraciones.cod = ListaDeclaraciones.cod
	 */
	public class DeclaracionesR1 extends Declaraciones
	{
		private ListaDeclaraciones ld;
		
		public DeclaracionesR1(ListaDeclaraciones ld)
		{
			super();
			this.ld = ld; 
			ld.registraCtx(new ListaDeclaracionesCtx() {
				public TS tsh_exp(){ return DeclaracionesR1.this.tsh().val(); }
				public Integer nivelh_exp(){ return DeclaracionesR1.this.nivelh().val(); }
				public Integer dirh_exp(){ return DeclaracionesR1.this.dirh().val(); }
				public Integer etqh_exp(){ return DeclaracionesR1.this.etqh().val(); }
			});
			
			//dependencias
			ld.nivelh().ponDependencias(this.nivelh());
			ld.dirh().ponDependencias(this.dirh());
			ld.tsh().ponDependencias(this.tsh());
			this.ts().ponDependencias(ld.ts());
			this.dir().ponDependencias(ld.dir());
			this.error().ponDependencias(ld.error());
			ld.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(ld.etq()); 
			this.anidamiento().ponDependencias(ld.anidamiento());
			this.cod().ponDependencias(ld.cod());
		}
		protected TS ts_exp(){return ld.ts().val(); }
		protected Integer dir_exp(){ return ld.dir().val(); }
		protected Error error_exp(){ return ld.error().val(); }
		protected Integer etq_exp(){ return ld.etq().val(); } 
		protected Integer anidamiento_exp(){ return ld.anidamiento().val(); } 
		protected List<InstruccionesMaquina> cod_exp(){ return ld.cod.val(); }
	}
	public class DeclaracionesR1Debug extends DeclaracionesR1 
	{
		private final static String REGLA = "Declaraciones ::= decs ListaDeclaraciones end decs";
		public DeclaracionesR1Debug(ListaDeclaraciones ld)
		{
			super(ld);
			ld.nivelh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.nivelh");
			ld.dirh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.dirh");
			this.ts().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.ts");
			this.dir().fijaDescripcion(REGLA    + "\n  =>  Declaraciones.dir");
			ld.tsh().fijaDescripcion(REGLA 		+ "\n  =>  ListaDeclaraciones.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.error");
			ld.etqh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.etqh");
			this.etq().fijaDescripcion(REGLA	+ "\n  =>  Declaraciones.etq");
			this.anidamiento().fijaDescripcion(REGLA + "\n  =>  Declaraciones.anidamiento");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.cod"); 
		}
	}
	
	/** Declaraciones ::=  ¬
	 *  
	 *  Construcción/Propagación de la tabla de símbolos+
	 *  	Declaraciones.ts = Declaraciones.tsh
	 *  	Declaraciones.dir = Declaraciones.dirh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Declaraciones.error = false
	 *  
	 *  Generación de código
	 *  	Declaraciones.etq = Declaraciones.etqh 
	 * 	 	Declaraciones.anidamiento = 0
	 * 	 	Declaraciones.cod = programaVacio()
	 */			      
	public class DeclaracionesR2 extends Declaraciones
	{
		public DeclaracionesR2()
		{
			super();
			
			//dependencias
			this.ts().ponDependencias(this.tsh());
			this.dir().ponDependencias(this.dirh());
			this.etq().ponDependencias(this.etqh());
		}
		protected TS ts_exp(){ return tsh().val(); }
		protected Integer dir_exp(){ return dirh().val(); }
		protected Error error_exp(){ return new Error(); }
		protected Integer etq_exp(){ return this.etqh().val(); }
		protected Integer anidamiento_exp(){ return 0; }
		protected List<InstruccionesMaquina> cod_exp(){ return programaVacio(); }
	}
	public class DeclaracionesR2Debug extends DeclaracionesR2 
	{
		private final static String REGLA = "Declaraciones ::=  ¬";
		public DeclaracionesR2Debug()
		{
			super();
			this.ts().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.ts");
			this.dir().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.dir");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.error");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.etq");
			this.anidamiento().fijaDescripcion(REGLA + "\n  =>  Declaraciones.anidamiento");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  Declaraciones.cod");
		}
	}
	
	
	/** ListaDeclaraciones ::= ListaDeclaraciones , Declaracion
	 *  
	 *  Construcción de la tabla de símbolos
	 *  	ListaDeclaraciones(1).tsh = ListaDeclaraciones(0).tsh
	 *  	ListaDeclaraciones(1).nivelh = ListaDeclaraciones(0).nivelh
	 *  	ListaDeclaraciones(1).dirh = ListaDeclaraciones(0).dirh
	 *  	Declaracion.nivelh = ListaDeclaraciones(0).nivelh
	 *  	Declaracion.dirh = ListaDeclaraciones(1).dir
	 *  	ListaDeclaraciones(0).ts = aniadeSimb(ListaDeclaraciones(1).ts, Declaracion.iden, Declaracion.clase, Declaracion.tipo, Declaracion.dir, ListaDeclaraciones(0).nivelh) 
	 *  	ListaDeclaraciones(0).dir = ListaDeclaraciones(1).dir + Declaracion.tam  
	 *  
	 *  Propagación de la tabla de símbolos
	 * 		Declaracion.tsh = ListaDeclaraciones(1).ts
	 * 
	 *  Comprobación de las restricciones contextuales
	 * 		ListaDeclaraciones(0).error = ListaDeclaraciones(1).error or Declaracion.error or existeSimbEnUltimoNivel(ListaDeclaraciones(1).ts, Declaracion.iden)  
	 * 
	 *  Generación de código
	 *  	ListaDeclaraciones(1).etqh = ListaDeclaraciones(0).etqh	
	 *  	Declaracion.etqh = ListaDeclaraciones(1).etq
	 *  	ListaDeclaraciones(0).etq = Declaracion.etq   
	 *  	ListaDeclaraciones(0).cod = ListaDeclaraciones(1).cod || Declaracion.cod
	 *  	ListaDeclaraciones(0).anidamiento = max(ListaDeclaraciones(1).anidamiento, Declaracion.anidamiento)
	 */
	public class ListaDeclaracionesR1 extends ListaDeclaraciones
	{
		private ListaDeclaraciones ld;
		private Declaracion d;
		public ListaDeclaracionesR1(ListaDeclaraciones ld, Declaracion d)
		{
			super();
			this.ld = ld; 
			this.d = d;
			ld.registraCtx(new ListaDeclaracionesCtx()
			{
				public TS tsh_exp(){ return ListaDeclaracionesR1.this.tsh().val(); }
				public Integer nivelh_exp(){ return ListaDeclaracionesR1.this.nivelh().val(); }
				public Integer dirh_exp(){ return ListaDeclaracionesR1.this.dirh().val(); }
				public Integer etqh_exp(){ return ListaDeclaracionesR1.this.etqh().val(); }
			});
			d.registraCtx(new DeclaracionCtx()
			{
				public TS tsh_exp(){ return ListaDeclaracionesR1.this.ld.ts().val(); }
				public Integer nivelh_exp(){ return ListaDeclaracionesR1.this.nivelh().val(); }
				public Integer dirh_exp(){ return ListaDeclaracionesR1.this.ld.dir().val(); }
				public Integer etqh_exp(){ return ListaDeclaracionesR1.this.ld.etq().val(); }
			});

			//dependencias
			ld.tsh().ponDependencias(this.tsh());
			ld.nivelh().ponDependencias(this.nivelh());
			ld.dirh().ponDependencias(this.dirh());
			d.nivelh().ponDependencias(this.nivelh());
			d.dirh().ponDependencias(ld.dir());
			this.ts().ponDependencias(ld.ts(), d.iden(), d.clase(), d.tipo(), d.dir(), this.nivelh());
			this.dir().ponDependencias(ld.dir(), d.tam());
			d.tsh().ponDependencias(ld.ts());
			this.error().ponDependencias(ld.error(), d.error(), ld.ts(), d.iden());
			ld.etqh().ponDependencias(this.etqh());	
			d.etqh().ponDependencias(ld.etq());
			this.etq().ponDependencias(d.etq());   
			this.cod().ponDependencias(ld.cod(), d.cod());
			this.anidamiento().ponDependencias(ld.anidamiento(), d.anidamiento());
		}
		protected TS ts_exp(){ return addSimbol(ld.ts().val(), d.iden().val(), d.clase().val(), d.tipo().val(), d.dir().val(), this.nivelh().val()); }
		protected Integer dir_exp(){ return ld.dir().val() + d.tam().val(); }
		protected Error error_exp(){ return new Error( new Error(ld.error().val(), d.error().val()) ,existeSimbEnUltimoNivel(ld.ts.val(), d.iden().val()) ); }
		protected Integer etq_exp(){ return d.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones(ld.cod().val(), d.cod().val()); }
		protected Integer anidamiento_exp(){ return Math.max(ld.anidamiento().val(), d.anidamiento().val()); }
		
	}
	public class ListaDeclaracionesR1Debug extends ListaDeclaracionesR1 
	{
		private final static String REGLA = "ListaDeclaraciones ::= ListaDeclaraciones , Declaracion";
		public ListaDeclaracionesR1Debug(ListaDeclaraciones ld, Declaracion d)
		{
			super(ld, d);
			ld.tsh().fijaDescripcion(REGLA 		+ "\n  =>  ListaDeclaraciones(1).tsh");
			ld.nivelh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(1).nivelh");
			ld.dirh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(1).dirh");
			d.nivelh().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.nivelh");
			d.dirh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.dirh");
			this.ts().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).ts");
			this.dir().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).dir");
			d.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).error"); 
			ld.etqh().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(1).etqh");	
			d.etqh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).etq");   
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).cod");
			this.anidamiento().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones(0).anidamiento");
		}
	}
	
	
	/** ListaDeclaraciones ::= Declaracion
	 *  
	 *  Construcción de la tabla de símbolos
	 *  	ListaDeclaraciones.ts = aniadeSimb(ListaDeclaraciones.tsh, Declaracion.iden, Declaracion.clase, Declaracion.tipo, Declaracion.dir, ListaDeclaraciones.nivelh)
	 *  	Declaracion.nivelh = ListaDeclaraciones.nivelh
	 *  	Declaracion.dirh = ListaDeclaraciones.dirh
	 *  	ListaDeclaraciones.dir = ListaDeclaraciones.dirh + Declaracion.tam
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Declaracion.tsh = ListaDeclaraciones.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ListaDeclaraciones.error = Declaracion.error or existeSimbEnUltimoNivel(ListaDeclaraciones.tsh,Declaracion.iden)
	 *  
	 *  Generación de código
	 *  	Declaracion.etqh = ListaDeclaraciones.etqh
	 *  	ListaDeclaraciones.etq = Declaracion.etq   
	 *  	ListaDeclaraciones.cod = Declaracion.cod
	 *  	ListaDeclaraciones.anidamiento = Declaracion.anidamiento
	 */		
	public class ListaDeclaracionesR2 extends ListaDeclaraciones
	{
		private Declaracion d;
		public ListaDeclaracionesR2(Declaracion d)
		{
			super();
			this.d = d; 
			d.registraCtx(new DeclaracionCtx()
			{
				public TS tsh_exp(){ return ListaDeclaracionesR2.this.tsh().val(); }
				public Integer nivelh_exp(){ return ListaDeclaracionesR2.this.nivelh().val(); }
				public Integer dirh_exp(){ return ListaDeclaracionesR2.this.dirh().val(); }
				public Integer etqh_exp(){ return ListaDeclaracionesR2.this.etqh().val(); }
			});
			
			//dependencias
			this.ts().ponDependencias(this.tsh(), d.iden(), d.clase(), d.tipo(), d.dir(), this.nivelh());
			d.nivelh().ponDependencias(this.nivelh());
			d.dirh().ponDependencias(this.dirh());
			this.dir().ponDependencias(this.dirh(), d.tam());
			d.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(d.error(), this.tsh(), d.iden());
			d.etqh.ponDependencias(this.etqh());
			this.etq().ponDependencias(d.etq());   
			this.cod().ponDependencias(d.cod());
			this.anidamiento().ponDependencias(d.anidamiento());
		}
		protected TS ts_exp(){ return addSimbol(this.tsh().val(), d.iden().val(), d.clase().val(), d.tipo().val(), d.dir().val(), this.nivelh().val()); }
		protected Integer dir_exp(){ return dirh().val() + d.tam().val(); }
		protected Error error_exp(){ return new Error(d.error.val(), existeSimbEnUltimoNivel(this.tsh().val(), d.iden().val())); }	
		protected Integer etq_exp(){ return d.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return d.cod().val(); }
		protected Integer anidamiento_exp(){ return d.anidamiento().val(); }
	}
	public class ListaDeclaracionesR2Debug extends ListaDeclaracionesR2 
	{
		private final static String REGLA = "ListaDeclaraciones ::= Declaracion";
		public ListaDeclaracionesR2Debug(Declaracion d)
		{
			super(d);
			this.ts().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.ts");
			d.nivelh().fijaDescripcion(REGLA 	+ "\n  =>  Declaraciones.nivelh");
			d.dirh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.dirh");
			this.dir().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.dir");
			d.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.error");
			d.etqh.fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.etq");   
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.cod");
			this.anidamiento().fijaDescripcion(REGLA 	+ "\n  =>  ListaDeclaraciones.anidamiento");
		}
	}
	
	
	/** Declaracion ::= IDEN
	 *  
	 *  Construcción de la tabla de símbolos
	 *  	Declaracion.clase = var
	 *  	Declaracion.tipo = number
	 *  	Declaracion.tam = 1
	 *  	Declaracion.iden = IDEN.lex
	 *  	Declaracion.dir = Declaracion.dirh  
	 *  
	 *  Comprobación de las restricciones contextuales 
	 *  	Declaracion.error = false
	 *  
	 *  Generación de código
	 *  	Declaracion.etq = Declaracion.etqh  
	 *  	Declaracion.cod = programaVacio()
	 *  	Declaracion.anidamiento=0
	 */
	public class DeclaracionR1 extends Declaracion
	{
		private Token iden;
		public DeclaracionR1(Token iden)
		{
			super();
			this.iden = iden;
			
			//dependencias
			this.dir().ponDependencias(this.dirh());
			this.etq().ponDependencias(this.etqh());
		}
		//metodos
		protected String iden_exp(){ return iden.getLexema(); }
		protected Clase clase_exp(){ return new Clase(EnumClases.VAR); }
		protected Tipo tipo_exp(){ return new Tipo(EnumTipos.NUM); }
		protected Integer tam_exp(){ return 1; }
		protected Integer dir_exp(){ return dirh().val(); }
		protected Error error_exp(){ return new Error(); }
		protected Integer etq_exp(){ return etqh().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return programaVacio(); }
		protected Integer anidamiento_exp(){ return 0; }
	}
	public class DeclaracionR1Debug extends DeclaracionR1 
	{
		private final static String REGLA = "Declaracion ::= IDEN";
		public DeclaracionR1Debug(Token iden)
		{
			super(iden);
			this.clase().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.clase");
			this.tipo().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.tipo");
			this.tam().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.tam");
			this.iden().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.iden");
			this.dir().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.dir");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.error");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.cod");
			this.anidamiento().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.anidamiento");
		}
	}
	
	
	/** Declaracion ::=  proc IDEN ( ParametrosFormales ) Declaraciones Cuerpo
	 *    
	 *   Construcción de la tabla de símbolos
	 *    	Declaracion.clase = proc
	 *      Declaracion.tipo = <t:proc, params: ParametrosFormales.params>
	 *      Declaracion.tam = 0
	 *      Declaracion.iden = IDEN.lex  
	 *      ParametrosFormales.nivelh = Declaracion.nivelh + 1
	 *      Declaraciones.nivelh = Declaracion.nivelh + 1
	 *      ParametrosFormales.tsh = creaNivel(Declaracion.tsh)
	 *      Declaraciones.tsh = ParametrosFormales.ts
	 *      Declaraciones.dirh = ParametrosFormales.dir    
	 *      Declaracion.dir = Declaraciones.etq
	 *      
	 *   Propagación de la tabla de símbolos
	 *      Cuerpo.tsh = añadeSimb(Declaraciones.ts, IDEN.lex, proc, <t:proc, params: ParametrosFormales.params>, Declaraciones.etq, Declaracion.nivelh+1)     
	 *      
	 *   Comprobación de las restricciones contextuales
	 *   	Declaracion.error = ParametrosFormales.error or Declaraciones.error or Cuerpo.error
	 *   
	 *   Generación de código
	 *   	Declaraciones.etqh = Declaracion.etqh
	 *   	Cuerpo.etqh = Declaraciones.etq + numeroInstruccionesPrologo()
	 *   	Declaracion.cod = Declaraciones.cod || codigoPrologo(Declaraciones.dir,Declaracion.nivelh+1) || Cuerpo.cod || codigoEpilogo(Declaraciones.dir,Declaracion.nivelh+1)
	 *   	Declaracion.etq = Cuerpo.etq + numeroInstruccionesEpilogo()     
	 *   	Declaracion.anidamiento = Declaraciones.anidamiento + 1
	 */
	public class DeclaracionR2 extends Declaracion
	{
		private Token iden;
		private ParametrosFormales pf;
		private Declaraciones ds;
		private Cuerpo c;
		public DeclaracionR2(Token iden, ParametrosFormales pf, Declaraciones ds, Cuerpo c)
		{
			super();
			this.iden = iden;
			this.pf = pf;
			this.ds = ds;
			this.c = c;
			
			pf.registraCtx(new ParametrosFormalesCtx()
			{
				public TS tsh_exp(){ return creaNivel(DeclaracionR2.this.tsh().val()); }
				public Integer nivelh_exp(){ return DeclaracionR2.this.nivelh().val() + 1; }
			});
			ds.registraCtx(new DeclaracionesCtx()
			{
				public TS tsh_exp(){ return DeclaracionR2.this.pf.ts().val(); }
				public Integer dirh_exp(){ return DeclaracionR2.this.pf.dir().val(); }
				public Integer nivelh_exp(){ return DeclaracionR2.this.nivelh().val() + 1; }
				public Integer etqh_exp(){ return DeclaracionR2.this.etqh().val(); }
			});
			c.registraCtx(new CuerpoCtx()
			{
				public TS tsh_exp(){ return addSimbol(DeclaracionR2.this.ds.ts().val(), DeclaracionR2.this.iden.getLexema(), new Clase(EnumClases.PROC), new Tipo(EnumTipos.PROC, DeclaracionR2.this.pf.params().val()), DeclaracionR2.this.ds.etq().val(), DeclaracionR2.this.nivelh().val() + 1); }
				public Integer etqh_exp(){ return DeclaracionR2.this.ds.etq().val() + numeroInstruccionesPrologo(); }
			});
			
			//dependencias
			this.tipo().ponDependencias(pf.params());
			pf.nivelh().ponDependencias(this.nivelh());
			ds.nivelh().ponDependencias(this.nivelh());
			pf.tsh().ponDependencias(this.tsh());
			ds.tsh().ponDependencias(pf.ts());
			ds.dirh().ponDependencias(pf.dir());
			this.dir().ponDependencias(ds.etq());
			c.tsh().ponDependencias(ds.ts(), pf.params(), ds.etq(), this.nivelh());     
			this.error().ponDependencias(pf.error(), ds.error(), c.error());
			ds.etqh().ponDependencias(this.etqh());
			c.etqh().ponDependencias(ds.etq());
			this.cod().ponDependencias(ds.cod(), ds.dir(), this.nivelh(), c.cod(), ds.dir(), this.nivelh());
			this.etq().ponDependencias(c.etq());     
			this.anidamiento().ponDependencias(ds.anidamiento());
		}
		
		//metodos
		protected Clase clase_exp(){ return new Clase(EnumClases.PROC); }
		protected Tipo tipo_exp(){ return new Tipo(EnumTipos.PROC, pf.params().val()); }
		protected Integer tam_exp(){ return 0; }
		protected String iden_exp(){ return iden.getLexema(); }
		protected Integer dir_exp(){ return ds.etq().val(); }
		protected Error error_exp(){ return new Error(pf.error().val(), new Error(ds.error().val(), c.error().val() )); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones(ds.cod().val(), codigoPrologo(ds.dir().val(), this.nivelh().val() + 1), c.cod().val(), codigoEpilogo(ds.dir().val(), this.nivelh().val() + 1)); }
		protected Integer etq_exp(){ return c.etq().val() + numeroInstruccionesEpilogo(); }
		protected Integer anidamiento_exp(){ return ds.anidamiento().val() + 1; }
	}
	public class DeclaracionR2Debug extends DeclaracionR2 
	{
		private final static String REGLA = "Declaracion ::=  proc IDEN ( ParametrosFormales ) Declaraciones Cuerpo";
		public DeclaracionR2Debug(Token iden, ParametrosFormales pf, Declaraciones ds, Cuerpo c)
		{
			super(iden, pf, ds, c);
			this.clase().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.clase");
			this.tipo().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.tipo");
			this.tam().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.tam");
			this.iden().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.iden");
			pf.nivelh().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.nivelh");
			ds.nivelh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.nivelh");
			pf.tsh().fijaDescripcion(REGLA 			+ "\n  =>  ParametrosFormales.tsh");
			ds.tsh().fijaDescripcion(REGLA 			+ "\n  =>  Declaraciones.tsh");
			ds.dirh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.dirh");
			this.dir().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.dir");
			c.tsh().fijaDescripcion(REGLA 			+ "\n  =>  Cuepo.tsh");
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.error");
			ds.etqh().fijaDescripcion(REGLA 		+ "\n  =>  Declaraciones.etqh");
			c.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Cuerpo.etqh");
			this.cod().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.cod");
			this.etq().fijaDescripcion(REGLA 		+ "\n  =>  Declaracion.etq");    
			this.anidamiento().fijaDescripcion(REGLA 	+ "\n  =>  Declaracion.anidamiento");
		}
	}
	
	
	/** ParametrosFormales ::= ListaParametrosFormales 
	 *  
	 *  Construcción de la tabla de símbolos
	 *  	ListaParametrosFormales.tsh = ParametrosFormales.tsh
	 *  	ListaParametrosFormales.nivelh = ParametrosFormales.nivelh
	 *  	ParametrosFormales.params = ListaParametrosFormales.params
	 *  	ParametrosFormales.ts = ListaParametrosFormales.ts
	 *  	ParametrosFormales.dir = ListaParametrosFormales.dir
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ParametrosFormales.error = ListaParametrosFormales.error 
	 */
	public class ParametrosFormalesR1 extends ParametrosFormales
	{
		private ListaParametrosFormales lpf;
		public ParametrosFormalesR1(ListaParametrosFormales lpf)
		{
			super();
			this.lpf = lpf;
			
			lpf.registraCtx(new ListaParametrosFormalesCtx()
			{
				public TS tsh_exp(){ return ParametrosFormalesR1.this.tsh().val(); }
				public Integer nivelh_exp(){ return ParametrosFormalesR1.this.nivelh().val(); }
			});
			
			//dependencias
			lpf.tsh().ponDependencias(this.tsh());
			lpf.nivelh().ponDependencias(this.nivelh());
			this.params().ponDependencias(lpf.params());
			this.ts().ponDependencias(lpf.ts());
			this.dir().ponDependencias(lpf.dir());
			this.error().ponDependencias(lpf.error()); 
		}
		//metodos
		protected List<Boolean> params_exp(){ return lpf.params().val(); }
		protected TS ts_exp(){ return lpf.ts().val(); }
		protected Integer dir_exp(){ return lpf.dir().val(); }
		protected Error error_exp(){ return lpf.error().val(); }
	}
	public class ParametrosFormalesR1Debug extends ParametrosFormalesR1 
	{
		private final static String REGLA = "ParametrosFormales ::= ListaParametrosFormales";
		public ParametrosFormalesR1Debug(ListaParametrosFormales lpf)
		{
			super(lpf);
			lpf.tsh().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales.tsh");
			lpf.nivelh().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales.nivelh");
			this.params().fijaDescripcion(REGLA 	+ "\n  =>  ParametrosFormales.params");
			this.ts().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.ts");
			this.dir().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.dir");
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.error");
		}
	}
	
	
	/** ParametrosFormales ::= ¬
	 *  
	 *   Construcción de la tabla de símbolos
	 *  	ParametrosFormales.ts = ParametrosFormales.tsh
	 * 	 	ParametrosFormales.params = listaVacia()
	 *  	ParametrosFormales.dir = 0
	 *  
	 *   Comprobación de las restricciones contextuales
	 *  	ParametrosFormales.error = false 
	 */
	public class ParametrosFormalesR2 extends ParametrosFormales
	{
		public ParametrosFormalesR2()
		{
			super();
			
			//dependencias
			this.ts().ponDependencias(this.tsh());
		}
		//metodos
		protected List<Boolean> params_exp(){ return listaVacia(); }
		protected TS ts_exp(){ return tsh().val(); }
		protected Integer dir_exp(){ return 0; }
		protected Error error_exp(){ return new Error(); }
	}
	public class ParametrosFormalesR2Debug extends ParametrosFormalesR2 
	{
		private final static String REGLA = "ParametrosFormales ::= ListaParametrosFormales";
		public ParametrosFormalesR2Debug()
		{
			super();
			this.ts().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.ts");
			this.params().fijaDescripcion(REGLA 	+ "\n  =>  ParametrosFormales.params");
			this.dir().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.dir");
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosFormales.error");
		}
	}

	
	/** ListaParametrosFormales ::= ListaParametrosFormales , ParametroFormal
	 *  
	 *   Construcción de la tabla de símbolos
	 *  	ListaParametrosFormales(1).tsh = ListaParametrosFormales(0).tsh
	 *  	ListaParametrosFormales(1).nivelh = ListaParametrosFormales(0).nivelh
	 *  	ListaParametrosFormales(0).params = añadeA(ListaParametrosFormales(1).params, ParametroFormal.modo)
	 *  	ListaParametrosFormales(0).ts = añadeSimb(ListaParametrosFormales(1).ts, ParametroFormal.iden, ParametroFormal.clase, number, ListaParametrosFormales(1).dir, ListaParametrosFormales(0).nivelh) 
	 *  	ListaParametrosFormales(0).dir = ListaParametrosFormales(1).dir + 1                               
	 *  
	 *   Comprobación de las restricciones contextuales
	 *  	ListaParametrosFormales(0).error = ListaParametrosFormales(1).error or existeSimbEnUltimoNivel(ListaParametrosFormales(1).ts, ParametroFormal.iden)
	 */
	public class ListaParametrosFormalesR1 extends ListaParametrosFormales
	{
		private ListaParametrosFormales lpf;
		private ParametroFormal pf;
		public ListaParametrosFormalesR1(ListaParametrosFormales lpf, ParametroFormal pf)
		{
			super();
			this.lpf = lpf;
			this.pf = pf;
			
			lpf.registraCtx(new ListaParametrosFormalesCtx()
			{ 
				public TS tsh_exp(){ return ListaParametrosFormalesR1.this.tsh().val(); }
				public Integer nivelh_exp(){ return ListaParametrosFormalesR1.this.nivelh().val(); }
			});
			
			//dependencias
			lpf.tsh().ponDependencias(this.tsh());
			lpf.nivelh().ponDependencias(this.nivelh());
			this.params().ponDependencias(lpf.params(), pf.modo());
			this.ts().ponDependencias(lpf.ts(), pf.iden(), pf.clase(), lpf.dir(), this.nivelh()); 
			this.dir().ponDependencias(lpf.dir());                               
			this.error().ponDependencias(lpf.error(), lpf.ts(), pf.iden());
		}
		//metodos
		protected List<Boolean> params_exp(){ return addParamToList(lpf.params().val(), pf.modo().val()); }
		protected TS ts_exp()
		{ 
			String i = pf.iden().val();
			return addSimbol(lpf.ts().val(), i, pf.clase().val(), new Tipo(EnumTipos.NUM), lpf.dir().val(), this.nivelh().val()); 
		}
		protected Integer dir_exp(){ return lpf.dir().val() + 1 ; }
		protected Error error_exp()
		{ return new Error(lpf.error().val(), existeSimbEnUltimoNivel(lpf.ts().val(), pf.iden().val()) ); }
	}
	public class ListaParametrosFormalesR1Debug extends ListaParametrosFormalesR1 
	{
		private final static String REGLA = "ListaParametrosFormales ::= ListaParametrosFormales , ParametroFormal";
		public ListaParametrosFormalesR1Debug(ListaParametrosFormales lpf, ParametroFormal pf)
		{
			super(lpf, pf);
			lpf.tsh().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(1).tsh");
			lpf.nivelh().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(1).nivelh");
			this.params().fijaDescripcion(REGLA 	+ "\n  =>  ListaParametrosFormales(0).params");
			this.ts().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).ts");
			this.dir().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).dir");                           
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).error");
		}
	}
	
	
	/** ListaParametrosFormales ::= ParametroFormal
	 * 
	 *   Construcción de la tabla de símbolos
	 *   	ListaParametrosFormales.params = nuevaLista(ParametroFormal.modo)
	 *   	ListaParametrosFormales.ts = añadeSimb(ListaParametrosFormales.tsh, ParametroFormal.iden, ParametroFormal.clase, number, 0, ListaParametrosFormales.nivelh) 
	 *   	ListaParametrosFormales.dir = 1
	 *   	ListaParametrosFormales.error = existeSimbEnUltimoNivel(ListaParametrosFormales.tsh, ParametroFormal.iden)
	 */
	public class ListaParametrosFormalesR2 extends ListaParametrosFormales
	{
		private ParametroFormal pf;
		public ListaParametrosFormalesR2(ParametroFormal pf)
		{
			super();
			this.pf = pf;
			
			//dependencias
			this.params().ponDependencias(pf.modo());
			this.ts().ponDependencias(this.tsh(), pf.iden(), pf.clase(), this.nivelh()); 
			this.error().ponDependencias(this.tsh(), pf.iden());
		}
		//metodos
		protected List<Boolean> params_exp(){ return nuevaLista(pf.modo().val()); }
		protected TS ts_exp()
		{ 
			String iden = pf.iden().val();
			return addSimbol(this.tsh().val(), iden, pf.clase().val(), new Tipo(EnumTipos.NUM), 0, this.nivelh().val() );
		}
		protected Integer dir_exp(){ return 1; }
		protected Error error_exp(){ return existeSimbEnUltimoNivel(this.tsh().val(), pf.iden().val() ); }
	}
	public class ListaParametrosFormalesR2Debug extends ListaParametrosFormalesR2 
	{
		private final static String REGLA = " ListaParametrosFormales ::= ParametroFormal";
		public ListaParametrosFormalesR2Debug(ParametroFormal pf)
		{
			super(pf);
			this.params().fijaDescripcion(REGLA 	+ "\n  =>  ListaParametrosFormales(0).params");
			this.ts().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).ts");
			this.dir().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).dir");                           
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosFormales(0).error");
		}
	}
	
	
	/** ParametroFormal ::= IDEN
	 * 
	 *   Construcción de la tabla de símbolos
	 *   	ParametroFormal.clase = var
	 *   	ParametroFormal.iden = IDEN.lex  
	 *   	ParametroFormal.modo = valor
	 */
	public class ParametroFormalR1 extends ParametroFormal
	{
		private Token t;
		public ParametroFormalR1(Token t)
		{
			super();
			this.t = t;
		}
		//metodos
		protected Clase clase_exp(){ return new Clase(EnumClases.VAR); }
		protected String iden_exp()
		{
			String s = t.getLexema();
			return s;
		}
		protected Boolean modo_exp(){ return true; }
	}
	public class ParametroFormalR1Debug extends ParametroFormalR1
	{
		private final static String REGLA = "ParametroFormal ::= IDEN";
		public ParametroFormalR1Debug(Token iden)
		{
			super(iden);
			this.clase().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.clase");
			this.iden().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.iden");
			this.modo().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.modo");                           
		}
	}
	
	
	/** ParametroFormal ::= var IDEN
	 *   
	 *   Construcción de la tabla de símbolos
	 *   	ParametroFormal.clase = pvar
	 *   	ParametroFormal.iden = IDEN.lex  
	 *   	ParametroFormal.modo = var
	 */
	public class ParametroFormalR2 extends ParametroFormal
	{
		private Token iden;
		public ParametroFormalR2(Token iden)
		{
			super();
			this.iden = iden;
		}
		//metodos
		protected Clase clase_exp(){ return new Clase(EnumClases.PVAR); }
		protected String iden_exp(){ return iden.getLexema(); }
		protected Boolean modo_exp(){ return false; }
	}
	public class ParametroFormalR2Debug extends ParametroFormalR2
	{
		private final static String REGLA = "ParametroFormal ::= IDEN";
		public ParametroFormalR2Debug(Token iden)
		{
			super(iden);
			this.clase().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.clase");
			this.iden().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.iden");
			this.modo().fijaDescripcion(REGLA 		+ "\n  =>  ParametroFormal.modo");                           
		}
	}
	
	
	/** Cuerpo ::= body Instrucciones end body
	 * 
	 *  Propagación de la tabla de símbolos
	 *  	Instrucciones.tsh = Cuerpo.tsh
	 * 
	 *  Comprobación de las restricciones contextuales
	 *  	Cuerpo.error = Instrucciones.error     
	 *  
	 *  Generación de código
	 *  	Instrucciones.etqh = Cuerpo.etqh
	 *  	Cuerpo.etq = Instrucciones.etq
	 *  	Cuerpo.cod = Instrucciones.cod 
	 */
	public class CuerpoR1 extends Cuerpo
	{
		private Instrucciones is;
		public CuerpoR1(Instrucciones is)
		{
			super();
			this.is = is;
			is.registraCtx(new InstruccionesCtx()
			{
				public TS tsh_exp(){ return CuerpoR1.this.tsh().val(); }
				public Integer etqh_exp(){ return CuerpoR1.this.etqh().val(); }
			});
			
			//dependencias
			is.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(is.error());
			is.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(is.etq());
			this.cod().ponDependencias(is.cod()); 
		}
		protected Error error_exp(){ return is.error().val(); }
		protected Integer etq_exp(){ return is.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return is.cod().val(); }
	}
	public class CuerpoR1Debug extends CuerpoR1 
	{
		private final static String REGLA = "Cuerpo ::= body Instrucciones end body";
		public CuerpoR1Debug(Instrucciones is)
		{
			super(is);
			is.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instrucciones.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Cuerpo.error");
			is.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Cuerpo.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Cuerpo.cod");
		}
	}
	
	
	/** Instrucciones ::= Instrucciones, Instruccion
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Instrucciones(1).tsh = Instrucciones(0).tsh
	 *  	Instruccion.tsh = Instrucciones(0).tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Instrucciones(0).error = Instrucciones(1).error or Instruccion.error
	 *  
	 *  Generación de código
	 *  	Instrucciones(1).etqh = Instrucciones(0).etqh
	 *  	Instruccion.etqh = Instrucciones(1).etq
	 *  	Instrucciones(0).etq = Instruccion.etq
	 *  	Instrucciones(0).cod = Instrucciones(1).cod || Instruccion.cod
	 */
	public class InstruccionesR1 extends Instrucciones
	{
		private Instrucciones is;
		private Instruccion i;
		public InstruccionesR1(Instrucciones is, Instruccion i)
		{
			super();
			this.is = is;
			this.i = i;
			is.registraCtx(new InstruccionesCtx() 
			{
				public TS tsh_exp(){ return InstruccionesR1.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionesR1.this.etqh().val(); }
			});
			i.registraCtx(new InstruccionCtx() 
			{
				public TS tsh_exp() { return InstruccionesR1.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionesR1.this.is.etq().val(); }
			});
			
			//dependencias
			is.tsh().ponDependencias(this.tsh());
			i.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(is.error(), i.error());
			is.etqh().ponDependencias(this.etqh());
			i.etqh().ponDependencias(is.etq());
			this.etq().ponDependencias(i.etq());
			this.cod().ponDependencias(is.cod(), i.cod());
		}
		
		protected Error error_exp(){ return new Error(is.error.val(), i.error.val()); }
		protected Integer etq_exp(){ return i.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones(is.cod().val(), i.cod().val()); }
	}
	public class InstruccionesR1Debug extends InstruccionesR1 
	{
		private final static String REGLA = "Instrucciones ::= Instrucciones, Instruccion";
		public InstruccionesR1Debug(Instrucciones is, Instruccion i)
		{
			super(is, i);
			is.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instrucciones(1).tsh");
			i.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instruccion.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones(0).error");
			is.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones(1).etqh");
			i.etqh().fijaDescripcion(REGLA 		+ "\n  =>  Instruccion.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones(0).etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones(0).cod");
		}
	}
	
	
	/** Instrucciones ::= Instruccion
	 * 
	 *  Propagación de la tabla de símbolos
	 *  	Instruccion.tsh = Instrucciones.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Instrucciones.error = Instruccion.error
	 *  
	 *  Generación de código
	 *  	Instruccion.etqh = Instrucciones.etqh
	 *  	Instrucciones.etq = Instruccion.etq
	 *  	Instrucciones.cod = Instruccion.cod
	 */
	public class InstruccionesR2 extends Instrucciones
	{
		private Instruccion i;
		public InstruccionesR2(Instruccion i)
		{
			super();
			this.i = i;
			i.registraCtx(new InstruccionCtx() 
			{
				public TS tsh_exp(){ return InstruccionesR2.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionesR2.this.etqh().val(); }
			});
			
			//dependencias
			i.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(i.error());
			i.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(i.etq());
			this.cod().ponDependencias(i.cod());
		}
		
		protected Error error_exp(){ return i.error().val(); }
		protected Integer etq_exp(){ return i.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return i.cod().val(); }
	}
	public class InstruccionesR2Debug extends InstruccionesR2 
	{
		private final static String REGLA = "Instrucciones ::= Instruccion";
		public InstruccionesR2Debug(Instruccion i)
		{
			super(i);
			i.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instruccion.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.error");
			i.etqh().fijaDescripcion(REGLA 		+ "\n  =>  Instruccion.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.cod");
		}
	}
	
	
	/** Instruccion ::= set IDEN to Exp0
	 *  
	 *   Propagación de la tabla de símbolos
	 *  	Exp0.tsh = Instruccion.tsh 
	 *   
	 *   Comprobación de las restricciones contextuales
	 *  	Instruccion.error = not asignacionCorrecta(Instruccion.tsh,IDEN.lex,Exp0.tipo)                     
	 *  
	 *   Generación de código
	 *  	Exp0.etqh = Instruccion.etqh 
	 *  	Instruccion.etq = Exp0.etq + numeroInstruccionesAsignacion(Instruccion.tsh, IDEN.lex, Exp0.esDesignador)
	 *  	Instruccion.cod = codigoAsignacion(Instruccion.tsh, IDEN.lex, Exp0.cod,Exp0.esDesignador),
	 */
	public class InstruccionR1 extends Instruccion
	{
		private Token iden;
		private Exp0 e0;
		public InstruccionR1(Token iden, Exp0 e0)
		{
			super();
			this.iden = iden;
			this.e0 = e0;
			e0.registraCtx(new Exp0Ctx()
			{
				public TS tsh_exp(){ return InstruccionR1.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionR1.this.etqh().val(); }
			});
			
			//dependencias
			e0.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(this.tsh(), e0.tipo()); 
			e0.etqh().ponDependencias(this.etqh()); 
			this.etq().ponDependencias(e0.etq(), this.tsh(), e0.esDesignador());
			this.cod().ponDependencias(this.tsh(), e0.cod(), e0.esDesignador());
		}
		
		protected Error error_exp()
		{ 
			boolean asignacionCorrecta = asignacionCorrecta( this.tsh().val(), iden.getLexema(), e0.tipo().val());
			return errorSi(!asignacionCorrecta, "Asignacion Incorrecta" );
		}
		protected Integer etq_exp(){ return e0.etq().val() + numeroInstruccionesAsignacion(this.tsh().val(), iden.getLexema(), e0.esDesignador().val()); }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoAsignacion(this.tsh().val(), iden.getLexema(), e0.cod.val(), e0.esDesignador.val()); }	
	}
	public class InstruccionR1Debug extends InstruccionR1 
	{
		private final static String REGLA = "Instruccion ::= set IDEN to Exp0";
		public InstruccionR1Debug(Token iden, Exp0 e0)
		{
			super(iden, e0);
			e0.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Exp0.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.error");
			e0.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Exp0.etqh"); 
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.cod");
		}
	}
	
	
	/** Instruccion ::= call IDEN ParametrosReales end call
	 *   
	 *   Propagación de la tabla de símbolos
	 *   	ParametrosReales.tsh = Instruccion.tsh
	 *   
	 *   Comprobación de las restricciones contextuales
	 *   	Instruccion.error = ParametrosReales.error || not llamadaCorrecta(Instruccion.tsh, IDEN.lex,ParametrosReales.nparams)
	 *   	ParametrosReales.subprogramah = IDEN.lex
	 *   
	 *   Generación de código
	 *   	ParametrosReales.etqh = Instruccion.etqh
	 *   	Instruccion.etq = ParametrosReales.etq + numeroInstruccionesFinLLamada()
	 *   	Instruccion.cod = ParametrosReales.cod || codigoFinLlamada(IDEN.lex,Instruccion.tsh, ParametrosReales.etq + numeroInstruccionesFinLLamada())
	 */
	public class InstruccionR2 extends Instruccion
	{
		private Token iden;
		private ParametrosReales pr;
		public InstruccionR2(Token iden, ParametrosReales pr)
		{
			super();
			this.iden = iden;
			this.pr = pr;
			pr.registraCtx(new ParametrosRealesCtx()
			{
				public TS tsh_exp(){ return InstruccionR2.this.tsh().val(); }
				public String subProgramah_exp(){ return InstruccionR2.this.iden.getLexema(); }
				public Integer etqh_exp(){ return InstruccionR2.this.etqh().val(); }
			});
			
			//dependencias
			 pr.tsh().ponDependencias(this.tsh());
			 this.error().ponDependencias(pr.error(), this.tsh(), pr.nparams());
			 pr.etqh().ponDependencias(this.etqh());
			 this.etq().ponDependencias(pr.etq());
			 this.cod().ponDependencias(pr.cod(), this.tsh(), pr.etq() );
		}
		//metodos
		protected Error error_exp(){ return new Error( pr.error.val() , errorSi(!llamadaCorrecta(tsh().val(), iden.getLexema(), pr.nparams.val()), "Llamada Incorrecta") ); }
		protected Integer etq_exp(){ return pr.etq().val() + numeroInstruccionesFinLlamada(); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones( pr.cod().val() , codigoFinLlamada(iden.getLexema(),tsh().val(), pr.etq().val() + numeroInstruccionesFinLlamada()) ); }
	}
	public class InstruccionR2Debug extends InstruccionR2 
	{
		private final static String REGLA = "Instruccion ::= call IDEN ParametrosReales end call";
		public InstruccionR2Debug(Token iden, ParametrosReales pr)
		{
			super(iden, pr);
			pr.tsh().fijaDescripcion(REGLA 				+ "\n  =>  ParametrosReales.tsh");
			this.error().fijaDescripcion(REGLA 			+ "\n  =>  Instruccion.error");
			pr.subProgramah().fijaDescripcion(REGLA 	+ "\n  =>  ParametrosReales.subProgramah");
			pr.etqh().fijaDescripcion(REGLA 			+ "\n  =>  ParametrosReales.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Instruccion.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Instruccion.cod");
		}
	}
	
	
	/** Instruccion ::= if Exp0 then {Instrucciones} ParteElse
	 * 
	 *  Propagación de la tabla de símbolos
	 *  	Exp0.tsh = Instruccion.tsh
	 *  	Instrucciones.tsh = Instruccion.tsh
	 *  	ParteElse.tsh = Instruccion.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Instruccion.error = not CondicionCorrecta(Exp0.tipo) or Instrucciones.error or ParteElse.error 
	 *  
	 *  Generación de código
	 *  	Exp0.etqh = Instruccion.etqh
	 *  	Instrucciones.etqh = Exp0.etq + 1
	 *  	ParteElse.etqh = Instrucciones.etq
	 *  	Instruccion.etq = ParteElse.etq
	 *  	Instruccion.cod = Exp0.cod || ir_f(ParteElse.ir_f) || Instrucciones.cod || ParteElse.cod  
	 */
	public class InstruccionR3 extends Instruccion
	{
		private Exp0 e0;
		private Instrucciones is;
		private ParteElse pe;
		public InstruccionR3(Exp0 e0, Instrucciones is, ParteElse pe)
		{
			super();
			this.e0 = e0;
			this.is = is;
			this.pe = pe;
			e0.registraCtx(new Exp0Ctx()
			{
				public TS tsh_exp(){ return InstruccionR3.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionR3.this.etqh().val(); }
			});
			is.registraCtx(new InstruccionesCtx()
			{
				public TS tsh_exp(){ return InstruccionR3.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionR3.this.e0.etq().val() + 1; }
			});
			pe.registraCtx(new ParteElseCtx()
			{
				public TS tsh_exp(){ return InstruccionR3.this.tsh().val(); }
				public Integer etqh_exp(){ return InstruccionR3.this.is.etq().val(); }
			});
			
			//dependencias
			e0.tsh().ponDependencias(this.tsh());
			is.tsh().ponDependencias(this.tsh());
			pe.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(e0.tipo(), is.error(), pe.error()); 
			e0.etqh().ponDependencias(this.etqh());
			is.etqh().ponDependencias(e0.etq());
			pe.etqh().ponDependencias(is.etq());
			this.etq().ponDependencias(pe.etq());
			this.cod().ponDependencias(e0.cod(), pe.ir_f(), is.cod(), pe.cod()); 
		}
		
		protected Error error_exp(){ return new Error(errorSi(!condicionCorrecta(e0.tipo().val()), "Condicion Incorrecta") , new Error(is.error().val(), pe.error().val()) ) ; }
		protected Integer etq_exp(){ return pe.etq().val(); } 
		protected List<InstruccionesMaquina> cod_exp()
		{
			return concatenarInstrucciones(e0.cod().val(), ir_falso(pe.ir_f().val()), is.cod().val(), pe.cod().val());
		}
	}
	public class InstruccionR3Debug extends InstruccionR3 
	{
		private final static String REGLA = "Instruccion ::= if Exp0 then {Instrucciones} ParteElse";
		public InstruccionR3Debug(Exp0 e0, Instrucciones is, ParteElse pe)
		{
			super(e0, is, pe);
			e0.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Exp0.tsh");
			is.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instrucciones.tsh");
			pe.tsh().fijaDescripcion(REGLA 		+ "\n  =>  ParteElse.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.error");
			e0.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Exp0.etqh");
			is.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.etqh");
			pe.etqh().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.cod");
		}
	}
	
	
	/** Instruccion ::= out IDEN
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Instruccion.error = not escrituraCorrecta(Instruccion.tsh,IDEN.lex)                     
	 *  
	 *  Generación de código
	 *  	Instruccion.etq = Instruccion.etqh + numeroInstruccionesEscritura(IDEN.lex,Instruccion.tsh)
	 *  	Instruccion.cod = codigoEscritura(Instruccion.tsh,IDEN.lex) 
	 */
	public class InstruccionR4 extends Instruccion
	{
		private Token iden;
		public InstruccionR4(Token iden)
		{
			super();
			this.iden = iden;
			
			//dependencias
			this.error().ponDependencias(this.tsh()); 
			this.etq().ponDependencias(this.etqh(), this.tsh());
			this.cod().ponDependencias(this.tsh()); 
		}
		
		protected Error error_exp(){return errorSi( !escrituraCorrecta(this.tsh().val(), iden.getLexema()) , "Escritura Incorrecta" ); }
		protected Integer etq_exp(){ return this.etqh().val() + numeroInstruccionesEscritura(this.tsh().val(), iden.getLexema()); }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoEscritura(this.tsh().val(), iden.getLexema()); }
	}
	public class InstruccionR4Debug extends InstruccionR4 
	{
		private final static String REGLA = "Instruccion ::= out IDEN";
		public InstruccionR4Debug(Token iden)
		{
			super(iden);
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.error");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.etq");
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  Instruccion.cod");
		}
	}
	
	
	/** ParametrosReales ::= with ListaParametrosReales end with
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	ListaParametrosReales.tsh = ParametrosReales.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ParametrosReales.error = ListaParametrosReales.error 
	 *  	ListaParametrosReales.subprogramah = ParametrosReales.subprogramah
	 *  	ParametrosReales.nparams = ListaParametrosReales.nparams
	 *  
	 *  Generación de código
	 *  	ListaParametrosReales.etqh = ParametrosReales.etqh + numeroInstruccionesInicioLlamada()
	 *  	ParametrosReales.etq = ListaParametrosReales.etq + 1
	 *  	ParametrosReales.cod = codigoInicioLlamada() || ListaParametrosReales.cod || desapila()
	 */
	public class ParametrosRealesR1 extends ParametrosReales
	{
		private ListaParametrosReales lpr;
		public ParametrosRealesR1(ListaParametrosReales lpr)
		{
			super();
			this.lpr = lpr;
			lpr.registraCtx(new ListaParametrosRealesCtx()
			{
				public TS tsh_exp(){return ParametrosRealesR1.this.tsh().val(); }
				public String subProgramah_exp(){return ParametrosRealesR1.this.subProgramah().val(); }
				public Integer etqh_exp(){return ParametrosRealesR1.this.etqh().val() + numeroInstruccionesInicioLlamada(); }
			});
			
			//dependencias
			lpr.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(lpr.error()); 
			lpr.subProgramah().ponDependencias(this.subProgramah());
			this.nparams().ponDependencias(lpr.nparams());
			lpr.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(lpr.etq());
			this.cod().ponDependencias(lpr.cod()); 
		}
		//metodos
		protected Error error_exp(){ return lpr.error().val(); }
		protected Integer nParams_exp(){ return lpr.nparams().val(); }
		protected Integer etq_exp(){ return lpr.etq().val() + 1; }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones( codigoInicioLlamada(), lpr.cod().val(), listaUnitaria(desapila()) ); }
	}
	public class ParametrosRealesR1Debug extends ParametrosRealesR1
	{
		private final static String REGLA = "ParametrosReales ::= with ListaParametrosReales end with";
		public ParametrosRealesR1Debug(ListaParametrosReales lpr)
		{
			super(lpr);
			lpr.tsh().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales.tsh");
			this.error().fijaDescripcion(REGLA 			+ "\n  =>  ParametrosReales.error");
			lpr.subProgramah().fijaDescripcion(REGLA 	+ "\n  =>  ListaParametrosReales.subProgramah");
			this.nparams().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosReales.nparams");
			lpr.etqh().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  ParametrosReales.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  ParametrosReales.cod");
		}
	}
	
	
	/** ParametrosReales ::= ¬
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ParametrosReales.error = false
	 *  	ParametrosReales.nparams=0
	 *  
	 *  Generación de código
	 *  	ParametrosReales.etq = ParametrosReales.etqh
	 *  	ParametrosReales.cod = programaVacio()
	 */ 
	public class ParametrosRealesR2 extends ParametrosReales
	{
		public ParametrosRealesR2()
		{
			super();
			//dependencias
			this.etq().ponDependencias(this.etqh());
		}
		protected Error error_exp(){return new Error();}
		protected Integer nParams_exp(){ return 0; }
		protected Integer etq_exp(){ return etqh().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return programaVacio(); }
	}
	public class ParametrosRealesR2Debug extends ParametrosRealesR2
	{
		private final static String REGLA = "ParametrosReales ::= ¬";
		public ParametrosRealesR2Debug()
		{
			super();
			this.error().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosReales.error");
			this.nparams().fijaDescripcion(REGLA 	+ "\n  =>  ParametrosReales.nParams");
			this.etq().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosReales.etq");
			this.cod().fijaDescripcion(REGLA 		+ "\n  =>  ParametrosReales.cod");
		}
	}
	
	
	/** ListaParametrosReales ::= ListaParametrosReales , Exp0
	  * 
	  * Propagación de la tabla de símbolos
	  * 	ListaParametrosReales(1).tsh = ListaParametrosReales(0).tsh
	  * 	Exp0.tsh = ListaParametrosReales(0).tsh
	  * 
	  * Comprobación de las restricciones contextuales
	  * 	ListaParametrosReales(0).error = ListaParametrosReales(1).error or not parametroCorrecto(ListaParametrosReales(0).subprogramah, ListaParametrosReales(1).nparams + 1, Exp0.tipo, Exp0.esDesignador, ListaParametrosReales(0).tsh)                                                          
	  * 	ListaParametrosReales(1).subprogramah = ListaParametrosReales(0).subprogramah
	  * 	ListaParametrosReales(0).nparams = ListaParametrosReales(1).nparams+1
	  * 
	  * Generación de código
	  * 	ListaParametrosReales(1).etqh = ListaParametrosReales(0).etqh
	  * 	Exp0.etqh = ListaParametrosReales(1).etq + 1
	  * 	ListaParametrosReales(0).etq = Exp0.etq + numeroInstruccionesCodigoPaso(ListaParametrosReales(0).subprogramah, ListaParametrosReales(1).nparams + 1, Exp0.esDesignador, ListaParametrosReales(0).tsh) 
	  * 	ListaParametrosReales(0).cod = ListaParametrosReales(1).cod || copia() || Exp0.cod || codigoPaso(ListaParametrosReales(0).subprogramah, ListaParametrosReales(1).nparams + 1, Exp0.esDesignador, ListaParametrosReales(0).tsh) 
	  */
	public class ListaParametrosRealesR1 extends ListaParametrosReales
	{
		private ListaParametrosReales lpr;
		private Exp0 e0;
		public ListaParametrosRealesR1(ListaParametrosReales lpr, Exp0 e0)
		{
			super();
			this.lpr = lpr;
			this.e0 = e0;
			lpr.registraCtx(new ListaParametrosRealesCtx()
			{
				public TS tsh_exp(){ return ListaParametrosRealesR1.this.tsh().val(); }
				public String subProgramah_exp(){ return ListaParametrosRealesR1.this.subProgramah().val(); }
				public Integer etqh_exp(){ return ListaParametrosRealesR1.this.etqh().val();  }
			});
			e0.registraCtx(new Exp0Ctx()
			{
				public TS tsh_exp(){ return ListaParametrosRealesR1.this.tsh().val(); }
				public Integer etqh_exp(){ return ListaParametrosRealesR1.this.lpr.etq().val() + 1;  }
			});
			
			//dependencias
			lpr.tsh().ponDependencias(this.tsh());
			e0.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(lpr.error(), this.subProgramah(), lpr.nparams(), e0.tipo(), e0.esDesignador(), this.tsh());                                                          
			lpr.subProgramah().ponDependencias(this.subProgramah());
			this.nparams().ponDependencias(lpr.nparams());
			lpr.etqh().ponDependencias(this.etqh());
			e0.etqh().ponDependencias(lpr.etq());
			this.etq().ponDependencias(e0.etq(), this.subProgramah(), lpr.nparams(), e0.esDesignador(), this.tsh()); 
			this.cod().ponDependencias(lpr.cod(), e0.cod(), this.subProgramah(), lpr.nparams(), e0.esDesignador(), this.tsh()); 
		}
		
		//metodos
		protected Error error_exp()
		{
			Tipo t = e0.tipo().val();
			boolean parametroCorrecto = parametroCorrecto(subProgramah().val(), lpr.nparams().val() + 1, t, e0.esDesignador().val(), tsh().val()); 
			return new Error( lpr.error().val(), errorSi(!parametroCorrecto, "Parametro Incorrecto"));
		}
		protected Integer nParams_exp(){ return lpr.nparams().val() + 1; }
		protected Integer etq_exp(){ return e0.etq().val() + numeroInstruccionesCodigoPaso(subProgramah().val(), lpr.nparams().val() + 1, e0.esDesignador().val(), tsh().val()); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones( lpr.cod().val() , listaUnitaria(copia()) , e0.cod().val() , codigoPaso(subProgramah().val(), lpr.nparams().val() + 1, e0.esDesignador().val(), tsh().val() )); }
	}
	public class ListaParametrosRealesR1Debug extends ListaParametrosRealesR1
	{
		private final static String REGLA = "ListaParametrosReales ::= ListaParametrosReales , Exp0";
		public ListaParametrosRealesR1Debug(ListaParametrosReales lpr, Exp0 e0)
		{
			super(lpr, e0);
			lpr.tsh().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales(1).tsh");
			e0.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp0.tsh");
			this.error().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales(0).error");
			lpr.subProgramah().fijaDescripcion(REGLA 	+ "\n  =>  ListaParametrosReales(1).subProgramah");
			this.nparams().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosReales(0).nparams");
			lpr.etqh().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales(1).etqh");
			e0.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales(0).etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales(0).cod");
		}
	}
	
	
	/** ListaParametrosReales ::= Exp0	
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp0.tsh = ListaParametrosReales.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ListaParametrosReales.error =   not parametroCorrecto(ListaParametrosReales.subprogramah, 1, Exp0.tipo, Exp0.esDesignador, ListaParametrosReales.tsh)                                                          
	 *  	ListaParametrosReales(0).nparams = 1
	 *  
	 *  Generación de código
	 *  	Exp0.etqh = ListaParametrosReales.etqh + 1
	 *  	ListaParametrosReales.etq = Exp0.etq + numeroInstruccionesCodigoPaso(ListaParametrosReales.subProgramah, 1, Exp0.esDesignador, ListaParametrosReales.tsh) <==== 
	 *  	ListaParametrosReales.cod = copia() || Exp0.cod || codigoPaso(ListaParametrosReales.subprogramah, 1,Exp0.esDesignador, ListaParametrosReales.tsh) 
	 */
	public class ListaParametrosRealesR2 extends ListaParametrosReales
	{
		private Exp0 e0;
		public ListaParametrosRealesR2(Exp0 e0)
		{
			super();
			this.e0 = e0;
			e0.registraCtx(new Exp0Ctx()
			{
				public TS tsh_exp(){ return ListaParametrosRealesR2.this.tsh().val(); }
				public Integer etqh_exp(){ return ListaParametrosRealesR2.this.etqh().val() + 1;  }
			});
			
			//dependencias
			e0.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(this.subProgramah(), e0.tipo(), e0.esDesignador(), this.tsh());                                                          
			e0.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e0.etq(), this.subProgramah(), e0.esDesignador(), this.tsh()); 
			this.cod().ponDependencias(e0.cod(), this.subProgramah(), e0.esDesignador(), this.tsh()); 
		}
		//metodos
		protected Error error_exp(){ return errorSi(!parametroCorrecto(subProgramah().val(), 1, e0.tipo().val(), e0.esDesignador().val(), tsh().val()), "Parametro Incorrecto"); }                                                          
		protected Integer nParams_exp(){ return 1; }
		protected Integer etq_exp(){ return e0.etq().val() + numeroInstruccionesCodigoPaso(subProgramah().val(), 1, e0.esDesignador().val(), tsh().val());  }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones( listaUnitaria(copia()) , e0.cod().val() , codigoPaso(subProgramah().val(), 1, e0.esDesignador().val(), tsh().val()) );  }
	}
	public class ListaParametrosRealesR2Debug extends ListaParametrosRealesR2
	{
		private final static String REGLA = "ListaParametrosReales ::= Exp0";
		public ListaParametrosRealesR2Debug(Exp0 e0)
		{
			super(e0);
			e0.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp0.tsh");
			this.error().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales.error");
			this.nparams().fijaDescripcion(REGLA 		+ "\n  =>  ListaParametrosReales.nparams");
			e0.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  ListaParametrosReales.cod");
		}
	}
	
	
	/** ParteElse ::= else {Instrucciones}
	 *  
	 *    Propagación de la tabla de símbolos
	 *  	Instrucciones.tsh = ParteElse.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	ParteElse.error = Instrucciones.error
	 *  
	 *  Generación de código
	 *  	Instrucciones.etqh = ParteElse.etqh+1
	 *  	ParteElse.etq = Instrucciones.etq	
	 *  	ParteElse.cod = ir_a(Instrucciones.etq) || Instrucciones.cod
	 *  	ParteElse.ir_f = ParteElse.etqh + 1
	 */
	public class ParteElseR1 extends ParteElse
	{
		private Instrucciones is;
		public ParteElseR1(Instrucciones is)
		{
			super();
			this.is = is;
			is.registraCtx(new InstruccionesCtx()
			{
				public TS tsh_exp(){ return ParteElseR1.this.tsh().val(); }
				public Integer etqh_exp(){ return ParteElseR1.this.etqh().val() + 1; }
			});
			
			//dependencias
			is.tsh().ponDependencias(this.tsh());
			this.error().ponDependencias(is.error());
			is.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(is.etq());	
			this.cod().ponDependencias(is.etq(), is.cod());
			this.ir_f().ponDependencias(this.etqh());
		}
		
		protected Error error_exp(){ return is.error.val(); }
		protected Integer etq_exp(){ return is.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return concatenarInstrucciones( ir_a(is.etq().val()) , is.cod().val() ); }
		protected Integer ir_f_exp(){ return this.etqh().val() + 1; }
	}
	public class ParteElseR1Debug extends ParteElseR1 
	{
		private final static String REGLA = "ParteElse ::= else {Instrucciones}";
		public ParteElseR1Debug(Instrucciones is)
		{
			super(is);
			is.tsh().fijaDescripcion(REGLA 		+ "\n  =>  Instrucciones.tsh");
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.error");
			is.etqh().fijaDescripcion(REGLA 	+ "\n  =>  Instrucciones.etqh");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.etq");	
			this.cod().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.cod");
			this.ir_f().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.ir_f");
		}
	}
	
	
	/** ParteElse ::= ¬ 
	 * 
	 *  Comprobación de las restricciones contextuales
	 * 		ParteElse.error = false
	 *  
	 *  Generación de código
	 *  	ParteElse.etq = ParteElse.etqh
	 *  	ParteElse.cod = programaVacio()
	 *  	ParteElse.ir_f = ParteElse.etqh 
	 */
	public class ParteElseR2 extends ParteElse
	{
		public ParteElseR2()
		{
			super();
			
			//dependencias
			this.etq().ponDependencias(this.etqh());
			this.ir_f().ponDependencias(this.etqh()); 
		}
		
		protected Error error_exp(){ return new Error(); }
		protected Integer etq_exp(){ return etqh().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return programaVacio(); }
		protected Integer ir_f_exp(){ return etqh().val(); }
	}
	public class ParteElseR2Debug extends ParteElseR2
	{
		private final static String REGLA = "ParteElse ::= ¬";
		public ParteElseR2Debug()
		{
			super();
			this.error().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.error");
			this.etq().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.etq");
			this.cod().fijaDescripcion(REGLA 		+ "\n  =>  ParteElse.cod");
			this.ir_f().fijaDescripcion(REGLA 	+ "\n  =>  ParteElse.ir_f"); 
		}
	}
	
	
	/** Exp0 ::= Exp1 OpComparacion Exp1
	 *  
	 *    Propagación de la tabla de símbolos
	 *  	Exp1(0).tsh = Exp0.tsh
	 *  	Exp1(1).tsh = Exp0.tsh
	 *  
	 *   Comprobación de las restricciones contextuales
	 *  	Exp0.tipo = tipoOpBin(OpComparacion.op,Exp1(0).tipo,Exp1(1).tipo)
	 *  	Exp0.esDesignador = false
	 *  
	 *   Generación de código
	 *  	Exp1(0).etqh = Exp0.etqh
	 *  	Exp1(1).etqh = Exp1(0).etq + unoSiCierto(Exp1(0).esDesignador)
	 * 	 	Exp0.etq = Exp1(1).etq + unoSiCierto(Exp1(1).esDesignador) + 1
	 * 	 	Exp0.cod = codigoOpComparacion(Exp1(0).cod, Exp1(1).cod, OpComparacion.cod, Exp1(0).esDesignador,Exp1(1).esDesignador)      
	 */
	public class Exp0R1 extends Exp0
	{
		private Exp1 e1_0;
		private OpComparacion op;
		private Exp1 e1_1;
		public Exp0R1(Exp1 e1_0, OpComparacion op, Exp1 e1_1)
		{
			super();
			this.e1_0 = e1_0;
			this.op = op;
			this.e1_1 = e1_1;
			e1_0.registraCtx(new Exp1Ctx()
			{
				public TS tsh_exp(){ return Exp0R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp0R1.this.etqh().val(); }
			});
			e1_1.registraCtx(new Exp1Ctx()
			{
				public TS tsh_exp(){ return Exp0R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp0R1.this.e1_0.etq().val() + unoSiCierto(Exp0R1.this.e1_0.esDesignador().val()); }
			});
			
			//dependencias
			e1_0.tsh().ponDependencias(this.tsh());
			e1_1.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(op.op(), e1_0.tipo(), e1_1.tipo());
			e1_0.etqh().ponDependencias(this.etqh());
			e1_1.etqh().ponDependencias(e1_0.etq(), e1_0.esDesignador());
			this.etq().ponDependencias(e1_1.etq(), e1_1.esDesignador());
			this.cod().ponDependencias(e1_0.cod(), e1_1.cod(), op.cod(), e1_0.esDesignador(), e1_1.esDesignador()); 
		}
		
		protected Tipo tipo_exp(){ return tipoOpBin(op.op().val(), e1_0.tipo().val(), e1_1.tipo().val()); }
		protected boolean esDesignador_exp(){ return false; }
		protected Integer etq_exp(){ return e1_1.etq().val() + unoSiCierto(e1_1.esDesignador().val()) + 1; }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoOpComparacion(e1_0.cod().val(), e1_1.cod().val(), op.cod().val(), e1_0.esDesignador().val(), e1_1.esDesignador().val()); }
	}
	public class Exp0R1Debug extends Exp0R1 
	{
		private final static String REGLA = "Exp0 ::= Exp1 OpComparacion Exp1";
		public Exp0R1Debug(Exp1 e1_0, OpComparacion op, Exp1 e1_1)
		{
			super(e1_0, op, e1_1);
			e1_0.tsh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(0).tsh");
			e1_1.tsh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(1).tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp0.esDesignador");
			e1_0.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(0).etqh");
			e1_1.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(1).etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.cod");
		}
	}
	
	
	/** Exp0 ::= Exp1
	 * 
	 *  Propagación de la tabla de símbolos
	 *  	Exp1.tsh = Exp0.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp0.tipo = Exp1.tipo
	 *  	Exp0.esDesignador = Exp1.esDesignador
	 *  
	 *  Generación de código
	 *  	Exp1.etqh = Exp0.etqh
	 *  	Exp0.etq = Exp1.etq
	 *  	Exp0.cod = Exp1.cod 
	 */
	public class Exp0R2 extends Exp0
	{
		private Exp1 e1;
		public Exp0R2(Exp1 e1)
		{
			super();
			this.e1 = e1;
			e1.registraCtx(new Exp1Ctx()
			{
				public TS tsh_exp(){ return Exp0R2.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp0R2.this.etqh().val(); }
			});
			
			//dependencias
			e1.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(e1.tipo());
			this.esDesignador().ponDependencias(e1.esDesignador());
			e1.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e1.etq());
			this.cod().ponDependencias(e1.cod()); 
		}
		
		protected Tipo tipo_exp(){ return e1.tipo().val(); }
		protected boolean esDesignador_exp(){ return e1.esDesignador().val(); }
		protected Integer etq_exp(){ return e1.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return e1.cod().val(); }
	}
	public class Exp0R2Debug extends Exp0R2
	{
		private final static String REGLA = "Exp0 ::= Exp1";
		public Exp0R2Debug(Exp1 e1)
		{
			super(e1);
			e1.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp1.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp0.esDesignador");
			e1.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.cod"); 
		}
	}
	
	
	/** Exp1 ::= Exp1 OpAditivo Exp2
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp1(1).tsh = Exp1(0).tsh
	 *  	Exp2.tsh = Exp1(0).tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp1(0).tipo = tipoOpBin(OpAditivo.op,Exp1(1).tipo,Exp2.tipo)
	 *  	Exp1(0).esDesignador = false
	 *  
	 *  Generación de código
	 *  	Exp1(1).etqh = Exp1(0).etqh
	 *  	Exp2.etqh = Exp1(1).etq + unoSiCierto(Exp1(1).esDesignador)
	 *  	Exp1(0).etq = Exp2.etq + unoSiCierto(Exp2.esDesignador) + 1
	 *  	Exp1(0).cod = codigoOpAditivo(Exp1(1).cod, Exp2.cod, OpAditivo.cod, Exp1(1).esDesignador,Exp2.esDesignador)  
	 */
	public class Exp1R1 extends Exp1
	{
		private Exp1 e1;
		private OpAditivo op;
		private Exp2 e2;
		public Exp1R1(Exp1 e1, OpAditivo op, Exp2 e2)
		{
			super();
			this.e1 = e1;
			this.op = op;
			this.e2 = e2;
			e1.registraCtx(new Exp1Ctx()
			{
				public TS tsh_exp(){ return Exp1R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp1R1.this.etqh().val(); }
			});
			e2.registraCtx(new Exp2Ctx()
			{
				public TS tsh_exp(){ return Exp1R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp1R1.this.e1.etq().val() + unoSiCierto(Exp1R1.this.e1.esDesignador().val()); }
			});
			
			//dependencias
			e1.tsh().ponDependencias(this.tsh());
			e2.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(op.op(), e1.tipo(), e2.tipo());
			e1.etqh().ponDependencias(this.etqh());
			e2.etqh().ponDependencias(e1.etq(), e1.esDesignador());
			this.etq().ponDependencias(e2.etq(), e2.esDesignador());
			this.cod().ponDependencias(e1.cod(), e2.cod(), op.cod(), e1.esDesignador(), e2.esDesignador());  
		}
		
		protected Tipo tipo_exp(){ return tipoOpBin(op.op().val(), e1.tipo().val(), e2.tipo().val()); }
		protected boolean esDesignador_exp(){ return false; }
		protected Integer etq_exp(){ return e2.etq().val() + unoSiCierto(e2.esDesignador().val()) + 1; }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoOpAditivo(e1.cod.val(), e2.cod.val(), op.cod.val(), e1.esDesignador.val(), e2.esDesignador.val()); }
	}
	public class Exp1R1Debug extends Exp1R1 
	{
		private final static String REGLA = " Exp1 ::= Exp1 OpAditivo Exp2";
		public Exp1R1Debug(Exp1 e1, OpAditivo op, Exp2 e2)
		{
			super(e1, op, e2);
			e1.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp1(1).tsh");
			e2.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp2.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(0).tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp1(0).esDesignador");
			e1.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(1).etqh");
			e2.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp2.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(0).etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp1(0).cod");
		}
	}
	
	
	/** Exp1 ::= Exp2
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp2.tsh = Exp1.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp1.tipo = Exp2.tipo
	 *  	Exp1.esDesignador = Exp2.esDesignador
	 *  
	 *  Generación de código
	 *  	Exp2.etqh = Exp1.etqh
	 *  	Exp1.etq = Exp2.etq
	 *  	Exp1.cod = Exp2.cod 
	 */
	public class Exp1R2 extends Exp1
	{
		private Exp2 e2;
		public Exp1R2(Exp2 e2)
		{
			super();
			this.e2 = e2;
			e2.registraCtx(new Exp2Ctx()
			{
				public TS tsh_exp(){ return Exp1R2.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp1R2.this.etqh().val(); }
			});
			
			//dependencias
			e2.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(e2.tipo());
			this.esDesignador().ponDependencias(e2.esDesignador());
			e2.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e2.etq());
			this.cod().ponDependencias(e2.cod()); 
		}
		
		protected Tipo tipo_exp(){ return e2.tipo().val(); }
		protected boolean esDesignador_exp(){ return e2.esDesignador().val(); }
		protected Integer etq_exp(){ return e2.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return e2.cod.val(); }
	}
	public class Exp1R2Debug extends Exp1R2
	{
		private final static String REGLA = "Exp1 ::= Exp2";
		public Exp1R2Debug(Exp2 e2)
		{
			super(e2);
			e2.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp2.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp1.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp1.esDesignador");
			e2.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp2.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp1.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp1.cod"); 
		}
	}
	
	
	/** Exp2 ::= Exp2 OpMultiplicativo Exp3
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp2(1).tsh = Exp2(0).tsh
	 *  	Exp3.tsh = Exp2(0).tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp2(0).tipo = tipoOpBin(OpMultiplicativo.op,Exp2(1).tipo,Exp3.tipo)
	 *  	Exp2(0).esDesignador = false
	 *  
	 *  Generación de código
	 *  	Exp2(1).etqh = Exp2(0).etqh
	 *  	Exp3.etqh = Exp2(1).etq + unoSiCierto(Exp2(1).esDesignador)
	 *  	Exp2(0).etq = Exp3.etq + unoSiCierto(Exp3.esDesignador) + 1
	 *  	Exp2(0).cod = codigoOpMultiplicativo(Exp2(1).cod, Exp3.cod, OpMultiplicativo.cod, Exp2(1).esDesignador,Exp3.esDesignador)    
	 */
	public class Exp2R1 extends Exp2
	{
		private Exp2 e2;
		private OpMultiplicativo op;
		private Exp3 e3;
		public Exp2R1(Exp2 e2, OpMultiplicativo op, Exp3 e3)
		{
			super();
			this.e2 = e2;
			this.op = op;
			this.e3 = e3;
			e2.registraCtx(new Exp2Ctx()
			{
				public TS tsh_exp(){ return Exp2R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp2R1.this.etqh().val(); }
			});
			e3.registraCtx(new Exp3Ctx()
			{
				public TS tsh_exp(){ return Exp2R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp2R1.this.e2.etq().val() + unoSiCierto(Exp2R1.this.e2.esDesignador().val()); }
			});
			
			//dependencias
			e2.tsh().ponDependencias(this.tsh());
			e3.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(op.op(), e2.tipo(), e3.tipo());
			e2.etqh().ponDependencias(this.etqh());
			e3.etqh().ponDependencias(e2.etq(), e2.esDesignador());
			this.etq().ponDependencias(e3.etq(), e3.esDesignador());
			this.cod().ponDependencias(e2.cod(), e3.cod(), op.cod(), e2.esDesignador(), e3.esDesignador());    
		}

		protected Tipo tipo_exp(){ return tipoOpBin(op.op().val(), e2.tipo().val(), e3.tipo().val()); }
		protected boolean esDesignador_exp(){ return false; }
		protected Integer etq_exp(){ return e3.etq().val() + unoSiCierto(e3.esDesignador().val()) + 1; }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoOpMultiplicativo(e2.cod().val(), e3.cod().val(), op.cod().val(), e2.esDesignador().val(), e3.esDesignador().val()); }
	}
	public class Exp2R1Debug extends Exp2R1 
	{
		private final static String REGLA = "Exp2 ::= Exp2 OpMultiplicativo Exp3";
		public Exp2R1Debug(Exp2 e2, OpMultiplicativo op, Exp3 e3)
		{
			super(e2, op, e3);
			e2.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp2(1).tsh");
			e3.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp3.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp2(0).tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp2(0).esDesignador");
			e2.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp2(1).etqh");
			e3.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp3.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp2(0).etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp2(0).cod");  
		}
	}
	
	
	/** Exp2 ::= Exp3
	 * 
	 *  Propagación de la tabla de símbolos
	 *   	Exp3.tsh = Exp2.tsh
	 *   
	 *   Comprobación de las restricciones contextuales
	 *   	Exp2.tipo = Exp3.tipo
	 *   	Exp2.esDesignador = Exp3.esDesignador
	 *   
	 *   Generación de código
	 *   	Exp3.etqh = Exp2.etqh
	 *   	Exp2.etq = Exp3.etq
	 *   	Exp2.cod = Exp3.cod 
	 */
	public class Exp2R2 extends Exp2
	{
		private Exp3 e3;
		public Exp2R2(Exp3 e3)
		{
			super();
			this.e3 = e3;
			e3.registraCtx(new Exp3Ctx()
			{
				public TS tsh_exp(){ return Exp2R2.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp2R2.this.etqh().val(); }
			});
			
			//dependencias
			e3.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(e3.tipo());
			this.esDesignador().ponDependencias(e3.esDesignador());
			e3.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e3.etq());
			this.cod().ponDependencias(e3.cod()); 
		}
		
		protected Tipo tipo_exp(){ return e3.tipo().val(); }
		protected boolean esDesignador_exp(){ return e3.esDesignador().val(); }
		protected Integer etq_exp(){ return e3.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return e3.cod().val(); }
	}
	public class Exp2R2Debug extends Exp2R2
	{
		private final static String REGLA = "Exp2 ::= Exp3";
		public Exp2R2Debug(Exp3 e3)
		{
			super(e3);
			e3.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp3.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp2.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp2.esDesignador");
			e3.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp3.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp2.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp2.cod"); 
		}
	}
	
	
	/** Exp3 ::= OpUnario Exp3
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp3(1).tsh = Exp3(0).tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp3(0).tipo = tipoOpUn(OpUnario.op,Exp3(1).tipo)
	 *  	Exp3(0).esDesignador = false
	 *  
	 *  Generación de código
	 *  	Exp3(1).etqh = Exp3(0).etqh
	 *  	Exp3(0).etq = Exp3(1).etq + numeroInstruccionesOpUnario(Exp3(1).esDesignador)
	 *  	Exp3(0).cod = codigoOpUnario(Exp3(1).cod, OpUnario.cod, Exp3(1).esDesignador) 
	 */
	public class Exp3R1 extends Exp3
	{
		private OpUnario op;
		private Exp3 e3;
		public Exp3R1(OpUnario op, Exp3 e3)
		{
			super();
			this.op = op;
			this.e3 = e3;
			e3.registraCtx(new Exp3Ctx()
			{
				public TS tsh_exp(){ return Exp3R1.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp3R1.this.etqh().val(); }
			});
			
			//dependencias
			e3.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(op.op(), e3.tipo());
			e3.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e3.etq(), e3.esDesignador());
			this.cod().ponDependencias(e3.cod(), op.cod(), e3.esDesignador()); 
		}
		
		protected Tipo tipo_exp(){ return tipoOpUn(op.op().val(), e3.tipo().val()); }
		protected boolean esDesignador_exp(){ return false; }
		protected Integer etq_exp(){ return e3.etq().val() + numeroInstruccionesOpUnario(e3.esDesignador().val()); }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoOpUnario(e3.cod().val(), op.cod().val(), e3.esDesignador().val()); }

	}
	public class Exp3R1Debug extends Exp3R1
	{
		private final static String REGLA = " Exp3 ::= OpUnario Exp3";
		public Exp3R1Debug(OpUnario op, Exp3 e3)
		{
			super(op, e3);
			e3.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp3(1).tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp3(0).error");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp3(0).esDesignador");
			e3.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp3(1).etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp3(0).etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp3(0).cod");
		}
	}
	
	
	/** Exp3 ::= Exp4
	 * 
	 *  Propagación de la tabla de símbolos
	 *  	Exp4.tsh = Exp3.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp3.tipo = Exp4.tipo
	 *  	Exp3.esDesignador = Exp4.esDesignador
	 *  
	 *  Generación de código
	 *  	Exp4.etqh = Exp3.etqh
	 *  	Exp3.etq = Exp4.etq
	 *  	Exp3.cod = Exp4.cod 
	 */
	public class Exp3R2 extends Exp3
	{
		private Exp4 e4;
		public Exp3R2(Exp4 e4)
		{
			super();
			this.e4 = e4;
			e4.registraCtx(new Exp4Ctx()
			{
				public TS tsh_exp(){ return Exp3R2.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp3R2.this.etqh().val(); }
			});
			
			//dependencias
			e4.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(e4.tipo());
			this.esDesignador().ponDependencias(e4.esDesignador());
			e4.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e4.etq());
			this.cod().ponDependencias(e4.cod()); 
		}
		
		protected Tipo tipo_exp(){ return e4.tipo().val(); }
		protected boolean esDesignador_exp(){ return e4.esDesignador().val(); }
		protected Integer etq_exp(){ return e4.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return e4.cod().val(); }
	}
	public class Exp3R2Debug extends Exp3R2
	{
		private final static String REGLA = "Exp3 ::= Exp4";
		public Exp3R2Debug(Exp4 e4)
		{
			super(e4);
			e4.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp4.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp3.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp3.esDesignador");
			e4.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp3.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp3.cod");
		}
	}
	
	
	/** Exp4 ::= NUM
	 * 
	 *  Comprobación de las restricciones contextuales
	 * 		Exp4.tipo = number
	 * 	 	Exp4.esDesignador = false 
	 *  
	 *  Generación de código
	 *  	Exp4.etq = Exp4.etqh + 1
	 *  	Exp4.cod = apila(NUM.lex)
	 */
	public class Exp4R1 extends Exp4
	{
		private Token num;
		public Exp4R1(Token num)
		{
			super();
			this.num = num;
			//dependencias
			this.etq().ponDependencias(this.etqh());
		}
		protected Tipo tipo_exp(){ return new Tipo(EnumTipos.NUM); }
		protected boolean esDesignador_exp(){ return false; }
		protected Integer etq_exp(){ return etqh().val() + 1; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(apila(Integer.valueOf(num.getLexema()))); } 
	}
	public class Exp4R1Debug extends Exp4R1
	{
		private final static String REGLA = "Exp4 ::= NUM";
		public Exp4R1Debug(Token num)
		{
			super(num);
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp4.esDesignador");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.cod");
		}
	}
	
	
	/** Exp4 ::= IDEN
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp4.tipo = tipoDe(IDEN.lex,Exp4.tsh)
	 *  	Exp4.esDesignador = true
	 *  
	 *  Generación de código	
	 *  	Exp4.etq = Exp4.etqh + numeroInstruccionesAccesoVar(IDEN.lex,Exp4.tsh)
	 *  	Exp4.cod = codigoAccesoVar(IDEN.lex,Exp4.tsh)
	 */
	public class Exp4R2 extends Exp4
	{
		private Token iden;
		public Exp4R2(Token iden)
		{
			super();
			this.iden = iden;
			
			//dependencias
			this.tipo().ponDependencias(this.tsh());
			this.etq().ponDependencias(this.etqh(), this.tsh());
			this.cod().ponDependencias(this.tsh());
		}
		
		protected Tipo tipo_exp()
		{ 
			Tipo t = tipoDe(iden.getLexema(), this.tsh().val());
			return t;
		}
		protected boolean esDesignador_exp(){ return true; }
		protected Integer etq_exp(){ return etqh().val() + numeroInstruccionesAccesoVar(tsh().val(), iden.getLexema()); }
		protected List<InstruccionesMaquina> cod_exp(){ return codigoAccesoVar(tsh().val(), iden.getLexema()); }
	}
	public class Exp4R2Debug extends Exp4R2
	{
		private final static String REGLA = "Exp4 ::= IDEN";
		public Exp4R2Debug(Token iden)
		{
			super(iden);
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp4.esDesignador");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.cod");
		}
	}
	
	
	/**	Exp4 ::= ( Exp0 )
	 *  
	 *  Propagación de la tabla de símbolos
	 *  	Exp0.tsh = Exp4.tsh
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	Exp4.tipo = Exp0.tipo
	 *  	Exp4.esDesignador = Exp0.esDesignador
	 *  
	 *  Generación de código
	 *  	Exp0.etqh = Exp4.etqh
	 *  	Exp4.etq = Exp0.etq
	 *  	Exp4.cod = Exp0.cod 
	 */
	public class Exp4R3 extends Exp4
	{
		private Exp0 e0;
		public Exp4R3(Exp0 e0)
		{
			super();
			this.e0 = e0;
			e0.registraCtx(new Exp0Ctx() 
			{
				public TS tsh_exp(){ return Exp4R3.this.tsh().val(); }
				public Integer etqh_exp(){ return Exp4R3.this.etqh().val(); }
			});
			
			//dependencias
			e0.tsh().ponDependencias(this.tsh());
			this.tipo().ponDependencias(e0.tipo());
			this.esDesignador().ponDependencias(e0.esDesignador());
			e0.etqh().ponDependencias(this.etqh());
			this.etq().ponDependencias(e0.etq());
			this.cod().ponDependencias(e0.cod()); 
		}
		
		protected Tipo tipo_exp(){ return e0.tipo().val(); }
		protected boolean esDesignador_exp(){ return e0.esDesignador().val(); }
		protected Integer etq_exp(){ return e0.etq().val(); }
		protected List<InstruccionesMaquina> cod_exp(){ return e0.cod().val(); }
	}
	public class Exp4R3Debug extends Exp4R3
	{
		private final static String REGLA = "Exp4 ::= ( Exp0 )";
		public Exp4R3Debug(Exp0 e0)
		{
			super(e0);
			e0.tsh().fijaDescripcion(REGLA 				+ "\n  =>  Exp0.tsh");
			this.tipo().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.tipo");
			this.esDesignador().fijaDescripcion(REGLA 	+ "\n  =>  Exp4.esDesignador");
			e0.etqh().fijaDescripcion(REGLA 			+ "\n  =>  Exp0.etqh");
			this.etq().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.etq");
			this.cod().fijaDescripcion(REGLA 			+ "\n  =>  Exp4.cod");
		}
	}
	
	
	/**	OpComparacion ::= =
	 * 
	 *  Comprobación de las restricciones contextuales
	 *  	OpComparacion.op = eq
	 *  
	 *  Generación de código
	 *  	OpComparacion.cod = eq()
	 */
	public class OpComparacionR1 extends OpComparacion
	{
		public OpComparacionR1()
		{
			super();
		}
		protected String op_exp(){ return "eq"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(eq()); }
	}
	public class OpComparacionR1Debug extends OpComparacionR1
	{
		private final static String REGLA = "	OpComparacion ::= =";
		public OpComparacionR1Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
		
	
	/** OpComparacion ::= =/=
	 * 
	 *  Comprobación de las restricciones contextuales
	 *  	OpComparacion.op = neq
	 *  
	 *  Generación de código
	 *  	OpComparacion.cod = neq()
	 */
	public class OpComparacionR2 extends OpComparacion
	{
		public OpComparacionR2()
		{
			super();
		}
		protected String op_exp(){ return "neq"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(neq()); }
	}
	public class OpComparacionR2Debug extends OpComparacionR2
	{
		private final static String REGLA = "	OpComparacion ::= =/=";
		public OpComparacionR2Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
	
	
	/** OpComparacion ::= >
	 * 
	 *  Comprobación de las restricciones contextuales
	 *  	OpComparacion.op = gt
	 *  
	 *  Generación de código
	 *  	OpComparacion.cod = gt()
	 */
	public class OpComparacionR3 extends OpComparacion
	{
		public OpComparacionR3()
		{
			super();
		}
		protected String op_exp(){ return "gt"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(gt()); }
	}
	public class OpComparacionR3Debug extends OpComparacionR3
	{
		private final static String REGLA = "	OpComparacion ::= >";
		public OpComparacionR3Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
	
	
	/** OpComparacion ::= >=
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	OpComparacion.op = ge
	 *  
	 *  Generación de código
	 *  	OpComparacion.cod = ge()		
	 */
	public class OpComparacionR4 extends OpComparacion
	{
		public OpComparacionR4()
		{
			super();
		}
		protected String op_exp(){ return "ge"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(ge()); }
	}
	public class OpComparacionR4Debug extends OpComparacionR4
	{
		private final static String REGLA = "	OpComparacion ::= >=";
		public OpComparacionR4Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
	
	
	/** OpComparacion ::= <
	 *  
	 *  Comprobación de las restricciones contextuales
	 *		OpComparacion.op = lt
	 *
	 *  Generación de código
	 * 		OpComparacion.cod = lt()
	 */
	public class OpComparacionR5 extends OpComparacion
	{
		public OpComparacionR5()
		{
			super();
		}
		protected String op_exp(){ return "lt"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(lt()); }
	}
	public class OpComparacionR5Debug extends OpComparacionR5
	{
		private final static String REGLA = "	OpComparacion ::= <";
		public OpComparacionR5Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
		
	
	/** OpComparacion ::= <=
	 * 
	 *  Comprobación de las restricciones contextuales
	 *  	OpComparacion.op = le
	 *  
	 *  Generación de código
	 *  	OpComparacion.cod = le()
	 */		
	public class OpComparacionR6 extends OpComparacion
	{
		public OpComparacionR6()
		{
			super();
		}
		protected String op_exp(){ return "le"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(le()); }
	}
	public class OpComparacionR6Debug extends OpComparacionR6
	{
		private final static String REGLA = "	OpComparacion ::= <=";
		public OpComparacionR6Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpComparacion.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpComparacion.cod");
		}
	}
	
	
	/**	OpAditivo ::= +
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	OpAditivo.op = +
	 *  
	 *  Generación de código
	 *  	OpAditivo.cod = suma()
	 */
	public class OpAditivoR1 extends OpAditivo
	{
		public OpAditivoR1() 
		{
			super();
		}
		protected String op_exp(){ return "+"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(suma()); }
	}
	public class OpAditivoR1Debug extends OpAditivoR1
	{
		private final static String REGLA = "OpAditivo ::= +";
		public OpAditivoR1Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpAditivo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpAditivo.cod");
		}
	}
	
	
	/** OpAditivo ::= -
	 * 
	 *   Comprobación de las restricciones contextuales
	 *   	OpAditivo.op = -
	 *   
	 *   Generación de código
	 *   	OpAditivo.cod = resta()
	 */
	public class OpAditivoR2 extends OpAditivo
	{
		public OpAditivoR2() 
		{
			super();
		}
		protected String op_exp(){ return "-"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(resta()); }
	}
	public class OpAditivoR2Debug extends OpAditivoR2
	{
		private final static String REGLA = "OpAditivo ::= -";
		public OpAditivoR2Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpAditivo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpAditivo.cod");
		}
	}
	
	
	/** OpAditivo ::= or
	 *   
	 *   Comprobación de las restricciones contextuales
	 *   	OpAditivo.op = or
	 *   
	 *   Generación de código
	 *   	OpAditivo.cod = or()
	 */
	public class OpAditivoR3 extends OpAditivo
	{
		public OpAditivoR3() 
		{
			super();
		}
		protected String op_exp(){ return "or"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(or()); }
	}
	public class OpAditivoR3Debug extends OpAditivoR3
	{
		private final static String REGLA = "OpAditivo ::= or";
		public OpAditivoR3Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpAditivo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpAditivo.cod");
		}
	}
	
	
	/**	OpMultiplicativo ::= *
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	OpMultiplicativo.op = *
	 *  
	 *  Generación de código
	 *  	OpMultiplicativo.cod = mul()
	 */
	public class OpMultiplicativoR1 extends OpMultiplicativo
	{
		public OpMultiplicativoR1()
		{
			super();
		}
		
		protected String op_exp(){ return "*"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(mul()); }
	}
	public class OpMultiplicativoR1Debug extends OpMultiplicativoR1
	{
		private final static String REGLA = "OpMultiplicativo ::= *";
		public OpMultiplicativoR1Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpMultiplicativo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpMultiplicativo.cod");
		}
	}
	
	
	/** OpMultiplicativo ::= /
	 *  
	 *   Comprobación de las restricciones contextuales
	 *   	OpMultiplicativo.op = /
	 *   
	 *   Generación de código
	 *   	OpMultiplicativo.cod = div()
	 */
	public class OpMultiplicativoR2 extends OpMultiplicativo
	{
		public OpMultiplicativoR2()
		{
			super();
		}
		
		protected String op_exp(){ return "/"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(div()); }
	}
	public class OpMultiplicativoR2Debug extends OpMultiplicativoR2
	{
		private final static String REGLA = "OpMultiplicativo ::= /";
		public OpMultiplicativoR2Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpMultiplicativo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpMultiplicativo.cod");
		}
	}
	
	
	/** OpMultiplicativo ::= and
	 *   
	 *   Comprobación de las restricciones contextuales
	 *   	OpMultiplicativo.op = and
	 *   
	 *   Generación de código
	 *   	OpMultiplicativo.cod = and()
	 */
	public class OpMultiplicativoR3 extends OpMultiplicativo
	{
		public OpMultiplicativoR3()
		{
			super();
		}
		
		protected String op_exp(){ return "and"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(and()); }
	}
	public class OpMultiplicativoR3Debug extends OpMultiplicativoR3
	{
		private final static String REGLA = "OpMultiplicativo ::= and";
		public OpMultiplicativoR3Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpMultiplicativo.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpMultiplicativo.cod");
		}
	}
	
	
	/** OpUnario ::= -
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	OpUnario.op = -
	 *  
	 *  Generación de código
	 *  	OpUnario.cod = negar()
	 */
	public class OpUnarioR1 extends OpUnario
	{
		public OpUnarioR1()
		{
			super();
		}
		protected String op_exp(){ return "-"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(negar()); }
	}
	public class OpUnarioR1Debug extends OpUnarioR1
	{
		private final static String REGLA = "OpUnario ::= -";
		public OpUnarioR1Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpUnario.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpUnario.cod");
		}
	}
	
	
	/** OpUnario ::= not
	 *  
	 *  Comprobación de las restricciones contextuales
	 *  	OpUnario.op = not
	 *  
	 *  Generación de código
	 *  	OpUnario.cod = not()
	 */
	public class OpUnarioR2 extends OpUnario
	{
		public OpUnarioR2()
		{
			super();
		}
		protected String op_exp(){ return "not"; }
		protected List<InstruccionesMaquina> cod_exp(){ return listaUnitaria(not()); }
	}
	public class OpUnarioR2Debug extends OpUnarioR2
	{
		private final static String REGLA = "OpUnario ::= not";
		public OpUnarioR2Debug()
		{
			super();
			this.op().fijaDescripcion(REGLA 	+ "\n  =>  OpUnario.op");
			this.cod().fijaDescripcion(REGLA	+ "\n  =>  OpUnario.cod");
		}
	}

	
	// ------------------------------------------------------
	// XXX 				FUNCIONES
	// ------------------------------------------------------
	
	/** TS **/
		
		private TS creaTS()
		{
			return new TS();
		}
		
		private TS addSimbol(TS ts, String iden, Clase clase, Tipo tipo, int dir, int nivel)
		{
			TS nueva_ts = ts.duplicarTS();
			nueva_ts.addElement_inLevel(iden, clase, tipo, dir, nivel);
			return nueva_ts;
		}
		
		private Tipo tipoDe(String iden, TS ts)
		{
			return ts.tipoDeIden(iden);
		}
		
		private TS creaNivel(TS ts)
		{
			TS nueva_ts = ts.duplicarTS();
			nueva_ts.addNivel();
			return nueva_ts;
		}
		
	/** ERRORES **/
		
		public static Error errorSi(boolean hayError, String msg)
		{
			if (hayError) {
				return new Error(msg);
			}
			else {
				return new Error();
			}
		}
		
		/** el tipo del simbolo y el tipo de entrada son numerico */
		private boolean asignacionCorrecta(TS ts, String iden, Tipo tipo)
		{
			Tipo tipoEncontrado = ts.tipoDeIden(iden);
			return (tipoEncontrado.es_num && tipo.es_num &&!tipoEncontrado.es_error && !tipo.es_error);
		} 
		
		/** tipo numerico */
		private boolean condicionCorrecta(Tipo tipo)
		{
			return tipo.es_num && !tipo.es_error;
		}
		
		/** simbolo repetido (ya estaba declarado)*/
		private Error existeSimbEnUltimoNivel(TS ts, String id)
		{
			return errorSi(ts.existeSimbEnUltimoNivel(id), "existeSimbEnUltimoNivel");
		}
		
		/** Tipo numerico, no un proc */
		private boolean escrituraCorrecta(TS ts, String iden)
		{
			return ts.tipoDeIden(iden).es_num;
		} 
		
		/** el simbolo es un procedimiento y el numero de parametros coincide */
		private boolean llamadaCorrecta(TS ts, String iden, Integer nParametros )
		{
			boolean existeSimbolo = !ts.tipoDeIden(iden).es_error;
			if (!existeSimbolo)
			{
				return false;
			}
			return ts.encontrarProcedimiento(iden, nParametros);
		}
		
		/** 1) f(VAR ) y le paso un noDESIGNADOR peta!
		 *  2) parametro declarado sea un num */
		private boolean parametroCorrecto(String nomreProcedimiento, int parametro_i, Tipo tipoParametro, Boolean esDesignador, TS ts)
		{
			// 1) Encontrar en la TS el procedimiento con num parametros correcto
			Tipo t = ts.tipoDeIden(nomreProcedimiento);
			
			if(t.es_error || t.es_num)
			{
				return false;
			}
			
			// 2) fallo si declarado como Referencia y !designador
			List<Boolean> listaParametros = t.params;
			try
			{
				boolean por_valor = listaParametros.get(parametro_i - 1);
				if(!esDesignador && !por_valor)
				{
					return false;
				}
			} 
			catch (Exception e)
			{
				// Numero de parametros incorrecto... podría haber usado ts.encontrarProcedimiento.
				return false;
			}
		
			// 3) tipo = num
			if(!tipoParametro.es_num || tipoParametro.es_error)
			{
				return false;
			}
			
			return true;
		}
		
	/** OPERACIONES ENTRE TIPOS **/ 
	
		private Tipo tipoOpBin(String op, Tipo tipo1, Tipo tipo2)
		{
			/*
			 * NUM OP NUM => NUM
			 * NUM OP PROC => ERROR
			 * PROC OP PROC => ERROR
			 */
			if(tipo1.es_num && !tipo1.es_error && tipo2.es_num && !tipo2.es_error)
			{
				return new Tipo(EnumTipos.NUM);
			}
			else
			{
				return new Tipo(EnumTipos.ERROR);
			}
		}
	
		private Tipo tipoOpUn(String op,Tipo tipo) 
		{
			if (tipo.es_num && !tipo.es_error)
			{
				return new Tipo(EnumTipos.NUM);
			}
			else
			{
				return new Tipo(EnumTipos.ERROR);
			}
		}
	
	/** CREACION DE LISTAS - CONCATENACION **/
	
		public List<InstruccionesMaquina> listaUnitaria(InstruccionesMaquina i) 
		{
			List<InstruccionesMaquina> l = new LinkedList<InstruccionesMaquina>();
			l.add(i);
			return l;
		}
		public List<InstruccionesMaquina> concatenarInstruccion(List<InstruccionesMaquina> l, InstruccionesMaquina i)
		{
			l.add(i);
			return l;
		}
		private List<InstruccionesMaquina> concatenarInstrucciones(List<InstruccionesMaquina> i1, List<InstruccionesMaquina> i2)
		{
			i1.addAll(i2);
			return i1;
		}
		private List<InstruccionesMaquina> concatenarInstrucciones(List<InstruccionesMaquina> i1, List<InstruccionesMaquina> i2, List<InstruccionesMaquina> i3)
		{
			i2.addAll(i3);
			i1.addAll(i2);
			return i1;
		}
		private List<InstruccionesMaquina> concatenarInstrucciones(List<InstruccionesMaquina> i1, List<InstruccionesMaquina> i2, List<InstruccionesMaquina> i3, List<InstruccionesMaquina> i4)
		{
			i3.addAll(i4);
			i2.addAll(i3);
			i1.addAll(i2);
			return i1;
		}
		/*
		private List<InstruccionesMaquina> concatenarInstrucciones(List<InstruccionesMaquina>... instrucciones)
		{
		
		}
		*/
		private Integer unoSiCierto(boolean condicion)
		{
			if (condicion){ return 1; }
			else{ return 0; }
		}
	
	/** Lista Parametros **/
		
		private List<Boolean> listaVacia()
		{
			return new LinkedList<Boolean>();
		}
		
		private List<Boolean> nuevaLista(Boolean modo)
		{
			List<Boolean> lista = new LinkedList<Boolean>();
			lista.add(modo);
			return lista;
		}
		
		private List<Boolean> addParamToList(List<Boolean> lista, Boolean modo)
		{
			lista.add(modo);
			return lista;
		}
		
		
	/** GENERACION DE CODIGO **/ 
		
		private List<InstruccionesMaquina> programaVacio()
		{
			return new LinkedList<InstruccionesMaquina>();
		}
		
		private List<InstruccionesMaquina> codigoActivacionProgramaPrincipal(Integer tamDatos, Integer dirPrograma, Integer anidamiento)
		{
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				// 1) fijar CP
				lista.add(apila(anidamiento + 1 + tamDatos + 2));
				lista.add(desapila_dir(0));
				
				// 2) mem[1] = [cp] + 3 ==> display del nivel al primer dato de ese registro
				lista.add(apila(1 + anidamiento + 2 + 1)); //anidamiento = 0?
				lista.add(desapila_dir(1));
				
				// 3) comenzar el programa
				lista.add(salto(dirPrograma));
			return lista; 
		}
			
		/** Dejar en la cima de la pila la direccon de la variable */
		private List<InstruccionesMaquina> codigoAccesoVar(TS ts, String iden)
		{
			// Consultas auxiliares
			int nivel = ts.nivelDeIden(iden);
			int dir = ts.direccionDeIden(iden);
			boolean es_PVAR = !ts.claseDeIden(iden).es_var;
			
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				lista.add(apila_dir(nivel + 1));
				lista.add(apila(dir));
				lista.add(suma());
				if (es_PVAR)
				{
					lista.add(apila_ind());
				}
			return lista; 
		}
			
		/** Guardar en memoria el valor de la variable [cod] */
		private List<InstruccionesMaquina> codigoAsignacion(TS ts, String iden, List<InstruccionesMaquina> cod, Boolean esDesinador)
		{
			// Consultas Auxiliares
			int nivel = ts.nivelDeIden(iden);
			int dir = ts.direccionDeIden(iden);
			boolean es_PVAR = !ts.claseDeIden(iden).es_var;
			
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				// 1) Direccion de la Variable
				lista.add(apila_dir(nivel + 1));
				lista.add(apila(dir));
				lista.add(suma());
				if (es_PVAR)
				{
					lista.add(apila_ind());
				}
				
				// 2) Valor de la variable
				concatenarInstrucciones(lista, cod);
				if(esDesinador)
				{
					lista.add(apila_ind());
				}
				
				// 3) Escritura
				lista.add(desapila_ind());
			return lista;
		}
		
		private List<InstruccionesMaquina> codigoEscritura(TS ts, String iden)
		{
			// Consultas auxiliares
			int nivel = ts.nivelDeIden(iden);
			int dir = ts.direccionDeIden(iden);
			boolean es_PVAR = !ts.claseDeIden(iden).es_var;			
						
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				lista.add(apila_dir(nivel + 1));
				lista.add(apila(dir));
				lista.add(suma());
				if (es_PVAR)
				{
					lista.add(apila_ind());
				}
				lista.add(apila_ind());
		   		lista.add(escribe()); 
			return lista;
		}
				
		private List<InstruccionesMaquina> ir_falso(Integer dir)
		{
			return listaUnitaria(salto_falso(dir));
		}
		
		private List<InstruccionesMaquina> ir_a(Integer dir)
		{
			return listaUnitaria(salto(dir));
		}

		private List<InstruccionesMaquina> codigoOpComparacion(	List<InstruccionesMaquina> cod_e1, List<InstruccionesMaquina> cod_e2, List<InstruccionesMaquina> cod_op, boolean esDesignador_1, boolean esDesignador_2)
		{
			LinkedList<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
	   			lista = (LinkedList<InstruccionesMaquina>) concatenarInstrucciones(lista, cod_e1);
	   			if (esDesignador_1)
		   		{
		   			lista.add(apila_ind());
		   		}
		   		
		   		lista = (LinkedList<InstruccionesMaquina>) concatenarInstrucciones(lista,cod_e2);
		   		if (esDesignador_2)
		   		{
		   			lista = (LinkedList<InstruccionesMaquina>) concatenarInstrucciones(lista, listaUnitaria(apila_ind()));
		   		}
		   		
		   		lista = (LinkedList<InstruccionesMaquina>) concatenarInstrucciones(lista, cod_op);
	   		return lista;
		}
		
		private List<InstruccionesMaquina> codigoOpAditivo(	List<InstruccionesMaquina> cod_1, List<InstruccionesMaquina> cod_2, List<InstruccionesMaquina> cod_op, boolean esDesignador_1, boolean esDesignador_2)
		{
			LinkedList<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				concatenarInstrucciones(lista,cod_1);
		   		if (esDesignador_1)
		   		{
		   			concatenarInstruccion(lista, apila_ind());
		   		}
		   		concatenarInstrucciones(lista, cod_2);
		   		if (esDesignador_2)
		   		{
		   			concatenarInstruccion(lista, apila_ind());
		   		}
		   		concatenarInstrucciones(lista, cod_op);
	   		return lista;
		}
		
		private List<InstruccionesMaquina> codigoOpMultiplicativo(	List<InstruccionesMaquina> cod_1, List<InstruccionesMaquina> cod_2, List<InstruccionesMaquina> cod_op, boolean esDesignador_1, boolean esDesignador_2)
		{
			LinkedList<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				concatenarInstrucciones(lista, cod_1);
		   		if (esDesignador_1)
		   		{
		   			concatenarInstruccion(lista, apila_ind());
		   		}
		   		concatenarInstrucciones(lista, cod_2);
		   		if (esDesignador_2)
		   		{
		   			concatenarInstruccion(lista, apila_ind());
		   		}
		   		concatenarInstrucciones(lista, cod_op);
		   	return lista;
		}
		
		private List<InstruccionesMaquina> codigoOpUnario(List<InstruccionesMaquina> cod_e, List<InstruccionesMaquina> cod_op, Boolean esDesignador_e)
		{
			LinkedList<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				concatenarInstrucciones(lista, cod_e);
				if (esDesignador_e)
				{
					concatenarInstruccion(lista, apila_ind());
				}
				concatenarInstrucciones(lista, cod_op);
			return lista;
		}
			
		/* 	PROCEDIMIENTOS */
		
		private List<InstruccionesMaquina> codigoPrologo(Integer tamDatos, Integer nivel)
		{
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();			
				// 1) Almacenar el antiguo display
				lista.add(apila_dir(0));
				lista.add(apila(2));
				lista.add(suma());
				lista.add(apila_dir(nivel + 1));
				lista.add(desapila_ind());
			
				// 2) Fijar el nuevo display
				lista.add(apila_dir(0));
				lista.add(apila(3));
				lista.add(suma());
				lista.add(desapila_dir(nivel + 1));
				
				// 3) Actualizar el CP
				lista.add(apila_dir(0));
				lista.add(apila(tamDatos + 2));
				lista.add(suma());
				lista.add(desapila_dir(0));	
			return lista;
		}
		
				
		private List<InstruccionesMaquina> codigoEpilogo(Integer tamDatos, Integer nivel)
		{
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();	
				// 1) Restaurar CP
				lista.add(apila_dir(nivel + 1));
				lista.add(apila(2));
				lista.add(resta());
				lista.add(apila_ind());
				
				// 2) Restaurar Display
				lista.add(apila_dir(nivel + 1));
				lista.add(apila(3));
				lista.add(resta());
				lista.add(copia());
				lista.add(desapila_dir(0));
				
				// 3) Recuperar Direccion de retorno
				lista.add(apila(2));
				lista.add(suma());
				lista.add(apila_ind());
				lista.add(desapila_dir(nivel + 1));
				lista.add(salto_ind());
			return lista;
		}
				
		private List<InstruccionesMaquina> codigoInicioLlamada()
		{
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				lista.add(apila_dir(0));
				lista.add(apila(3));
				lista.add(suma());
			return lista;
		}
				
		private List<InstruccionesMaquina> codigoFinLlamada(String nombreProcedure, TS ts, int direccionRetorno)
		{
			// Consultas a la TS
			int dirComienzo = ts.direccionDeIden(nombreProcedure);
			
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				lista.add(apila_dir(0));
				lista.add(apila(1));
				lista.add(suma());
				lista.add(apila(direccionRetorno));
				lista.add(desapila_ind());
				lista.add(salto(dirComienzo));
			return lista;
		}
			
		private List<InstruccionesMaquina> codigoPaso(String nombreProc, int numParametro, Boolean esDesignador, TS ts)
		{
			// Consultas a la TS
			List<Boolean> listaParametros = ts.tipoDeIden(nombreProc).params;
			boolean por_valor = listaParametros.get(numParametro - 1); 
			
			List<InstruccionesMaquina> lista = new LinkedList<InstruccionesMaquina>();
				if(esDesignador && por_valor) //!declarado var mayuscula
				{
					lista.add(apila_ind());
				}
				lista.add(desapila_ind());
				lista.add(apila(1));
				lista.add(suma());
			return lista;
		}
		
	// -------------------------------------------------------
	//			NUMERO DE INSTRUCCIONES
	// -------------------------------------------------------
	private Integer numeroIntruccionesActivacionProgramaPrincipal(){ return 5 ; } 
	private Integer numeroInstruccionesAccesoVar(TS ts, String iden){ return 3 + unoSiCierto(!ts.claseDeIden(iden).es_var); }
	private Integer numeroInstruccionesAsignacion(TS ts, String iden, Boolean esDesignador)
	{
		boolean es_PVAR = !ts.claseDeIden(iden).es_var;
		return 4 + unoSiCierto(esDesignador) + unoSiCierto(es_PVAR); 
	}
	private Integer numeroInstruccionesEscritura(TS ts, String iden){ return 5 + unoSiCierto(!ts.claseDeIden(iden).es_var); }
	private Integer numeroInstruccionesOpUnario(boolean esDesinador){ return 0 + unoSiCierto(esDesinador); }
	
	private Integer numeroInstruccionesPrologo(){ return 13; }
	private Integer numeroInstruccionesEpilogo(){ return 14; }
	private Integer numeroInstruccionesInicioLlamada(){ return 3; }
	private Integer numeroInstruccionesFinLlamada(){ return 6; }
	private Integer numeroInstruccionesCodigoPaso(String nombreProc, int numParametro, Boolean esDesignador, TS ts)
	{
		List<Boolean> listaParametros = ts.tipoDeIden(nombreProc).params;
		boolean por_valor = listaParametros.get(numParametro - 1); 
		
		boolean condicion = esDesignador && por_valor; 
		return 3 + unoSiCierto(condicion);
	}

		
	// -------------------------------------------------------
	//			CONSTRUCTORAS DE INSTRUCCIONES
	// -------------------------------------------------------
	private InstruccionesMaquina apila(int num){return InstruccionesMaquina.nueva_apila(num);}
	private InstruccionesMaquina apila_ind(){return InstruccionesMaquina.nueva_apila_ind();}
	private InstruccionesMaquina apila_dir(int d){return InstruccionesMaquina.nueva_apila_dir(d);}
	private InstruccionesMaquina desapila(){return InstruccionesMaquina.nueva_desapila();}
	private InstruccionesMaquina desapila_dir(int d){return InstruccionesMaquina.nueva_desapila_dir(d);}
	private InstruccionesMaquina desapila_ind(){return InstruccionesMaquina.nueva_desapila_ind();}
	
	private InstruccionesMaquina not(){return InstruccionesMaquina.nueva_not();}
	private InstruccionesMaquina eq() {return InstruccionesMaquina.nueva_eq();}
	private InstruccionesMaquina neq() {return InstruccionesMaquina.nueva_neq();}
	private InstruccionesMaquina gt() {return InstruccionesMaquina.nueva_gt();}
	private InstruccionesMaquina ge() {return InstruccionesMaquina.nueva_ge();}
	private InstruccionesMaquina lt() {return InstruccionesMaquina.nueva_lt();}
	private InstruccionesMaquina le() {return InstruccionesMaquina.nueva_le();}
	
	private InstruccionesMaquina suma(){return InstruccionesMaquina.nueva_suma();}
	private InstruccionesMaquina resta(){return InstruccionesMaquina.nueva_resta();}
	private InstruccionesMaquina or(){return InstruccionesMaquina.nueva_or();}
	private InstruccionesMaquina mul(){return InstruccionesMaquina.nueva_mul();}
	private InstruccionesMaquina div(){return InstruccionesMaquina.nueva_div();}
	private InstruccionesMaquina and(){return InstruccionesMaquina.nueva_and();}
	
	private InstruccionesMaquina salto(int d){return InstruccionesMaquina.nueva_salto(d);}
	private InstruccionesMaquina salto_falso(int d){return InstruccionesMaquina.nueva_salto_falso(d);}
	private InstruccionesMaquina salto_ind(){return InstruccionesMaquina.nueva_salto_ind();}
	
	@SuppressWarnings("unused")
	private InstruccionesMaquina debug(String m){return InstruccionesMaquina.nueva_debug(m);}
	private InstruccionesMaquina escribe(){return InstruccionesMaquina.nueva_escribe();}
	private InstruccionesMaquina copia(){return InstruccionesMaquina.nueva_copia();}
	private InstruccionesMaquina negar(){return InstruccionesMaquina.nueva_negar();}
	
}
