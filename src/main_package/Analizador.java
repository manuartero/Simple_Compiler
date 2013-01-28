package main_package;

import gramatica.GramaticaAtributos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import arbol.ConstructorArboles;

import lexico.AnalizadorLexico;
import maquinaPila.MaquinaVirtual;

public class Analizador 
{

	public static void main(String[] args)
	{
		try
		{
			if (args.length != 2) 
			{
				System.err.println("ERROR: Se precisa indicar el archivo fuente y el archivo objeto");
				System.exit(0);
			}

			InputStream input = new FileInputStream(args[0]);
			AnalizadorLexico analizadorLexico = new AnalizadorLexico(input);
			ConstructorArboles constructorArboles = new ConstructorArboles(analizadorLexico);
			GramaticaAtributos.Programa programa = constructorArboles.parse();
			Evaluador evaluador = new Evaluador();
			
			if (evaluador.evalua(programa.error()).val().hayError())
			{
				for (String e : programa.error().val().errores())
					System.out.println(e);
			} 
			else
			{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]));
				out.writeObject(evaluador.evalua(programa.cod()).val().toString());
				MaquinaVirtual mv = new MaquinaVirtual(programa.cod().val());
				mv.run();
				System.out.println("FIN");
			}
			
		}
		catch (Exception e)
		{
			System.err.println("ERROR:"+e);
	    	e.printStackTrace();
	        System.exit(1);
		}
	}

}
