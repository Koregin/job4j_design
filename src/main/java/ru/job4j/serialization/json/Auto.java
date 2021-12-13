package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.util.Arrays;

public class Auto {
    private final boolean isOld;
    private final int power;
    private final String brand;
    private final String[] specifications;
    private final Contact contact;

    public Auto(boolean isOld, int power, String brand, String[] specifications, Contact contact) {
        this.isOld = isOld;
        this.power = power;
        this.brand = brand;
        this.specifications = specifications;
        this.contact = contact;
    }

    public boolean isOld() {
        return isOld;
    }

    public int getPower() {
        return power;
    }

    public String getBrand() {
        return brand;
    }

    public String[] getSpecifications() {
        return specifications;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "isOld=" + isOld
                + ", power=" + power
                + ", brand='" + brand + '\''
                + ", specifications=" + Arrays.toString(specifications)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) {
        final Auto kiaCeed = new Auto(true, 129, "Kia",
                new String[] {"черный металлик", "автомат", "бензин", "хэтчбек 5 дв."},
                new Contact("(123) 456-78-90"));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(kiaCeed));

        final String kiaCeedJson =
                "{"
                        + "\"isOld\":true,"
                        + "\"power\":129,"
                        + "\"brand\":\"Kia\","
                        + "\"specifications\":"
                        + "[\"черный металлик\",\"автомат\",\"бензин\",\"хэтчбек 5 дв.\"],"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"(123) 456-78-90\""
                        + "}"
                + "}";
        final Auto kiaCeedFromString = gson.fromJson(kiaCeedJson, Auto.class);
        System.out.println(kiaCeedFromString);

        /* Преобразование в jsonObject */
        JSONObject jsonAuto = new JSONObject();
        jsonAuto.put("isOld", kiaCeed.isOld());
        jsonAuto.put("power", kiaCeed.getPower());
        jsonAuto.put("brand", kiaCeed.getBrand());
        jsonAuto.put("specifications", kiaCeed.getSpecifications());
        jsonAuto.put("contact", kiaCeed.getContact());
        System.out.println(jsonAuto.toString());
        /* Преобразование объекта kiaCeed в json-строку */
        System.out.println(new JSONObject(kiaCeed));

    }
}
