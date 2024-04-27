package uvigo.si.leagueoflegends.entidades;


import jakarta.persistence.*;


@Entity
public class Jugador {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public Jugador(){

    }
    public Jugador(String nickname, String country){
        this.nickname=nickname;
        this.country=country;
    }

    
    private String nickname;
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }
}
