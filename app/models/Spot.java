package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Spot extends Model {

	private static final long serialVersionUID = -2418665935460908310L;

	@Id
    public Long id;

    @Required
    public String label;

    @Required
    public String gender;    

    @Required
    public Date lastUpdate;

    @Enumerated(EnumType.STRING)
    public SpotStatus status;    
        
    private static Finder<Long, Spot> find = new Finder<Long, Spot>(Long.class, Spot.class);
    
    public static List<Spot> findAll(){
    	return find.all();
    }

    public static Spot findById(Long id) {
        return Spot.find.byId(id);
    }

}
