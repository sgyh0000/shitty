package club.itguys.shitty.network;

import club.itguys.shitty.Session;
import club.itguys.shitty.dto.UserDTO;
import club.itguys.shitty.io.DefaultMessageReader;
import club.itguys.shitty.io.DefaultMessageWriter;
import club.itguys.shitty.io.MessageReader;
import club.itguys.shitty.io.MessageWriter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * @author sgyh
 */
public class DefaultSocketContext implements SocketContext {

    private Socket socket;
    private InetAddress inetAddress;

    private MessageReader messageReader;
    private MessageWriter messageWriter;

    private UserDTO user;
    private Long loginTime;

    private Session session;

    public DefaultSocketContext(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void init() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.messageReader = new DefaultMessageReader(objectInputStream);
        this.messageWriter = new DefaultMessageWriter(objectOutputStream);
    }

    @Override
    public Date getLoginTime() {
        return new Date(loginTime);
    }

    @Override
    public Long getTotalTime() {
        return System.currentTimeMillis() - loginTime;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public UserDTO getUser() {
        return user;
    }

    @Override
    public MessageReader getMessageReader() {
        return messageReader;
    }

    @Override
    public InetAddress getInetAddress() {
        return inetAddress;
    }

    @Override
    public MessageWriter getMessageWriter() {
        return messageWriter;
    }

    @Override
    public boolean isLogin() {
        return user != null;
    }
}
