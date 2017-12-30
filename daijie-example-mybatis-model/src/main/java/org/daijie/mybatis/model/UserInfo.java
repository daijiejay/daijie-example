package org.daijie.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 姓名
     */
    @Column(name = "realname")
    private String realname;

    /**
     * 姓别
     */
    @Column(name = "gander")
    private Integer gander;

    /**
     * 生日
     */
    @Column(name = "brithday")
    private Date brithday;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 禁用状态
     */
    @Column(name = "enable")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    public UserInfo withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取姓名
     *
     * @return realname - 姓名
     */
    public String getRealname() {
        return realname;
    }

    public UserInfo withRealname(String realname) {
        this.setRealname(realname);
        return this;
    }

    /**
     * 设置姓名
     *
     * @param realname 姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取姓别
     *
     * @return gander - 姓别
     */
    public Integer getGander() {
        return gander;
    }

    public UserInfo withGander(Integer gander) {
        this.setGander(gander);
        return this;
    }

    /**
     * 设置姓别
     *
     * @param gander 姓别
     */
    public void setGander(Integer gander) {
        this.gander = gander;
    }

    /**
     * 获取生日
     *
     * @return brithday - 生日
     */
    public Date getBrithday() {
        return brithday;
    }

    public UserInfo withBrithday(Date brithday) {
        this.setBrithday(brithday);
        return this;
    }

    /**
     * 设置生日
     *
     * @param brithday 生日
     */
    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    public UserInfo withMobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取住址
     *
     * @return address - 住址
     */
    public String getAddress() {
        return address;
    }

    public UserInfo withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    /**
     * 设置住址
     *
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取禁用状态
     *
     * @return enable - 禁用状态
     */
    public Boolean getEnable() {
        return enable;
    }

    public UserInfo withEnable(Boolean enable) {
        this.setEnable(enable);
        return this;
    }

    /**
     * 设置禁用状态
     *
     * @param enable 禁用状态
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", realname=").append(realname);
        sb.append(", gander=").append(gander);
        sb.append(", brithday=").append(brithday);
        sb.append(", mobile=").append(mobile);
        sb.append(", address=").append(address);
        sb.append(", enable=").append(enable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getGander() == null ? other.getGander() == null : this.getGander().equals(other.getGander()))
            && (this.getBrithday() == null ? other.getBrithday() == null : this.getBrithday().equals(other.getBrithday()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getGander() == null) ? 0 : getGander().hashCode());
        result = prime * result + ((getBrithday() == null) ? 0 : getBrithday().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        return result;
    }
}