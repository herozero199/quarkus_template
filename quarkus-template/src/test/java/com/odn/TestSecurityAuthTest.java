package com.odn;

import com.odn.customer.CustomerResource;
import com.odn.security.jwt.TokenUtils;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.jwt.Claims;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
public class TestSecurityAuthTest {
    private static String token;
    private static String generateToken() throws Exception {
        String claimsJson = "/JwtClaims.json";
        HashMap<String, Long> timeClaims = new HashMap<>();
        long exp = TokenUtils.currentTimeInSecs() + 300000L;
        timeClaims.put(Claims.exp.name(), exp);
        token = TokenUtils.generateTokenString(claimsJson, timeClaims);
        System.out.println("Jwt Token:" +token);
        return token;
    }

    public static String getToken() {
        if (StringUtils.isBlank(token)) {
            try {
                token = generateToken();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return token;
    }

//    @Test
//    @TestSecurity(user = "longlv", roles = "Tester")
//    public void testJwt() {
//        RestAssured.when().get("/customers").then()
//                .body(is("userJwt:viewer"));
//    }
//
//    @Test
//    @TestSecurity(user = "longlv", roles = "Tester")
//    @JwtSecurity(claims = {
//            @Claim(key = "user", value = "longlv")
//    })
//    public void testJwtWithClaims() {
//        RestAssured.when().get("/customers").then()
//                .body(is("userJwt:viewer:user@gmail.com"));
//    }
}
