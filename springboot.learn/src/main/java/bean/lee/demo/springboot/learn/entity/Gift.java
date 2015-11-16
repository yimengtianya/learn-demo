package bean.lee.demo.springboot.learn.entity;

/**
 * 礼物
 * 
 * @author Dube
 * @date 2015年11月14日 下午1:34:30
 */
public class Gift {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 价格
	 */
	private double price;

	public Gift(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Gift() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
