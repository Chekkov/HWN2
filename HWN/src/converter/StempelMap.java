package converter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Locale;

public class StempelMap {

	//Map<Integer, List<Stempel>> stempelMap = new HashMap<>(222);

	// MapMethod1: Fill Map with List(s) of Stempel horizontal (Separated by
	// sourceList(s))
	public static Map<Integer, List<Stempel>> addList(
			List<Stempel> stempelList,
			Map<Integer, List<Stempel>> stempelSourceMap) {

		//Map<Integer, List<Stempel>> stempelTargetMap = stempelSourceMap;
		List<Stempel> stempelListShort = null;

		for (Stempel stempel : stempelList) {
			if (stempelSourceMap.containsKey(stempel.getNumber())) {
				stempelSourceMap.get(stempel.getNumber()).add(stempel);
				//tmpStempel.add(stempel);
				//stempelSourceMap.put(stempel.getNumber(), tmpStempel);

			} else {
				stempelListShort = new ArrayList<Stempel>();
				stempelListShort.add(stempel);
				stempelSourceMap.put(new Integer(stempel.getNumber()),
						stempelListShort);
			}
		}

		return stempelSourceMap;

	};
	


	public static void printStempelMap(Map<Integer, List<Stempel>> stempelMap) {
		List<Stempel> stempelListShort;
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.GERMANY);

		System.out.println();
		System.out
				.println("====================== Stempelmap ======================");
		for (Map.Entry<Integer, List<Stempel>> entry : stempelMap.entrySet()) {
			System.out.println();
			System.out.println("Stempelstelle: " + entry.getKey().toString());
			stempelListShort = ((List<Stempel>) entry.getValue());
			// System.out.println(Stempel.getHeader());
			for (Stempel stempel : stempelListShort) {
				System.out.println("Found: " + stempel.getFound().toString());
				System.out.println("Rating: " + stempel.getRating().toString());
				if (stempel.getStamptime() != null)
					System.out.println("Stamptime: "
							+ df.format(stempel.getStamptime()));

			}
		}
	}

}
