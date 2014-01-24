import java.util.Date;

import models.Spot;
import models.SpotStatus;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		
		Logger.info("Application has started");

		Logger.info("loading spots...");
		if (Ebean.find(Spot.class).findRowCount() == 0) {
			persistSpot("wc homme lounge");
			persistSpot("wc femme lounge");
			persistSpot("wc handi garantie sys");
			persistSpot("wc femme garantie sys");
			persistSpot("wc homme rh");
			persistSpot("wc femme rh");
			persistSpot("wc homme serv. gen.");
			persistSpot("wc femme serv. gen.");
		}

	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

	private static void persistSpot(String label) {
		Spot spot = new Spot();
		spot.label = label;
		spot.status = SpotStatus.UNKNOWN;
		spot.lastUpdate = new Date();
		spot.save();
	}

}
