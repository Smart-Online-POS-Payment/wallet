package com.sopp.wallet.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    val clientBuilder: WebClient.Builder,
) {
    @Bean("istepayWebClient")
    fun istepayWebClient(
        @Value("\${istepay.url}") baseUrl: String,
    ): WebClient {
        return clientBuilder.baseUrl(baseUrl).build()
    }

    @Bean("authWebClient")
    fun authWebClient(
        @Value("\${sopp.server.auth.url}") baseUrl: String,
    ): WebClient {
        return clientBuilder.baseUrl(baseUrl).build()
    }
}
