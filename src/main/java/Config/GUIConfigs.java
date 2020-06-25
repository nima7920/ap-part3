package Config;

import java.awt.*;

public class GUIConfigs extends ConfigRepository {

    public GUIConfigs(){
        initialize("properties\\GUI");
    }
    public int getInt(String configName,String name){
        return configs.get(configName).readInt(name);
    }

    public String getString(String configName,String name){
        return configs.get(configName).getProperty(name);
    }

    public Dimension getSize(String configName, String name) {
        return configs.get(configName).readDimension(name);
    }

    public Rectangle getBounds(String configName, String name) {
        return configs.get(configName).readRectangle(name);
    }

    public Point getPoint(String configName, String name) {
        return configs.get(configName).readPoint(name);
    }

    public Color getColor(String configName,String name){
        return configs.get(configName).readColor(name);
    }
    public Font getFont(String configName,String name){
        return configs.get(configName).readFont(name);
    }
}
