package br.com.tjf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tjf.model.ContentModel;

public interface ContentRepository extends JpaRepository<ContentModel, Integer>{

}
