package org.example.bot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;


public class StartCommand extends BotCommand {
    public StartCommand(){
        super("start","start descr");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage msg = SendMessage.builder()
                .chatId(chat.getId())
                .text("Choose formula to calculate")
                .build();



        KeyboardRow kr1 = new KeyboardRow();
        kr1.addAll(Arrays.asList("Discriminant", "Gerona", "Difficult %", "Pifagor"));

        KeyboardRow kr2 = new KeyboardRow();
        kr2.addAll(Arrays.asList("Cosinus Teorem", "Sinus Teorem"));

        ReplyKeyboardMarkup km = ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(kr1, kr2))
                .build();

        msg.setReplyMarkup(km);
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
