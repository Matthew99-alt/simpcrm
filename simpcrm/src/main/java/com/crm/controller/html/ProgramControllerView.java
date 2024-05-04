package com.crm.controller.html;

import com.crm.dto.ProgramDTO;
import com.crm.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramControllerView {

    private final ProgramService programService;


    @GetMapping("/all")
    public String getAllPrograms(Model model) {
        List<ProgramDTO> allPrograms = programService.findAllPrograms();
        model.addAttribute("programs", allPrograms);
        return "program/program.html";
    }

    @GetMapping("/personalPage/{userId}")
    public String getProgram(@PathVariable("userId") Long userId, Model model) {
        ProgramDTO programDTO = programService.findById(userId);
        model.addAttribute("program", programDTO);
        return "programs/personal_program.html";
    }

}
