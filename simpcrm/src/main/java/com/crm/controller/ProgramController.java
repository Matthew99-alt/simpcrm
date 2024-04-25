package com.crm.controller;

import com.crm.dto.ProgramDTO;
import com.crm.entity.Program;
import com.crm.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/program")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/all")
    public List<Program> getAllProgram() {
        return programService.findAllPrograms();
    }

    @GetMapping("/title")
    public List<Program> getByTitle(@RequestBody ProgramDTO programDTO) {
        return programService.findByTitle(programDTO.getTitle());
    }

    @GetMapping("/price")
    public List<Program> getByPrice(@RequestBody ProgramDTO programDTO) {
        return programService.findByPrice(programDTO.getPrice());
    }

    @PostMapping("/save")
    public Program saveProgram(@RequestBody ProgramDTO programDTO) {
        return programService.saveProgram(programDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody ProgramDTO programDTO) {
        programService.deleteProgram(programDTO);
    }

    @PutMapping("/edit")
    public Program editUser(@RequestBody ProgramDTO programDTO){ return programService.editProgram(programDTO);}

}
