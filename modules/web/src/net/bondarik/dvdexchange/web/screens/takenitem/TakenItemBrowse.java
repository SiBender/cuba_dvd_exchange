package net.bondarik.dvdexchange.web.screens.takenitem;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import net.bondarik.dvdexchange.entity.TakenItem;

import javax.inject.Inject;
import java.util.UUID;

@UiController("dvdexchange_TakenItem.browse")
@UiDescriptor("taken-item-browse.xml")
@LookupComponent("takenItemsTable")
@LoadDataBeforeShow
public class TakenItemBrowse extends StandardLookup<TakenItem> {
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionLoader<TakenItem> takenItemsDl;
    @Inject
    private GroupTable<TakenItem> takenItemsTable;
    @Inject
    private Dialogs dialogs;
    @Inject
    private DataManager dataManager;

    @Subscribe
    private void onInit(InitEvent event) {
        UUID userId = userSession.getUser().getId();

        takenItemsDl.setParameter("userId", userId);
        takenItemsDl.load();

        takenItemsTable.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->  {
                    TakenItem takenItem = takenItemsTable.getSingleSelected();
                    if (takenItem != null) {
                        promptForReturnDisk(takenItem);
                    }
                })  );
    }

    private void promptForReturnDisk(TakenItem takenItem) {
        dialogs.createOptionDialog()
                .withCaption("Return disk")
                .withMessage("Do you want to return disk?")
                .withType(Dialogs.MessageType.CONFIRMATION)
                .withActions(
                        new DialogAction(DialogAction.Type.OK)
                                .withHandler(e -> { returnDisk(takenItem); }),
                        new DialogAction(DialogAction.Type.CANCEL))
                .show();
    }

    private void returnDisk(TakenItem takenItem) {
        dataManager.remove(takenItem);
        takenItemsDl.load();
    }
}