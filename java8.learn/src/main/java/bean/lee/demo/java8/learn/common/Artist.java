package bean.lee.demo.java8.learn.common;

/**
 * 艺术家
 * 
 * @author Dube
 * @date 2015年12月9日 下午12:28:01
 */
public class Artist {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 成员数量
	 */
	private int members;

	/**
	 * 地区
	 */
	private String origin;

	public Artist(String name, int members, String origin) {
		super();
		this.name = name;
		this.members = members;
		this.origin = origin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
