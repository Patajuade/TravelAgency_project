package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;

import java.io.IOException;

public class DisconnectMessage extends AbstractMessage {

    @Override
    public void accept(Visitor v) throws IOException {
        v.visitDisconnectMessage(this);
    }
}
