package com.tsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.tsb.entity.BlogArticle;
import com.tsb.entity.BlogArticleRepository;
import com.tsb.entity.Message;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(path = "/article")
@RestController
public class articleService {

	@Autowired
	private SQLServerDataSource datasource;
	
	@Autowired
	private BlogArticleRepository blogarticleRep;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@GetMapping(path = "/query/{id}")
	public BlogArticle queryById(@PathVariable("id") String no) {
		 BlogArticle ba = blogarticleRep.findByNo(no);
		return ba;
	}
	
	@GetMapping(path = "/query/all")
	public List<BlogArticle> queryAll(){
		List<BlogArticle> baAll = blogarticleRep.findAll();
		return baAll;
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Message> delete(@PathVariable(name = "id")String no){
		ResponseEntity<Message> httpEntity =null;
		Message msg = new Message();
		
		BlogArticle article = blogarticleRep.findByNo(no);
		if(article == null) {
			msg.setCode(400);
			msg.setMsg("找不到該文章");
			httpEntity = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			return httpEntity;
		}
		try {
			blogarticleRep.delete(article);
		} catch (Exception e) {
			msg.setCode(400);
			msg.setMsg("刪除文章有問題，請檢查");
			httpEntity = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		msg.setCode(200);
		msg.setMsg("刪除成功");
		httpEntity = new ResponseEntity<>(msg, HttpStatus.OK);
		return httpEntity;
	}
	
	@PutMapping(path = "/update")
	public Message update(@RequestBody BlogArticle blogArticle, HttpServletResponse response) {
		Message msg = new Message();
		String sql = "update blog_article set title = ?, content = ? where no = ?";
		int count = jdbcTemplate.update(sql, blogArticle.getTitle(), blogArticle.getContent(), blogArticle.getNo());
		if(count >0) {
			msg.setCode(200);
			msg.setMsg("更新文章成功");
		}else {
			msg.setCode(400);
			msg.setMsg("文章更新不到，請確認");
		}
		return msg;
	}
	
}
