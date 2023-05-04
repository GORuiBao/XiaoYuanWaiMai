package com.YR.reggie.common;

/**
 * ClassName: BaseContext
 * Description: 基于 ThreadLocal 封装工具类，用户保存和获取当前登录用户id
 * date: 2023/4/20 0020 13:24
 *
 * @author YR
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
