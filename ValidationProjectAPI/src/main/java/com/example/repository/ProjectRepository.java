package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer>{

}
