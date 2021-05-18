import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class MyBot extends TelegramLongPollingBot {
    private long chat_id;
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public String getBotUsername() {
        return " @WhatHolidayTodayBot";
    }

    @Override
    public String getBotToken() {
        return "1847774223:AAEZ58Y7y-JwfMaEjzUSCqJUueePaezZk24";
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();

        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            sendMessage.setText(getMesssage(text));
            execute(sendMessage);
        } catch ( TelegramApiException e ) {
            e.printStackTrace();
        }
    }

    public String getMesssage(String msg) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        if (msg.equals("привет") || msg.equals("Привет") || msg.equals("Праздник")|| msg.equals("День")) {
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Какой сегодня праздник");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Нажимай на кнопку:)";
        }
        if (msg.equals("Какой сегодня праздник"))
            return getInfoCalendar();
        return msg;
    }

    public String getInfoCalendar() {
        Calendar calendar = new Calendar();
        String info = calendar.getDayToday()
                + "\n" + calendar.getHolidays();
        return info;
    }
}
