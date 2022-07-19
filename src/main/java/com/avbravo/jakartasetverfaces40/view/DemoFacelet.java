/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jakartasetverfaces40.view;

import com.avbravo.jakartasetverfaces40.model.Oceano;
import static jakarta.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;

import java.io.IOException;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.component.UIColumn;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.*;
import jakarta.faces.context.FacesContext;
import jakarta.faces.el.ValueBinding;
import jakarta.faces.model.ListDataModel;
import jakarta.faces.view.facelets.Facelet;
import java.util.ArrayList;

/**
 *
 * @author avbravo
 */
@View("/demo.xhtml")
@ApplicationScoped
public class DemoFacelet extends Facelet {

    @Override
    public void apply(FacesContext facesContext, UIComponent root) throws IOException {
        if (!facesContext.getAttributes().containsKey(IS_BUILDING_INITIAL_STATE)) {
            return;
        }

        ComponentBuilder components = new ComponentBuilder(facesContext);
        List<UIComponent> rootChildren = root.getChildren();

        UIOutput output = new UIOutput();

        output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        rootChildren.add(output);

        HtmlBody body = components.create(HtmlBody.COMPONENT_TYPE);
        rootChildren.add(body);

        HtmlForm form = components.create(HtmlForm.COMPONENT_TYPE);
        form.setId("form");
        body.getChildren().add(form);

        HtmlOutputLabel label = components.create(HtmlOutputLabel.COMPONENT_TYPE);
        label.setValue("Enter your name");
        form.getChildren().add(label);

        HtmlInputText message = components.create(HtmlInputText.COMPONENT_TYPE);
        message.setId("message");
        form.getChildren().add(message);

        HtmlOutputText text = components.create(HtmlOutputText.COMPONENT_TYPE);
        form.getChildren().add(text);
        /**
         * Datatable
         */
        HtmlDataTable dataTable = new HtmlDataTable();
        // Get amount of columns.
        List<Oceano> list = new ArrayList<>();
        int columns = ((List) list.get(0)).size();

        // Set columns.
        for (int i = 0; i < columns; i++) {

            // Set header (optional).
            UIOutput header = new UIOutput();
            header.setValue("idoceano");
            

            // Set output.
            UIOutput outputColumn = new UIOutput();
            ValueBinding myItem
                    = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding("#{myItem[" + i + "]}");
            outputColumn.setValueBinding("value", myItem);

            // Set column.
            UIColumn column = new UIColumn();
            column.setHeader(header);
            column.getChildren().add(outputColumn);

            // Add column.
          dataTable.getChildren().add(column);
        }
form.getChildren().add(dataTable);

/**
 * Otro datatable
 */
//HtmlDataTable data;
//    HtmlOutputText output0;
//    ArrayList<String> hobbits = new ArrayList<String>();
//    hobbits.add("bilbo");
//    hobbits.add("frodo");
//    hobbits.add("merry");
//    hobbits.add("pippin");
//    hobbits.add("lumpy");
//    ListDataModel dataModel = new ListDataModel(hobbits);
//    panel = new UINamingContainer();
//    panel.setId(panelId);
//    form.getChildren().add(panel);
//    input0 = new HtmlInputText();
//    input0.setId("input0");
//    panel.getChildren().add(input0);
//    input1 = new HtmlInputText();
//    input1.setId("input1");
//    panel.getChildren().add(input1);
//    data = new HtmlDataTable();
//    data.setId("data");
//    panel.getChildren().add(data);
//    data.setValue(dataModel);
//    data.setVar("hobbitName");
//    String dataId = data.getClientId();
//  HtmlColumn  column0 = new HtmlColumn();
//   column0.setId("column0");
//    data.getChildren().add(column0);
//    output0 = new HtmlOutputText();
//    output0.setId("output0");
//    output0.setValue(getFacesContext().getApplication().getExpressionFactory().createValueExpression(getFacesContext().getELContext(), "#{hobbitName}", String.class));
//    column0.getChildren().add(output0);
//  HtmlCommandButton  button0 = new HtmlCommandButton();
//    button0.setId("button0");
//    panel.getChildren().add(button0);
//  HtmlCommandButton  button1 = new HtmlCommandButton();
//    button1.setId("button1");
//    panel.getChildren().add(button1);
        /**
         * CommandButton
         */
        HtmlCommandButton actionButton = components.create(HtmlCommandButton.COMPONENT_TYPE);
        actionButton.setId("button");
        actionButton.addActionListener(e -> text.setValue("Hi " + message.getValue()));
        actionButton.setValue("Do action");
        form.getChildren().add(actionButton);

        output = new UIOutput();
        output.setValue("</html>");
        rootChildren.add(output);
    }

    private static class ComponentBuilder {

        FacesContext facesContext;

        ComponentBuilder(FacesContext facesContext) {
            this.facesContext = facesContext;
        }

        @SuppressWarnings("unchecked")
        <T> T create(String componentType) {
            return (T) facesContext.getApplication().createComponent(facesContext, componentType, null);
        }
    }
}
