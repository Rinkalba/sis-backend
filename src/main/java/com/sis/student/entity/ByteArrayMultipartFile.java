package com.sis.student.entity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class ByteArrayMultipartFile implements MultipartFile {
	 private final byte[] content;
	    private final String filename;

	    public ByteArrayMultipartFile(byte[] content, String filename) {
	        this.content = content;
	        this.filename = filename;
	    }
	   @Override
	    public boolean isEmpty() {
	        return content == null || content.length == 0;
	    }

	    @Override
	    public long getSize() {
	        return content.length;
	    }

	    @Override
	    public byte[] getBytes() throws IOException {
	        return content;
	    }

	    @Override
	    public InputStream getInputStream() throws IOException {
	        return new ByteArrayInputStream(content);
	    }


		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getOriginalFilename() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void transferTo(File dest) throws IOException, IllegalStateException {
			  try(FileOutputStream fos = new FileOutputStream(dest)) {
		            fos.write(content);
		        }
			
		}
}

