package org.example.bot;

import org.example.bot.commands.StartCommand;
import org.example.math.FormulaCalculator;
import org.example.math.Formula;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class MathBot extends TelegramLongPollingCommandBot {
    public Map<Long, Formula> formulasMap = new HashMap<>();

    public MathBot() {
        register(new StartCommand());
    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {

            Long chatId = update.getMessage().getChatId();
            SendMessage msg = new SendMessage();
            msg.setChatId(chatId);

            String text = update.getMessage().getText();
            try {
                formulasMap.put(chatId, Formula.valueOf(text));
            } catch (Exception ex) {
                text = text + ", ";
            }
            Formula formula = formulasMap.get(chatId);

            if (text.matches("(.=\\d*, )*")) {

                String[] strings = text.split(", ");

                double[] numbers = new double[strings.length];

                for (int i = 0; i < strings.length; i++) {
                    String s = strings[i].substring(2);
                    numbers[i] = Double.parseDouble(s);
                }

                assert formula != null;
                String calculated = FormulaCalculator.calcFormula(formula, numbers);
                msg.setText(calculated);
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                askNumber(formula, update.getMessage().getChatId());
            }
        }
    }

    public void askNumber(Formula formula, long chatId) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);

        switch (formula) {
            case Discriminant:
                msg.setText("Print numbers like this:'a=9, b=12, d=34'");
                break;
            case Gerona:
                msg.setText("Print numbers like this:'a=45, b=12, d=34'\n" +
                        "a = 1side,b = 2side,c = 3 side");
                break;
            case DifficultPer:
                msg.setText("Print numbers like this:'a=4, b=12, d=34'\n" +
                        "a = start value,b = %rate,c = period count");
                break;
            case Pifagor:
                msg.setText("Print numbers like this:'a=9, b=12'\n" +
                        "a = 1catet,b = 2catet");
                break;
            case Cosinus_Teorem:
                msg.setText("Print numbers like this:'a=12, b=12'\n" +
                        "a = 1side,b = 2side");
                break;
            case Sinus_Teorem:
                msg.setText("Print numbers like this:'a=9, b=13'\n" +
                        "a = 1side,b = 2side");
                break;
        }
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
