package club.itguys.shitty.dto;

import lombok.*;

import java.io.Serializable;

import static club.itguys.shitty.constants.StatusCode.*;

/**
 * @author sgyh
 */
@Getter
@ToString
@EqualsAndHashCode
public class MessageDTO<T> implements Serializable {

    private static final long serialVersionUID = -6870023416135073457L;
    @Setter
    private String cookie;
    private Integer code;
    private String msg;
    private T data;

    public MessageDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static MessageDTO<Exception> exception(Exception ex) {
        return new MessageDTO<>(EXCEPTION, "服务器异常: " + ex.getMessage(), ex);
    }

}
