package be.helha.common.messages;

import be.helha.common.interfaces.Visitor;

import java.io.IOException;
import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {
    public abstract void accept(Visitor v) throws IOException;
}
