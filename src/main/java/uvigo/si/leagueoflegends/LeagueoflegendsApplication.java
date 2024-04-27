package uvigo.si.leagueoflegends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages= {"uvigo.si.leagueoflegends.entidades"})
public class LeagueoflegendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueoflegendsApplication.class, args);
	}

}
