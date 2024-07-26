package com.alphalabs.connectify.config;

import org.springframework.web.client.RestClient;

public interface HttpInterfaceFactory {

	<S> S create(Class<S> clientClass, RestClient restClient);

}
