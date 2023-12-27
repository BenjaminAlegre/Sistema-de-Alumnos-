package gm.proyecto;

import gm.proyecto.service.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ProyectoApplication.class);
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("********** INICIO DE LA APLICACIÓN **********");

		SpringApplication.run(ProyectoApplication.class, args);
		logger.info("********** FIN DE LA APLICACIÓN **********");
	}



	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando run de Spring" + nl);
	}
}
