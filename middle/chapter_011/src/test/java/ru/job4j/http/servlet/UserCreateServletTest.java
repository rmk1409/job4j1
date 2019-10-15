package ru.job4j.http.servlet;

import org.hamcrest.MatcherAssert;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO add comments
 *
 * @author Pogorelov Roman
 * @since 15.10.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    @Test
    public void creationTest() throws ServletException, IOException {
        Store validate = new ValidateServiceStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("StubName");
        when(req.getParameter("login")).thenReturn("StubLogin");
        when(req.getParameter("password")).thenReturn("StubPassword");
        when(req.getParameter("email")).thenReturn("StubEmail");
        when(req.getParameter("role")).thenReturn("StubRole");
        new UserCreateServlet().doPost(req, resp);
        User expected = validate.findAll().iterator().next();
        MatcherAssert.assertThat(expected.getName(), is("StubName"));
        MatcherAssert.assertThat(expected.getLogin(), is("StubLogin"));
        MatcherAssert.assertThat(expected.getPassword(), is("StubPassword"));
        MatcherAssert.assertThat(expected.getEmail(), is("StubEmail"));
        MatcherAssert.assertThat(expected.getRole(), is("StubRole"));
    }
}