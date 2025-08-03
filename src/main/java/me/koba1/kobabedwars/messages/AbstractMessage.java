package me.koba1.kobabedwars.messages;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractMessage {
    protected String message;
    protected List<String> messageList;

    public AbstractMessage(String message) {
        this.message = message;
        this.messageList = new ArrayList<>();
    }

    public AbstractMessage(List<String> messageList) {
        this.messageList = messageList;
    }

    public MessageBuilder builder() {
        if (this.message == null) {
            return new MessageBuilder(this.messageList).setPrefix();
        }
        return new MessageBuilder(this.message).setPrefix();
    }
}
