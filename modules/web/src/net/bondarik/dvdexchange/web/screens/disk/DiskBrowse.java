package net.bondarik.dvdexchange.web.screens.disk;

import com.haulmont.cuba.gui.screen.*;
import net.bondarik.dvdexchange.entity.Disk;

@UiController("dvdexchange_Disk.browse")
@UiDescriptor("disk-browse.xml")
@LookupComponent("disksTable")
@LoadDataBeforeShow
public class DiskBrowse extends StandardLookup<Disk> {
}