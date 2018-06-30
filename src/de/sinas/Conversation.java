package de.sinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A conversation containing an arbitrary amount of users
 */
public class Conversation {
	private String id;
	private String name;
	private ArrayList<Message> messages = new ArrayList<>();
	private ArrayList<String> users = new ArrayList<>();

	/**
	 * Creates a new conversation with an id and an arbitrary amount of users.
	 * 
	 * @param id    the conversation id
	 * @param users an arbitrary amount of users
	 */
	public Conversation(String id, String... users) {
		this.id = id;
		this.users.addAll(Arrays.asList(users));
	}

	public void addUser(String user) {
		users.add(user);
	}

	/**
	 * Adds one or more messages to the conversation. The messages in this
	 * conversation will be sorted by time stamp.
	 * 
	 * @param messages The message(s) to add
	 */
	public void addMessages(Message... msgs) {
		for (int i = 0; i < msgs.length; i++) {
			messages.add(msgs[i]);
		}
		messages.sort((m1, m2) -> (int) Math.signum(m1.getTimestamp() - m2.getTimestamp()));
	}

	public boolean contains(String user) {
		for (String u : users) {
			if (u.equals(user)) {
				return true;
			}
		}
		return false;
	}

	public String getId() {
		return id;
	}

	/**
	 * @return the name of this conversation
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns an unmodifiable list containing all messages in this conversation.
	 * 
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return Collections.unmodifiableList(messages);
	}

	/**
	 * Returns an unmodifiable list containing all users in this conversation.
	 * 
	 * @return the users
	 */
	public List<String> getUsers() {
		return Collections.unmodifiableList(users);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Conversation && ((Conversation) obj).getId().equals(id);
	}
}
