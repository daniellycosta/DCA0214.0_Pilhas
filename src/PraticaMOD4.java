import java.util.LinkedList;
import java.io.*;


class Dado<X> {
	private X dado;
	public Dado(X x) {
		dado = x; }

	  public X getDado() { 
		  return dado; 
	}
}


class Pilha<X>{
	private LinkedList<X> conteudo;

	public Pilha() {
		conteudo = new LinkedList<>();
	}

	public empilha(Dado<X> x) {
		conteudo.addFirst(x);
	}

	public X desempilha() {
		return conteudo.removeFirst();
	}

	public boolean estaVazia() {
		return conteudo.isEmpty();
	}

	public X topo() {
		return conteudo.getFirst();
	}
	
	public String toString() {
		return null;
		
	}
}

class CalcRPN {
	// variaveis da instancia :
	// uma pilha para os calculos
	Pilha<Double> aPilha;
	Pilha<Operacao> hist;
	// construtor
	CalcRPN () {
		aPilha = new Pilha<>();
		hist = new Pilha<>();
	}
	// Adic~ao de dois elementos do topo da pilha
	void mais() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b+a;
		aPilha.empilha(res);
		//TEM QUE EMPILHAR TAMBEM EM HIST PARA TODAS AS OPERACOES
	}
	// Subtrac~ao de dois elementos do topo da pilha
	void menos() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b-a;
		aPilha.empilha(res);	}
	// Multiplicac~ao de dois elementos do topo da pilha
	void vezes() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b*a;
		aPilha.empilha(res);
	}
	// Divis~ao de dois elementos no topo da pilha
	void dividido() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b/a;
		aPilha.empilha(res);
	}
	// retorna o conteudo do topo da pilha
	Double resultado() {
		return aPilha.getFirst();
	}
	// interpretador de comandos
	void exec(String cmd) {
		throw new Error("a ser completado");
	}
}

class Operacao{
	private char code;
	private double a,b;

	public Operacao(double a) {
		this.code = 'e';
	}
	public Operacao(char code, double a, double b) {

	}
	public String toString() {
		return null;
		
	}
}
