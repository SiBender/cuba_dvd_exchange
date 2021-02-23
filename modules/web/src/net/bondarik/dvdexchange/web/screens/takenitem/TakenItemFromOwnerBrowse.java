package net.bondarik.dvdexchange.web.screens.takenitem;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import net.bondarik.dvdexchange.entity.TakenItem;

import javax.inject.Inject;
import java.util.UUID;

@UiController("dvdexchange_TakenItemFromOwner.browse")
@UiDescriptor("taken-items-from-owner-browse.xml")
@LookupComponent("takenItemsTable")
@LoadDataBeforeShow
public class TakenItemFromOwnerBrowse extends StandardLookup<TakenItem> {
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionLoader<TakenItem> takenItemsDl;

    @Subscribe
    private void onInit(InitEvent event) {
        UUID userId = userSession.getUser().getId();

        takenItemsDl.setParameter("userId", userId);
        takenItemsDl.load();
    }
}