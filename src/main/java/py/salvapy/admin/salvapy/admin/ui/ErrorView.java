package py.salvapy.admin.salvapy.admin.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ErrorView extends VerticalLayout implements View {

    private Label explanation;

    public ErrorView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label("The view could not be found");
        header.addStyleName(ValoTheme.BUTTON_LINK);
        addComponent(header);
        addComponent(explanation = new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(String.format(
                "You tried to navigate to a view ('%s') that does not exist.",
                event.getViewName()));
    }
}
