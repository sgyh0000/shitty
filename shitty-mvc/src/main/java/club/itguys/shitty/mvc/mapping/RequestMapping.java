package club.itguys.shitty.mvc.mapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sgyh
 */
public interface RequestMapping {

    void mapping(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
