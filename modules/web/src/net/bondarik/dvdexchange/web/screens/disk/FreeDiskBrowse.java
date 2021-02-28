package net.bondarik.dvdexchange.web.screens.disk;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import net.bondarik.dvdexchange.entity.Disk;
import net.bondarik.dvdexchange.entity.DvdUser;
import net.bondarik.dvdexchange.entity.TakenItem;

import javax.inject.Inject;

@UiController("dvdexchange_FreeDisk.browse")
@UiDescriptor("free-disk-browse.xml")
@LookupComponent("disksTable")
@LoadDataBeforeShow
public class FreeDiskBrowse extends StandardLookup<Disk> {
    @Inject
    private GroupTable<Disk> disksTable;
    @Inject
    private Dialogs dialogs;
    @Inject
    private DataManager dataManager;
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionLoader disksDl;

    @Subscribe
    protected void onInit(InitEvent event) {
        disksDl.setParameter("userId", userSession.getUser().getId());

        disksTable.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->  {
                    Disk disk = disksTable.getSingleSelected();
                    if (disk != null) {
                        promptForTakeDisk(disk);
                    }
                })  );
    }

    private void promptForTakeDisk(Disk disk) {
        dialogs.createOptionDialog()
                .withCaption("Take disk")
                .withMessage("Do you want to take this disk?")
                .withType(Dialogs.MessageType.CONFIRMATION)
                .withActions(
                        new DialogAction(DialogAction.Type.OK)
                                .withHandler(e -> { takeDisk(disk); }),
                        new DialogAction(DialogAction.Type.CANCEL))
                .show();
    }

    private void takeDisk(Disk disk) {
        TakenItem takenItem = new TakenItem();
        takenItem.setDisk(disk);
        takenItem.setUser((DvdUser)userSession.getUser());
        dataManager.commit(takenItem);
        disksDl.load();
    }
}