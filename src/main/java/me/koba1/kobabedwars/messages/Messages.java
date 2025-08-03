package me.koba1.kobabedwars.messages;

import lombok.Getter;

@Getter
public enum Messages {

    PREFIX(new Message("prefix", "&8[&eDynamic&aWarps&8]")),
    NO_PERMISSION(new Message("no_permission", "%prefix% &cYou dont have permission.")),
    ONLY_PLAYER_EXECUTE(new Message("only_player_execute", "%prefix% &cThis command only execute by player.")),
    RELOADED(new Message("reloaded", "%prefix% &aConfiguration files successfully reloaded.")),
    TARGET_NOT_FOUND(new Message("target_not_found", "%prefix% &cTarget not found.")),
    ;

    private final Message message;

    Messages(Message message) {
        this.message = message;
    }

    public MessageBuilder builder() {
        return message.builder();
    }

    public static void reload() {
        for (Messages value : values()) {
            value.getMessage().load();
        }
    }
}
