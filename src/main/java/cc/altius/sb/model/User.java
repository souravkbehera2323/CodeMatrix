/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Akil Mahimwala
 */
public class User implements Serializable {

    private int userId;
    private String username;
    private String name;
    private String emailId;
    private String phoneNo;
    private String password;
    private boolean active;
    private boolean expired;
    private int failedAttempts;
    private Date expiresOn;
    private List<Role> roles;
    private boolean outsideAccess;
    private Date lastLoginDate;
    private Date createdDate;
    private User createdBy;
    private Date lastModifiedDate;
    private User lastModifiedBy;
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isOutsideAccess() {
        return outsideAccess;
    }

    public void setOutsideAccess(boolean outsideAccess) {
        this.outsideAccess = outsideAccess;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getActiveString() {
        if (this.active == false) {
            return "Disabled";
        } else if (this.active && this.failedAttempts < 3) {
            return "Active";
        } else {
            return "Locked";
        }
    }

    public String getNameLimit() {
        if (this.name != null) {
            if (this.name.length() > 20) {
                return this.name.substring(0, 16) + " ...";
            } else {
                return this.name;
            }
        } else {
            return "";
        }
    }

    public String getHasOutsideAccess() {
        if (this.outsideAccess) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public String getRoleList() {
        String roleList = "";
        for (Role r : this.roles) {
            roleList += r.getRoleName() + ", ";
        }
        if (roleList.length() > 2) {
            roleList = roleList.substring(0, roleList.length() - 2);
        }
        return roleList;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", name=" + name + ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", password=" + password + ", active=" + active + ", expired=" + expired + ", failedAttempts=" + failedAttempts + ", expiresOn=" + expiresOn + ", roles=" + roles + ", outsideAccess=" + outsideAccess + ", lastLoginDate=" + lastLoginDate + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", multipartFile=" + multipartFile + '}';
    }

}
