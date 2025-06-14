package com.crimson.app.crimson.view.login;

import com.crimson.app.crimson.config.AuthenticatedUser;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "login")
@PageTitle("Login")
@AnonymousAllowed
public class LogInView extends VerticalLayout  implements BeforeEnterObserver {//

    private static final long serialVersionUID = 1L;
    private LoginOverlay loginForm = new LoginOverlay();

    private final AuthenticatedUser authenticatedUser;

    public LogInView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.setAction("login");
        loginForm.setTitle("CRIMSON");
        loginForm.setDescription("Enter your Email Address and Password.");
        loginForm.setOpened(true);

        add( new H1("CRIMSON"), loginForm);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in

            event.forwardTo("");
        }

    }
}
