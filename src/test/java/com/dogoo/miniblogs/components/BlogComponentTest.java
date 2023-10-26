package com.dogoo.miniblogs.components;

import com.dogoo.miniblogs.api.BlogsApi;
import com.dogoo.miniblogs.model.Blog;
import com.dogoo.miniblogs.model.BlogContent;
import com.dogoo.miniblogs.model.BlogReq;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogComponentTest {
    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String BASE_PATH = "/miniblgos/test/v1";

    public static String ID = "600f1c7b9c3b9e0b7c7b3b3b";
    public static String AUTHOR_ID = "600f1c7b9c3b9e0b7c7b3b3b";
    public static String SUMMARY = "This is summary";
    public static String TITLE = "This is title";
    public static String SOURCE = "This is source";
    public static String AUTHOR = "This is author";
    public static String PUBLIC_DATE = "2021-01-01";
    public static String IMAGE = "This is image";
    public static List<String> CATEGORIES = Arrays.asList("category1", "category2");
    public static List<BlogContent> CONTENT = initContent();
    public static String CREATED_DATE = "2021-01-01";
    public static String UPDATED_DATE = "2021-01-01";
    public static boolean APPROVED = false;

    private BlogsApi api;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        String url = HTTP_LOCALHOST + port + BASE_PATH;
//        ApiClient client = new ApiClient();
//        client.setBasePath(url);
        //api = new PropertiesApi(client);
    }

    @Test
    public void ensureBlogCreated() {
        Blog property = api.createBlog(mockBlogReq()).getBody();

        assert property != null;
        assertBlog(property);
    }

    @Test
    public void ensureBlogCreateThrowExceptionWhenRequiredFieldNotPass() {
        thrown.expect(HttpClientErrorException.class);

        BlogReq propertyReq = mockBlogReq();
        propertyReq.setTitle("");

        api.createBlog(propertyReq);
    }

    @Test
    public void ensureBlogUpdated() {
        Blog property = api.updateBlog(ID, mockBlogReq()).getBody();

        assert property != null;
        assertBlog(property);
    }

    @Test
    public void ensureBlogUpdateThrowExceptionWhenWrongPassed() {
        thrown.expect(HttpClientErrorException.class);

        BlogReq propertyReq = mockBlogReq();
        propertyReq.setTitle("");

        api.updateBlog(ID + "FAKE", propertyReq);
    }

    private void assertBlog(Blog actual) {
        assertEquals(actual.getId(), ID);
        assertEquals(actual.getAuthorId(), AUTHOR_ID);
        assertEquals(actual.getSummary(), SUMMARY);
        assertEquals(actual.getTitle(), TITLE);
        assertEquals(actual.getSource(), SOURCE);
        assertEquals(actual.getPublicDate(), PUBLIC_DATE);
        assertEquals(actual.getImage(), IMAGE);
        assertEquals(actual.getCategories(), CATEGORIES);
        assertEquals(actual.getContent(), CONTENT);
        assertEquals(actual.getUpdateDate(), UPDATED_DATE);
        assertEquals(actual.getCreateDate(), CREATED_DATE);
        assertEquals(actual.getApproved(), APPROVED);
    }

    private BlogReq mockBlogReq() {
        BlogReq blogReq = new BlogReq();
        blogReq.setAuthorId(AUTHOR_ID);
        blogReq.setSummary(SUMMARY);
        blogReq.setTitle(TITLE);
        blogReq.setSource(SOURCE);
        blogReq.setPublicDate(PUBLIC_DATE);
        blogReq.setImage(IMAGE);
        blogReq.setCategories(CATEGORIES);
        blogReq.setContent(CONTENT);
        return blogReq;
    }

    public static List<BlogContent> initContent() {
        List<BlogContent> content = new ArrayList<>();
        BlogContent blogContent = new BlogContent();
        blogContent.setTitle("This is content");
        blogContent.setDetail("This is detail");

        content.add(blogContent);
        return content;
    }
}