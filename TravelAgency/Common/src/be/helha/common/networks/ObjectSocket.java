package be.helha.common.networks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Object socket manager
 */
public class ObjectSocket implements AutoCloseable{
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ObjectSocket(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Writes the object
     * @param object written object
     * @throws IOException management of input/output exceptions.
     */
    public void write(Object object) throws IOException {
        out.reset();
        out.writeObject(object);
        out.flush();
    }

    /**
     * Reads an object
     * @param <T> generic parameter
     * @return Read object
     * @throws IOException management of input/output exceptions.
     * @throws ClassNotFoundException occurs when the JVM tries to load a particular class but does not find it in the classpath
     */
    public <T> T read() throws IOException, ClassNotFoundException {
        return (T) in.readObject();
    }

    @Override
    public void close() throws Exception {
        out.close();
        in.close();
    }
}
