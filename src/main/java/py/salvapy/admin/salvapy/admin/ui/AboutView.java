/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import py.salvapy.admin.salvapy.admin.util.Constants;

/**
 *
 * @author hectorvillalba
 */
public class AboutView  extends VerticalLayout implements View{
      public static final String VIEW_NAME = "AboutView";
      final Image image = new Image("");
      String url = Constants.PUBLIC_SERVER_URL + "/salvapy/appweb.jpeg";
      

    public AboutView() {
        CssLayout aboutContent = new CssLayout();
        aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout
        image.setSource(new ExternalResource(url));
        image.setWidth(600, Unit.PIXELS);
        image.setHeight(600, Unit.PIXELS);
        aboutContent.addComponent(image);
//        aboutContent.addComponent(
//                new Label(VaadinIcons.CIRCLE.getHtml()
//                        + " This application is using Vaadin "
//                        + Version.getFullVersion(), ContentMode.HTML));

        setSizeFull();
        setStyleName("about-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
  
}
