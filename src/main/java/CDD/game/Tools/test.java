package CDD.game.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import CDD.game.model.Player.User;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user=new User("test");
		User user1=new User("test1");
		List<User> users=new ArrayList<>();
		users.add(user);
		users.add(user1);
		try {
			FileUtils.writeUser(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		users.clear();
		System.out.println(users.size());
		try {
			users=FileUtils.readUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(users.size());
	}

}
