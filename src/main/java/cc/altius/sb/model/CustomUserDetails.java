/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.model;

//import cc.altius.utils.DateUtils;
import cc.altius.maven.AllAltiusUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Akil Mahimwala
 */
public class CustomUserDetails implements UserDetails {

    private int userId;
    private String username;
    private String name;
    private String emailId;
    private String phoneNo;
    private String password;
    private boolean active;
    private boolean expired;
    private Date expiresOn;
    private int failedAttempts;
    private boolean outsideAccess;
    private List<Role> roles;
    private List<SimpleGrantedAuthority> businessFunction;
    private byte[] imageData;

    public Collection<SimpleGrantedAuthority> getBusinessFunction() {
        return businessFunction;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setBusinessFunction(List<String> businessFunction) {
        List<SimpleGrantedAuthority> finalBusinessFunctions = new ArrayList<>();
        for (String bf : businessFunction) {
            finalBusinessFunctions.add(new SimpleGrantedAuthority(bf));
        }
        this.businessFunction = finalBusinessFunctions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
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

    @Override
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.businessFunction;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.failedAttempts < 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isPasswordExpired() {
        String curDate = AllAltiusUtil.getCurrentDateString(AllAltiusUtil.EST, AllAltiusUtil.YMD);
        return AllAltiusUtil.compareDates(AllAltiusUtil.formatDate(this.expiresOn, AllAltiusUtil.YMD), curDate) < 0;
    }

    public boolean isOutsideAccess() {
        return outsideAccess;
    }

    public void setOutsideAccess(boolean outsideAccess) {
        this.outsideAccess = outsideAccess;
    }

    public String getImageDataBase64() {
        StringBuilder sb = new StringBuilder("data:image/png;base64,");
        if (this.imageData.length > 0) {
            byte[] encodeBase64 = Base64.encodeBase64(this.imageData);
            try {
                return sb.append(new String(encodeBase64, "UTF-8")).toString();
            } catch (UnsupportedEncodingException ex) {
                return "../assets/images/users/no-image.jpg";
            }
        } else {
            return "../assets/images/users/no-image.jpg";
        }
    }

    public boolean hasBusinessFunction(String bf) {
        for (SimpleGrantedAuthority currentBf : this.getBusinessFunction()) {
            if (currentBf.toString().equals(bf)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" + "userId=" + userId + ", username=" + username + ", businessFunction=" + getAuthorities() + '}';
    }
}
