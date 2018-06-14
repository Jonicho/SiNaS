package de.sinas.server;

import java.io.File;

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
	 * @param databaseDirectory
	 *            the directory in which the data is to be stored
	 * @throws IllegalArgumentException
	 *             when given database directory is not a directory
	 */
	public Database(File databaseDirectory) throws IllegalArgumentException {
		if (!databaseDirectory.isDirectory()) {
			throw new IllegalArgumentException("Database directory has to be a directory!");
		}
		this.databaseDirectory = databaseDirectory;
	}

	/**
	 * Loads the user with the given name
	 * 
	 * @return The user with the given username
	 */
	public User getUser(String username) {
		return null;
	}

	/**
	 * Loads all conversations of the given user
	 * 
	 * @return all conversations of the given user
	 */
	public Conversation[] getConversations(User user) {
		return null;
	}

	/**
	 * Saves the given user
	 */
	public void saveUser(User user) {

	}

	/**
	 * Saves the given conversation
	 */
	public void saveConversation(Conversation conversation) {

	}
}