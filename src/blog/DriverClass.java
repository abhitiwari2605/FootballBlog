package blog;

public class DriverClass {

	public static void main(String[] args) {
		Article AA= new Article("targz", "abhishek");
		AA.postArticle();
		
		AA.editArticle("xasd", AA.getArticleID());
		AA.deleteArticle(AA.getArticleID());

	}

}
