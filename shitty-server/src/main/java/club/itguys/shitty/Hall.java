package club.itguys.shitty;

import java.util.List;

/**
 * @author sgyh
 */
public interface Hall extends UserSpace {

    void getHallSize();

    List<Room> getRooms();

    Room getRoom();

}
