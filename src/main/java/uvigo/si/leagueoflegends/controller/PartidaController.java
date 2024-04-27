package uvigo.si.leagueoflegends.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uvigo.si.leagueoflegends.dtos.PartidaDTO;
import uvigo.si.leagueoflegends.entidades.Partida;
import uvigo.si.leagueoflegends.entidades.Servidor;
import uvigo.si.leagueoflegends.servicios.PartidaService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/partida", produces="application/json")
public class PartidaController {
	
	 @Autowired
	 private PartidaService partidaService;
	 
	  @GetMapping
	  public ResponseEntity<List<Partida>> getAllPartidas() {
		  try {
				List<Partida> resultado = new ArrayList<>();
				
				resultado = partidaService.buscarTodos();
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Partida> getPartidaById(@PathVariable("id") long id) {
		  try {
				

				Optional<Partida> resultado= partidaService.buscarPorId(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  @GetMapping("/servidor/{id}")
	  public ResponseEntity<List<Partida>> getPartidasPorServidor(@PathVariable("id") long id) {
		  try {
				List<Partida> resultado = new ArrayList<>();
				
				resultado = partidaService.buscarPorServidor(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  @PostMapping
	  public ResponseEntity<Partida> createPartida(@RequestBody PartidaDTO partida) {
		  try {
				
				//VALIDAR ATRIBUTOS
				
			  Partida nuevaPartida=this.partidaService.crear(partida);

				URI uri = crearURIPartida(nuevaPartida);

				return ResponseEntity.created(uri).body(nuevaPartida);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deletePartida(@PathVariable("id") long id) {
	   
		  try {
				Optional<Partida> partida = partidaService.buscarPorId(id);
				if (partida.isPresent()) {
					partidaService.eliminar(partida.get());
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  private URI crearURIPartida(Partida nuevaPartida) {
			// TODO Auto-generated method stub
			return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevaPartida.getId()).toUri();
	  }
}
