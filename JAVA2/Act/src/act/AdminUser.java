package act;

public class AdminUser extends User{
	
	boolean isAdmin;
	
	AdminUser(String userid , String username , String email , boolean isAdmin){
		super(userid,username,email);
		this.isAdmin=isAdmin;
	}
	
}
