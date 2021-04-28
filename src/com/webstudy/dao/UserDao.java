package com.webstudy.dao;

import com.webstudy.entity.Users;
import com.webstudy.util.JDBCutil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: long
 * @date: 2021/3/24 14:31
 * @description: 用户表sql命令封装工具类
 */
public class UserDao {
    private JDBCutil util = new JDBCutil();

    /**
     * 封装sql语句中的添加命令，向用户表添加注册信息用户
     * @param user 用户实体类对象
     * @return 命令条数
     */
    public int add(Users user){
        String sql = "insert into users(userName,userPassword,userSex,userEmail)" +
                     "values(?,?,?,?)";
        PreparedStatement ps = util.getStatement(sql);
        int result = 0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getUserPassword());
            ps.setString(3,user.getUserSex());
            ps.setString(4,user.getUserEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    /**
     * 重载add方法，封装sql语句中的添加命令，向用户表添加注册信息用户
     * @param user 用户实体类对象
     * @param request Tomcat请求对象
     * @return 命令条数
     */
    public int add(Users user, HttpServletRequest request){
        String sql = "insert into users(userName,userPassword,userSex,userEmail)" +
                "values(?,?,?,?)";
        PreparedStatement ps = util.getStatement(sql,request);
        int result = 0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getUserPassword());
            ps.setString(3,user.getUserSex());
            ps.setString(4,user.getUserEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(request);
        }
        return result;
    }


    /**
     * 封装sql语句中的查询命令，查询用户表所有信息
     * @return 一个带有所有用户信息Users对象列表
     */
    public List findAll(){
        List userList = new ArrayList();
        String sql = "select * from users";
        PreparedStatement ps = util.getStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String userPwd = rs.getString("userPassword");
                String userSex = rs.getString("userSex");
                String userEmail = rs.getString("userEmail");
                Users user = new Users(userId,userName,userPwd,userSex,userEmail);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(rs);
        }
        return userList;
    }

    /**
     * 封装sql语句中的删除命令，根据用户ID删除用户
     * @param userId 用户ID
     * @return 命令条数
     */
    public int delete(String userId){
        String sql = "delete from users where userId = ?";
        PreparedStatement ps = util.getStatement(sql);
        int result = 0;
        try {
            ps.setInt(1,Integer.valueOf(userId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }

    public int login(String userName,String userPassword){
        String sql = "select count(*) from users where userName = ? and userPassword = ?";
        PreparedStatement ps = util.getStatement(sql);
        ResultSet rs = null;
        int result = 0;
        try {
            ps.setString(1,userName);
            ps.setString(2,userPassword);
            rs = ps.executeQuery();
            while(rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }
}
