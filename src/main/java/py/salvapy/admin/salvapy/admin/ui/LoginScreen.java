package py.salvapy.admin.salvapy.admin.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import javax.ejb.EJB;
import javax.inject.Inject;
import py.salvapy.admin.salvapy.admin.dao.AutorizationResolver;
import py.salvapy.admin.salvapy.admin.entities.Usuario;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;


public class LoginScreen extends CssLayout {

    private TextField username;
    private PasswordField password;
    private Button login;
    private Button forgotPassword;
    private AutorizationResolver autorizationResolver = ResourceLocator.locate(AutorizationResolver.class);

    private LoginListener loginListener;

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public LoginScreen() {
        buildUI();
        username.focus();
    }

    private void buildUI() {
        addStyleName("login-screen");
        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
        
        // information text about logging in
        Component loginForm = buildLoginForm();
        CssLayout loginInformation = buildLoginInformation();
        
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(loginInformation);
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm,
                Alignment.MIDDLE_CENTER);
                centeringLayout.setComponentAlignment(loginInformation,
                Alignment.MIDDLE_CENTER);
        centeringLayout.setMargin(true);
        centeringLayout.setSizeFull();
        //addComponent(loginInformation);
        Panel panel = new Panel("Login");
        panel.setWidth(null);
        panel.addStyleName("mypanelexample");
        panel.setSizeUndefined();
        panel.setContent(centeringLayout);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(panel);
        vLayout.setSizeFull();
        vLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        addComponent(vLayout);
        setSizeFull();  
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

        loginForm.addComponent(username = new TextField("Username", ""));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Password"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Write anything");
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Login"));
        login.setDisableOnClick(true);
        login.addClickListener(e -> {
            try {
                login();
            } finally {
                login.setEnabled(true);
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        buttons.addComponent(forgotPassword = new Button("Forgot password?"));
        forgotPassword.addClickListener(e -> showNotification(new Notification("Hint: Try anything")));
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        return loginForm;
    }

    private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        //&quot;admin&quot;
        Label loginInfoText = new Label("<h1>Admin Salva Py</h1>Administrador de Personal",ContentMode.HTML);
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }


    private void login() {
        String user = username.getValue();
        String pass = password.getValue();
        Usuario usuarios = autorizationResolver.authorizedUser(user,pass);
        if (usuarios != null) { // FIXME, ejb cal
            UserHolder.setUsuarios(usuarios);
            loginListener.loginSuccessful();
        } else {
            showNotification(new Notification("Login failed", "Por favor asegurese que el usuario y password sean correctos...", Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public interface LoginListener {
        void loginSuccessful();
    }
}
