package com.alphalabs.connectify.app.member.application.port.out;

import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.List;

public interface DetectModerationPort {
	List<String> detectModerationLabels(InputStream sourceStream);
	List<String> detectModerationLabelsByFileName(String image);
}
