package act;
import java.util.ArrayList;
public class Post {
	
	String postid;
	User author;
	String content;
	int likes;
	ArrayList<User> likedBy = new ArrayList<User>();
	
	Post(String postid , User author , String content){
		this.postid=postid;
		this.author=author;
		this.content=content;
		this.likes = 0 ;
		this.likedBy=new ArrayList<User>();
	}
	
	public void addLike(User user) {
		if (!(likedBy.contains(user))) {
			likes++;
			likedBy.add(user);
		}
	}
	
	public int getlikes() {
		return likes;
	}

	@Override
	public String toString() {
		return "Post [postid=" + postid + ", author=" + author + ", content=" + content + ", likes=" + likes
				+ ", likedBy=" + likedBy + "]";
	}
	
	
}
