package com.crimson.app.crimson.view.complainant;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.dto.ComplaintCategoryDto;
import com.crimson.app.crimson.dto.ComplaintDTO;
import com.crimson.app.crimson.model.MdlComplaintCategory;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.service.ComplaintCategoryServiceImp;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.MultiFileReceiver;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class AddComplaint extends VerticalLayout {


    private final HorizontalLayout headerLayout = new HorizontalLayout();
    private final HorizontalLayout btnLayoutFrm = new HorizontalLayout();
    private final HorizontalLayout contentArea = new HorizontalLayout();
    private final VerticalLayout rContentArea = new VerticalLayout();
    private final VerticalLayout lContentArea = new VerticalLayout();

    private final  FormLayout mainlayout = new FormLayout();
    private final HorizontalLayout footerLayout = new HorizontalLayout();
    private final Button btnBack = new Button();
    private final Button btnSave = new Button();
    private final Button btnCancel = new Button();
    private final ComboBox<ComplaintCategoryDto> category = new ComboBox<>();
    private final TextArea details = new TextArea();
    private final DatePicker dtPicker = new DatePicker();

    private final File uploadDir = new File("uploads");

    private AuthenticatedUser authenticatedUser;
    private ComplaintServiceImp complaintServiceImp;
    private Long id;
    private ComplaintCategoryServiceImp complaintCategoryServiceImp;



    public AddComplaint(ComplaintListView complaintListView, String detailsId,AuthenticatedUser authenticatedUser,
                        ComplaintServiceImp complaintServiceImp, ComplaintCategoryServiceImp complaintCategoryServiceImp ){
        this.complaintServiceImp = complaintServiceImp;
        this.authenticatedUser = authenticatedUser;
        this.complaintCategoryServiceImp= complaintCategoryServiceImp;



        btnBack.setText("Back");
        btnBack.setHeight("44px");
        btnBack.getStyle().set("background-color", "#1E90FF");
        btnBack.getStyle().set("color", "white"); // Text color
        btnBack.setIcon(VaadinIcon.ARROW_BACKWARD.create());
        headerLayout.setWidthFull();
        headerLayout.getStyle().set("padding-bottom", "20px");
        headerLayout.getStyle().set("border-bottom", "1px solid #ccc");
        headerLayout.add(btnBack);
        footerLayout.getStyle().set("padding-top", "20px");
        footerLayout.getStyle().set("border-top", "1px solid #ccc");
        footerLayout.add(new Span("CRIMSON APP : Alright Reserve 2025"));
        footerLayout.setWidthFull();
        mainlayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",1)
        );
       // lContentArea.add(uploadFiles);
        rContentArea.add(mainlayout);
        rContentArea.setWidth("600px");
        rContentArea.setPadding(false);
        contentArea.setSizeFull();
        btnLayoutFrm.getStyle().set("padding-top","30px");
        btnLayoutFrm.add(btnSave, btnCancel);
        contentArea.add(rContentArea,lContentArea);

        //Data



        //Form
        List<ComplaintCategoryDto> mdlComplaintCategoryList = complaintCategoryServiceImp.findALl();
        category.setItems(mdlComplaintCategoryList);
        category.setWidthFull();
        category.setLabel("Category");
        details.setWidthFull();
        details.setLabel("Details");
        details.setHeight("350px");
        dtPicker.setLabel("Date File");
        btnSave.setText("Save");
        btnSave.setHeight("44px");
        btnSave.getStyle().set("background-color", "#1E90FF");
        btnSave.getStyle().set("color", "white");
        btnSave.setIcon(VaadinIcon.FILE_ADD.create());
        btnCancel.setText("Cancel");
        btnCancel.setHeight("44px");
        btnCancel.setIcon(VaadinIcon.EXIT.create());
        mainlayout.add(category,details,dtPicker,btnLayoutFrm);

        //Listener

        category.addValueChangeListener(event -> {
            ComplaintCategoryDto complaintCategoryDto = event.getValue();
            id = complaintCategoryDto.getComplaintCategoryId();
        });

        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        MultiFileBuffer buffer = new MultiFileBuffer();
        Upload uploadFiles = new Upload(buffer);

        //Upload

        uploadFiles.setReceiver((fileName, mineType) -> {
            try {
                File targetFile = new File(uploadDir, fileName);

                return new FileOutputStream(targetFile);
            } catch (Exception e) {
                System.out.println(e);
                Notification.show("Upload Failed: " + fileName);
                return null;
            }


        });

        uploadFiles.addSucceededListener(event -> {
           String filename = event.getFileName();
           Notification.show("Uploaded and Saved to: " + filename);


        });
        uploadFiles.addFailedListener(event -> {
            String filename = event.getFileName();
            Notification.show("Uploaded failed: " + filename);
        });

        uploadFiles.setWidthFull();
        uploadFiles.setDropLabel(new Span("Drop files here..."));
        uploadFiles.setUploadButton(new Button("Upload files"));
        // Enable multiple file upload
        uploadFiles.setMaxFiles(10); // or any number you prefer
        uploadFiles.setMaxFileSize(530000000);
        uploadFiles.setDropAllowed(true);
        uploadFiles.setAcceptedFileTypes("image/jpg", "application/pdf", ".docx",".jpeg"); // optional
        lContentArea.add(uploadFiles);
        btnSave.addClickListener(event -> {

            ComplaintDTO newComplaint = new ComplaintDTO();
            Optional<User> user = authenticatedUser.get();
            if(user.isPresent()){
                User user1 = user.get();
                newComplaint.setUser(user1);
                newComplaint.setComplaintCategoryId(id);
                newComplaint.setDescription(details.getValue());
                newComplaint.setFiledAT(dtPicker.getValue().atStartOfDay());
                newComplaint.setStatus(ComplaintStatus.SUBMITTED);
                newComplaint.setComplaintCategoryId(id);
                complaintServiceImp.fileComplaint(newComplaint.getUser().getUserId(),newComplaint);
                Dialog dlgSave = new Dialog("Successfully save!");
                Button ok = new Button("OK");

                dlgSave.add(ok);
                dlgSave.open();
                ok.addClickListener(event1 -> {
                   dlgSave.close();
                });
            }
        });

        btnBack.addClickListener(event -> {

            complaintListView.navigateBack();

        });


        add(headerLayout,contentArea,footerLayout);
    }




}
