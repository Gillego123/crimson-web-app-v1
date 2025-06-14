package com.crimson.app.crimson.view.investigator;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@Route("investigation")
@RolesAllowed("ADMIN")
@Menu(order = 0, icon = LineAwesomeIconUrl.SEARCH_PLUS_SOLID)
@PageTitle("Investigation")
public class InvestigationView extends VerticalLayout{
    public InvestigationView() {

        add(new VerticalLayout(), new H1("Investigation View"));
    }
}
