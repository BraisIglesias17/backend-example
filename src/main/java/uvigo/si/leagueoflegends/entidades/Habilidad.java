package uvigo.si.leagueoflegends.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    
    @ManyToOne
    private Campeon campeon;
    
    public Habilidad(){

    }

    public Habilidad(String name, String description, Campeon campeon) {
        this.name = name;
        this.description = description;
        this.campeon=campeon;
       
    }

    public long getId() {
        return id;
    }
    
	public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

	public Campeon getCampeon() {
		return campeon;
	}

	public void setCampeon(Campeon campeon) {
		this.campeon = campeon;
	}
    
    
}

