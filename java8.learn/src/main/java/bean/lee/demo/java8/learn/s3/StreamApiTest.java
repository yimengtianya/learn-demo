package bean.lee.demo.java8.learn.s3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class StreamApiTest {

	@Test
	public void testCollect() {
		// of方法由列表生成一个新的stream，collect由stream生成列表
		List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("a", "b", "c"), list);
	}

	@Test
	public void testMap() {

		List<String> list = Stream.of("a", "b", "c").map((string) -> {
			return string.toUpperCase();
		}).collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("A", "B", "C"), list);

		
		List<String> list2 = Stream.of("a", "b", "c").map(string -> string.toUpperCase()).collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("A", "B", "C"), list2);
	}

}
