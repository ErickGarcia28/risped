package com.example.risped.paciente.control;

import com.example.risped.paciente.model.Paciente;
import com.example.risped.paciente.model.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Obtener todos los pacientes
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    // Guardar un nuevo paciente
    @Transactional
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Actualizar un paciente existente
    @Transactional
    public Paciente update(Long id, Paciente paciente) {
        Optional<Paciente> existingPaciente = pacienteRepository.findById(id);
        if (existingPaciente.isPresent()) {
            Paciente updatedPaciente = existingPaciente.get();
            updatedPaciente.setNombre(paciente.getNombre());
            updatedPaciente.setApellido(paciente.getApellido());
            updatedPaciente.setAlergias(paciente.getAlergias());
            updatedPaciente.setPeso(paciente.getPeso());
            updatedPaciente.setAltura(paciente.getAltura());
            updatedPaciente.setEdad(paciente.getEdad());
            return pacienteRepository.save(updatedPaciente);
        }
        return null; // Manejar este caso según sea necesario
    }

    // Eliminar un paciente por ID
    @Transactional
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    // Buscar un paciente por ID
    @Transactional(readOnly = true)
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }
}
