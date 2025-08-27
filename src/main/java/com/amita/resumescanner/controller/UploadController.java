package com.amita.resumescanner.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UploadController {
	
	@Value("${uploadPath}")
	private String uploadPath;

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {

		String uploadFileName = "";
	    String fileName = file.getOriginalFilename();
	    try {
	    	uploadFileName = uploadPath + fileName;
	    	System.out.println("File name: " + uploadFileName);
	      file.transferTo( new File(uploadFileName));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    return ResponseEntity.ok(uploadFileName);
	  }

	@GetMapping("/")
	public ResponseEntity<?> get() {
		return ResponseEntity.ok("Application running successfully.");
	}
}
