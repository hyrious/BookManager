package bkmgr;

import lombok.Data;

public @Data class User {
    private Boolean canBorrow     = true;
    private Boolean canManageBook = false;
    private Boolean canManageUser = false;
    private Integer id            = 1;
    private String  name          = null;
    private String  password      = null;
    public User(Integer id, String name, String password, Boolean canBorrow, Boolean canManageBook,
            Boolean canManageUser) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.canBorrow = canBorrow;
        this.canManageBook = canManageBook;
        this.canManageUser = canManageUser;
    }
    public User(Integer id, String name, String password, Integer permission) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.canBorrow = (permission & 1) == 1;
        this.canManageBook = (permission & 2) == 2;
        this.canManageUser = (permission & 4) == 4;
    }
    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }
    public final int getPermission() {
        return (canBorrow ? 1 : 0) | (canManageBook ? 2 : 0) | (canManageUser ? 4 : 0);
    }
}
