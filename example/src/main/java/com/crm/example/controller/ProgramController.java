package com.crm.example.controller;

import com.crm.example.entity.dto.BookDTO;
import com.crm.example.entity.dto.ProgramDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.Program;
import com.crm.example.entity.model.User;
import com.crm.example.service.ProgramService;
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
        return programService.findAllUsers();
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
