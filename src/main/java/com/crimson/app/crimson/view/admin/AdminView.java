package com.crimson.app.crimson.view.admin;

import com.crimson.app.crimson.config.AuthenticatedUser;
import com.crimson.app.crimson.dto.AddressCityDto;
import com.crimson.app.crimson.dto.AddressRegionDto;
import com.crimson.app.crimson.model.AddressRegion;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.repository.AddressRegionRepository;
import com.crimson.app.crimson.service.AddressCityService;
import com.crimson.app.crimson.service.AddressRegionServiceImp;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import com.crimson.app.crimson.service.UserServiceImp;
import com.crimson.app.crimson.view.style.HorizontalLayoutDesign;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.RolesAllowed;

import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
import org.jsoup.internal.StringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import javax.swing.plaf.basic.BasicBorders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@PageTitle("Add User")
@Route("")
@RolesAllowed("ADMIN")
@Menu(order = 0, icon = LineAwesomeIconUrl.USER_CIRCLE_SOLID)
public class AdminView extends Composite<VerticalLayout> { //

    private AuthenticatedUser authenticatedUser;
    private UserServiceImp userServiceImp;
    private ComplaintServiceImp complaintServiceImp;
    private AddressRegionServiceImp addressRegionServiceImp;
    private AddressCityService addressCityService;

    private int totalUsers = 0;
    private int errorCode = -1;
    private Long userID= 0L;

    private String errorMessage;

    Hr hr = new Hr();
    H5 h5= new H5();
    ComboBox<String> prefix = new ComboBox<>("Select Prefix");
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField middleNmae = new TextField("Middle name");
    TextField phoneNumber = new TextField("Phone number");
    TextArea txtAreaAddress = new TextArea("Room No./Street/Drive");
    ComboBox<AddressRegionDto> cmbProvince = new ComboBox<>("Region");
    ComboBox<AddressCityDto> cmbCity = new ComboBox<>("City/Municipality");
    ComboBox<String> cmbBarangay = new ComboBox<>("Barangay");
    TextField searchField = new TextField();
    ComboBox<String> cmbRole = new ComboBox<>("Select Role:");
    Button addButton = new Button();
    Button delButton = new Button();
    Button btnSave = new Button("Save");
    Button btnCancel = new Button("Cancel");
    Button button = new Button("Edit");
    Button editButton = new Button();
    EmailField username = new EmailField("Email Address (This will be your User Name also during Login)");
    PasswordField password = new PasswordField("Password");
    PasswordField confirmPassword = new PasswordField("Confirm password");
    Button dlgCancelButton = new Button("Cancel");
    Button dlgDeleteButton = new Button("Delete");
    Dialog dlgConfirmDelete = new Dialog();
    Checkbox chckStatus = new Checkbox("Status");

    Grid<User> gridUserDetails = new Grid<>(User.class,false);

    HorizontalLayout horizontalLayoutButton = new HorizontalLayout();
    HorizontalLayout rightHeaderHorizontal = new HorizontalLayout();
    HorizontalLayout horizontalLayoutFooter = new HorizontalLayout();
    FormLayout formLayout = new FormLayout();
    VerticalLayout mainLayOut = new VerticalLayout();
    HorizontalLayout mainHLayOut = new HorizontalLayout();
    HorizontalLayout HeaderLayOut = new HorizontalLayout();
    VerticalLayout leftVLayOut = new VerticalLayout();
    VerticalLayout rightVLayOut = new VerticalLayout();


