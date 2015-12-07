package bean.lee.push.notification.message.entity;

public class Message {

	/**
	 * 主题
	 */
	private String topic;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 存活时间，单位：秒
	 */
	private Long lifetime;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getLifetime() {
		return lifetime;
	}

	public void setLifetime(Long lifetime) {
		this.lifetime = lifetime;
	}
	
	

}
