package Config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public abstract class ConfigRepository {

    protected HashMap<String,Configs> configs;
    protected File repositoryDirectory;

    public void initialize(String directory){
        configs=new HashMap<>();
        repositoryDirectory = new File(directory);
        for (File file : repositoryDirectory.listFiles()) {
            try{
                FileReader fileReader =new FileReader(file);
                Configs config=new Configs();
                config.load(fileReader);
                configs.put(file.getName(),config);
            }catch(IOException e){

            }
        }
    }

    public String readString(String configName,String name){
        return configs.get(configName).getProperty(name);
    }
    public int readInt(String configName,String name){
        return configs.get(configName).readInt(name);
    }

}

