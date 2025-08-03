package me.koba1.kobabedwars.messages;

import me.koba1.kobabedwars.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Message extends AbstractMessage {

    private final String key;

    public Message(String key, String message) {
        super(message);
        this.key = key;
        load();
    }

    public Message(String key, List<String> messageList) {
        super(messageList);
        this.key = key;
        load();
    }

    public void load() {
        FileConfiguration config = Main.getInstance().getFileManager().getMessages().getConfig();

        if (config.contains(this.key)) {
            this.messageList = config.getStringList(this.key);
            if (this.messageList.isEmpty()) {
                this.message = config.getString(this.key);
            }
        } else {
            config.set(this.key, this.message != null ? this.message : this.messageList);
            Main.getInstance().getFileManager().getMessages().save();
        }
    }
}
