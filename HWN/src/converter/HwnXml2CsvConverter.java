package converter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.tree.ExpandVetoException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

/**
 * @author Bernd Paul
 *
 */
public class HwnXml2CsvConverter {
	public static void main(String[] args) throws IOException {
		String file2read2 = "/Users/d057673/Documents/ws_mars/HWN/20140723_Bernd_hwn_persdata.xml";
		String file2write1 = "Bernd_hwn_persdata.csv";
		String file2read1 = "/Users/d057673/Documents/ws_mars/HWN/20150724_Moni_hwn_persdata.xml";
		String file2write2 = "Moni_hwn_persdata.csv";
		String file2write3 = "AllFound.csv";
		String file2write4 = "NoneFound.csv";
		String file2write5 = "FoundByBerndOnly.csv";
		String file2write6 = "FoundByMoniOnlyFoundByBerndOnly.csv";

		List<Stempel> stempelList = xml2StempelList(file2read1);
		List<Stempel> stempelList2 = xml2StempelList(file2read2);
		List<Stempel> stempelListShort = null;

		// Create Map of StempelList

		Map<Integer, List<Stempel>> stempelMap = new HashMap<>(222);
		stempelMap = StempelMap.addList(stempelList, stempelMap);

		stempelMap = StempelMap.addList(stempelList2, stempelMap);
		// sortListsShort(stempelMap);
		// StempelMap.printStempelMap(stempelMap);
		fillResultLists(stempelMap);

		stempelList = xml2StempelList(file2read2);
		Collections.sort(stempelList);

	}

	private static void write2csv(List<Stempel> stempelList, String file2write)
			throws IOException {

		File csvFile = new File(file2write);
		FileUtils.write(csvFile, Stempel.getHeader());
		for (Stempel stempel : stempelList) {

			FileUtils.write(csvFile, stempel.toString() + "\n", true);
		}
	}

	private static List<Stempel> xml2StempelList(String file2read) {

		JAXBContext context;
		List<Stempel> stempelList = null;

		try {
			context = JAXBContext.newInstance(HWNObject.class);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			;
			// unmarshaller = context.createUnmarshaller();
			File xmlFile = new File(file2read);
			final HWNObject hwn = (HWNObject) unmarshaller.unmarshal(xmlFile);
			stempelList = hwn.achievements.getStempelList();
			stempelList = HWNAchievements.expandStempelList(stempelList);
			Collections.sort(stempelList);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stempelList;

	}

	public static void fillResultLists(Map<Integer, List<Stempel>> stempelMap) throws IOException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.GERMANY);
		List<Stempel> stempelListShort;

		List<Stempel> allFoundList = new ArrayList<Stempel>();
		List<Stempel> noneFoundList = new ArrayList<Stempel>();
		List<Stempel> Found1List = new ArrayList<Stempel>();
		List<Stempel> Found2List = new ArrayList<Stempel>();
		Boolean tmpFound = null;

		for (Map.Entry<Integer, List<Stempel>> entry : stempelMap.entrySet()) {
			stempelListShort = entry.getValue();
			// Iterator<Stempel> stempelIterator = stempelListShort.iterator();
			// tmpFound = stempelIterator.next().getFound();
			int i = 0;
			while (i < stempelListShort.size()) {
				if (i == 0)
					tmpFound = stempelListShort.get(i).getFound();
				else {
					if (tmpFound.equals(Boolean.TRUE)) {
						if (stempelListShort.get(i).getFound().equals(tmpFound))
							allFoundList.add(stempelListShort.get(i));
						else
							Found1List.add(stempelListShort.get(0));
					} else if (tmpFound.equals(Boolean.FALSE)) {
						if (stempelListShort.get(i).getFound().equals(tmpFound))
							noneFoundList.add(stempelListShort.get(i));
						else
							Found2List.add(stempelListShort.get(i));
					}
				}
				i++;
			}
		}
		String file2write3 = "AllFound.csv";
		String file2write4 = "NoneFound.csv";
		String file2write5 = "FoundByMoniOnly.csv";
		String file2write6 = "FoundByBerndOnly.csv";
		write2csv(allFoundList, file2write3);
		write2csv(noneFoundList, file2write4);
		write2csv(Found1List, file2write5);
		write2csv(Found2List, file2write6);
		
		System.out.println("Found by all: ");
		System.out.println("=======================================");
		System.out.println("Anzahl Stempel: " + allFoundList.size());
		System.out.println();
		for (Stempel stempel : allFoundList) {
			System.out.println();
			System.out.println("Nummer: " + stempel.getNumber().toString());
			// System.out.println("Found: " + stempel.getFound().toString());
			// System.out.println("Rating: " + stempel.getRating().toString());
			if (stempel.getStamptime() != null && !stempel.getStamptime().equals("0")) {
				Long.parseLong(stempel.getStamptime());
				Date stempelzeit = new Date();
				stempelzeit.setTime(Long.parseLong(stempel.getStamptime()));
				System.out.println("Stamptime: " + df.format(stempelzeit));
			}

		}
		System.out.println();
		System.out.println();
		System.out.println("Found by none: ");
		System.out.println("=======================================");
		System.out.println("Anzahl Stempel: " + noneFoundList.size());
		System.out.println();
		for (Stempel stempel : noneFoundList) {
			System.out.println("Nummer: " + stempel.getNumber().toString());

		}
		System.out.println();
		System.out.println();
		System.out.println("Found by Pers1: ");
		System.out.println("=======================================");
		System.out.println("Anzahl Stempel: " + Found1List.size());
		System.out.println();
		for (Stempel stempel : Found1List) {
			// System.out.println();
			System.out.println("Nummer: " + stempel.getNumber().toString());
			// System.out.println("Found: " + stempel.getFound().toString());
			// System.out.println("Rating: " + stempel.getRating().toString());
			if ((stempel.getStamptime() != null) && !stempel.getStamptime().equals("0")) {
				Long.parseLong(stempel.getStamptime());
				Date stempelzeit = new Date();
				stempelzeit.setTime(Long.parseLong(stempel.getStamptime()));
				System.out.println("Stamptime: " + df.format(stempelzeit));
			}

		}
		System.out.println();
		System.out.println();
		System.out.println("Found by Pers2: ");
		System.out.println("=======================================");
		System.out.println("Anzahl Stempel: " + Found2List.size());
		System.out.println();
		for (Stempel stempel : Found2List) {
			System.out.println();
			System.out.println("Nummer: " + stempel.getNumber().toString());
			// System.out.println("Found: " + stempel.getFound().toString());
			// System.out.println("Rating: " + stempel.getRating().toString());
			if ((stempel.getStamptime() != null) && !stempel.getStamptime().equals("0")) {
				Long.parseLong(stempel.getStamptime());
				Date stempelzeit = new Date();
				stempelzeit.setTime(Long.parseLong(stempel.getStamptime()));
				System.out.println("Stamptime: " + df.format(stempelzeit));
			}

		}

	}

	/**
	 * 
	 */
	void thingsToLearn() {
		Collections.disjoint(null, null);
		List<Stempel> list = new ArrayList<>();
		list.retainAll(null);
		list.containsAll(null);
	}
}