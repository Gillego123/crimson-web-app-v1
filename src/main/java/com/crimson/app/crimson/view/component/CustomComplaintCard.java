package com.crimson.app.crimson.view.component;


import com.crimson.app.crimson.view.style.HorizontalLayoutDesign;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class CustomComplaintCard extends VerticalLayout {


    public  final String SUBMITTED = "SUBMITTED";
    public  final String UNDER_INVESTIGATION = "UNDER_INVESTIGATION";
    public  final String RESOLVED = "RESOLVED";
    public  final String CLOSED="CLOSED";
    public  final String CANCELED="CANCELED";

    private String title;
    private String description;
    private String dateFiled;
    private String dateSolved;
    private String status;
    private String category;
    private String investigationStatus;

    private Button viewDetails ;
    private Span badgeStatus;


    private final String width ="375px";

    public CustomComplaintCard(String title, String description, String dateFiled, String dateSolved, String status, String category,String investigationStatus, Component... children) {
        super(children);
        this.title = title;
        this.description = description;
        this.dateFiled = dateFiled;
        this.dateSolved = dateSolved;
        this.status = status;
        this.category = category;
        this.investigationStatus=investigationStatus;
    }

    public CustomComplaintCard() {
        viewDetails = new Button("View Details");

        getElement().addEventListener("mouseover", e -> {
           // Notification.show("Hover:"+ title) ;
        });
        getElement().addEventListener("mouseout", e -> {
          //  Notification.show("Out: "+title) ;
        });
    }


    public void build(){
        badgeStatus = new Span(String.valueOf(status));
        HorizontalLayoutDesign horizontalLayoutDesign = new HorizontalLayoutDesign();

        HorizontalLayout header = new HorizontalLayout();
        horizontalLayoutDesign.setDesignHeader(header);
        header.setWidth(width);
        header.setHeight("56px");
        H3 headerTitle = new H3( title);
        headerTitle.getStyle().set("color", "white");
        header.add(headerTitle);

        //Initialization
        VerticalLayout details = new VerticalLayout();
        HorizontalLayout TitleDetails = new HorizontalLayout();
        HorizontalLayout contentDetails = new HorizontalLayout();
        HorizontalLayout subTitleDetails = new HorizontalLayout();
        VerticalLayout rTitleDetails = new VerticalLayout();
        VerticalLayout lTitleDetails = new VerticalLayout();
        VerticalLayout dateDetails = new VerticalLayout();
        HorizontalLayout footer = new HorizontalLayout();
        H6 dteField = new H6("Date Filed: "+dateFiled);
        H6 dteSolved = new H6("Date Resolved: "+ dateSolved);
        H4 cat= new H4( category);
        H6 investigationStat = new H6(investigationStatus);

        //Design
        details.setWidth(width);
        details.setHeight("141px");
        details.setSpacing(false);
        dateDetails.getStyle().set("padding-left","0px");
        dateDetails.setSpacing(false);
        lTitleDetails.setWidth("400px");
        lTitleDetails.setPadding(false);
        TitleDetails.setPadding(false);
        rTitleDetails.setPadding(false);
        rTitleDetails.setSpacing(false);
        rTitleDetails.setJustifyContentMode(JustifyContentMode.EVENLY);
        horizontalLayoutDesign.setCardDesignDetails(details);
        setUpBadgeDesign(status);
        footer.setWidth(width);
        footer.setHeight("80px");


        //Render
        dateDetails.add(dteField,dteSolved);
        lTitleDetails.add(cat);
        rTitleDetails.add(badgeStatus);
        TitleDetails.add(lTitleDetails,rTitleDetails);
        subTitleDetails.add(investigationStat);
        contentDetails.add(dateDetails);
        details.add(TitleDetails,subTitleDetails,contentDetails);
        footer.add(viewDetails);
        horizontalLayoutDesign.setCardDesignFooter(footer);


        setPadding(false);
        setSpacing(false);
        add(header, details, footer);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(String dateFiled) {
        this.dateFiled = dateFiled;
    }

    public String getDateSolved() {
        return dateSolved;
    }

    public void setDateSolved(String dateSolved) {
        this.dateSolved = dateSolved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInvestigationStatus() {
        return investigationStatus;
    }

    public void setInvestigationStatus(String investigationStatus) {
        this.investigationStatus = investigationStatus;
    }

    @Override
    public String getWidth() {
        return width;
    }

    public Button getViewDetails() {
        return viewDetails;
    }

    public void setViewDetails(Button viewDetails) {
        this.viewDetails = viewDetails;
    }

    public void setUpBadgeDesign(String status){
        String theme = "";
        switch (status) {
            case SUBMITTED:
                theme = "badge primary small";
                break;
            case UNDER_INVESTIGATION:
                theme = "badge primary small";
                break;
            case RESOLVED:
                theme = "badge success primary  small";
                break;
            case CLOSED:
                theme = "badge contrast primary small";
                break;
            case CANCELED:
                theme = "badge error primary small";
                break;
        }

        badgeStatus.getElement().getThemeList().add(theme);

    }

}
