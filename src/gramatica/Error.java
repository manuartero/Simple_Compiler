package gramatica;

import java.util.LinkedList;
import java.util.List;

public class Error {
	private boolean error;
	private List<String> errors;

	public Error() {
		this.error = false;
		this.errors = new LinkedList<String>();
	}

	public Error(String msg) {
		this.error = true;
		this.errors = new LinkedList<String>();
		errors.add(msg);
	}

	public Error(Error e1, Error e2) {
		this.error = e1.hayError() || e2.hayError();
		this.errors = new LinkedList<String>(e1.errores());
		this.errors.addAll(e2.errores());
	}

	public boolean hayError() {
		return error;
	}

	public List<String> errores() {
		return errors;
	}

	public String toString() {
		return "{error: " + error + ", mensajes: " + errors + "}";
	}

}