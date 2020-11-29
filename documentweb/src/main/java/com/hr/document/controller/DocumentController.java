package com.hr.document.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.hr.document.entity.Document;
import com.hr.document.repos.DocumentRepository;

@Controller
public class DocumentController {

	@Autowired
	private DocumentRepository documentRepository;

	@RequestMapping("/displayUpload")
	public String displayUpload(ModelMap modelMap) {
		List<Document> documents = documentRepository.findAll();
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartfile,@RequestParam("id") long id, ModelMap modelMap) {
		Document document = new Document();
		document.setId(id);
		document.setName(multipartfile.getOriginalFilename());
		try {
			document.setData(multipartfile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		documentRepository.save(document);
		
		List<Document> documents = documentRepository.findAll();
		modelMap.addAttribute("documents", documents);
		
		return "documentUpload";
	}
	
	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") long id, HttpServletResponse response) {
		Document document = documentRepository.findById(id).get();
		//to send back the data
		byte[] data = document.getData();
		
		//content dosiposition tells browser that an attachement is coming
		//2nd parameter means that use this name to save the atachment wiht this name
		response.setHeader("Content-Disposition", "attachment:filename-downloaded.jpeg");
		
		
		return OutputStream->{
			OutputStream.write(data);
		};
	}
}
