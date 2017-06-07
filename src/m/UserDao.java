package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zcy on 2017/6/6.
 */
public class UserDao {
    private Connection conn;
    private  String driver;
    private String url;
    private String username;
    private String pass;
    public UserDao(){}
    public UserDao(String driver,String url,String username,String pass){
       this.driver=driver;
       this.url=url;
       this.username=username;
       this.pass=pass;
    }
    //getter setter
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //获取数据库连接
    public Connection getConnection() throws Exception{
        if (conn==null){
            Class.forName(this.driver);
            conn= DriverManager.getConnection(url,username,this.pass);
        }
        return conn;
    }

    // 插入记录，Object... 是jdk1.5新增语法，这里指的是可以输入多个参数，作为where？的条件
    public boolean insert(String sql,Object... args)throws Exception{
        //操作命令
        PreparedStatement pstmt=getConnection().prepareCall(sql);
        //对应的？ 对应的set
        for (int i=0;i<args.length;i++){
            pstmt.setObject(i+1,args[i]);
        }
        //返回更新的条数
        if (pstmt.executeUpdate()!=1){
            return false;
        }
        return true;
    }

    //执行查询
    public ResultSet query(String sql,Object... args)throws Exception{
        PreparedStatement pstmt=getConnection().prepareCall(sql);
        for (int i=0;i<args.length;i++){
            pstmt.setObject(i+1,args[i]);
        }
        return pstmt.executeQuery();
    }

    //执行修改
    public  void modify(String sql,Object... args) throws Exception{
        PreparedStatement pstmt=getConnection().prepareStatement(sql);
        //对应的？ 对应的set
        for (int i=0;i<args.length;i++){
            pstmt.setObject(i+1,args[i]);
        }
        pstmt.executeUpdate();
        pstmt.close();
    }


    //关闭数据库连接的方法
    public void closeConn()throws Exception{
        if (conn!=null&&!conn.isClosed()){
            conn.close();
        }
    }
}
