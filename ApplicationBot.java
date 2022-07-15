package org.example;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationBot extends TelegramLongPollingBot {

    public final String TOKEN = "5521472067:AAFxBmwqOTbSIly0-eFxsjafaR1L5NXuYV0";
    public final String USER = "MoneyConverter_1_bot";
    Map<String, String> flagmap = new HashMap<>();


    @Override
    public String getBotUsername() {

        return this.USER;
    }

    @Override
    public String getBotToken() {
        return this.TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        long chat_id = message.getChatId();
        if (message.hasText() && message.getText().equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Assalomu alaykum " + message.getFrom().getFirstName() + " botga xush kelibsiz !");
            sendMessage.setChatId(chat_id);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (message.hasText() && check(message.getText())) {
            String Ccy = getCcy(text).toUpperCase();
            Vayuta vayuta = Service(Ccy);
            double rate = Double.valueOf(vayuta.getRate());
            double summ = getSumm(text);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(getText(rate, summ, Ccy));
            sendMessage.setChatId(chat_id);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }


        }


    }

    public boolean check(String text) {
        if (text.length() > 4) {
            for (int i = 0; i < text.length() - 3; i++) {
                if (!(Character.isDigit(text.charAt(i)) || text.charAt(text.length() - 4) == ' ')) return false;
            }

            for (int i = text.length() - 3; i < 3; i++) {
                if (!Character.isLetter(text.charAt(i))) return false;
            }
        } else {
            return false;
        }

        return true;

    }

    public double getSumm(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) stringBuilder.append(text.charAt(i));
        }
        return Double.valueOf(stringBuilder.toString());
    }

    public String getCcy(String text) {
        return text.substring(text.length() - 3);
    }

    public Vayuta[] getValyutas() {
        Vayuta[] vayutas;
        try {
            Gson gson = new Gson();
            URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            Type type = new TypeToken<List<Vayuta>>() {
            }.getType();
            vayutas = gson.fromJson(bufferedReader, Vayuta[].class);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Vayuta uzs = new Vayuta(76, null, "UZS", null, null, null, null, null, "1", null, null);
        Vayuta[] arr = new Vayuta[vayutas.length + 1];
//        Arrays.copyOfRange(vayutas,0,vayutas.length);
//        arr[arr.length-1]=uzs;
        for (int i = 0; i < vayutas.length; i++) {
            arr[i] = vayutas[i];
        }
        arr[arr.length - 1] = uzs;


        return arr;
    }

    public Vayuta Service(String Ccy) {
        Vayuta[] valyutas = getValyutas();
        for (int i = 0; i < valyutas.length; i++) {
            if (valyutas[i].getCcy().equals(Ccy)) return valyutas[i];
        }

        return null;
    }

    public String flags(String Ccy) {
        flagmap.put("USD", "\uD83C\uDDFA\uD83C\uDDF8");
        flagmap.put("GBP", "\uD83C\uDDEC\uD83C\uDDE7");
        flagmap.put("UZS", "\uD83C\uDDFA\uD83C\uDDFF");
        flagmap.put("RUB", "\uD83C\uDDF7\uD83C\uDDFA");
        flagmap.put("JPY", "\uD83C\uDDEF\uD83C\uDDF5");
        flagmap.put("EUR", "\uD83C\uDDEA\uD83C\uDDFA");
        flagmap.put("UAH", "\uD83C\uDDFA\uD83C\uDDE6");
        flagmap.put("CAD", "\uD83C\uDDE8\uD83C\uDDE6");
        return flagmap.get(Ccy);
    }

    public String getText(double rate, double summ, String Ccy) {
        Vayuta[] vayutas = getValyutas();
        String text =
                flags(Ccy) + summ + " " + Ccy + "\n" +
                        "========================\n" +
                        "\uD83D\uDCB1" + round(summ * rate) + " " + "UZS" + "\n" +
                        flags(vayutas[0].getCcy()) + round((summ * rate) / Double.valueOf(vayutas[0].getRate())) + " " + vayutas[0].getCcy() + "\n" +
                        flags(vayutas[1].getCcy()) + round((summ * rate) / Double.valueOf(vayutas[1].getRate())) + " " + vayutas[1].getCcy() + "\n" +
                        flags(vayutas[2].getCcy()) + round((summ * rate) / Double.valueOf(vayutas[2].getRate())) + " " + vayutas[2].getCcy() + "\n" +
                        flags(vayutas[3].getCcy()) + round((summ * rate) / Double.valueOf(vayutas[3].getRate())) + " " + vayutas[3].getCcy() + "\n" +
                        flags(vayutas[4].getCcy()) + round((summ * rate) / Double.valueOf(vayutas[4].getRate())) + " " + vayutas[4].getCcy() + "\n\n" +
                        "\uD83E\uDD16" + " @" + this.USER;
        ;


        return text;
    }

    public double round(double value) {
        double s=value*100;
        int i=(int) s;
        return s/100.0;
    }
}
