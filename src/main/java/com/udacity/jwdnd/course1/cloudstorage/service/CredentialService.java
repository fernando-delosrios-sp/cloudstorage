
package com.udacity.jwdnd.course1.cloudstorage.service;

import java.security.SecureRandom;
import java.util.Base64;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void save(String credentialId, String url, String userName, String password, Integer userId) {
        String key;
        String hashedPassword;

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        key = Base64.getEncoder().encodeToString(salt);
        hashedPassword = encryptionService.encryptValue(password, key);

        if (credentialId == null || credentialId.length() < 1) {
            credentialMapper.create(url, userName, key, hashedPassword, userId);
        } else {
            Credential credential = new Credential(Integer.valueOf(credentialId), url, userName, key, hashedPassword, userId);
            credentialMapper.save(credential);
        }
    }

    public Credential[] list(Integer userId) {
        Credential[] credentials = credentialMapper.list(userId);
        String password;
        for (int i = 0; i < credentials.length; i++) {
            password = encryptionService.decryptValue(credentials[i].getHashedPassword(), credentials[i].getKey());
            credentials[i].setPassword(password);;
        }
        return credentials;
    }

    // public Credential get(Integer credentialId) {
    //     return credentialMapper.get(credentialId);
    // }

    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}
