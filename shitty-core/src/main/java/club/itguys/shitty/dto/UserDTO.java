package club.itguys.shitty.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author sgyh
 */
@Getter
@ToString
@EqualsAndHashCode
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 224027527129091667L;

    private String userName;
    private Integer totalRaces;
    private Integer winRaces;
    private Integer loseRaces;
    /**
     * 胜率
     */
    private String winRate;

    public UserDTO(String userName, Integer totalRaces, Integer winRaces, Integer loseRaces) {
        this.userName = userName;
        this.totalRaces = totalRaces;
        this.winRaces = winRaces;
        this.loseRaces = loseRaces;
        this.winRate = calculateWinRate();
    }

    public synchronized void win() {
        this.winRaces += 1;
        this.totalRaces += 1;
        this.winRate = calculateWinRate();
    }

    public synchronized void lose() {
        this.loseRaces += 1;
        this.totalRaces += 1;
        this.winRate = calculateWinRate();
    }

    private String calculateWinRate() {
        double winRate = ((double) winRaces) / ((double) totalRaces) * 100;
        return String.format("%.2f%s", winRate, "%");
    }

}
