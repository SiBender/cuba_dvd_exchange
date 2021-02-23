package net.bondarik.dvdexchange.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "DVDEXCHANGE_DISK")
@Entity(name = "dvdexchange_Disk")
@NamePattern("%s|title")
public class Disk extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -4326773150391184224L;

    @Column(name = "UUID")
    private UUID uuid;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OWNER_ID")
    private DvdUser owner;

    public DvdUser getOwner() {
        return owner;
    }

    public void setOwner(DvdUser owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}