import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariBuilder {

    private HikariConfig config = new HikariConfig();

    private String IP;
    private String username;
    private String password; // Maybe store it as char[] for added security?

    HikariBuilder setIP(String IP){
        this.IP = IP;
        return this;
    }


    HikariBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    HikariBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    HikariDataSource buildConnection(){
        config.setJdbcUrl(this.IP);
        config.setUsername(this.username);
        config.setPassword(this.password);
        return new HikariDataSource(config);
    }


}
