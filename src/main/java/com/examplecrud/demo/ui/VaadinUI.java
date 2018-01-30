package com.examplecrud.demo.ui;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.service.DocumentService;
import com.examplecrud.demo.ui.editor.DocumentEditor;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


@SpringUI
@Theme("valo")
public class VaadinUI extends UI{

    private DocumentService documentService;

    private Grid<Document> documentGrid = new Grid<>(Document.class);
    private TextField filter = new TextField();

    private Button addNewButton = new Button("New document");

    private DocumentEditor editor;

    @Autowired
    public VaadinUI(DocumentEditor editor, DocumentService documentService){
        this.editor = editor;
        this.documentService = documentService;
    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
        VerticalLayout mainLayout = new VerticalLayout(actions, documentGrid, editor);
        setContent(mainLayout);

        documentGrid.setHeight(400, Unit.PIXELS);
        documentGrid.setWidth(1000, Unit.PIXELS);
        documentGrid.setColumns("id", "name", "code", "description");

        filter.setPlaceholder("Filter by name");

        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listDocuments(e.getValue()));

        documentGrid.asSingleSelect().addValueChangeListener(e ->{
            editor.editDocument(e.getValue());
        });

        addNewButton.addClickListener(e -> {
            editor.editDocument(new Document());
        });

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listDocuments(filter.getValue());
        });

        listDocuments(null);
    }

    private void listDocuments(String filterText){
        if(StringUtils.isEmpty(filterText)){
            documentGrid.setItems(documentService.findAll());
        }
        else{
            documentGrid.setItems(documentService.findByNameStart(filterText));
        }
    }

}

