package bkmgr.controller;
public abstract class ControllerBase {
    public void init() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
