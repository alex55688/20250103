package com.tsb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(name = "blog_article")
@Entity
public class BlogArticle {
	
	@Id
	@Column(name = "no", nullable = false)
	private String no;
	
	@Column(name = "title", nullable = true)
	private String title;
	
	@Column(name= "content", nullable = true)
	private String content;
}
