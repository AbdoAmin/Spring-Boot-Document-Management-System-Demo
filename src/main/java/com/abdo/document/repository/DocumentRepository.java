package com.abdo.document.repository;

import com.abdo.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, String> {

    @Query("select distinct d From Document d " +
            "join d.documentAuthorities auth " +
            "where auth.fileAuthority in :authorities")
    List<Document> findAllByAuthorities(List<String> authorities);

}
