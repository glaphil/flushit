package models;

public enum SpotStatus {
	
	AVAILABLE("disponible"),
	OCCUPIED("occupé"),
	TOXIC("alerte toxique"),
	UNKNOWN("inconnu");
	
	public String label;
	
	private SpotStatus(String label){
		this.label=label;
	}
	
}
