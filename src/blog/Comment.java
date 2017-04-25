package blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import tools.EeyaDBConnection;

public class Comment {
	private String  Aid,Cid;
	private Date createDate=null,lastUpdateDate=null;
	private String text;
	private int likes,dislikes;
	private String commentor;
	
	Comment(String Aid,String text,String commentor){
		this.Aid = Aid;
		this.text=text;
		this.commentor=commentor;
		this.Cid = UUID.randomUUID().toString();// to generate uniqueid for comments
		this.likes=0;
		this.dislikes=0;
	}
	
	Comment(){
	}
	
	
	public void postComment(){
		createDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(createDate);
		
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			//System.out.println(C.getCatalog());
			//Edb.queryExecuter("insert into Article (Aid,createdate,lastupdatedate,content,poster) values ("++","+currentTime+","+currentTime+","+this.text+","+this.poster+")");
			String query = "insert into Comment (Aid,Cid,createdate,lastupdatedate,content,commentor) values (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = C.prepareStatement(query);
			preparedStmt.setString(1,this.Aid);
			preparedStmt.setString(1,this.Cid);
			preparedStmt.setString(2,currentTime);
			preparedStmt.setString(3,currentTime);
			preparedStmt.setString(4,this.text);
			preparedStmt.setString(5,this.commentor);
			
			preparedStmt.execute();
			C.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void deleteArticle(String cid){
		lastUpdateDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = sdf.format(lastUpdateDate);
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			String query ="update Article set deleted=?, lastupdatedate=? where Cid=?";
			PreparedStatement preparedStmt = C.prepareStatement(query);
			preparedStmt.setString(1,"true");
			preparedStmt.setString(2,updateTime);
			preparedStmt.setString(3,cid);
			preparedStmt.execute();
			C.close();
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public String getAid() {
		return Aid;
	}

	public void setAid(String aid) {
		Aid = aid;
	}

	public String getCid() {
		return Cid;
	}

	public void setCid(String cid) {
		Cid = cid;
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

	public String getCommentor() {
		return commentor;
	}

	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}
	
	
	
	
	
	
}
