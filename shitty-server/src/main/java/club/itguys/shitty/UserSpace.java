package club.itguys.shitty;

import club.itguys.shitty.dto.UserDTO;

import java.util.List;
import java.util.Queue;

/**
 * @author sgyh
 */
public interface UserSpace {

    void init();

    List<UserDTO> getAllUser();

    void addUser(UserDTO user);

    void remove(UserDTO user);

    void removeAll();

    Queue<String> messages();

    void setQueueSize(int size);

    void clearMessages();

}
