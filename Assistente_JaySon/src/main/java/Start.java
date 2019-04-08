import model.*;
import view.*;
import controller.*;
import java.util.List;
import java.io.IOException;
import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Start {
	public static void main(String[] args) throws JSONException, ParseException, IOException{
		//Criação do objeto Bot com as informações de acesso
		TelegramBot bot = TelegramBotAdapter.build("780392770:AAEu1SsvTJUWd9QysdllyzQfLJvFaXUPNMQ");
		// https://www.getpostman.com/
		//objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;
		//objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
		//objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;
		//Controle de off-set, isto é, a partir desse ID  será lido as mensagens pendentes de um off-set (limite inicial)
		int m = 0;
		//loop infinito pode ser alterado por algum timer de intervalo curto
		FileReader reader = new FileReader("C:\\Space\\response.json");
		JSONParser parser = new JSONParser();
		JSONObject response = null;
		try{
			response = (JSONObject) parser.parse(reader);
		} catch (FileNotFoundException e) {
            e.printStackTrace();
		}
		while(true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			List<Update> updates = updatesResponse.updates();
			for( Update update : updates ) {
				m = update.updateId() + 1;
				System.out.println("Recebendo Mensagem: " + update.message().text());
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				System.out.println("Resposta de Chat Action Enviada: " + baseResponse.isOk());
				String resp;
				if ( !(response.get(update.message().text()) == null) ) {
					resp = response.get(update.message().text()).toString();
				} else {
					resp = "nao entendi?";
				}				
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), resp));
				System.out.println("Mensagem enviada: " + sendResponse.isOk());
			}
		}
	}
}
