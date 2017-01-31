package by.suboch.entity;

import java.util.List;
import java.util.Locale;

/**
 *
 */
public class Visitor {
    public enum Role {
        ADMIN, USER, GUEST
    }

    private Role role;
    private Locale locale;
    private String currentPage;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
