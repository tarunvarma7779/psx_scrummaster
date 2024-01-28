package com.posidex;

import org.springframework.boot.test.context.SpringBootTest;

import com.posidex.util.StringEncrypter;

@SpringBootTest
class ScrumMasterApplicationTests {

	public static void main(String[] args) throws Exception {
		System.out.println(StringEncrypter.decrypt("xwibIVA6C4ep2hPjFzXnMg=="));;
	}

}
