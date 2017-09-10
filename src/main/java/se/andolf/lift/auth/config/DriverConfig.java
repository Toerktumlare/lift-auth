package se.andolf.lift.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Thomas on 2017-06-10.
 */
@Component
@ConfigurationProperties(prefix = "driver.config")
public class DriverConfig {

    private String uri;
    private String name;
    private String index;
    private String username;
    private String password;

    private final Pool pool = new Pool();

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(final String index) {
        this.index = index;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pool getPool() {
        return pool;
    }

    public static class Pool {
        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(final int size) {
            this.size = size;
        }
    }
}
