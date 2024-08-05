package com.alphalabs.connectify.app.member.adapter.out.external;

import com.alphalabs.connectify.app.member.application.port.out.FileStoragePort;
import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@PersistenceAdapter
public class AWSS3Adapter implements FileStoragePort {
	private final S3Client s3Client;
	private final String bucket;

	public AWSS3Adapter(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucket) {
		this.s3Client = s3Client;
		this.bucket = bucket;
	}


	@Override
	public String savePicture(MultipartFile multipartFile) throws IOException {
		String fileName = multipartFile.getName();
		String originalFilename = multipartFile.getOriginalFilename();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucket)
				.key("profile/" + UUID.randomUUID())
				.contentType(multipartFile.getContentType())
				.contentLength(multipartFile.getSize())
				.build();

		PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));// s3에 업로드

		log.debug(putObjectResponse.toString());

		if (putObjectResponse.sdkHttpResponse().isSuccessful()) {
			log.debug("Picture saved to {}", putObjectRequest.key());
			return putObjectRequest.key();
		}

		return null;
	}

	@Override
	public boolean deletePicture(String key) {
		DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.build();

		DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(deleteObjectRequest);

		if (deleteObjectResponse.sdkHttpResponse().isSuccessful()) {
			return true;
		}

		return false;
	}
}
