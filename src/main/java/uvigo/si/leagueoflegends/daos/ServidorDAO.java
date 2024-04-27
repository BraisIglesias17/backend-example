package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uvigo.si.leagueoflegends.entidades.Servidor;

public interface ServidorDAO  extends JpaRepository<Servidor,Long> {
	
	@Query("SELECT s FROM Servidor s WHERE s.version LIKE :version%")
	List<Servidor> findByVersion(String version);
	
	@Query("SELECT s FROM Servidor s WHERE s.region LIKE :region%")
	List<Servidor> findByRegion(String region);
}
