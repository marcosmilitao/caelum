package domain;

public final class Capitulo{
	
	private final String titulo;
	public Capitulo(String titulo, String conteudoHTML) {
		this.titulo = titulo;
		this.conteudoHTML = conteudoHTML;
	}
	private final String conteudoHTML;
	
	public String getTitulo() {
		return titulo;
	}
	public String getConteudoHTML() {
		return conteudoHTML;
	}

}
