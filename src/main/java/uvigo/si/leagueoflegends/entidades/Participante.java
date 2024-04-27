package uvigo.si.leagueoflegends.entidades;



import jakarta.persistence.Entity;



import jakarta.persistence.*;

@Entity
public class Participante  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Jugador jugador;

    @ManyToOne
    private Campeon campeon;

   

    public Participante(){

    }

    public Participante(Jugador jugador, Campeon campeon) {
        
        this.jugador = jugador;
        this.campeon = campeon;
       
    }
    
  

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Campeon getCampeon() {
        return campeon;
    }

    public void setCampeon(Campeon campeon) {
        this.campeon = campeon;
    }
}
