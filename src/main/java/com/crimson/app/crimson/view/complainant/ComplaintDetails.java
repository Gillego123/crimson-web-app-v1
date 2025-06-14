package com.crimson.app.crimson.view.complainant;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ComplaintDetails extends VerticalLayout {

    public ComplaintDetails(String detailsId){
        add(new Span(detailsId));
    }
}
