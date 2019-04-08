package model;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Note {
	private User user;
	private Calendar date;
	private EventUser event;
	private String os_home;
	public Note(User user, Calendar date, EventUser event) {
		this.user = user;
		this.date = date;
		this.event = event;
	}
	public String getNote(User user){
		String noteStr =
				"Usuario: " + this.user.getName() + "\n"
				+ "Data: " + this.getDate() + "\n"
				+ "Evento: " + this.event.EventFormat()
		;
		return noteStr;
	}
	public String getNoteFormat(User user) {
		return null;
	}
	public boolean saveNote() {
		// System.getProperty("user.home")
		try {
			File arquivo = new File(this.os_home + "\\user_notes\\usuario1_" + this.user.getChatID());
			if ( !arquivo.exists() ) { arquivo.mkdirs(); }
			new Json().save(this.os_home + "\\user_notes\\usuario1_" + this.user.getChatID() + "\\" + this.getDateForFile() + ".json", this);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public User getUser() {
		return user;
	}
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
		String date = format.format(this.date.getTime());
		return date;
	}
	public String getDateForFile() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmm");
		String date = format.format(this.date.getTime());
		return date;
	}
	public EventUser getEvent() {
		return event;
	}
	public String getOs_home() {
		return os_home;
	}
	public void setOs_home(String os_home) {
		this.os_home = os_home;
	}
}
