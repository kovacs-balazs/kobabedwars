package me.koba1.kobabedwars.files;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

public class FileValueUtils {

    private final KobaFile file;

    public FileValueUtils(KobaFile file) {
        this.file = file;
    }

    public String getOrSet(String path, String defaultValue, String... comments) {
        if(defaultValue == null) return null;
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getString(path, defaultValue);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

    public int getOrSet(String path, int defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getInt(path, defaultValue);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

    public boolean getOrSet(String path, boolean defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getBoolean(path, defaultValue);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

    public long getOrSet(String path, long defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getLong(path, defaultValue);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

    public double getOrSet(String path, double defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getDouble(path, defaultValue);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

    public List<String> getOrSet(String path, List<String> defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getStringList(path);
        } else {
            this.file.getConfig().set(path, defaultValue);
            this.file.getConfig().setComments(path, List.of(comments));
            this.file.save();
            return defaultValue;
        }
    }

//    public List<Long> getOrSet(String path, List<Long> defaultValue, String... comments) {
//        if (this.file.getConfig().contains(path, true)) {
//            return this.file.getConfig().getLongList(path);
//        } else {
//            this.file.getConfig().set(path, defaultValue);
//            this.file.getConfig().setComments(path, List.of(comments));
//            this.file.save();
//            return defaultValue;
//        }
//    }

//    public Location getOrSet(String path, Location defaultValue, boolean blockOnly, boolean yawPitch, String... comments) {
//        if (this.file.getConfig().contains(path, true)) {
//            return LocationUtils.parse(this.file.getConfig().getString(path));
//        } else {
//            this.file.getConfig().set(path, LocationUtils.toString(defaultValue, blockOnly, yawPitch));
//            this.file.getConfig().setComments(path, List.of(comments));
//            this.file.save();
//            return defaultValue;
//        }
//    }

//    public ItemStack getOrSet(String path, ItemStack defaultValue, String... comments) {
//        ItemStack is = new ItemStack(Material.STONE, 1);
//        ConfigurationSection section = this.file.getConfig().getConfigurationSection(path);
//        if(section != null) {
//            return Utils.getItemStack(section);
//        } else {
//            this.file.getConfig().set(path, XItemStack.serialize(is));
//            this.file.getConfig().setComments(path, List.of(comments));
//            this.file.save();
//            return defaultValue;
//        }
//    }

    public ConfigurationSection getOrSet(String path, Map<?, ?> defaultValue, String... comments) {
        if (this.file.getConfig().contains(path, true)) {
            return this.file.getConfig().getConfigurationSection(path);
        }
        this.file.getConfig().setComments(path, List.of(comments));
        return this.file.getConfig().createSection(path, defaultValue);
    }
}
