package net.vergessxner.ttt.utils.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * @author Jonas
 * Created: 03.01.2021
 * Class: ConfigLoader
 */

public class ConfigLoader<T> {

    public static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private final File configFile;

    private Class<T> clazz;

    private T config;


    public ConfigLoader(File file, Class<T> type) {
        this.configFile = file;
        this.clazz = type;

        if(!configFile.exists()) try {
            throw new FileNotFoundException("Config File in ConfigLoader cannot found!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            config = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.configFile));

            this.config = PRETTY_GSON.fromJson(reader, clazz);
            if(config == null) {
                set(clazz.newInstance());
            }
            reader.close();

        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

    }

    public void set(T instance) {
        try {
            if (!this.configFile.exists())
                configFile.createNewFile();

            Writer writer = new FileWriter(this.configFile);

            PRETTY_GSON.toJson(instance, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        load();

    }

    public static Gson getPrettyGson() {
        return PRETTY_GSON;
    }

    public File getConfigFile() {
        return configFile;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T getConfig() {
        return config;
    }

}
