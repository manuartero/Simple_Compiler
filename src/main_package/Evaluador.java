package main_package;

import gramatica.Atributo;

import java.util.Stack;

public class Evaluador {
	private final static boolean DEBUG = false;
  @SuppressWarnings("rawtypes")
public Evaluador() {
     atributos = new Stack<Atributo>();  
  }
  @SuppressWarnings("rawtypes")
public <Val> Atributo<Val> evalua(Atributo<Val> atributo) {
   atributos.push(atributo);
   while (! atributos.isEmpty()) {
     Atributo top = atributos.peek();
     Atributo newTop = top.siguienteDependencia();
     if (newTop == null) {
        if ( DEBUG )//(Config.DEBUG) 
           System.err.print("· "+top.descripcion());
        top.calcula();
        if ( DEBUG )// (Config.DEBUG)
           System.err.println(top.val()); 
        atributos.pop();
     }
     else 
        atributos.push(newTop);   
   }
   return atributo;
  }    
 @SuppressWarnings("rawtypes")
private Stack<Atributo> atributos;      
}
