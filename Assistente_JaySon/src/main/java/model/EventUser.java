package model;
public class EventUser {
	private String assunto;
	private String texto;
	public EventUser(String assunto, String texto) {
		this.assunto = assunto;
		this.texto = texto;
	}
	public String EventFormat() {
		String event =
				"Titulo:  " + this.assunto + "\n"
				+ " Descricao: " + this.texto;
		return event;
	}
	public String getAssunto() {
		return assunto;
	}
	public String getTexto() {
		return texto;
	}
}
