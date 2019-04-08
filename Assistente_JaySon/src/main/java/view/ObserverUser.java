package view;
import com.pengrad.telegrambot.model.Update;
public interface ObserverUser {
	public Update update(long chatID, String userData);
}
