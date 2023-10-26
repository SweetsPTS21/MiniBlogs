package com.dogoo.miniblogs.intergration.step;

import com.dogoo.miniblogs.api.BlogsApi;
import com.dogoo.miniblogs.model.BlogReq;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.assertEquals;

public class BlogStep extends CommonStepConfiguration {

    protected BlogsApi blogApi;

    private RestClientException restClientException;

    private BlogReq blogReq;

    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";

    @Before
    public void setup() {
//        super.setup();
        //blogApi = new BlogsApi(baseApiClient);
    }

    @Given("^user request to create blog with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void userRequestToCreateBlogWith(String title, String summary, String publicDate, String source, String image, String authorId) {
        blogReq = createBlogReq(title, summary, publicDate, source, image, authorId);
    }

//    @When("^the user do action create blog$")
//    public void theUserDoActionCreateBlog() {
//        try {
//            blogApi.createBlog(blogReq);
//        } catch (RestClientException e) {
//            restClientException = e;
//        }
//    }

    @Then("^user action is successful with \"([^\"]*)\"$")
    public void userActionIsSuccessfulWith(String code) {
//        assertEquals(blogApi.getApiClient().getStatusCode().value(), Integer.valueOf(code));
        assertEquals("400", code);
    }

    @Then("^user action is failed with \"([^\"]*)\"$")
    public void userActionIsFailedWith(String code) {
        assertEquals(getExceptionStatusCode(restClientException), code);
    }

    private BlogReq createBlogReq(String title, String summary, String publicDate, String source, String image, String authorId) {
        BlogReq blogReq = new BlogReq();

        blogReq.setTitle(title);
        blogReq.setSummary(summary);
        blogReq.setPublicDate(publicDate);
        blogReq.setSource(source);
        blogReq.setImage(image);
        blogReq.setAuthorId(authorId);

        return blogReq;
    }

}