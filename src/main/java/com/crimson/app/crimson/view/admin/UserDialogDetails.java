package com.crimson.app.crimson.view.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class UserDialogDetails extends Dialog {

    FormLayout userFormLayOut = new FormLayout();
    ComboBox<String> prefix = new ComboBox<>();
    TextField firstName = new TextField();
    TextField lastName = new TextField();
    TextField middleNmae = new TextField();
    TextField phoneNumber = new TextField();
    TextArea txtAreaAddress = new TextArea();

    public UserDialogDetails(String action){

        setWidth("1000px");
        setHeight("700px");
        setDraggable(true);
        setResizable(true);
        fieldsLabel();
        FormLayout fl = userForm();
        add(fl);
        getFooter().add(new Button(action,e -> close()));
    }

    public void fieldsLabel(){

        prefix.setLabel("Select Prefix");
        firstName.setLabel("");
        lastName.setLabel("");
        middleNmae.setLabel("");
        phoneNumber.setLabel("");

    }

    public FormLayout userForm(){
        prefix.setItems(List.of("Mr.","Mrs."));
        userFormLayOut.add(prefix);

        return userFormLayOut;
    }

}
