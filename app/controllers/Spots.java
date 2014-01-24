package controllers;

import static play.data.Form.form;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import models.Spot;
import models.SpotStatus;
import play.Logger;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;


public class Spots extends Controller {

    public static Result all() {
    	Logger.info("List all - ip=["+request().remoteAddress()+"]");
    	// si un spot n'a pas été updaté auj, on force le status à UNKNOWN (pour affichage uniquement)
    	List<Spot> spots = Spot.findAll();
    	for (Spot spot : spots) {
			if(spot.lastUpdate.before(getTodayMidnight())){
				spot.status=SpotStatus.UNKNOWN;
			}
		}
    	return ok(index.render("Flushit! Take (sh)it easy!", spots));
	}
    
    @SuppressWarnings("rawtypes")
    public static Result update(Long spotId) {
    	Spot spot = Spot.findById(spotId);
    	DynamicForm requestData = form().bindFromRequest();
    	for (SpotStatus spotStatus : SpotStatus.values()) {
    		if(requestData.get(spotStatus.name())!=null){
    			spot.status=spotStatus;
    			spot.lastUpdate=new Date();
    			spot.save();
    			Logger.info("update spot id=["+spotId+"] to status=["+spotStatus+"] - ip=["+request().remoteAddress()+"]");
    			break;
    		}
		}

		return redirect("/");
	}
    
    // utils
    private static Date getTodayMidnight(){
    	Calendar todayMidnight = new GregorianCalendar();
    	todayMidnight.set(Calendar.HOUR_OF_DAY, 0);
    	todayMidnight.set(Calendar.MINUTE, 0);
    	todayMidnight.set(Calendar.SECOND, 0);
    	todayMidnight.set(Calendar.MILLISECOND, 0);
    	
    	return todayMidnight.getTime();
    }

}
