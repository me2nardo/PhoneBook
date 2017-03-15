package org.rbo.security.jwt.model;

/**
 * Return user id token
 * @author vitalii.levash
 */
public class UserToken {
    private String id;

    public UserToken(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
