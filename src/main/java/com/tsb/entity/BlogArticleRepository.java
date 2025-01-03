package com.tsb.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BlogArticleRepository extends JpaRepository<BlogArticle,String>{
	
	List<BlogArticle> findAll();
	
	public BlogArticle findByNo(String no);
	
	
}
