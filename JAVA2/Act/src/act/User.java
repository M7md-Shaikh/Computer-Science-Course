package act;
import java.util.ArrayList;
public class User {
	
	String userid;
	String username;
	String email;
	ArrayList<User> friends = new ArrayList<User>();
	static int numberOfUsers = 0;
	
	User(String userid , String username , String email){
		this.userid=userid;
		this.username=username;
		this.email=email;
		this.friends=new ArrayList<User>();
		numberOfUsers++;
	}
	
	public void addFriend(User fri) {
		friends.add(fri);
	}
	
	public ArrayList<User> getFriends() {
		return friends;
	}
	

}
