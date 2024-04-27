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
import uvigo.si.leagueoflegends.entidades.Habilidad;
import uvigo.si.leagueoflegends.servicios.HabilidadService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/habilidad", produces="application/json")
public class HabilidadController {
	
	 @Autowired
	 private HabilidadService habilidadService;

	  @GetMapping
	  public ResponseEntity<List<Habilidad>> getAllHabilidades() {
		  try {
				List<Habilidad> resultado = new ArrayList<>();

				resultado = habilidadService.buscarTodos();
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Habilidad> getHabilidadById(@PathVariable("id") long id) {
		  try {
				

				Optional<Habilidad> resultado= habilidadService.buscarPorId(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @PostMapping
	  public ResponseEntity<Habilidad> createHabilidad(@RequestBody Habilidad habilidad) {
		  try {
				
				//VALIDAR ATRIBUTOS
				
			  Habilidad nuevoHabilidad=this.habilidadService.crear(habilidad);

				URI uri = crearURIHabilidad(nuevoHabilidad);

				return ResponseEntity.created(uri).body(nuevoHabilidad);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  @GetMapping("/nombre")
	  public ResponseEntity<List<Habilidad>> getHabilidadByName(@RequestParam(name = "name", required = false)String name) {
		  try {
				List<Habilidad> resultado = new ArrayList<>();

				resultado = habilidadService.buscarPorName(name);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  @GetMapping("/campeon/{id}")
	  public ResponseEntity<List<Habilidad>> getHabilidadByChampion(@PathVariable("id") long id) {
		  try {
				List<Habilidad> resultado = new ArrayList<>();

				resultado = habilidadService.buscarPorCampeon(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }


 
	@PutMapping("/{id}")
	  public ResponseEntity<Habilidad> updateHabilidad(@PathVariable("id") long id, @RequestBody  Habilidad habilidad) {
		Optional<Habilidad> habilidadOptional = habilidadService.buscarPorId(id);

		if (habilidadOptional.isPresent()) {
			Habilidad habilidadActualizado = habilidadService.modificar(habilidad);
			return new ResponseEntity<>(habilidadActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteHabilidad(@PathVariable("id") long id) {
	   
		  try {
				Optional<Habilidad> habilidad = habilidadService.buscarPorId(id);
				if (habilidad.isPresent()) {
					habilidadService.eliminar(habilidad.get());
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  private URI crearURIHabilidad(Habilidad nuevaHabilidad) {
			// TODO Auto-generated method stub
			return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevaHabilidad.getId()).toUri();
	  }
}
