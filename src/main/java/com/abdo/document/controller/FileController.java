package com.abdo.document.controller;

import com.abdo.document.entity.Document;
import com.abdo.document.service.DocumentService;
import com.abdo.document.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
@Controller
public class FileController {

    private FileService fileService;

    private DocumentService documentService;

    @GetMapping({"/home", "/", ""})
    public ModelAndView prepareHomePage(@ModelAttribute ArrayList<Document> documents, Authentication authentication) {
        return documentService.getAllDocumentsAsModel(authentication);
    }

    @PostMapping(path = "home")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, Authentication authentication, @ModelAttribute ArrayList<Document> documents) {
        String updatedFileName = fileService.saveFileToLocal(file);
        documentService.saveDocument(file, updatedFileName, authentication);
        return documentService.getAllDocumentsAsModel(authentication);
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFileFromLocal(@PathVariable String fileName) {
        return documentService.getResponseEntity(fileName);
    }

    @GetMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) throws IOException {
        documentService.deleteFile(fileName);
        return "redirect:/";
    }


    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
