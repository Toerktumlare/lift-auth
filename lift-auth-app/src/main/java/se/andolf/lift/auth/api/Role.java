package se.andolf.lift.auth.api;

/**
 * @author Thomas on 2017-10-28.
 */
public class Role {

    private final Roles value;

    public Role(Roles value) {
        this.value = value;
    }

    public Roles getValue() {
        return value;
    }
}
