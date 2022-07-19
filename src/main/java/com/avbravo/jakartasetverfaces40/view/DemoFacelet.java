/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jakartasetverfaces40.view;

import com.avbravo.jakartasetverfaces40.model.Oceano;
import jakarta.el.MethodExpression;
import jakarta.el.ValueExpression;
import static jakarta.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;

import java.io.IOException;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.component.UIColumn;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.*;
import jakarta.faces.context.FacesContext;
import jakarta.faces.el.ValueBinding;
import jakarta.faces.view.facelets.Facelet;
import java.util.Arrays;

/**
 *
 * @author avbravo
 */
@View("/demo.xhtml")
@ApplicationScoped
public class DemoFacelet extends Facelet {
 List<Oceano> list =  Arrays.asList(new Oceano("pac","Pacifico"), new Oceano("at","Atlantico"));
 private HtmlPanelGroup editableDataTableGroup; // Placeholder.
    public List<Oceano> getList() {
        return list;
    }

    public void setList(List<Oceano> list) {
        this.list = list;
    }
 
 
 
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
//        HtmlDataTable dataTable = new HtmlDataTable();
//        // Get amount of columns.
//       
//        int columns = ((List) list.get(0)).size();
//
//        // Set columns.
//        for (int i = 0; i < columns; i++) {
//
//            // Set header (optional).
//            UIOutput header = new UIOutput();
//            header.setValue("idoceano");
//            
//
//            // Set output.
//            UIOutput outputColumn = new UIOutput();
//            ValueBinding myItem
//                    = FacesContext
//                            .getCurrentInstance()
//                            .getApplication()
//                            .createValueBinding("#{myItem[" + i + "]}");
//            outputColumn.setValueBinding("value", myItem);
//
//            // Set column.
//            UIColumn column = new UIColumn();
//            column.setHeader(header);
//            column.getChildren().add(outputColumn);
//
//            // Add column.
//          dataTable.getChildren().add(column);
//        }
//form.getChildren().add(dataTable);


/**
 * Other
 * 
 */
// Create <h:dataTable value="#{myBean.dataList}" var="dataItem">.
        HtmlDataTable editableDataTable = new HtmlDataTable();
        editableDataTable.setValueExpression("value",
            createValueExpression("#{DemoFacelet.list}", List.class));
        editableDataTable.setVar("dataItem");

        // Create <h:column> for 'ID' column.
        HtmlColumn idColumn = new HtmlColumn();
        editableDataTable.getChildren().add(idColumn);
        
        // Create <h:outputText value="ID"> for <f:facet name="header"> of 'ID' column.
        HtmlOutputText idHeader = new HtmlOutputText();
        idHeader.setValue("ID");
        idColumn.setHeader(idHeader);

        // Create <h:outputText value="#{dataItem.id}"> for the body of 'ID' column.
        HtmlOutputText idOutput = new HtmlOutputText();
        idOutput.setValueExpression("value",
            createValueExpression("#{dataItem.idoceano}", String.class));
        idColumn.getChildren().add(idOutput);

        // Create <h:column> for 'Name' column.
        HtmlColumn nameColumn = new HtmlColumn();
        editableDataTable.getChildren().add(nameColumn);
        
         // Create <h:outputText value="Name"> for <f:facet name="header"> of 'Name' column.
        HtmlOutputText nameHeader = new HtmlOutputText();
        nameHeader.setValue("Name");
        nameColumn.setHeader(nameHeader);

        // Create <h:inputText id="name" value="#{dataItem.name}"> for the body of 'Name' column.
        HtmlInputText nameInput = new HtmlInputText();
        nameInput.setId("name"); // Custom ID is required in dynamic UIInput and UICommand.
        nameInput.setValueExpression("value",
            createValueExpression("#{dataItem.oceano}", String.class));
        nameColumn.getChildren().add(nameInput);
// Create <h:column> for 'Value' column.
        HtmlColumn valueColumn = new HtmlColumn();
        editableDataTable.getChildren().add(valueColumn);

      //   Create <h:outputText value="Value"> for <f:facet name="header"> of 'Value' column.
        HtmlOutputText valueHeader = new HtmlOutputText();
        valueHeader.setValue("Value");
        valueColumn.setHeader(valueHeader);

        // Create <h:inputText id="value" value="#{dataItem.value}"> for the body of 'Value' column.
        HtmlInputText valueInput = new HtmlInputText();
        valueInput.setId("value"); // Custom ID is required in dynamic UIInput and UICommand.
        valueInput.setValueExpression("value",
            createValueExpression("#{dataItem.oceano}", String.class));
        valueColumn.getChildren().add(valueInput);

        // Add the datatable to <h:panelGroup binding="#{myBean.editableDataTableGroup}">.
        editableDataTableGroup = new HtmlPanelGroup();
        editableDataTableGroup.getChildren().add(editableDataTable);

        // Create <h:outputText value="<br/>" escape="false"> and add to <h:panelGroup
        // binding="#{myBean.editableDataTableGroup}">.
        HtmlOutputText lineBreak = new HtmlOutputText();
        lineBreak.setValue("<br/>");
        lineBreak.setEscape(false); // Don't escape HTML.
        editableDataTableGroup.getChildren().add(lineBreak);

        // Create <h:commandButton id="save" value="Save" action="#{myBean.saveDataList}">
        // and add to <h:panelGroup binding="#{myBean.editableDataTableGroup}">.
        HtmlCommandButton saveButton = new HtmlCommandButton();
        saveButton.setId("save"); // Custom ID is required in dynamic UIInput and UICommand.
        saveButton.setValue("Save");
        saveButton.setActionExpression(
            createActionExpression("#{myBean.saveDataList}", String.class));
        editableDataTableGroup.getChildren().add(saveButton);
        form.getChildren().add(editableDataTable);
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
    
    private ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createValueExpression(
            facesContext.getELContext(), valueExpression, valueType);
    }

    private MethodExpression createActionExpression(String actionExpression, Class<?> returnType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createMethodExpression(
            facesContext.getELContext(), actionExpression, returnType, new Class[0]);
    }
}
