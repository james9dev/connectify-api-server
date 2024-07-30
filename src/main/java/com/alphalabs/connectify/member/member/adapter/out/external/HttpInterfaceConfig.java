package com.alphalabs.connectify.member.member.adapter.out.external;

import com.alphalabs.connectify.common.httpinterface.HttpInterfaceFactory;
import com.alphalabs.connectify.common.httpinterface.SimpleHttpInterfaceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class HttpInterfaceConfig {

	private final HttpInterfaceFactory httpInterfaceFactory;

	public HttpInterfaceConfig() {
		this.httpInterfaceFactory = new SimpleHttpInterfaceFactory();
	}

	@Bean
	public HttpKakaoAuthClient kakaoAuthClient(@Value("${external-api.kakaoauth.url}") String clientUrl) {
		return httpInterfaceFactory.create(HttpKakaoAuthClient.class, createRestClient(clientUrl));
	}

	private RestClient createRestClient(String baseUrl) {
		return RestClient.builder()
				.baseUrl(baseUrl)
				.defaultStatusHandler(
						HttpStatusCode::is4xxClientError,
						(request, response) -> {
							log.error("Client Error Code={}", response.getStatusCode());
							log.error("Client Error Message={}", new String(response.getBody().readAllBytes()));
						})
				.defaultStatusHandler(
						HttpStatusCode::is5xxServerError,
						(request, response) -> {
							log.error("Server Error Code={}", response.getStatusCode());
							log.error("Server Error Message={}", new String(response.getBody().readAllBytes()));
						})
				.build();
	}
}