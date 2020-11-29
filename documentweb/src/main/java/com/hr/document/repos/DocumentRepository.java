package com.hr.document.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
