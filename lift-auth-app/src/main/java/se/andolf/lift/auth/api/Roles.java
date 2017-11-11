package se.andolf.lift.auth.api;

/**
 * @author Thomas on 2017-10-28.
 */
public enum Roles {

    USER("user"),
    ADMIN("admin");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
