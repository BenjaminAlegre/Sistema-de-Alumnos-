package gm.proyecto;

import gm.proyecto.model.Estudiante;
import gm.proyecto.service.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

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
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
    			*** Sistema de Estudiantes ***
				1. Listar estudiantes
				2. Buscar estudiante
				3. Agregar estudiante
				4. Modificar estudiante
				5. Eliminar estudiante
				6. Salir
				Elige una opcion:""");
	}

	private boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info("Listado de estudiantes" + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + nl));
			}
			case 2 -> {
				logger.info("Buscar estudiante:" + nl);
				var idEstudiante = Integer.parseInt(consola.nextLine());

				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					logger.info("Estudiante encontrado: "+ estudiante.toString());
				} else {
					logger.info("Estudiante no encontrado");
				}
			}
			case 3 -> {
				logger.info("Agregar estudiante: " + nl);
				logger.info("Ingrese el nombre del estudiante: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del estudiante: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el email del estudiante: ");
				var email = consola.nextLine();
				logger.info("Ingrese el telefono del estudiante: ");
				var telefono = consola.nextLine();
				Estudiante estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setEmail(email);
				estudiante.setTelefono(telefono);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante guardado con éxito" + estudiante + nl);
			}
			case 4 -> {
				logger.info("Modificar estudiante: " + nl);
				logger.info("Ingrese el id del estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					logger.info("Estudiante encontrado: "+ estudiante.toString());
					logger.info("Ingrese el nombre del estudiante: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del estudiante: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el email del estudiante: ");
					var email = consola.nextLine();
					logger.info("Ingrese el telefono del estudiante: ");
					var telefono = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setEmail(email);
					estudiante.setTelefono(telefono);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado con éxito" + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado con id: " + idEstudiante + nl);
				}
			}

			case 5 -> {
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Ingrese el id del estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					estudianteServicio.eliminarEstudiante(idEstudiante);

					logger.info("Estudiante eliminado con éxito" + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado con id: " + idEstudiante + nl);
				}

			}
			case 6 -> {
				logger.info("Saliendo del sistema..." + nl);
				salir = true;
			}
			default -> logger.info("Opción no válida" + nl);
		}
		return salir;
	}
}
