package com.example.cloudhuntchartbackend.service;

import com.example.cloudhuntchartbackend.entity.AuthorEntity;
import com.example.cloudhuntchartbackend.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }

    public Optional<AuthorEntity> findById(Long id) {
        return authorRepository.findById(id);
    }

    public void save(AuthorEntity node) {
        authorRepository.save(node);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

}