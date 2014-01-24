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
			persistSpot("wc lounge", "male");
			persistSpot("wc lounge", "female");
			persistSpot("wc garantie sys", "wheelchair");
			persistSpot("wc garantie sys", "female");
			persistSpot("wc rh", "male");
			persistSpot("wc rh", "female");
			persistSpot("wc serv. gen.", "wheelchair");
			persistSpot("wc serv. gen.", "female");
		}

	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

	private static void persistSpot(String label, String gender) {
		Spot spot = new Spot();
		spot.label = label;
		spot.gender = gender;
		spot.status = SpotStatus.UNKNOWN;
		spot.lastUpdate = new Date();
		spot.save();
	}

}
