package net.bondarik.dvdexchange.web.screens.takenitem;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import net.bondarik.dvdexchange.entity.TakenItem;

import javax.inject.Inject;
import java.util.List;
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

    @Subscribe
    private void onInit(InitEvent event) {
        UUID userId = userSession.getUser().getId();

        takenItemsDl.setParameter("userId", userId);
        takenItemsDl.load();
    }
}