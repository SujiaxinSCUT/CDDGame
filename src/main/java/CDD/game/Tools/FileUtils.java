package CDD.game.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import CDD.game.model.Player.User;
import CDD.game.view.App;

public class FileUtils {

	private static FileOutputStream fos;
	
	private static ObjectOutputStream ops;
	
	private static FileInputStream fis;
	
	private static ObjectInputStream ois;
	
	private static File file;
	static {
		try {
			file=new File("record/players.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<User> readUser()
	{
		List<User> lists=new ArrayList<>();
		try {
			fis=new FileInputStream(file);
			ois=new ObjectInputStream(fis);
			while(lists.add((User) ois.readObject()))
			{
					User user=(User) ois.readObject();
					lists.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}		
		
		return lists;
	}
	
	public static void writeUser(List<User> users) throws IOException
	{
		fos=new FileOutputStream(file);
		ops=new ObjectOutputStream(fos);
		for(User user:users)
		{
			ops.writeObject(user);
			ops.flush();
		}

	}
}
