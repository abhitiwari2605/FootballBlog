package blog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.EeyaDBConnection;

public class Blog {
	
	private ArrayList<Article>  ArticleList;
	public void buildBlog(){
		ArticleList = new ArrayList<>();
		try {
			EeyaDBConnection Edb = new EeyaDBConnection();
			Connection C = Edb.returnConnection();
			String query ="select * from Article order by lastupdatedate desc'";
			java.sql.Statement stmt = C.createStatement();
			ResultSet rs;
			rs=stmt.executeQuery(query);
			while(rs.next()){
				Article A = new Article();
				A.setAid(rs.getString("Aid"));
				A.setText(rs.getString("Content"));
				A.setPoster(rs.getString("poster"));
				A.buildCommentList();
				ArticleList.add(A);
			}
			
			C.close();
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}

}