    public AdminView(AuthenticatedUser authenticatedUser,UserServiceImp userServiceImp,ComplaintServiceImp complaintServiceImp,
                     AddressRegionServiceImp addressRegionServiceImp, AddressCityService addressCityService ) {
        this.authenticatedUser = authenticatedUser;
        this.userServiceImp = userServiceImp;
        this.complaintServiceImp = complaintServiceImp;
        this.addressRegionServiceImp = addressRegionServiceImp;
        this.addressCityService = addressCityService;

         getStyle().set("background-color","white");


        Optional<User> maybeUser = authenticatedUser.get();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Avatar image = new Avatar("Gillego");
        image.setImage("icons/icon.png");

        H1 h1 = new H1();
        if (maybeUser.isPresent()){
            User user = maybeUser.get();
            h1.setText(user.getUserId().toString());
        }

        //Layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        mainLayOut.setWidthFull();
        getContent().setFlexGrow(1.0, mainLayOut);
        mainLayOut.setWidth("100%");
        mainLayOut.getStyle().set("flex-grow", "1");
        mainHLayOut.setWidthFull();
        mainLayOut.setFlexGrow(1.0, mainHLayOut);
        mainHLayOut.addClassName(LumoUtility.Gap.MEDIUM);
        mainHLayOut.setWidth("100%");
        mainHLayOut.getStyle().set("flex-grow", "1");
        leftVLayOut.setHeightFull();
        mainHLayOut.setFlexGrow(1.0, leftVLayOut);
        leftVLayOut.setWidth("100%");
        leftVLayOut.getStyle().set("flex-grow", "1");
        rightVLayOut.setHeightFull();
        rightVLayOut.setVisible(false);
        //rightVLayOut.add(dialog);
        mainHLayOut.setFlexGrow(1.0, rightVLayOut);
        rightVLayOut.setWidth("400px");
        rightVLayOut.getStyle().set("flex-grow", "1");
        getContent().add(mainLayOut);
        mainLayOut.add(HeaderLayOut);
        mainLayOut.add(mainHLayOut);
        mainHLayOut.add(leftVLayOut);
        mainHLayOut.add(rightVLayOut);

        //Left Form
        List<User> userList = userServiceImp.getAllUsers();
        List<AddressRegionDto> addressRegionDtoList = addressRegionServiceImp.getAllRegion();
        gridUserDetails.setItems(userList);
        totalUsers = userList.size();
        //gridUserDetails.setAllRowsVisible(true);
        gridUserDetails.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUserDetails.setHeight("700px");
        gridUserDetails.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        //createEmployeeRenderer()
        gridUserDetails.addColumn(createEmployeeRenderer()).setHeader("Names").setWidth("400px").setFlexGrow(0);
        gridUserDetails.addColumn(User::getAddress).setHeader("Location").setSortable(true);
        gridUserDetails.addColumn(User::getPhoneNumber).setHeader("Phone Number").setSortable(true);
        gridUserDetails.addComponentColumn(stat -> createStatusBadge(stat.getStatus())).setHeader("Status").setSortable(true);
        gridUserDetails.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        dlgConfirmDelete.add("Are you want to delete this user permanently?");
        dlgConfirmDelete.getHeader().add(new H5("Delete item"));
        gridUserDetails.addComponentColumn(user -> {
            Button editButton = new Button("", event -> updateUserDetails(user));
            editButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            editButton.setIcon(VaadinIcon.EDIT.create());
            Button del = new Button("", buttonClickEvent -> {
                userID = user.getUserId();
                deleteUser(user.getUserId());});
            del.addThemeVariants(ButtonVariant.LUMO_ERROR);
            del.setIcon(VaadinIcon.TRASH.create());
            return   new HorizontalLayout(editButton ,del);
        }).setHeader("Actions").setAutoWidth(true);


        searchField.setWidth("400%");
        searchField.setPlaceholder("Search User");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e-> refreshTableCount());
        addButton.setText("New User");
        HeaderLayOut.add(searchField, addButton);

        gridUserDetails.getListDataView().addFilter(user -> {
            String searchText = searchField.getValue().trim();
            //totalUsers = (int) gridUserDetails.getListDataView().getItems().count();
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            return user.getFirstName() != null &&
                    user.getLastName().toLowerCase().contains(searchText.toLowerCase()) ||
                    user.getFirstName().toLowerCase().contains(searchText.toLowerCase());
        });

        addButton.setIcon(VaadinIcon.PLUS.create());
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickListener(buttonClickEvent -> {
            btnSave.setText("Save");
            btnSave.setEnabled(true);
            rightVLayOut.setVisible(true);
        });
        addButton.setIcon(VaadinIcon.PLUS.create());
        delButton.setIcon(VaadinIcon.MINUS.create());
        delButton.setText("Delete users...");
        delButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        delButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delButton.addClickListener(buttonClickEvent -> {
            rightVLayOut.setVisible(false);
            new UserDialogDetails("Edit").open();
        });

        setDesignFooter(horizontalLayoutFooter);
        horizontalLayoutFooter.setWidthFull();
        h5.setText(String.format("%s total users", totalUsers));
        horizontalLayoutFooter.add(h5);
        setPhoneFieldProperties(phoneNumber);
        dlgCancelButton.addClickListener(buttonClickEvent -> dlgConfirmDelete.close());
        dlgDeleteButton.addClickListener(buttonClickEvent -> {
                    userServiceImp.deleteUserById(userID);
                    refreshUserList();
                    refreshTableCount();
                    dlgConfirmDelete.close();
        });

