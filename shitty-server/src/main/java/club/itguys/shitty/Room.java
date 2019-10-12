package club.itguys.shitty;

import club.itguys.shitty.dto.UserDTO;

import java.util.List;
import java.util.Queue;

/**
 * @author sgyh
 */
public interface Room extends UserSpace {

    int getId();

    void reset();

}
