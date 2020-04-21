package com.abdo.document.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    private String fileName;

    private Date insertionDate;

    private String fileType;

    private String userUploader;

}
