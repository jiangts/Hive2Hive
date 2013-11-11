package org.hive2hive.core.model;

import java.util.LinkedList;
import java.util.Queue;

import org.hive2hive.core.TimeToLiveStore;
import org.hive2hive.core.network.data.NetworkContent;
import org.hive2hive.core.network.messages.BaseMessage;

/**
 * The queue stores all {@link DirectUserMessage}s that need to be processed by a client when he comes online. This
 * way, clients can communicate among each other even if they are not always online.
 * 
 * @author Nico
 * 
 */
public class UserMessageQueue extends NetworkContent {

	private static final long serialVersionUID = 7764541495712181000L;
	
	private final String userId;
	private final Queue<BaseMessage> messageQueue;

	public UserMessageQueue(String userId) {
		this.userId = userId;
		messageQueue = new LinkedList<BaseMessage>();
	}

	public String getUserId() {
		return userId;
	}

	public Queue<BaseMessage> getMessageQueue() {
		return messageQueue;
	}

	@Override
	public int getTimeToLive() {
		return TimeToLiveStore.getInstance().getUserMessageQueue();
	}
}
