package com.examplecrud.demo.ui.login;

import com.examplecrud.demo.service.SecurityService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/login")
@Title("LoginPage")
@Theme("valo")
public class LoginUI extends UI{
    TextField user;
    PasswordField password;
    Button loginButton = new Button("Login", this::loginButtonClick);
    private static final String username = "username";
    private static final String passwordValue = "test123";

    @Autowired
    SecurityService securityService;

    @Override
    protected void init(VaadinRequest request) {
        setSizeFull();

        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequiredIndicatorVisible(true);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        user.setRequiredIndicatorVisible(true);
        password.setValue("");

        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout uiLayout = new VerticalLayout(fields);
        uiLayout.setSizeFull();
        uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);

        setFocusedComponent(user);

        setContent(uiLayout);
    }

    public void loginButtonClick(Button.ClickEvent e) {
        //authorize/authenticate user
        //tell spring that my user is authenticated and dispatch to my mainUI
        securityService.autoLogin(user.getValue(), password.getValue());
    }
}
