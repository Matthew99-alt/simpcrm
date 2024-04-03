package com.crm.example.service;

import com.crm.example.entity.dto.ProgramDTO;
import com.crm.example.entity.model.Program;
import com.crm.example.reposotory.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    public final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> findAllUsers() {
        return programRepository.findAll();
    }

    public Program saveProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        return programRepository.save(program);
    }
    public void deleteProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setId(programDTO.getId());
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        programRepository.delete(program);
    }

    public Program editProgram(ProgramDTO programDTO){
        Program program = new Program();
        program.setId(programDTO.getId());
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        return programRepository.save(program);
    }
}
