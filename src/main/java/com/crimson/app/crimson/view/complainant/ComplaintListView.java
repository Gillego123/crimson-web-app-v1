package com.crimson.app.crimson.view.complainant;

import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.service.ComplaintCategoryServiceImp;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.crimson.app.crimson.view.component.CustomComplaintCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

@PageTitle("Add Complaint")
@Route("complaint")
@RolesAllowed({"ADMIN","COMPLAINANT","AGENT"})
@Menu(order = 0, icon = LineAwesomeIconUrl.EXCLAMATION_CIRCLE_SOLID)
public class ComplaintListView extends VerticalLayout{

    HorizontalLayout headerLayout = new HorizontalLayout();
    FormLayout mainlayout = new FormLayout();
    HorizontalLayout footerLayout = new HorizontalLayout();


    private AuthenticatedUser authenticatedUser;
    private ComplaintServiceImp complaintServiceImp;
    private Button btnAddComplaint = new Button();

    private final VerticalLayout contentArea= new VerticalLayout();
    private final Deque<Component> viewHistory = new ArrayDeque<>();
    private ComplaintCategoryServiceImp complaintCategoryServiceImp;


    public ComplaintListView(AuthenticatedUser authenticatedUser, ComplaintServiceImp complaintServiceImp,
                             ComplaintCategoryServiceImp complaintCategoryServiceImp ) {
        this.complaintServiceImp = complaintServiceImp;
        this.authenticatedUser = authenticatedUser;
        this.complaintCategoryServiceImp= complaintCategoryServiceImp;

        setSizeFull();
        setSpacing(false);
        setPadding(false);
        contentArea.setSizeFull();
        add(contentArea);

        navigateTo(new ComplaintView(this, authenticatedUser, complaintServiceImp,
                complaintCategoryServiceImp));
    }

    public void navigateTo(Component view) {
        if(!contentArea.getChildren().findFirst().isEmpty()) {
            Component current= contentArea.getComponentAt(0);
            viewHistory.push(current);
        }
        contentArea.removeAll();
        contentArea.add(view);
    }

    public void navigateBack(){
        if(!viewHistory.isEmpty()){
            Component previous = viewHistory.pop();
            contentArea.removeAll();
            contentArea.add(previous);
        }
    }



}
