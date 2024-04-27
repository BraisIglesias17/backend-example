package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uvigo.si.leagueoflegends.entidades.Jugador;


public interface JugadorDAO  extends JpaRepository<Jugador,Long> {

	@Query("SELECT j FROM Jugador j WHERE j.country LIKE :country%")
	public List<Jugador> findByCountry(String country);
	
	@Query("SELECT j FROM Jugador j WHERE j.nickname LIKE :nickname%")
	public List<Jugador> findByNickname(String nickname);
}
