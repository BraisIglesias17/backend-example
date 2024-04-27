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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uvigo.si.leagueoflegends.entidades.Campeon;
import uvigo.si.leagueoflegends.servicios.CampeonService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/campeones", produces="application/json")
public class CampeonController {
	
	 @Autowired
	 private CampeonService campeonService;

	  @GetMapping
	  public ResponseEntity<List<Campeon>> getAllCampeon() {
		  try {
				List<Campeon> resultado = new ArrayList<>();

				resultado = campeonService.buscarTodos();
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  @GetMapping("/nombre")
	  public ResponseEntity<List<Campeon>> getCampeonByName(@RequestParam(name = "name", required = false)String nickname) {
		  try {
				List<Campeon> resultado = new ArrayList<>();

				resultado = campeonService.buscarPorName(nickname);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }


	  @GetMapping("/{id}")
	  public ResponseEntity<Campeon> getCampeonById(@PathVariable("id") long id) {
		  try {
				

				Optional<Campeon> resultado= campeonService.buscarPorId(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @PostMapping
	  public ResponseEntity<Campeon> createCampeon(@RequestBody Campeon jugador) {
		  try {
				
				//VALIDAR ATRIBUTOS
				
			  Campeon nuevoJugador=this.campeonService.crear(jugador);

				URI uri = crearURICampeon(nuevoJugador);

				return ResponseEntity.created(uri).body(nuevoJugador);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

 
	@PutMapping("/{id}")
	  public ResponseEntity<Campeon> updateCampeon(@PathVariable("id") long id, @RequestBody  Campeon campeon) {
		Optional<Campeon> jugadorOptional = campeonService.buscarPorId(id);

		if (jugadorOptional.isPresent()) {
			Campeon jugadorActualizado = campeonService.modificar(campeon);
			return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteCampeon(@PathVariable("id") long id) {
	   
		  try {
				Optional<Campeon> campeon = campeonService.buscarPorId(id);
				if (campeon.isPresent()) {
					campeonService.eliminar(campeon.get());
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  private URI crearURICampeon(Campeon Campeon) {
			// TODO Auto-generated method stub
			return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Campeon.getId()).toUri();
	  }
}
