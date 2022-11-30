package com.gs.studyManyUseFrame;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StudyManyUseFrameApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void jasypt() {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String userName = "publicConnect";
		String userPassword = "gsitmLee$$";


		System.out.println("URL==============================");
		System.out.println(jasyptEncoding(url));
		System.out.println("userName=========================");
		System.out.println(jasyptEncoding(userName));
		System.out.println("userPassword======================");
		System.out.println(jasyptEncoding(userPassword));
	}

	public String jasyptEncoding(String value) {

		String key = "gsitm";
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(key);
		config.setAlgorithm("PBEWithMD5AndDes");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		pbeEnc.setConfig(config);
		return pbeEnc.encrypt(value);
	}

}
