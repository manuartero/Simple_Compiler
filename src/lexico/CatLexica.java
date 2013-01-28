package lexico;

public enum CatLexica {
	NUM,
	IDEN,
	COMA 				{ public String toString(){return ",";} },
	PARENTESIS_ABIERTO	{ public String toString(){return "(";} },
	PARENTESIS_CERRADO	{ public String toString(){return ")";} },
	LLAVE_ABIERTA		{ public String toString(){return "{";} },
	LLAVE_CERRADA		{ public String toString(){return "}";} },
	DECS				{ public String toString(){return "DECS";} },
	END					{ public String toString(){return "END";} },
	BODY				{ public String toString(){return "BODY";} },
	SET					{ public String toString(){return "SET";} },
	TO					{ public String toString(){return "TO";} },
	IF					{ public String toString(){return "IF";} },
	THEN				{ public String toString(){return "THEN";} },
	ELSE				{ public String toString(){return "ELSE";} },
	OUT					{ public String toString(){return "OUT";} },
	IGUAL				{ public String toString(){return "=";} },
	MAS 				{ public String toString(){return "+";} }, 
	POR					{ public String toString(){return "*";} },
	NOT					{ public String toString(){return "NOT";} },
	
	CALL				{ public String toString(){return "CALL";} },
	WITH				{ public String toString(){return "WITH";} },
	PROC				{ public String toString(){return "PROC";} },
	VAR					{ public String toString(){return "VAR";} },
	MENOS				{ public String toString(){return "-";} },
	OR					{ public String toString(){return "OR";} },
	AND					{ public String toString(){return "AND";} },
	DIV					{ public String toString(){return "/";} },
	MAYOR				{ public String toString(){return ">";} },
	MENOR				{ public String toString(){return "<";} },
	
	MAYOR_IGUAL			{ public String toString(){return ">=";} },
	MENOR_IGUAL			{ public String toString(){return "<=";} },
	DISTINTO			{public String toString(){return "=/=";} },
	
	EOF
}
