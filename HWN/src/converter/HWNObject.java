package converter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bernd Paul
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name= "hwn_personaldata")
public class HWNObject {
    
	/**
	 * ghfghfgh
	 */
	@XmlElement( name="hwn_achievments") HWNAchievements achievements;

	/**
	 * @return {@link #achievements} 
	 */
	public HWNAchievements getAchievements() {
		return achievements;
	}

	/**
	 * @param achievements
	 */
	public void setAchievements(HWNAchievements achievements) {
		this.achievements = achievements;
	}

	
}
