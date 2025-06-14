package com.crimson.app.crimson.view.complainant;

import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.service.ComplaintCategoryServiceImp;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.crimson.app.crimson.view.component.CustomComplaintCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
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
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.checkerframework.checker.units.qual.A;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.rightPad;



//@CssImport("./styles/styles.css")
public class ComplaintView extends VerticalLayout{

    HorizontalLayout headerLayout = new HorizontalLayout();
    FormLayout mainlayout = new FormLayout();
    HorizontalLayout footerLayout = new HorizontalLayout();

    private AuthenticatedUser authenticatedUser;
    private ComplaintServiceImp complaintServiceImp;
    private Button btnAddComplaint = new Button();
    private final Deque<Component> viewHistory = new ArrayDeque<>();
    private ComplaintCategoryServiceImp complaintCategoryServiceImp;



    public ComplaintView(ComplaintListView complaintListView, AuthenticatedUser authenticatedUser,
                         ComplaintServiceImp complaintServiceImp,ComplaintCategoryServiceImp complaintCategoryServiceImp ) {
        this.complaintServiceImp = complaintServiceImp;
        this.authenticatedUser = authenticatedUser;
        this.complaintCategoryServiceImp = complaintCategoryServiceImp;



        //Button
        btnAddComplaint.setText("Add complaint");
        btnAddComplaint.setHeight("44px");
        btnAddComplaint.getStyle().set("background-color", "#1E90FF");
        btnAddComplaint.getStyle().set("color", "white"); // Text color
        btnAddComplaint.setIcon(VaadinIcon.PLUS.create());
        headerLayout.setWidthFull();
        headerLayout.getStyle().set("padding-bottom", "20px");
        headerLayout.getStyle().set("border-bottom", "1px solid #ccc");
        headerLayout.add(btnAddComplaint);
        footerLayout.getStyle().set("padding-top", "20px");
        footerLayout.getStyle().set("border-top", "1px solid #ccc");
        footerLayout.add(new Span("CRIMSON APP : Alright Reserve 2025"));
        footerLayout.setWidthFull();
        Optional<User> maybeUser = authenticatedUser.get();

         if(maybeUser.isPresent()){
            User user = maybeUser.get();
            List<Complaint> getALlComplaintViewById = complaintServiceImp.getComplaintsByComplainantId(user);
            for(Complaint complaint: getALlComplaintViewById){

                CustomComplaintCard card1 =  new CustomComplaintCard();
                card1.setWidth("300px");
                card1.setTitle("INC"+StringUtils.leftPad(complaint.getComplaintId().toString(),10,'0'));
                card1.setDateFiled(complaint.getFiledAT().toString());
                card1.setDateSolved(complaint.getUpdatedAT() == null ? "": complaint.getUpdatedAT().toString());
                card1.setCategory(String.valueOf(complaint.getComplaintCategory().getCategory()));
                card1.setStatus(String.valueOf(complaint.getStatus()));
                card1.setInvestigationStatus(String.valueOf(complaint.getInvestigationStatus()));
                card1.getStyle().set("padding", "10px");
                card1.build();
                mainlayout.add(card1);
                mainlayout.getStyle().set("spacing", "10px");
                mainlayout.setResponsiveSteps(
                        // Use one column by default
                        new FormLayout.ResponsiveStep("0", 1),
                        new FormLayout.ResponsiveStep("900px", 2),
                        // Use two columns, if layout's width exceeds 500px
                        new FormLayout.ResponsiveStep("1200px", 3),
                        new FormLayout.ResponsiveStep("1500px", 4));

                card1.getViewDetails().addClickListener(event -> {
                    complaintListView.navigateTo(new ViewDetailComplaint(complaintListView,"Add Complaint",authenticatedUser,complaintServiceImp));
                });


            }

             btnAddComplaint.addClickListener(event -> {
                 complaintListView.navigateTo(new AddComplaint(complaintListView,"Add Complaint",authenticatedUser,complaintServiceImp,complaintCategoryServiceImp));
             });


         }

        add(headerLayout,mainlayout,footerLayout);

    }
}
