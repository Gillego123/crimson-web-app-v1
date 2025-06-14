package com.crimson.app.crimson.view.complainant;

import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.crimson.app.crimson.view.style.HorizontalLayoutDesign;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;

public class ViewDetailComplaint extends VerticalLayout {


    private final HorizontalLayout headerLayout = new HorizontalLayout();
    private final HorizontalLayout btnLayoutFrm = new HorizontalLayout();
    private final HorizontalLayout contentArea = new HorizontalLayout();
    private final VerticalLayout rContentArea = new VerticalLayout();
    private final VerticalLayout lContentArea = new VerticalLayout();

    private final  FormLayout mainlayout = new FormLayout();
    private final HorizontalLayout footerLayout = new HorizontalLayout();
    private final Button btnBack = new Button();
    private final Button btnEdit = new Button();
    private final Button btnSave = new Button();
    private final Button btnCancel = new Button();
    private final ComboBox<String> category = new ComboBox<>();
    private final TextArea details = new TextArea();
    private final DatePicker dtPicker = new DatePicker();
    private final Upload uploadFiles = new Upload();
    private final Span investigatorStatus = new Span();
    private final Span agentAssign = new Span();
    private final HorizontalLayoutDesign horizontalLayoutDesign = new HorizontalLayoutDesign();
    private final VerticalLayout verticalStatus = new VerticalLayout();
    private final Hr hr = new Hr();

    private AuthenticatedUser authenticatedUser;
    private ComplaintServiceImp complaintServiceImp;

    public ViewDetailComplaint(ComplaintListView complaintListView, String detailsId, AuthenticatedUser authenticatedUser, ComplaintServiceImp complaintServiceImp ){
        this.complaintServiceImp = complaintServiceImp;
        this.authenticatedUser = authenticatedUser;



        btnBack.setText("Back");
        btnBack.setHeight("44px");
        btnBack.getStyle().set("background-color", "#1E90FF");
        btnBack.getStyle().set("color", "white"); // Text color
        btnBack.setIcon(VaadinIcon.ARROW_BACKWARD.create());
        btnEdit.setText("EDIT");
        btnEdit.setHeight("44px");
        btnEdit.getStyle().set("background-color", "GREEN");
        btnEdit.setIcon(VaadinIcon.EDIT.create());
        btnEdit.getStyle().set("color", "white"); // Text color
        headerLayout.setWidthFull();
        headerLayout.getStyle().set("padding-bottom", "20px");
        headerLayout.getStyle().set("border-bottom", "1px solid #ccc");
        headerLayout.add(btnBack,btnEdit);
        footerLayout.getStyle().set("padding-top", "20px");
        footerLayout.getStyle().set("border-top", "1px solid #ccc");
        footerLayout.add(new Span("CRIMSON APP : Alright Reserve 2025"));
        footerLayout.setWidthFull();
        mainlayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",1)
        );
        lContentArea.add(uploadFiles);
        rContentArea.add(mainlayout);
        rContentArea.setWidth("600px");
        rContentArea.setPadding(false);
        contentArea.setSizeFull();
        btnLayoutFrm.getStyle().set("padding-top","30px");
        btnLayoutFrm.add(btnSave, btnCancel);
        contentArea.add(rContentArea,lContentArea);

        //Data

        //Upload
        uploadFiles.setWidthFull();
        uploadFiles.setDropLabel(new Span("Drop files here..."));
        uploadFiles.setUploadButton(new Button("Upload files"));
        // Enable multiple file upload
        uploadFiles.setMaxFiles(Integer.MAX_VALUE); // or any number you prefer
        uploadFiles.setDropAllowed(true);
        uploadFiles.setAcceptedFileTypes("image/png", "application/pdf", ".docx"); // optional

        //Form
        horizontalLayoutDesign.setDottedBorder(investigatorStatus);
        horizontalLayoutDesign.setDottedBorder(agentAssign);
        investigatorStatus.setText("ESCALATED");
        agentAssign.setText("GARRYLLOYD GILLEGO");
        category.setItems("HACKING","HARASMENT","ANTI PHOTO AND VIDEO","VOYEURISM",
                "ATM CREDIT CARD FRAUD","CHILD PORNOGRAPHY","COMPUTER RELATED FORGERY","COMPUTER RELATED IDENTITY",
                "THEFT","DATA INTERFERENCE","ILLEGAL ACCESS","ONLINE LIBEL","ONLINE PIRACY","ONELINE SCAM","ONLINE THREAT"
                ,"SEXTORTION","WRONGFULLY REPORTED ACCOUNT");
        category.setWidthFull();
        category.setLabel("Category");
        details.setWidthFull();
        details.setLabel("Details");
        details.setHeight("350px");
        dtPicker.setLabel("Date File");
        btnSave.setText("Update");
        btnSave.setHeight("44px");
        btnSave.getStyle().set("background-color", "#1E90FF");
        btnSave.getStyle().set("color", "white");
        btnSave.setIcon(VaadinIcon.CHECK.create());
        btnCancel.setText("Cancel");
        btnCancel.setHeight("44px");
        btnCancel.setIcon(VaadinIcon.CLOSE.create());
        investigatorStatus.setWidthFull();
        agentAssign.setWidthFull();
        verticalStatus.add(investigatorStatus,agentAssign);
        verticalStatus.setSizeFull();
        verticalStatus.setPadding(false);
        hr.setTitle("Status");
        mainlayout.add(category,details,dtPicker,hr,verticalStatus,btnLayoutFrm);

        //Listener
        btnBack.addClickListener(event -> {

            complaintListView.navigateBack();

        });

        add(headerLayout,contentArea,footerLayout);
    }

}
