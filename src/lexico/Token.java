package lexico;

public class Token {
	private String lexema;
	private CatLexica categoria;
	int fila;
	int col;

	public Token(int fila, int col, CatLexica categoria, String lexema) {
		this.fila = fila;
		this.col = col;
		this.categoria = categoria;
		this.lexema = lexema;
	}

	public Token(int fila, int col, CatLexica categoria) {
		this(fila, col, categoria, null);
	}

	public String getLexema() {
		return lexema;
	}

	public int getFila() {
		return fila;
	}

	public int getCol() {
		return col;
	}

	public CatLexica getCategoria() {
		return categoria;
	}

	public String toString() {
		return "<cat:" + categoria + "=> lex:" + lexema + ">";
	}
}
