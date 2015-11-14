package bean.lee.demo.springboot.learn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import bean.lee.demo.springboot.learn.vo.Gift;

/**
 * @author Dube
 * @date 2015年11月14日 下午3:25:25
 */
@Component
public class GiftService {

	private static Map<String, Gift> gifts = new HashMap<>();

	static {
		Gift gift1 = new Gift("g1", 567);
		Gift gift2 = new Gift("g2", 45646);
		Gift gift3 = new Gift("g3", 78);
		Gift gift4 = new Gift("g4", 4654);
		gifts.put("g1", gift1);
		gifts.put("g2", gift2);
		gifts.put("g3", gift3);
		gifts.put("g4", gift4);

	}

	/**
	 * 通过礼物名称查找礼物
	 * 
	 * @param name
	 * @return
	 *
	 * @author Dube
	 * @date 2015年11月14日 下午3:31:40
	 */
	public Gift findGiftByName(String name) {
		if (gifts.containsKey(name)) {
			return gifts.get(name);
		} else {
			return new Gift(name, 0);
		}
	}

	/**
	 * 获取所有礼物
	 * 
	 * @return
	 *
	 * @author Dube
	 * @date 2015年11月14日 下午3:31:16
	 */
	public List<Gift> findGifts() {
		List<Gift> giftList = new ArrayList<>();
		giftList.addAll(gifts.values());
		return giftList;
	}

}
