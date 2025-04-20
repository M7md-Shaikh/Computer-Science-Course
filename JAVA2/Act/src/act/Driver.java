package act;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = new User("1" , "Mohammad Muslim Sheikh", "mhmdshaikh4@gmail.com");
		User user2 = new User("2", "Ahmad Issa Bazzar", "ahmad123@gmail.com");
		
		user1.addFriend(user2);
		
		Post post = new Post("P1" , user1 , "mmm");
		System.out.println(post.toString());
		post.addLike(user2);
		
		System.out.println("Update Likes : "+post.getlikes());
		System.out.println("Number of Users is : "+User.numberOfUsers);
	}

}
