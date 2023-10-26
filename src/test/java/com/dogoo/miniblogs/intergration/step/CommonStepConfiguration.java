package com.dogoo.miniblogs.intergration.step;

import com.dogoo.miniblogs.MiniblogsApplication;
//import com.dogoo.miniblogs.handler.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClientException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {MiniblogsApplication.class}, loader = SpringBootContextLoader.class)
@TestPropertySource("/application-test.properties")
public class CommonStepConfiguration {

    private static final String BASE_PATH = "/miniblogs/test/v1";

//    protected ApiClient baseApiClient;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    protected ApplicationContext context;
//
//    public void setup() {
//        if (baseApiClient == null) {
//            String url = "http://localhost:" + port + BASE_PATH;
//
//            baseApiClient = new ApiClient();
//            baseApiClient.setBasePath(url);
//        }
//    }

    protected String getExceptionStatusCode(RestClientException restClientException) {
        return restClientException.getLocalizedMessage().trim().substring(0, 3);
    }
}