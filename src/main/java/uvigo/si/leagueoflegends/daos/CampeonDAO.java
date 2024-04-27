package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uvigo.si.leagueoflegends.entidades.*;
public interface CampeonDAO  extends JpaRepository<Campeon,Long>{
	
	@Query("SELECT j FROM Campeon j WHERE j.name LIKE :name%")
	public List<Campeon> findByName(String name);
}
