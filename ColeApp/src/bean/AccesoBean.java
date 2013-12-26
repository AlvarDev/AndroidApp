package bean;

public class AccesoBean {

	String idAcceso;
	String nom;
	String contra;
	
	public AccesoBean() {
		idAcceso="";
		nom="--";
		contra="";
	}

	public String getIdAcceso() {
		return idAcceso;
	}

	public void setIdAcceso(String idAcceso) {
		this.idAcceso = idAcceso;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}
	
	
	
}
