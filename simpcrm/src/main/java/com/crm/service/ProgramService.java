package com.crm.service;

import com.crm.dto.ProgramDTO;
import com.crm.entity.Program;
import com.crm.reposotiry.ProgramRepository;
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

    public List<Program> findByTitle(String title){return programRepository.findByTitle(title);}

    public List<Program> findByPrice(Long price){return programRepository.findByPrice(price);}

    private Program makeAProgram(ProgramDTO programDTO, boolean idEnable){
        Program program = new Program();
        if(idEnable){
            program.setId(programDTO.getId());
        }
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setPrice(programDTO.getPrice());

        return program;
    }

    public Program saveProgram(ProgramDTO programDTO) {
        return programRepository.save(makeAProgram(programDTO, false));
    }
    public void deleteProgram(ProgramDTO programDTO) {
        programRepository.delete(makeAProgram(programDTO, true));
    }

    public Program editProgram(ProgramDTO programDTO){
        return programRepository.save(makeAProgram(programDTO, true));
    }
}
