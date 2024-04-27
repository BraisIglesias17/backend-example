package uvigo.si.leagueoflegends.entidades;



import jakarta.persistence.*;

@Entity
public class Servidor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String region;
    private String version;

    
    
    public Servidor(){

    }

    public Servidor(String region, String version) {
       
        this.region = region;
        this.version = version;
       
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
