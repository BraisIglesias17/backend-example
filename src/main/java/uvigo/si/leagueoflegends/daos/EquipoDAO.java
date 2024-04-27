package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uvigo.si.leagueoflegends.entidades.Equipo;


public interface EquipoDAO extends JpaRepository<Equipo,Long>{

	@Query("SELECT e FROM Equipo e WHERE EXISTS (SELECT p FROM e.participantes p WHERE p.campeon.id=:id)")
	public List<Equipo> findByCampeon(@Param("id") long id);
	
	
	@Query("SELECT e FROM Equipo e WHERE EXISTS (SELECT p FROM e.participantes p WHERE p.jugador.id=:id)")
	public List<Equipo> findByJugador(@Param("id") long id);
}
