package bean.lee.demo.rabbitmq.learn.p6;

import java.io.Serializable;
import java.util.Set;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3118460985330862293L;

	/**
	 * 消息id
	 */
	private int messageId;

	/**
	 * 主题
	 */
	private Set<String> topic;

	/**
	 * 主题运算符
	 */
	private String topicOp;

	/**
	 * 内容
	 */
	private String content;

	public Message(int messageId, Set<String> topic, String topicOp, String content) {
		super();
		this.messageId = messageId;
		this.topic = topic;
		this.topicOp = topicOp;
		this.content = content;
	}

	public int getMessageId() {
		return messageId;
	}

	public Set<String> getTopic() {
		return topic;
	}

	public String getTopicOp() {
		return topicOp;
	}

	public String getContent() {
		return content;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public void setTopic(Set<String> topic) {
		this.topic = topic;
	}

	public void setTopicOp(String topicOp) {
		this.topicOp = topicOp;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
