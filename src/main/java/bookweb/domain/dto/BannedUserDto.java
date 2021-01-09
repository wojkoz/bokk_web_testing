package bookweb.domain.dto;
import java.sql.Timestamp;

public class BannedUserDto {
    private Long bannedUserId;
    private Timestamp banDate;
    private String Reason;

    public BannedUserDto() {
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

