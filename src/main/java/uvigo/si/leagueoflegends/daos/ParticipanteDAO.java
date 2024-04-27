package uvigo.si.leagueoflegends.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uvigo.si.leagueoflegends.entidades.Participante;
import uvigo.si.leagueoflegends.entidades.Campeon;

public interface ParticipanteDAO  extends JpaRepository<Participante,Long> {
	List<Participante> findByCampeon(Campeon campeon);
}
