package net.bondarik.dvdexchange.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "dvdexchange_DvdUser")
@Extends(User.class)
public class DvdUser extends User {
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "owner")
    private List<Disk> ownedDisks;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "user")
    private List<TakenItem> takenItems;

    public List<TakenItem> getTakenItems() {
        return takenItems;
    }

    public void setTakenItems(List<TakenItem> takenItems) {
        this.takenItems = takenItems;
    }

    public List<Disk> getOwnedDisks() {
        return ownedDisks;
    }

    public void setOwnedDisks(List<Disk> ownedDisks) {
        this.ownedDisks = ownedDisks;
    }
}