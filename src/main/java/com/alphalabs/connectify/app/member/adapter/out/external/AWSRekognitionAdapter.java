package com.alphalabs.connectify.app.member.adapter.out.external;

import com.alphalabs.connectify.app.member.application.port.out.DetectModerationPort;
import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PersistenceAdapter
public class AWSRekognitionAdapter implements DetectModerationPort {

	private final RekognitionClient rekognitionClient;
	private final String bucket;

	public AWSRekognitionAdapter(RekognitionClient rekognitionClient, @Value("${aws.s3.bucket-name}") String bucket) {
		this.rekognitionClient = rekognitionClient;
		this.bucket = bucket;
	}

	@Override
	public List<String> detectModerationLabels(InputStream sourceStream /*String sourceImage*/) {

		//InputStream sourceStream = new FileInputStream(sourceImage);
		SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

		Image image = Image.builder()
				.bytes(sourceBytes)
				.build();

		return this.detectModerationLabels(image);
	}

	@Override
	public List<String> detectModerationLabelsByFileName(String imagePath) {

		String bucket = this.bucket;

		S3Object s3Object = S3Object.builder()
				.bucket(bucket)
				.name(imagePath)
				.build();

		Image image = Image.builder()
				.s3Object(s3Object)
				.build();

		return this.detectModerationLabels(image);
	}


	public List<String> detectModerationLabels(Image image) {

		try {
			DetectModerationLabelsRequest moderationLabelsRequest = DetectModerationLabelsRequest.builder()
					.image(image)
					.minConfidence(60F)
					.build();

			DetectModerationLabelsResponse moderationLabelsResponse = rekognitionClient.detectModerationLabels(moderationLabelsRequest);

			List<ModerationLabel> labels = moderationLabelsResponse.moderationLabels();

			List<String> moderationLabelNames = new ArrayList<>();

			if (labels.isEmpty()) {
				log.info("Undetected labels for image");
			} else {
				log.info("Detected labels for image");

				for (ModerationLabel label : labels) {
					log.info("Label: {}\n Confidence: {}%\n Parent:{}", label.name(), label.confidence().toString(), label.parentName());

					moderationLabelNames.add(label.name());
				}
			}

			//rekognitionClient.close(); //싱글톤이라.. 닫으면 에러 발생

			return moderationLabelNames;

		} catch (RekognitionException e) {
			//rekognitionClient.close();

			throw e;
		}

	}

}
