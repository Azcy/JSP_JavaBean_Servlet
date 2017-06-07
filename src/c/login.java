package c;

import m.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by zcy on 2017/6/7.
 */
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errMsg="";
        //Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
        RequestDispatcher rd;
        //获取请求参数
        String username = request.getParameter("username");
        String pass=request.getParameter("pass");
        try {
            //Servlet本身并不执行任何的业务逻辑处理，它就调用javabean处理用户请求
            UserDao userDao=new UserDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/myzcy","root","root");
            //查询结果集
            ResultSet rs=userDao.query("select pass from user"+" where username=?",username);
            if (rs.next()){
                //用户名和密码匹配
                if (rs.getString("pass").equals(pass))
                {
                    //获取session对象
                    HttpSession session=request.getSession(true);
                    //设置session属性，跟踪用户会话状态
                    session.setAttribute("name",username);
                    //获取转发对象
                    rd=request.getRequestDispatcher("/welcome.jsp");
                    //转发请求
                    rd.forward(request,response);
                }
                else {
                    //用户名和密码不匹配
                    errMsg+="您的用户密码不符合请重新输入";
                }
            }else {
                //用户名不存在时
                errMsg +="您的用户名不存在，请先注册";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果出现错误，转发到重新登录
        if (errMsg!=null&&!errMsg.equals("")){
            rd=request.getRequestDispatcher("/index.jsp");
            request.setAttribute("err",errMsg);
            rd.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
