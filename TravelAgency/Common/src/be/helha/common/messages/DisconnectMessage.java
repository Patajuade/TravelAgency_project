package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;

import java.io.IOException;

/**
 * Disconnect request
 */
public class DisconnectMessage extends AbstractMessage {

    @Override
    public void accept(Visitor v) throws IOException {
        v.visitDisconnectMessage(this);
    }
}
