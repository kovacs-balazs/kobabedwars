package me.koba1.kobabedwars.messages;

import java.util.List;

public class CustomMessage extends AbstractMessage {

    public CustomMessage(String message) {
        super(message);
    }

    public CustomMessage(List<String> messageList) {
        super(messageList);
    }

    public boolean isNull() {
        return this.message == null && this.messageList.isEmpty();
    }
}
