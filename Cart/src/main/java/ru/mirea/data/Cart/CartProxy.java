package ru.mirea.data.Cart;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CartProxy implements InvocationHandler {
    Object obj;
    public CartProxy (Object f1)
    {obj = f1;}

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke: " + method.getName());
        return method.invoke(obj, args);
    }
}
