package se.andolf.lift.auth.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import se.andolf.lift.auth.api.Role;

import java.util.List;

/**
 * @author Thomas on 2017-07-16.
 */
@Document(collection = "User")
public class UserEntity {

    @Id
    private String id;
    private String userName;
    private boolean active;
    private List<Role> roles;
    private String password;

    @PersistenceConstructor
    private UserEntity(String id, String userName, boolean active, List<Role> roles, String password) {
        this.id = id;
        this.userName = userName;
        this.active = active;
        this.roles = roles;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isActive() {
        return active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }
}
