
package com.crm.service;

import com.crm.dto.ProgramDTO;
import com.crm.entity.Program;
import com.crm.reposotiry.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    public final ProgramRepository programRepository;

    public List<ProgramDTO> findAllPrograms() {
        List<Program> programs = programRepository.findAll();
        ArrayList<ProgramDTO> programDTOS = new ArrayList<>();
        for (Program program : programs) {
            programDTOS.add(makeAProgramDTO(new ProgramDTO(), program));
        }
        return programDTOS;
    }

    public ProgramDTO findByTitle(String title) {
        Program program = programRepository.findByTitle(title);
        return makeAProgramDTO(new ProgramDTO(), program);
    }

    public ProgramDTO findById(Long id){
        Program program = programRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return makeAProgramDTO(new ProgramDTO(), program);
    }

    public List<ProgramDTO> findByPrice(Long price) {
        List<Program> programs = programRepository.findByPrice(price);
        ArrayList<ProgramDTO> programDTOS = new ArrayList<>();
        for (Program program : programs) {
            programDTOS.add(makeAProgramDTO(new ProgramDTO(), program));
        }
        return programDTOS;
    }

    public ProgramDTO makeAProgramDTO(ProgramDTO programDTO, Program program) {
        programDTO.setId(program.getId());
        programDTO.setTitle(program.getTitle());
        programDTO.setDescription(program.getDescription());
        programDTO.setPrice(program.getPrice());

        return programDTO;
    }

    public Program makeAProgram(ProgramDTO programDTO, Program program) {
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        return program;
    }

    public ProgramDTO saveProgram(ProgramDTO programDTO) {
        Program program = new Program();
        programRepository.save(makeAProgram(programDTO, program));
        return programDTO;
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    public ProgramDTO editProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setId(programDTO.getId());
        programRepository.save(makeAProgram(programDTO, program));
        return programDTO;
    }
}
