package de.sinas.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.sinas.Conversation;
import de.sinas.User;

/**
 * The database for SiNaS. Stores user and conversation data in the given
 * directory
 */
public class Database {
	private File databaseDirectory;

	/**
	 * Creates a new database object
	 * 
	 * @param databaseDirectory the directory in which the data is to be stored
	 * @throws IllegalArgumentException when given database directory is not a
	 *                                  directory
	 */
	public Database(File databaseDirectory) throws IllegalArgumentException {
		if(!databaseDirectory.exists()) {
			databaseDirectory.mkdir();
			File[] structure = { new File(databaseDirectory + "/conversations"), new File(databaseDirectory + "/files"), new File(databaseDirectory + "/users") };
			for( File folder : structure) {
				folder.mkdir();
			}
		}
		if (!databaseDirectory.isDirectory()) {
			throw new IllegalArgumentException("Database directory has to be a directory!");
		}
		this.databaseDirectory = databaseDirectory;
	}

	/**
	 * Loads the user with the given name with ip and port.</br>
	 * This method is to get a user that connected to the server.</br>
	 * If no user with the given name exists a new one is created.
	 * 
	 * @return The user with the given username
	 */
	public User getConnectedUser(String username, String ip, int port) {
		return new User(ip, port, username, username); // TODO load user from database
	}

	/**
	 * Loads the user with the given name without ip and port.</br>
	 * This method is to get information about a user without having the user to
	 * connect to the server.</br>
	 * If no user with the given name exists {@code null} is returned.
	 * 
	 * @return The user with the given username. {@code null} if no user with the
	 *         given name exists.
	 */
	public User getUserInfo(String username) {
		return new User("", 0, username, username); // TODO load user from database
	}

	/**
	 * Loads all conversations of the given user
	 * 
	 * @return all conversations of the given user
	 */
	public ArrayList<Conversation> getConversations(User user) {
		ArrayList<Conversation> conversations = new ArrayList<>();

		File[] filelist = databaseDirectory.listFiles();
		for (int i = 0; i < databaseDirectory.list().length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filelist[i]));
				ArrayList<String> lines = new ArrayList<>();
				while (reader.readLine() != null) {
					lines.add(reader.readLine());
				}
				String[] conversationInformation = lines.get(0).split(":");
				if(conversationInformation[1].equals(user.getUsername()) || conversationInformation[2].equals(user.getUsername())) {
					conversations.add(new Conversation(conversationInformation[0], user.getUsername(), user.getUsername())); //TODO change to user1 and user2
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Saves the given user
	 */
	public void saveUser(User user) {
		File userfile = new File(databaseDirectory + "\\users\\" + user.getUsername() + ".txt");
		if (!userfile.exists()) {
			try {
				userfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves the given conversation
	 */
	public void saveConversation(Conversation conversation) {
		File conversationfile = new File(databaseDirectory + "\\conversations\\" + conversation.getId() + ".txt");
		if (!conversationfile.exists()) {
			try {
				conversationfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
