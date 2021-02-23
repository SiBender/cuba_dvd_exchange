package net.bondarik.dvdexchange.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "DVDEXCHANGE_TAKEN_ITEM")
@Entity(name = "dvdexchange_TakenItem")
public class TakenItem extends StandardEntity {
    private static final long serialVersionUID = -6313895336408471091L;

    @NotNull
    @Unique
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DISK_ID", unique = true)
    private Disk disk;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private DvdUser user;

    public DvdUser getUser() {
        return user;
    }

    public void setUser(DvdUser user) {
        this.user = user;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}