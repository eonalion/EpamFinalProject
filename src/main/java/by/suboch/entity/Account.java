package by.suboch.entity;

import by.suboch.command.VisitorRole;

import java.util.Objects;

/**
 *
 */
public class Account {
    private int accountId;
    private boolean adminRights;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private byte[] avatar;
    private VisitorRole role;

    public Account() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, adminRights, firstName, lastName, login, password, email);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public VisitorRole getRole() {
        return role;
    }

    public void setRole(VisitorRole role) {
        this.role = role;
    }
}
