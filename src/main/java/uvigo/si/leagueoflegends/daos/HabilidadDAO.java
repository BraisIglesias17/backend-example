package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uvigo.si.leagueoflegends.entidades.Habilidad;

public interface HabilidadDAO extends JpaRepository<Habilidad,Long>{

	@Query("SELECT j FROM Habilidad j WHERE j.name LIKE :name%")
	public List<Habilidad> findByName(String name);
	
	
	@Query("SELECT p FROM Habilidad p WHERE p.campeon.id = :id")
	public List<Habilidad> findByCampeonId(Long id);
}
