package club.itguys.shitty.network;

import club.itguys.shitty.Session;
import club.itguys.shitty.dto.UserDTO;
import club.itguys.shitty.io.MessageReader;
import club.itguys.shitty.io.MessageWriter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * @author sgyh
 */
public interface SocketContext {

    void init() throws IOException;

    MessageReader getMessageReader();

    MessageWriter getMessageWriter();

    InetAddress getInetAddress();

    Date getLoginTime();

    Long getTotalTime();

    Session getSession();

    UserDTO getUser();

    boolean isLogin();

}
