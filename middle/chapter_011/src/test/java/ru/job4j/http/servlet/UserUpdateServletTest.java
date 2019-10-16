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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {
    @Test
    public void updateTest() throws ServletException, IOException {
        Store validate = new ValidateServiceStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        User user = new User("StubName", "StubLogin", "StubEmail");
        validate.add(user);
        when(req.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        when(req.getParameter("name")).thenReturn("newStubName");
        when(req.getParameter("login")).thenReturn("newStubLogin");
        when(req.getParameter("password")).thenReturn(user.getPassword());
        when(req.getParameter("email")).thenReturn(user.getEmail());
        when(req.getParameter("role")).thenReturn(user.getRole());
        when(req.getParameter("createdDate")).thenReturn(String.valueOf(user.getCreateDate()));
        HttpSession sessionMock = mock(HttpSession.class);
        when(req.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("user")).thenReturn(user);
        new UserUpdateServlet().doPost(req, mock(HttpServletResponse.class));
        assertThat(validate.findAll().iterator().next().getName(), is("newStubName"));
        assertThat(validate.findAll().iterator().next().getLogin(), is("newStubLogin"));
    }
}