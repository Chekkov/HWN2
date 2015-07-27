package converter;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hwn_achievments")
public class HWNAchievements {

	@XmlElement(name = "stempel")
	List<Stempel> stempelList;

	public List<Stempel> getStempelList() {
		return stempelList;
	}

	public void setStempelList(List<Stempel> stempelList) {
		this.stempelList = stempelList;
	}

	
	public static List<Stempel> expandStempelList(List<Stempel> stempelList) {
		// Gehe durch die Anzahl der M�glichen Stempelstellen (222)
		// Schaue nach ob ein Stempel mit der nummer existiert
		// Falls nicht erzeuge ein neuen Stempel mit
		// Nummer = aktuelle Nummer,
		int i = 1;
		for (i = 1; i <= 222; i++) {
			Stempel stempel = new Stempel(i);

			if (!stempelList.contains(stempel)) {
				stempelList.add(stempel);
			}
		}
		return stempelList;
	}
}
