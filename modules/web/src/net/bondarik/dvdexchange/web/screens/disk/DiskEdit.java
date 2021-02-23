package net.bondarik.dvdexchange.web.screens.disk;

import com.haulmont.cuba.gui.screen.*;
import net.bondarik.dvdexchange.entity.Disk;

@UiController("dvdexchange_Disk.edit")
@UiDescriptor("disk-edit.xml")
@EditedEntityContainer("diskDc")
@LoadDataBeforeShow
public class DiskEdit extends StandardEditor<Disk> {
}