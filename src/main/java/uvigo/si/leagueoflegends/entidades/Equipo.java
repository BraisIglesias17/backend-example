package uvigo.si.leagueoflegends.entidades;


import jakarta.persistence.*;
import java.util.List;


@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lado;

    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Participante> participantes;

    public Equipo(){

    }
    public Equipo(String lado, List<Participante> participantes) {
      
        this.lado = lado;
        this.participantes = participantes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
}
