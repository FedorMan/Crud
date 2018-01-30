package com.examplecrud.demo.ui.editor;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.service.DocumentService;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
@UIScope
public class DocumentEditor extends VerticalLayout{

    private DocumentService documentService;

    private Document document;

    private TextField name = new TextField("Name");
    private TextField code = new TextField("Code");
    private TextField description = new TextField("Description");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");
    private Button deleteButton = new Button("Delete");

    private CssLayout actions = new CssLayout(saveButton,cancelButton,deleteButton);

    private Binder<Document> binder = new Binder<>(Document.class);

    @Autowired
    public DocumentEditor(DocumentService documentService){
        this.documentService = documentService;

        addComponents(name,code,description,actions);

        binder.bindInstanceFields(this);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        saveButton.addClickListener(e -> documentService.save(document));
        deleteButton.addClickListener(e -> documentService.delete(document.getId()));
        cancelButton.addClickListener(e -> editDocument(document));
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editDocument(Document doc){
        if(doc == null){
            setVisible(false);
            return;
        }
        final boolean persisted = doc.getId() != null;
        if (persisted)
            document = documentService.read(doc.getId());
        else
            document = doc;
        cancelButton.setVisible(persisted);

        binder.setBean(document);

        setVisible(true);

        saveButton.focus();
        name.selectAll();

    }

    public void setChangeHandler(ChangeHandler h) {
        saveButton.addClickListener(e -> h.onChange());
        deleteButton.addClickListener(e -> h.onChange());
    }
}
