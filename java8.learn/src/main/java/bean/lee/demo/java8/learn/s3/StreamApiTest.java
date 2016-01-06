package bean.lee.demo.java8.learn.s3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import bean.lee.demo.java8.learn.common.Artist;

public class StreamApiTest {

	@Test
	public void testCollect() {
		// of方法由列表生成一个新的stream，collect由stream生成列表
		List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("a", "b", "c"), list);
	}

	@Test
	public void testMap() {
		// map用数据转换
		List<String> list = Stream.of("a", "b", "c").map((string) -> {
			return string.toUpperCase();
		}).collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("A", "B", "C"), list);

		List<String> list2 = Stream.of("a", "b", "c").map(string -> string.toUpperCase()).collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList("A", "B", "C"), list2);
	}

	@Test
	public void testFlatMap() {
		// flatMap用于多个stream合成一个stream
		List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
		Assert.assertEquals(Arrays.asList(1, 2, 3, 4), together);
	}

	@Test
	public void testMinAndMax() {

		List<Artist> artists = new ArrayList<Artist>();
		artists.add(new Artist("lee1", 3, "Gz"));
		artists.add(new Artist("lee2", 6, "Hn"));
		artists.add(new Artist("lee3", 7, "Zz"));
		artists.add(new Artist("lee4", 5, "Jz"));
		artists.add(new Artist("lee5", 6, "Qy"));

		Artist artistMin = artists.stream().min(Comparator.comparing(artist -> artist.getMembers())).get();
		Assert.assertEquals(artistMin.getName(), "lee1");

		Artist artistMax = artists.stream().max(Comparator.comparing(artist -> artist.getMembers())).get();
		Assert.assertEquals(artistMax.getName(), "lee3");

	}

	@Test
	public void testReduce() {
		int sum = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
		Assert.assertEquals(6, sum);

	}

}
