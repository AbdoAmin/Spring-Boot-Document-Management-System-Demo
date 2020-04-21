package com.abdo.document.service;

import com.abdo.document.dto.DocumentDTO;
import com.abdo.document.entity.Document;
import com.abdo.document.entity.DocumentAuthority;
import com.abdo.document.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    private FileService fileService;

    private ModelMapper modelMapper;


    public List<Document> getAllDocuments(Authentication authentication) {
        return documentRepository.findAllByAuthorities(
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
    }

    public Document saveDocument(MultipartFile file, String updatedFileName, Authentication authentication) {
        Document document = new Document();
        document.setFileName(updatedFileName);
        document.setFileType(file.getContentType());
        document.setUserUploader(authentication.getName());
        document.setDocumentAuthorities(
                authentication.getAuthorities()
                        .stream()
                        .map((userAuth) -> new DocumentAuthority(null, updatedFileName, userAuth.getAuthority()))
                        .collect(Collectors.toSet()));
        return documentRepository.save(document);
    }

    public ResponseEntity<Resource> getResponseEntity(String fileName) {
        Resource resource = fileService.getResource(fileName);
        Optional<Document> documentOptional = documentRepository.findById(fileName);
        ResponseEntity.BodyBuilder ok = ResponseEntity.ok();
        ok = documentOptional.isPresent() ?
                ok.contentType(MediaType.parseMediaType(documentOptional.get().getFileType())) :
                ok;
        return ok.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    public void deleteFile(String fileName) throws IOException {
        documentRepository.deleteById(fileName);
        fileService.deleteFile(fileName);
    }

    public ModelAndView getAllDocumentsAsModel(Authentication authentication) {
        ArrayList<DocumentDTO> documents;

        documents = getAllDocuments(authentication)
                .stream()
                .map((document)->modelMapper.map(document,DocumentDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));
        return new ModelAndView("filesOverview", "documents", documents);
    }
}
