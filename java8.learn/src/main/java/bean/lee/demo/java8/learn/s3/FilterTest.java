package bean.lee.demo.java8.learn.s3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import bean.lee.demo.java8.learn.common.Artist;

public class FilterTest {

	@Test
	public void testFilter() {

		List<Artist> artists = new ArrayList<Artist>();
		artists.add(new Artist("lee1", 5, "Gz"));
		artists.add(new Artist("lee2", 6, "Hn"));
		artists.add(new Artist("lee3", 7, "Zz"));
		artists.add(new Artist("lee4", 5, "Jz"));
		artists.add(new Artist("lee5", 6, "Qy"));

		long a = artists.stream().filter((artist) -> {
			if (artist.getMembers() == 5)
				return true;
			return false;

		}).count();
		System.out.println(a);

		Artist artist2 = artists.stream().filter((artist) -> {
			if (artist.getMembers() == 6)
				return true;
			return false;
		}).findFirst().get();
		System.out.println(artist2.getName());

	}
}
