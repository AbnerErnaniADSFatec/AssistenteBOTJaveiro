package model;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
public class Json {
	public boolean save(String file_system, Note note) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonEvent = new JSONObject();
		FileWriter writer = null;
		jsonObject.put("User", note.getUser().getName());
		jsonObject.put("Date", note.getDate());
		jsonEvent.put("Titulo", note.getEvent().getAssunto());
		jsonEvent.put("Descricao", note.getEvent().getTexto());
		jsonObject.put("Event", jsonEvent);
		try {
			writer = new FileWriter(file_system);
			writer.write(jsonObject.toString());
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return true;
	}
	public boolean edit(String file_system, Note note) {
		return false;
	}
	public boolean dell(String file_system) {
		return false;
	}
	public List<Json> list() {
		return new ArrayList<Json>();
	}
}
