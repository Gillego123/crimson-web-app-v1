package com.crimson.app.crimson.view;


import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;

import com.vaadin.flow.theme.material.Material;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Layout
@AnonymousAllowed
public class MainView extends AppLayout {

    private H1 viewTitle;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainView(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {

        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;


       //getElement().executeJs("this.shadowRoot.querySelector('[part=\"drawer\"]').style.backgroundColor = 'rgba(0, 0, 0, 0.5)';");

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);

    }

    private void addDrawerContent() {

        Span appName = new Span("CRIMSON");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        Optional<User> maybeUser = authenticatedUser.get();
        if(maybeUser.isPresent()){
            User user = maybeUser.get();
            Avatar avatar = new Avatar(user.getFirstName());
            //StreamResource resource = new StreamResource("profile-pic", () -> new ByteArrayInputStream(user.getProfilePicture()));
//            StreamResource imageResource = new StreamResource("logo.jpg",
//                    () -> getClass()
//                            .getResourceAsStream("src/main/resources/static/images/logo.jpg"));
//            avatar.setImageResource(imageResource);

            avatar.setImage("icons/icon.jpg");

            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");
            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add("Hello! " +user.getFirstName() + " " + user.getLastName());
            div.add(new Icon("lumo", "dropdown"));
            div.addClassNames(LumoUtility.Display.FLEX, LumoUtility.AlignItems.CENTER, LumoUtility.Gap.SMALL);
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });
            layout.add(userMenu);
        }else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        // Adjust position using inline styles
        nav.getStyle().set("margin-top", "50px");  // Adds top margin
        nav.getStyle().set("margin-bottom", "30px");  // Adds bottom margin
        //nav.getStyle().set("padding-left", "30px");  // Shifts the item inward
        List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
        menuEntries.forEach(entry -> {

            if (entry.icon() != null) {
                nav.addItem(new SideNavItem(entry.title(), entry.path(), new SvgIcon(entry.icon())));
            } else {
                nav.addItem(new SideNavItem(entry.title(), entry.path()));
            }
        });

        return nav;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        return MenuConfiguration.getPageHeader(getContent()).orElse("");
    }

}
