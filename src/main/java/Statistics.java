import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

public class Statistics {
    private Scanner s = new Scanner(System.in);
    private HikariBuilder db = new HikariBuilder();

    public static void main(String[] args) throws SQLException {
        Statistics main = new Statistics();
        if(!configExists()) {
            System.out.println("Please input Database IP: ");
            String IP = main.s.nextLine();
            if(reachAble(IP)) {
                main.db.setIP(IP);
            } else {
                System.out.println("Invalid IP!");
                System.out.println("Please input a valid Database IP, or edit it later in the config.");
            }

            System.out.println("Please input Database Username:");
            main.db.setUsername(main.s.nextLine());
            System.out.println("Please input Database Password:");
            main.db.setPassword(main.s.nextLine());
            System.out.println("Successfully set Database.");
            System.out.println("Trying out connection...");
            if(main.db.buildConnection().getConnection() != null) {
                System.out.println("Successfully connected!");
            }
            System.out.println("Couldn't connect, please check your arguments.");
        }
    }

    private static boolean configExists(){
        return new File("config.txt").exists();
    }
    // Ping IP for connection.
    private static boolean reachAble(String IP){
        try {
            InetAddress host = InetAddress.getByName(IP);
            return host.isReachable(5000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
