package ru.job4j.http.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersServletTest {
    @Test
    public void deleteTest() throws ServletException, IOException {
        Store validate = new ValidateServiceStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        User user = new User("StubName", "StubLogin", "StubEmail");
        validate.add(user);
        assertThat(validate.findAll().size(), is(1));
        when(req.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        new UsersServlet().doPost(req, mock(HttpServletResponse.class));
        assertThat(validate.findAll().size(), is(0));
    }
}