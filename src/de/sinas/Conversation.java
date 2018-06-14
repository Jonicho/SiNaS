package de.sinas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A normal conversation with two users
 */
public class Conversation {
	private String id;
	private ArrayList<Message> messages = new ArrayList<>();
	private User user1;
	private User user2;

	/**
	 * Creates a new conversation object.
	 * 
	 * @param id    the conversation's id
	 * @param user1 the first user
	 * @param user2 the second user
	 */
	public Conversation(String id, User user1, User user2) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public User getUser1() {
		return user1;
	}

	public User getUser2() {
		return user2;
	}

	/**
	 * Adds a message to the conversation. The messages in this conversation will be
	 * sorted by time stamp.
	 * 
	 * @param message The message to add
	 */
	public void addMessage(Message message) {
		messages.add(message);
		messages.sort((m1, m2) -> (int) Math.signum(m1.getTimestamp() - m2.getTimestamp()));
	}

	public String getId() {
		return id;
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
	 * Returns the user in this conversation that is not been given.
	 * 
	 * @return the other user. {@code null} if the given user is not in this
	 *         conversation
	 */
	public User getOtherUser(User user) {
		if (user.equals(user1))
			return user2;
		else if (user.equals(user2))
			return user1;
		else
			return null;
	}
}
