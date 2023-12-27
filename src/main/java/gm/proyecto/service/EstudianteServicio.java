package gm.proyecto.service;

import gm.proyecto.model.Estudiante;
import gm.proyecto.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio implements IEstudianteServicio {

    @Autowired
    private EstudianteRepository estudianteRepository;


    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);

    }

    @Override
    public void eliminarEstudiante(Integer idEstudiante) {
        estudianteRepository.deleteById(idEstudiante);
    }
}