        //Right Form - Userform----------------------------------------------------------------------------------------

        //Populate City
        cmbProvince.addValueChangeListener(event ->{
            AddressRegionDto selectedProvice = event.getValue();
            if(selectedProvice != null){
                Long id = selectedProvice.getAddressRegionId();
                AddressRegion addressRegion= addressRegionServiceImp.getByAddressRegionId(id);
                List<AddressCityDto> addressCityDtoList = addressCityService.getCityByRegion(addressRegion);
                cmbCity.setItems(addressCityDtoList);
            }
        });

        //Populate Barangays
        cmbProvince.addValueChangeListener(event ->{
            AddressRegionDto selectedProvice = event.getValue();
            if(selectedProvice != null){
                Long id = selectedProvice.getAddressRegionId();
                AddressRegion addressRegion= addressRegionServiceImp.getByAddressRegionId(id);
                List<AddressCityDto> addressCityDtoList = addressCityService.getCityByRegion(addressRegion);
                cmbCity.setItems(addressCityDtoList);
            }
        });


        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        middleNmae.setRequiredIndicatorVisible(true);


        txtAreaAddress.setHeight("100px");
        txtAreaAddress.setWidthFull();
        cmbRole.setItems(List.of("COMPLAINANT","AGENT","ADMIN"));
        prefix.setItems(List.of("Mr.","Mrs."));
        cmbProvince.setItems(addressRegionDtoList);


        // cmbProvince.setItems();

        cmbRole.setRequiredIndicatorVisible(true);
        cmbRole.setAllowedCharPattern("[A-Z]");
        cmbRole.setI18n(new ComboBox.ComboBoxI18n()
                .setRequiredErrorMessage("Field is required"));


        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnSave.setEnabled(false);
        btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

        setDesignFooter(horizontalLayoutButton);
        horizontalLayoutButton.setWidthFull();
        horizontalLayoutButton.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayoutButton.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayoutButton.add(btnSave,btnCancel);
//        formLayout.getStyle()
//                        .set("border","1px solid #d3d3d3")
//                                .set("padding","10px");
//        formLayout.addClassNames("gap-m", "p-m");

//        HorizontalLayout horizontalLayoutTab1 = new HorizontalLayout();
//        horizontalLayoutTab1.setSpacing(true);
//        horizontalLayoutTab1.add(txtAreaAddress);
//        tab1.add(horizontalLayoutTab1);
//        tabs.add(tab1,tab2,tab3);
        formLayout.add(prefix,firstName, lastName,middleNmae, phoneNumber,hr);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 3));
        // Stretch the username field over 2 columns

 //       formLayout.setHeight("px");
        Scroller scroller = new Scroller(
                new Div(formLayout));
