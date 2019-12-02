package com.finalwork.android.e_commerce.model.entity;

import java.util.Date;

/**
 * �û�ʵ����
 *
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable {

    private static User user = new User();

    public static User getInstance() {
        return user;
    }

    /*
	 * create table wm_user
		(
		   id                   bigint not null,
		   loginName            varchar(255) not null,
		   loginPwd             varchar(255) not null,
		   loginPin             varchar(255) not null,
		   userType             bigint not null,
		   userSex              bigint not null,
		   userName             varchar(255) not null,
		   trueName             varchar(255) not null,
		   birthday             date not null,
		   userPhoto            varchar(255) not null,
		   userPhone            varchar(255) not null,
		   userEmail            varchar(255) not null,
		   userScore            int not null,
		   userTotalScore       bigint not null,
		   userMoney            bigint not null,
		   userStatus           bigint not null,
		   registerTime         datetime not null,
		   payPwd               varchar(255) not null,
		   primary key (id)
		);
	 */

    private Long id;

    private String loginName;

    private String loginPassword;

    private String loginPin;

    private String payPassword;

    private BasicDic userType;

    private BasicDic userSex;

    private String userName;

    private String trueName;

    private Date birthday;

    private Date registerTime;

    private String userPhotoLocation;

    private String userPhone;

    private String userEmail;

    private Integer userScore;

    private Long userTotalScore;

    private Long userMoney;

    private BasicDic userStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPin() {
        return loginPin;
    }

    public void setLoginPin(String loginPin) {
        this.loginPin = loginPin;
    }

    public BasicDic getUserType() {
        return userType;
    }

    public void setUserType(BasicDic userType) {
        this.userType = userType;
    }

    public BasicDic getUserSex() {
        return userSex;
    }

    public void setUserSex(BasicDic userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserPhotoLocation() {
        return userPhotoLocation;
    }

    public void setUserPhotoLocation(String userPhotoLocation) {
        this.userPhotoLocation = userPhotoLocation;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Long getUserTotalScore() {
        return userTotalScore;
    }

    public void setUserTotalScore(Long userTotalScore) {
        this.userTotalScore = userTotalScore;
    }

    public Long getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Long userMoney) {
        this.userMoney = userMoney;
    }

    public BasicDic getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(BasicDic userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
        result = prime * result + ((loginPassword == null) ? 0 : loginPassword.hashCode());
        result = prime * result + ((loginPin == null) ? 0 : loginPin.hashCode());
        result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
        result = prime * result + ((trueName == null) ? 0 : trueName.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        result = prime * result + ((userMoney == null) ? 0 : userMoney.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
        result = prime * result + ((userPhotoLocation == null) ? 0 : userPhotoLocation.hashCode());
        result = prime * result + ((userScore == null) ? 0 : userScore.hashCode());
        result = prime * result + ((userSex == null) ? 0 : userSex.hashCode());
        result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
        result = prime * result + ((userTotalScore == null) ? 0 : userTotalScore.hashCode());
        result = prime * result + ((userType == null) ? 0 : userType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (loginName == null) {
            if (other.loginName != null)
                return false;
        } else if (!loginName.equals(other.loginName))
            return false;
        if (loginPassword == null) {
            if (other.loginPassword != null)
                return false;
        } else if (!loginPassword.equals(other.loginPassword))
            return false;
        if (loginPin == null) {
            if (other.loginPin != null)
                return false;
        } else if (!loginPin.equals(other.loginPin))
            return false;
        if (registerTime == null) {
            if (other.registerTime != null)
                return false;
        } else if (!registerTime.equals(other.registerTime))
            return false;
        if (trueName == null) {
            if (other.trueName != null)
                return false;
        } else if (!trueName.equals(other.trueName))
            return false;
        if (userEmail == null) {
            if (other.userEmail != null)
                return false;
        } else if (!userEmail.equals(other.userEmail))
            return false;
        if (userMoney == null) {
            if (other.userMoney != null)
                return false;
        } else if (!userMoney.equals(other.userMoney))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userPhone == null) {
            if (other.userPhone != null)
                return false;
        } else if (!userPhone.equals(other.userPhone))
            return false;
        if (userPhotoLocation == null) {
            if (other.userPhotoLocation != null)
                return false;
        } else if (!userPhotoLocation.equals(other.userPhotoLocation))
            return false;
        if (userScore == null) {
            if (other.userScore != null)
                return false;
        } else if (!userScore.equals(other.userScore))
            return false;
        if (userSex == null) {
            if (other.userSex != null)
                return false;
        } else if (!userSex.equals(other.userSex))
            return false;
        if (userStatus == null) {
            if (other.userStatus != null)
                return false;
        } else if (!userStatus.equals(other.userStatus))
            return false;
        if (userTotalScore == null) {
            if (other.userTotalScore != null)
                return false;
        } else if (!userTotalScore.equals(other.userTotalScore))
            return false;
        if (userType == null) {
            if (other.userType != null)
                return false;
        } else if (!userType.equals(other.userType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", loginName=" + loginName + ", loginPassword=" + loginPassword + ", loginPin="
                + loginPin + ", userType=" + userType + ", userSex=" + userSex + ", userName=" + userName
                + ", trueName=" + trueName + ", birthday=" + birthday + ", registerTime=" + registerTime
                + ", userPhotoLocation=" + userPhotoLocation + ", userPhone=" + userPhone + ", userEmail=" + userEmail
                + ", userScore=" + userScore + ", userTotalScore=" + userTotalScore + ", userMoney=" + userMoney
                + ", userStatus=" + userStatus + "]";
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
