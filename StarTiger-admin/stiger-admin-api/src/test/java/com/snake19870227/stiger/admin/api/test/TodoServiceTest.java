package com.snake19870227.stiger.admin.api.test;

import com.snake19870227.stiger.admin.api.service.TodoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Bu HuaYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
public class TodoServiceTest {

    @MockBean
    private TodoService todoService;

    private void todoServiceMockBean(String arg) {
        BDDMockito.given(todoService.todo(arg)).willReturn("Hello " + arg);
    }

    @Test
    public void testTodo() {
        todoServiceMockBean("Snake");
        Assert.assertEquals("Hello Snake", todoService.todo("Snake"));
    }
}
