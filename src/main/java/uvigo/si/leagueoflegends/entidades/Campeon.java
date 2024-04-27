package uvigo.si.leagueoflegends.entidades;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class Campeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
  
    @OneToMany(fetch=FetchType.EAGER)
    private List<Habilidad> habilidades;

    public Campeon(){

    }

    public Campeon(String name, List<Habilidad> habilidades) {
        this.name = name;
        this.habilidades = habilidades;
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

    public List<Habilidad> getHability() {
        return this.habilidades;
    }

    public void setHability(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }
}
