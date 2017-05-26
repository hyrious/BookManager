package bkmgr;

public class User {
    private Integer id = null;
    private String name = null;
    private Integer permission = null;

    User(Integer id, String name, Integer permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPermission() {
        return permission;
    }

    public boolean can(Integer i) {
        return (permission & i) == i;
    }

    public boolean canBorrow() {
        return can(1);
    }

    public boolean canManageBook() {
        return can(2);
    }

    public boolean canManageUser() {
        return can(4);
    }

    public static void main(String[] args) {
        
    }
}