//        scroller.setHeight("500px");
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);

        btnSave.addClickListener(buttonClickEvent -> {
            saveUser();
        });

        btnCancel.addClickListener(buttonClickEvent -> {
            clearFiedls();
        });
        //End - User form -----------------------------------------------------------------------------------------------

        //Render
        rightVLayOut.setPadding(false);
        rightVLayOut.setSpacing(false);
        H5 h5 = new H5("User details");
        h5.getStyle().set("color", "white");
        rightHeaderHorizontal.add(h5);
        rightHeaderHorizontal.setWidthFull();
        rightHeaderHorizontal.getStyle()
                .set("border","1px solid #d3d3d3")
                .set("border-radius","10px 10px 0px 0px")
                .set("border-bottom", "none")
                .set("background-color", "#1E90FF")
                .set("padding","10px");

        TabSheet tabSheet = new TabSheet();
        tabSheet.setWidth("350px");
        tabSheet.setHeight("298px");
        tabSheet.setWidthFull();

        VerticalLayout tablayoutAddress = new VerticalLayout();
        tablayoutAddress.setPadding(false);
        tablayoutAddress.setSpacing(false);
        txtAreaAddress.setWidthFull();
        cmbProvince.setWidthFull();
        cmbCity.setWidthFull();
        cmbBarangay.setWidthFull();
        tablayoutAddress.add(txtAreaAddress,cmbProvince,cmbCity,cmbBarangay);


        VerticalLayout tablayoutAccess = new VerticalLayout();
        tablayoutAccess.setSpacing(false);
        tablayoutAccess.setPadding(false);
        username.setWidthFull();
        password.setWidthFull();
        confirmPassword.setWidthFull();
        tablayoutAccess.add(username, password, confirmPassword);

        VerticalLayout tablayoutStatus = new VerticalLayout();
        tablayoutStatus.setPadding(false);
        tablayoutStatus.setSpacing(false);

        chckStatus.setLabel("Active");
        tablayoutStatus.add(cmbRole, chckStatus);



        tabSheet.add( createTabWithIcon(VaadinIcon.MAP_MARKER.create(), "Address"), tablayoutAddress).setFlexGrow(1);
        tabSheet.add(createTabWithIcon(VaadinIcon.KEY.create(), "Access"), tablayoutAccess).setFlexGrow(1);
        tabSheet.add(createTabWithIcon(VaadinIcon.CLOCK.create(),"Status"), tablayoutStatus).setFlexGrow(1);

        horizontalLayoutButton.setHeight("61px");
        rightVLayOut.add(rightHeaderHorizontal,scroller,tabSheet,horizontalLayoutButton);


        HorizontalLayout header = new HorizontalLayout();
      //  HorizontalLayout header1 = new HorizontalLayout();
        H5 h6 = new H5("User list");
        h6.getStyle().set("color", "white");
        header.add(h6);
        HorizontalLayoutDesign horizontalLayoutDesign = new HorizontalLayoutDesign();
        horizontalLayoutDesign.setDesignHeader(header);
        header.setWidthFull();
       // header1.add(searchField);
        leftVLayOut.setSpacing(false);
        leftVLayOut.setPadding(false);
        leftVLayOut.add(header,gridUserDetails,horizontalLayoutFooter);
        button.addClickListener(e -> {
           rightVLayOut.setVisible(true);
        });
        btnCancel.addClickListener(e -> {
            rightVLayOut.setVisible(false);
        });

        getContent().add(mainLayOut);

    }

    public Tab createTabWithIcon(Icon icon, String text){

        icon.getStyle().set("margin-right", "5px");
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(icon, new Span(text));
        Tab tab = new Tab(layout);
        return tab;
    }

    private void setDesignFooter(HorizontalLayout horizontalLayout){
        HorizontalLayoutDesign horizontalLayoutDesign = new HorizontalLayoutDesign();
        horizontalLayoutDesign.setDesignFooter(horizontalLayout);
    }

    private void refreshTableCount() {
        gridUserDetails.getListDataView().refreshAll();
        h5.setText(String.format("%s total users", gridUserDetails.getListDataView().getItems().count()));
    }

    private void updateUserDetails(User user) {

        Long userID = user.getUserId();
        boolean isExist = userServiceImp.existsById(userID);
        if(isExist){
            firstName.setValue(user.getFirstName());
            lastName.setValue(user.getLastName());
            middleNmae.setValue(user.getMiddleName());
            btnSave.setText("Edit");
            rightVLayOut.setVisible(true);

        }

    }

    public void setPhoneFieldProperties(TextField phoneField){

        phoneField.setRequiredIndicatorVisible(true);
        phoneField.setPattern(
                "^[+]?[\\(]?[0-9]{3}[\\)]?[\\-]?[0-9]{3}[\\-]?[0-9]{4,6}$"
        );
        phoneField.setAllowedCharPattern("[0-9()+-]");
        phoneField.setMinLength(5);
        phoneField.setMaxLength(11);

        phoneField.setI18n(new TextField.TextFieldI18n()
                .setRequiredErrorMessage("Field is required")
                .setMinLengthErrorMessage("Minimum length is 5 characters")
                .setMaxLengthErrorMessage("Maximum length is 18 characters")
                .setPatternErrorMessage("Invalid phone number format"));
    }
    public void setEmailAddressProperties(EmailField emailField){
        emailField.setRequiredIndicatorVisible(true);
        emailField.setI18n(new EmailField.EmailFieldI18n()
                .setRequiredErrorMessage("Field is required")
                .setPatternErrorMessage("Invalid email address format"));
    }

    public HorizontalLayout addUserForm(){

        HorizontalLayout layoutRow = new HorizontalLayout();
        Button btnSave = new Button("Save");
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName,middleNmae, username, password,
                confirmPassword);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 3));
        // Stretch the username field over 2 columns
        formLayout.setColspan(username, 3);

        layoutRow.add(layoutRow, btnSave);

        return layoutRow;
        //getContent().add(layoutRow, btnSave);
    }

    public void saveUser(){

        if(isValidated()){
            try {

                User newUser = new User();
                newUser.setProfilePicture(createRandomAvatar());
                newUser.setFirstName(firstName.getValue());
                newUser.setLastName(lastName.getValue());
                newUser.setMiddleName(middleNmae.getValue());
                newUser.setPassWordHash(generateBCryptedPassword(password.getValue()));
                newUser.setAddress(txtAreaAddress.getValue());
                newUser.setPhoneNumber(phoneNumber.getValue());
                newUser.setEmail(username.getValue());
                newUser.setTimestamp(LocalDateTime.now());
                userServiceImp.registerUser(newUser);
                Notification.show("Successfully saved!", 5000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                refreshUserList();
                refreshTableCount();
            } catch (Exception e) {
                Notification.show(e.getMessage(), 5000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                throw new RuntimeException(e);
            }

        }else{
            //Check what field is not correct
            //To do here......

            Notification.show("Please complete all the required fields. Thanks! " + errorMessage,5000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);

        }

    }

    private String generateBCryptedPassword(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    private String createRandomAvatar() {

        String pf;
        int number;
        pf = switch (prefix.getValue()) {
            case "Mr." -> {
                number = ThreadLocalRandom.current().nextInt(1, 3);
                yield "icons/" + number + ".jpg";
            }
            case "Mrs." -> {
                number = ThreadLocalRandom.current().nextInt(4, 6);
                yield "icons/" + number + ".jpg";
            }
            default -> "";
        };

        return pf;
    }

    private static Renderer<User> createEmployeeRenderer() {
        return LitRenderer.<User> of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "<vaadin-avatar img=\"${item.pictureUrl}\" name=\"${item.fullName}\" alt=\"User avatar\"></vaadin-avatar>"
                                + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                                + "    <span> ${item.fullName},  ${item.famName} </span>"
                                + "    <span style=\"font-size: var(--lumo-font-size-s); color: var(--lumo-secondary-text-color);\">"
                                + "      ${item.email}" + "    </span>"
                                + "  </vaadin-vertical-layout>"
                                + "</vaadin-horizontal-layout>")

                .withProperty("pictureUrl", User::getProfilePicture)
                .withProperty("fullName", User::getFirstName)
                .withProperty("famName", User::getLastName)
                .withProperty("email", User::getEmail);
    }


    public void deleteUser(Long userId){

        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidthFull();
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        hl.add(dlgDeleteButton,dlgCancelButton);
        dlgCancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dlgDeleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        dlgConfirmDelete.getFooter().add(dlgDeleteButton,dlgCancelButton);
        dlgConfirmDelete.open();


    }

    public void clearFiedls(){
        firstName.setValue("");
        lastName.setValue("");
        middleNmae.setValue("");
        txtAreaAddress.setValue("");
        username.setValue("");
        password.setValue("");
        confirmPassword.setValue("");
        phoneNumber.setValue("");
    }

    public Boolean  isValidated(){

        Boolean valid = Boolean.TRUE;

        if(firstName.getValue().isEmpty()){
            valid = Boolean.FALSE;
            errorCode=0;
        }else if(lastName.getValue().isEmpty()){
            valid = Boolean.FALSE;
            errorCode=1;
        } else if (middleNmae.getValue().isEmpty()) {
            valid = Boolean.FALSE;
            errorCode=2;
        } else if (txtAreaAddress.getValue().isBlank()) {
            valid = Boolean.FALSE;
            errorCode=3;
        } else if (phoneNumber.getValue().isEmpty() || phoneNumber.isInvalid()) {

            errorMessage = phoneNumber.getErrorMessage();
            valid = Boolean.FALSE;
            errorCode=4;

        } else if (username.getValue().isEmpty()) {
            valid = Boolean.FALSE;
            errorCode=5;
        } else if (password.getValue().isEmpty()) {
            valid = Boolean.FALSE;
            errorCode=6;
        } else if (confirmPassword.getValue().isEmpty()) {
            valid = Boolean.FALSE;
            errorCode=7;
        } else if (!password.getValue().equals(confirmPassword.getValue())) {
            valid = Boolean.FALSE;
            errorCode=8;
        }

        return valid;
    }

    public void refreshUserList(){
        List<User> items = userServiceImp.getAllUsers();
        gridUserDetails.setItems(items);
    }

    private Span createStatusBadge(String status) {
        String theme;
        switch (status) {
            case "Active":
                theme = "badge primary";
                break;
            default:
                theme = "badge contrast primary";
                break;
        }
        Span badge = new Span(status);
        badge.getElement().getThemeList().add(theme);
        return badge;
    }

}
