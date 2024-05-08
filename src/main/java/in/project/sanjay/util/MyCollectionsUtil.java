package in.project.sanjay.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//JDK 8 (static methods + default methods allowed)
// We can this class into interface
public class MyCollectionsUtil {

	public static Map<Long,String> convertToMap(List<Object[]> list){
		//JAVA 8 Stream API
		Map<Long, String> map = 
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(
								ob[0].toString()),
						ob->ob[1].toString()));
		return map;
	}
	
	//foreach
	/*
	 * Map<Long, String> map2 = new HashMap<>(); for(Object[] ob:List) {
	 * map2.put(Long.valueOf(ob[0].toString()), ob[1].toString()); }
	 */
}
