import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot {
    public static void main(String[] args)  {
        ApiContextInitializer.init();
        TelegramBotsApi telegram=new TelegramBotsApi();

        MyBot bot=new MyBot();
        try{
            telegram.registerBot(bot);
        } catch ( TelegramApiRequestException e ){
            e.printStackTrace();
        }
    }
}
