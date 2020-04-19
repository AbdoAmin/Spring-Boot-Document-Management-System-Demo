package com.abdo.document.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class Document {

    @Id
    private String fileName;

    @CreationTimestamp
    private Date insertionDate;

    private String fileType;

    private String userUploader;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fileName" ,cascade = CascadeType.ALL)
    private Set<DocumentAuthority> documentAuthorities;

}
