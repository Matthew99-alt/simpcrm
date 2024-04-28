package com.crm.service;

import com.crm.dto.ProgramDTO;
import com.crm.entity.Program;
import com.crm.reposotiry.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    public final ProgramRepository programRepository;

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }

    public List<Program> findByTitle(String title) {
        return programRepository.findByTitle(title);
    }

    public List<Program> findByPrice(Long price) {
        return programRepository.findByPrice(price);
    }

    private Program makeAProgram(ProgramDTO programDTO, Program program) {
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        return program;
    }

    public Program saveProgram(ProgramDTO programDTO) {
        Program program = new Program();
        return programRepository.save(makeAProgram(programDTO, program));
    }

    public void deleteProgram(ProgramDTO programDTO) {
        programRepository.deleteById(programDTO.getId());
    }

    public Program editProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setId(programDTO.getId());
        return programRepository.save(makeAProgram(programDTO, program));
    }
}
