package blog;

import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tools.EeyaDBConnection;

//Class to post articles on blog
public class Article {
	private String Aid;
	private Date createDate=null,lastUpdateDate=null;
	private String text;
	private int likes,dislikes;
	private String poster;
	boolean commentEnable;
	boolean deleted;
	public ArrayList<Comment> commentList;
	
	
	Article(String text,String poster){
		this.text=text;
		this.poster=poster;
		this.Aid = UUID.randomUUID().toString();// to generate uniqueid for comments
		Date d=new Date();
		this.createDate=d;
		this.lastUpdateDate=d;
		this.likes=0;
		this.dislikes=0;
	}
	Article(){
	}
	
	public void postArticle(){
		createDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(createDate);
		
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			//System.out.println(C.getCatalog());
			//Edb.queryExecuter("insert into Article (Aid,createdate,lastupdatedate,content,poster) values ("++","+currentTime+","+currentTime+","+this.text+","+this.poster+")");
			String query = "insert into Article (Aid,createdate,lastupdatedate,content,poster) values (?,?,?,?,?)";
			PreparedStatement preparedStmt = C.prepareStatement(query);
			preparedStmt.setString(1,this.Aid);
			preparedStmt.setString(2,currentTime);
			preparedStmt.setString(3,currentTime);
			preparedStmt.setString(4,this.text);
			preparedStmt.setString(5,this.poster);
			
			preparedStmt.execute();
			C.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void editArticle(String newtext,String Aid){
		lastUpdateDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = sdf.format(lastUpdateDate);
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			String query ="update Article set content=?, lastupdatedate=? where Aid=?";
			PreparedStatement preparedStmt = C.prepareStatement(query);
			preparedStmt.setString(1,newtext);
			preparedStmt.setString(2,updateTime);
			preparedStmt.setString(3,Aid);
			preparedStmt.execute();
			C.close();
			}catch (SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	public void deleteArticle(String Aid){
		lastUpdateDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = sdf.format(lastUpdateDate);
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			String query ="update Article set deleted=?, lastupdatedate=? where Aid=?";
			PreparedStatement preparedStmt = C.prepareStatement(query);
			preparedStmt.setString(1,"true");
			preparedStmt.setString(2,updateTime);
			preparedStmt.setString(3,Aid);
			preparedStmt.execute();
			C.close();
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	
	public void buildCommentList(){
		commentList = new ArrayList<>();
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			String query ="select * from Comments where Aid= '"+this.Aid+", order by lastupdatedate desc'";
			java.sql.Statement stmt = C.createStatement();
			ResultSet rs;
			rs=stmt.executeQuery(query);
			while(rs.next()){
				Comment c = new Comment();
				c.setAid(rs.getString("Aid"));
				c.setCid(rs.getString("Cid"));
				c.setText(rs.getString("Content"));
				c.setCommentor(rs.getString("commentor"));
				commentList.add(c);
				
			}
			
			C.close();
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public String getArticleID(){
		return this.Aid;
	}

	public String getAid() {
		return Aid;
	}

	public void setAid(String aid) {
		Aid = aid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public boolean isCommentEnable() {
		return commentEnable;
	}

	public void setCommentEnable(boolean commentEnable) {
		this.commentEnable = commentEnable;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
