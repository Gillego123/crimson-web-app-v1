package com.crimson.app.crimson.view.style;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class HorizontalLayoutDesign {

    public HorizontalLayoutDesign() {
    }

    public void setDesignHeader(HorizontalLayout horizontalLayout){
        horizontalLayout.getStyle()
                .set("border","1px solid #d3d3d3")
                .set("border-radius","10px 10px 0px 0px")
                .set("border-bottom", "none")
                .set("background-color", "#1E90FF")
                .set("padding","10px");
    }

    public void setDesignFooter(HorizontalLayout horizontalLayout){
        horizontalLayout.getStyle().set("border","1px solid #d3d3d3")
                .set("padding","20px")
                .set("box-shadow", ".5px .5px 1px #aaaaaa, -.5px -.5px .5px #ffffff")
                .set("border-top", "none")
                .set("background-color", "#f5f5f5")
                .set("border-radius", "0px 0px 4px 4px");
    }

    public void setButtonAddDesign(){

    }

    public void setCardDesignHeader(HorizontalLayout horizontalLayout){
        horizontalLayout.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        horizontalLayout.getStyle().set("border-top", "none");
        horizontalLayout.getStyle().set("padding", "1rem");
        horizontalLayout.getStyle().set("background-color", "#1E90FF");
        horizontalLayout.getStyle().set("border-radius", "8px 8px 0px 0px");
    }

    public void setCardDesignDetails(VerticalLayout VerticalLayout){
        VerticalLayout.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        VerticalLayout.getStyle().set("border-radius", "8px");
        VerticalLayout.getStyle().set("padding", "1rem");
        VerticalLayout.getStyle().set("background-color", "white");
        VerticalLayout.getStyle().set("border-radius", "0px 0px 0px 0px");
    }
    public void setCardDesignFooter(HorizontalLayout horizontalLayout){
        horizontalLayout.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        horizontalLayout.getStyle().set("border-top", "none");
        horizontalLayout.getStyle().set("padding", "1rem");
        horizontalLayout.getStyle().set("background-color", "white");
        horizontalLayout.getStyle().set("border-radius", "0px 0px 8px 8px");
    }

    public void setDottedBorder(Component component){
        component.getStyle()
                .set("border","2px dotted black")
                .set("border-radius", "8px 8px 8px 8px")
                .set("display","flex")
                .set("justify-content","center")
                .set("align-items","center")
                .set("font-weight","bold")
                .set("spacing","10px")
                .set("height","50px");
    }



}
