package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uvigo.si.leagueoflegends.entidades.*;

public interface PartidaDAO  extends JpaRepository<Partida,Long>{

	
	
	@Query("SELECT p FROM Partida p WHERE p.servidor.id = :id")
	public List<Partida> findByServer(@Param("id") long id);
	
	@Query("SELECT p FROM Partida p WHERE p.rojo.id = :id OR p.azul.id=:id")
	public Partida findByEquipo(@Param("id") long id);
}
