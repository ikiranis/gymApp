/*
 *  File: Subscriber.java
 * 
 * The MIT License
 *
 * Copyright 2022 Yiannis Kyranis <yiannis.kiranis at gmail.com>.
 * https://apps4net.eu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 *  Date: Jan 25, 2022
 *  Time: 5:30:49 PM
 * 
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yiannis Kyranis <yiannis.kiranis at gmail.com>
 */
@Entity
@Table(name = "SUBSCRIBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscriber.findAll", query = "SELECT s FROM Subscriber s")
    , @NamedQuery(name = "Subscriber.findByPkId", query = "SELECT s FROM Subscriber s WHERE s.pkId = :pkId")
    , @NamedQuery(name = "Subscriber.findByCode", query = "SELECT s FROM Subscriber s WHERE s.code = :code")
    , @NamedQuery(name = "Subscriber.findByFullname", query = "SELECT s FROM Subscriber s WHERE s.fullname = :fullname")
    , @NamedQuery(name = "Subscriber.findByAddress", query = "SELECT s FROM Subscriber s WHERE s.address = :address")
    , @NamedQuery(name = "Subscriber.findByPhone", query = "SELECT s FROM Subscriber s WHERE s.phone = :phone")
    , @NamedQuery(name = "Subscriber.findByStartDate", query = "SELECT s FROM Subscriber s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Subscriber.findByEndDate", query = "SELECT s FROM Subscriber s WHERE s.endDate = :endDate")})
public class Subscriber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ID")
    private Integer pkId;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "FULLNAME")
    private String fullname;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "SUBSCRIPTION_OPTION", referencedColumnName = "PK_ID")
    @ManyToOne(optional = false)
    private SubscriptionOption subscriptionOption;

    public Subscriber() {
    }

    public Subscriber(Integer pkId) {
        this.pkId = pkId;
    }

    public Subscriber(Integer pkId, String code, String fullname, String address) {
        this.pkId = pkId;
        this.code = code;
        this.fullname = fullname;
        this.address = address;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SubscriptionOption getSubscriptionOption() {
        return subscriptionOption;
    }

    public void setSubscriptionOption(SubscriptionOption subscriptionOption) {
        this.subscriptionOption = subscriptionOption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkId != null ? pkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscriber)) {
            return false;
        }
        Subscriber other = (Subscriber) object;
        if ((this.pkId == null && other.pkId != null) || (this.pkId != null && !this.pkId.equals(other.pkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Subscriber[ pkId=" + pkId + " ]";
    }
    
}
