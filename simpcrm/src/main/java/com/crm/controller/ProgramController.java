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
    public List<ProgramDTO> getAllProgram() {
        return programService.findAllPrograms();
    }

    @GetMapping("/title")
    public ProgramDTO getByTitle(@RequestBody ProgramDTO programDTO) {
        return programService.findByTitle(programDTO.getTitle());
    }

    @GetMapping("/price")
    public List<ProgramDTO> getByPrice(@RequestBody ProgramDTO programDTO) {
        return programService.findByPrice(programDTO.getPrice());
    }

    @PostMapping("/save")
    public ProgramDTO saveProgram(@RequestBody ProgramDTO programDTO) {
        return programService.saveProgram(programDTO);
    }

    @DeleteMapping("/delete")
    public void deleteProgram(@RequestParam Long id) {
        programService.deleteProgram(id);
    }

    @PutMapping("/edit")
    public ProgramDTO editProgram(@RequestBody ProgramDTO programDTO){ return programService.editProgram(programDTO);}

}
