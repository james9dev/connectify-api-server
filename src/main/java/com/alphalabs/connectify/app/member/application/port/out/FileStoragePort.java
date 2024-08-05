package com.alphalabs.connectify.app.member.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStoragePort {
	boolean deletePicture(String key);
	String savePicture(MultipartFile image) throws IOException;
}
