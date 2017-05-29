package bkmgr;

import lombok.Data;

public @Data class User {
    private Integer id = 1;
    private String name;
    private String password; // encrypted
    private Integer permission = 1;
    private final static Integer BORROW = 1;
    private final static Integer MNG_BOOK = 2;
    private final static Integer MNG_USER = 4;

    public User(Integer id, String name, String password, Integer permission) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public User(Integer id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        super();
        this.name = name;
    }

    public boolean can(Integer i) {
        return (permission & i) == i;
    }

    public boolean canBorrow() {
        return can(BORROW);
    }

    public boolean canManageBook() {
        return can(MNG_BOOK);
    }

    public boolean canManageUser() {
        return can(MNG_USER);
    }

}
