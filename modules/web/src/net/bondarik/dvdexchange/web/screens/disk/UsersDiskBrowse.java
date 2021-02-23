package net.bondarik.dvdexchange.web.screens.disk;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.StandardLookup;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import net.bondarik.dvdexchange.entity.Disk;
import net.bondarik.dvdexchange.entity.DvdUser;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@UiController("dvdexchange_UsersDisk.browse")
public class UsersDiskBrowse extends StandardLookup<DvdUser> {

    @Inject
    private DataComponents dataComponents;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private DataManager dataManager;


    private InstanceContainer<Disk> diskDc;
    private InstanceLoader<Disk> diskDl;

    @Subscribe
    private void init(Screen.InitEvent event) {
        Label<String> label = uiComponents.create(Label.TYPE_STRING);
        label.setValue("Users disks");
        getWindow().add(label);

        UUID userId = userSessionSource.getUserSession().getUser().getId();
        List<Disk> usersDisks = dataManager.load(Disk.class)
                                           .query("select d from dvdexchange_Disk d" +
                                                            "JOIN d.owner o" +
                                                            "WHERE o.id = :userId")
                                           .parameter("userId", userId)
                                           .list();



        createDataComponents();
        createUiComponents();

    }

    private void createDataComponents() {
        DataContext dataContext = dataComponents.createDataContext();
        getScreenData().setDataContext(dataContext);

        diskDc = dataComponents.createInstanceContainer(Disk.class);

        diskDl = dataComponents.createInstanceLoader();
        diskDl.setContainer(diskDc);
        diskDl.setDataContext(dataContext);
        //diskDl.setView("some-view-name");

    }

    private void createUiComponents() {

    }
}
