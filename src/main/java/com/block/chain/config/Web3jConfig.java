package com.block.chain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Configuration
public class Web3jConfig {
    @Value("${spring.blockchain.rpc.url}")
    private String rpcUrl;

    @Value("spring.blockchain.private.key")
    private String privateKey;

    @Bean
    public Web3j web3j() throws IOException {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println("web3j Web3ClientVersion: " + web3ClientVersion);
        return web3j;
    }
}
