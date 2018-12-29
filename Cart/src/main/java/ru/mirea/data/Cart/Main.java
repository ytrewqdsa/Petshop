package ru.mirea.data.Cart;

import ru.mirea.data.Cart.Interface.ICart;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String []args){
        CartApplication cartApplication = new CartApplication();
        ICart cartProxy = (ICart) Proxy.newProxyInstance(CartApplication.class.getClassLoader(), CartApplication.class.getInterfaces(),
                new CartProxy(cartApplication));
        cartProxy.getBalance(1);
    }

}
