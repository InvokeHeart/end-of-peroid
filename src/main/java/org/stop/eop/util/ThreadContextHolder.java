package org.stop.eop.util;

public class ThreadContextHolder {
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void setLocal(Integer value) {
        local.set(value);
    }

    public static void remove() {
        local.remove();
    }

    public static Integer get() {
        return local.get();
    }
}
