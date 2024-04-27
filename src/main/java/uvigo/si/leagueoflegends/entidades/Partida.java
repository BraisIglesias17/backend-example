package uvigo.si.leagueoflegends.entidades;

import jakarta.persistence.*;

@Entity
public class Partida {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Servidor servidor;

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    private Equipo rojo;

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    private Equipo azul;


    public Partida(){

    }

    public Partida(Servidor servidor, Equipo rojo, Equipo azul) {
       
        this.servidor = servidor;
        this.rojo = rojo;
        this.azul = azul;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Equipo getRojo() {
        return rojo;
    }

    public void setRojo(Equipo rojo) {
        this.rojo = rojo;
    }

    public Equipo getAzul() {
        return azul;
    }

    public void setAzul(Equipo azul) {
        this.azul = azul;
    }
}
