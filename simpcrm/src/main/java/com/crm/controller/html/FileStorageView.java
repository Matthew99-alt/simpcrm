package com.crm.controller.html;

import com.crm.dto.FileStorage;
import com.crm.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fileStorage")
@RequiredArgsConstructor
public class
FileStorageView {

    private final FileStorageService fileStorageService;

    @GetMapping("/all")
    public String getAllFiles(Model model) {
        List<FileStorage> allFiles = fileStorageService.getAllFilesFromStorage();
        model.addAttribute("files", allFiles);
        return "files/fileStorage.html";
    }

}
