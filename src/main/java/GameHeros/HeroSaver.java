package GameHeros;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class HeroSaver {
    static Hero mage,rogue,warlock,paladin,hunter;

    public static void main(String[] args) {
    mage=new Mage();
    rogue=new Rogue();
    warlock=new Warlock();
    paladin=new Paladin();
    hunter=new Hunter();
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();
        try {
            FileWriter fileWriter = new FileWriter(".//Game Data//Models//Heros//Hunter.JSON");
            gson.toJson(hunter, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("can't find infoFile");
        }
    }
}
