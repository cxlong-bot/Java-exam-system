package com.webstudy.listener;

import com.webstudy.util.JDBCutil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: long
 * @date: 2021/3/27 13:49
 * @description: 全局作用域对象监听器
 */
public class OneListener implements ServletContextListener {
    //在Tomcat启动时，利用监听器预先创建20个Connection
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JDBCutil util = new JDBCutil();
        Map map = new HashMap();
        for(int i=1;i<20;i++){
            Connection con = util.getConnection();
            map.put(con,true);
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1",map);
        System.out.println("成功创建20个Connection");
    }

    //销毁map中的Connection
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            Connection con = (Connection) it.next();
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("20个Connection已关闭");
    }
}
