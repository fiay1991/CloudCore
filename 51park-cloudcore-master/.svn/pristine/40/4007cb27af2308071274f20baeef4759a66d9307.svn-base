package com.park.cloudcore.properites;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("keysConfig")
public class KeysConfig {
    @Value("${privateKey}")
    private String privateKey;
    @Value("${publicKey}")
    private String publicKey;
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
