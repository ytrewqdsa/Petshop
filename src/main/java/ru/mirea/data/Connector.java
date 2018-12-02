package ru.mirea.data;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Connector {

    List<Connection> ConnectionList = new ArrayList<>();
    Connection connection1 = null;
    Connection connection2 = null;
    Connection connection3 = null;
    Connection connection4 = null;
    Connection connection5 = null;

    @PostConstruct
    public void init(){
        ConnectionList.add(0, connection1);
        ConnectionList.add(1, connection2);
        ConnectionList.add(2, connection3);
        ConnectionList.add(3, connection4);
        ConnectionList.add(4, connection5);

    }

    public Connection get() {
        if(ConnectionList.isEmpty())
            return null;
        else
            return ConnectionList.get(0);

    }

    public void give(Connection connection){
        ConnectionList.add(connection);
    }

}
