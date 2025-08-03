package me.koba1.kobabedwars.files;

import lombok.Getter;
import me.koba1.kobabedwars.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Getter
public class KobaFile {

    private static final Main m = Main.getPlugin(Main.class);
    private final File file;
    private FileConfiguration config;

    private final FileValueUtils valueLoader;

    public KobaFile(String ymlFile) {
        this.file = new File(m.getDataFolder(), ymlFile);
        setup();

        this.valueLoader = new FileValueUtils(this);
    }

    public KobaFile(String folder, String file) {
        this.file = new File(m.getDataFolder(), folder + File.separator + file);
        setup();

        this.valueLoader = new FileValueUtils(this);
    }

    public KobaFile(File file) {
        this(getPath(file));
    }

    public void setup() {
        //cfg = ymlFile;
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();

                InputStream in = m.getResource(getPath(file));
                FileOutputStream out = new FileOutputStream(file);

                if (in != null) {
                    try {
                        int n;
                        while ((n = in.read()) != -1) {
                            out.write(n);
                        }
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    }
                }

            } catch (IOException e) {
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("Can't save language file");
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String getPath(File file) {
        return file.getPath()
                .replace(m.getDataFolder().getPath() + File.separator, "");
               // .replace("\\", File.separator);
    }
}
