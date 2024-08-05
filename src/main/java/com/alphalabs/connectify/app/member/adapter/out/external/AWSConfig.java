package com.alphalabs.connectify.app.member.adapter.out.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Configuration
class AWSConfig {

	@Bean
	public RekognitionClient rekognitionClient(@Value("${aws.credentials.access-key}") String accessKey, @Value("${aws.credentials.secret-key}") String secretKey)  {

		AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
		AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

		return RekognitionClient.builder()
				.region(Region.AP_NORTHEAST_2)
				.credentialsProvider(credentialsProvider)
				.build();
	}

	@Bean
	public S3Client s3Client(@Value("${aws.credentials.access-key}") String accessKey, @Value("${aws.credentials.secret-key}") String secretKey)  {

		AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
		AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);

		return S3Client.builder()
				.region(Region.AP_NORTHEAST_2)
				.credentialsProvider(credentialsProvider)
				.build();
	}
}