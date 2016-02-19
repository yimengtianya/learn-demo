package bean.lee.demo.java8.learn.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 专辑
 * 
 * @author Dube
 * @date 2016年2月19日 下午9:39:29
 */
public class Album {

	/**
	 * 专辑名称
	 */
	private String name;

	/**
	 * 曲目列表
	 */
	private List<String> tracks = new ArrayList<>();

	/**
	 * 艺术家列表
	 */
	private List<String> musicians = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	public List<String> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<String> musicians) {
		this.musicians = musicians;
	}

}
