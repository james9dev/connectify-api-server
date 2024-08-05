package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.application.port.in.command.UploadPictureCommand;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface UploadPictureUseCase {
	ProfileDomain.ProfilePicture uploadPicture(UploadPictureCommand uploadPictureCommand) throws IOException;
	ProfileDomain.ProfilePicture uploadPictureNoncheck(UploadPictureCommand uploadPictureCommand) throws IOException;
}
