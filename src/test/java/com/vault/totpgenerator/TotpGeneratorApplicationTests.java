package com.vault.totpgenerator;

import com.vault.dto.TOTPRequestPayload;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
class TotpGeneratorApplicationTests {

	@Test
	void validateTOTPPayload() {
		TOTPRequestPayload payload = new TOTPRequestPayload();

		payload.setGenerate(true);
	}

}
