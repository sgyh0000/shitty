package club.itguys.shitty.server;

import club.itguys.shitty.db.BeanUtils;
import club.itguys.shitty.db.ConnectionPool;
import club.itguys.shitty.db.TestDao;
import club.itguys.shitty.db.TestDaoImpl;

import java.sql.Connection;
import java.util.*;

/**
 * @author sgyh
 */
public class Main {

    public static void main(String[] args) {
//        commandParse(args);
        try {
            TestDao testDaoImpl = BeanUtils.getBean(TestDaoImpl.class);
            testDaoImpl.testSQL("SELECT 1;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void commandParse(String[] args) {
        String host = "localhost";
        int port = 8888;
        // default
        if (args.length == 0) {
            init(host, port);
        }
        // help
        if (args.length == 1) {
            if ("-h".equals(args[0]) || "--help".equals(args[0])) {
                help();
                return;
            }
        }
        for (int i = 0; i < args.length; i++) {
            if ("-H".equals(args[i])) {

            }
        }
    }

    private static void init(String host, int port) {
        System.out.println("Starting");
        System.out.println(String.format("Start server at %s:%d", host, port));
    }

    private static void help() {
        System.out.println("" +
                "java Main [option]\n" +
                "option can be:\n" +
                "\t-h        help\n" +
                "\t--help    help\n" +
                "\t-H        [host]\n" +
                "\t          start at host that u input, default localhost\n" +
                "\t-P        [port]\n" +
                "\t          start application with the port, default 8888");
    }

}
