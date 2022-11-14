package platform.szxyzxx.web.schoolaffair.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@RequestMapping("/ceshilianjie")
public class CeshiLianjieController {
    @RequestMapping("/awasd")
    public void findByasda(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://10.191.113.30:1433;DatabaseName=xiaoxue";
        String userName="User_1";            //sqlserver用户名
        String userPwd="Yihao888";    //sqlserver用户密码
        try{
            Class.forName(driverName);   //加载sqlserver的驱动类
            System.out.println("加载SQLServer驱动类成功!");
        }
        catch(ClassNotFoundException a){
            System.out.println("加载SQLServer驱动失败!");
            a.printStackTrace();
        }
        Connection dbcon=null;           //处理与特定数据库的连接
        try{
            dbcon= DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("数据库连接成功!");
            dbcon.close();
        }
        catch(SQLException e){
            System.out.println("数据库连接失败!");
            e.printStackTrace();
        }
    }

    }
