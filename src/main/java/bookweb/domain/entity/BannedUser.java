package bookweb.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "banned_users")
public class BannedUser implements Serializable {
    @Id
    @Column(name = "id_banned_user")
    private Long bannedUserId;

    @Column(name = "ban_date")
    private Timestamp banDate;

    @Column(name = "ban_reason")
    private String Reason;

    public BannedUser() {

    }

    public Long getBannedUserId() {
        return bannedUserId;
    }

    public Timestamp getBanDate() {
        return banDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setBannedUserId(Long bannedUserId) {
        this.bannedUserId = bannedUserId;
    }

    public void setBanDate(Timestamp banDate) {
        this.banDate = banDate;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}
