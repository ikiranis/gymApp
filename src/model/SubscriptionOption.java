/*
 *  File: SubscriptionOption.java
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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yiannis Kyranis <yiannis.kiranis at gmail.com>
 */
@Entity
@Table(name = "SUBSCRIPTION_OPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubscriptionOption.findAll", query = "SELECT s FROM SubscriptionOption s")
    , @NamedQuery(name = "SubscriptionOption.findByPkId", query = "SELECT s FROM SubscriptionOption s WHERE s.pkId = :pkId")
    , @NamedQuery(name = "SubscriptionOption.findByDescription", query = "SELECT s FROM SubscriptionOption s WHERE s.description = :description")
    , @NamedQuery(name = "SubscriptionOption.findByCost", query = "SELECT s FROM SubscriptionOption s WHERE s.cost = :cost")
    , @NamedQuery(name = "SubscriptionOption.findByMonthsDur", query = "SELECT s FROM SubscriptionOption s WHERE s.monthsDur = :monthsDur")})
public class SubscriptionOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ID")
    private Integer pkId;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "COST")
    private double cost;
    @Basic(optional = false)
    @Column(name = "MONTHS_DUR")
    private int monthsDur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionOption")
    private Collection<Subscriber> subscriberCollection;

    public SubscriptionOption() {
    }

    public SubscriptionOption(Integer pkId) {
        this.pkId = pkId;
    }

    public SubscriptionOption(Integer pkId, String description, double cost, int monthsDur) {
        this.pkId = pkId;
        this.description = description;
        this.cost = cost;
        this.monthsDur = monthsDur;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMonthsDur() {
        return monthsDur;
    }

    public void setMonthsDur(int monthsDur) {
        this.monthsDur = monthsDur;
    }

    @XmlTransient
    public Collection<Subscriber> getSubscriberCollection() {
        return subscriberCollection;
    }

    public void setSubscriberCollection(Collection<Subscriber> subscriberCollection) {
        this.subscriberCollection = subscriberCollection;
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
        if (!(object instanceof SubscriptionOption)) {
            return false;
        }
        SubscriptionOption other = (SubscriptionOption) object;
        if ((this.pkId == null && other.pkId != null) || (this.pkId != null && !this.pkId.equals(other.pkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SubscriptionOption[ pkId=" + pkId + " ]";
    }
    
}
