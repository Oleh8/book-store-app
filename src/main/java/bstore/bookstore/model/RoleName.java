package bstore.bookstore.model;

public enum RoleName {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RoleName getRole(String roleName) {
        for (RoleName name : RoleName.values()) {
            if (name.roleName.equals(roleName)) {
                return name;
            }
        }
        return null;
    }
}
