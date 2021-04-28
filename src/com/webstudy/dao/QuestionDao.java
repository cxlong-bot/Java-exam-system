package com.webstudy.dao;

import com.webstudy.entity.Question;
import com.webstudy.util.JDBCutil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: long
 * @date: 2021/3/29 11:18
 * @description:试题表sql命令封装工具类
 */
public class QuestionDao {
    JDBCutil util = new JDBCutil();

    /**
     * 封装sql语句的添加命令，给试题表添加试题信息
     * @param question 试题类实体对象
     * @param request 请求对象
     * @return 命令条数
     */
    public int add(Question question, HttpServletRequest request){
        String sql = "insert into questions(title,optionA,optionB,optionC,optionD,answer) " +
                     "values(?,?,?,?,?,?)";
        int result = 0;
        PreparedStatement ps = util.getStatement(sql,request);
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(request);
        }
        return result;
    }

    /**
     * 封装sql语句的查询命令，查询试题表信息
     * @param request 请求对象
     * @return 试题对象列表
     */
    public List findAll(HttpServletRequest request){
        String sql = "select * from questions";
        PreparedStatement ps = util.getStatement(sql,request);
        List questionList = new ArrayList();
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs,request);
        }
        return questionList;
    }

    /**
     * 封装sql语句的删除命令，根据questionId从试题表中删除试题
     * @param request 请求对象
     * @param questionId 试题表Id
     * @return 命令条数
     */
    public int delete(HttpServletRequest request,String questionId){
        String sql = "delete from questions where questionId = ?";
        PreparedStatement ps = util.getStatement(sql,request);
        int result = 0;
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(request);
        }
        return result;
    }

    /**
     * 封装sql语句的查询命令，根据试题Id查询某一试题详细信息
     * @param request 请求对象
     * @param questionId 试题Id
     * @return 试题对象
     */
    public Question find(HttpServletRequest request,String questionId){
        String sql = "select title,optionA,optionB,optionC,optionD,answer from questions " +
                     "where questionId = ?";
        Question question = null;
        PreparedStatement ps = util.getStatement(sql,request);
        ResultSet rs = null;
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            rs = ps.executeQuery();
            while(rs.next()){
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question = new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs,request);
        }
        return question;
    }

    /**
     * 封装sql语句的更新命令，根据试题Id更新试题信息
     * @param request 请求对象
     * @param question 试题对象
     * @return 命令条数
     */
    public int update(HttpServletRequest request,Question question){
        String sql = "update questions set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? " +
                     "where questionId=?";
        PreparedStatement ps = util.getStatement(sql,request);
        int result = 0;
        try {
            ps.setInt(7,question.getQuestionId());
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(request);
        }
        return result;
    }

    /**
     * 封装sql语句的查询命令，随机查询试题表中的4道试题
     * @param request 请求对象
     * @return 试题对象列表
     */
    public List findRand(HttpServletRequest request){
        String sql = "select * from questions order by rand() limit 0,4";
        PreparedStatement ps = util.getStatement(sql,request);
        List questionList = new ArrayList();
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs,request);
        }
        return questionList;
    }
}
