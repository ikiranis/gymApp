/*
 *  File: GymApp.java
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
 *  Date: Jan 24, 2022
 *  Time: 7:05:03 PM
 * 
 * 
 */
package gymapp;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Subscriber;
import model.SubscriptionOption;

/**
 *
 * @author Yiannis Kyranis <yiannis.kiranis at gmail.com>
 */
public class GymApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Δημιουργία EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GymAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        // Δημιουργία συνδρομών
        SubscriptionOption o1 = new SubscriptionOption(null, "Μηνιαία Σύνδρομή", 50, 1);
        SubscriptionOption o2 = new SubscriptionOption(null, "Τρίμηνη Σύνδρομή", 130, 3);
        SubscriptionOption o3 = new SubscriptionOption(null, "Εξάμηνη Σύνδρομή", 250, 6);
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.flush();
        
        
        // Δημιουργία συνδρομητών
        LocalDate date = LocalDate.of(2013,12,1);
        
        Subscriber s1 = new Subscriber(null, "1", "Νίκος Παπαδόπουλος", "Τσιμισκή 10");
        Subscriber s2 = new Subscriber(null, "2", "Γιώργος Παπαγεωργίου", "25ης Μαρτίου 10");
        
        s1.setSubscriptionOption(o2);
        s1.setStartDate(Date.valueOf(date));
        s1.setEndDate(Date.valueOf(date.plusMonths(o2.getMonthsDur())));
        
        s2.setSubscriptionOption(o3);
        s2.setStartDate(Date.valueOf(date));
        s2.setEndDate(Date.valueOf(date.plusMonths(o3.getMonthsDur())));
        
        em.persist(s1);
        em.persist(s2);
        
        em.getTransaction().commit();
        
        
        // Εκτύπωση συνδρομών
        Query query = em.createNamedQuery("SubscriptionOption.findAll", SubscriptionOption.class);
        ArrayList<SubscriptionOption> subscriptions = new ArrayList<SubscriptionOption>(query.getResultList());
        
        for (SubscriptionOption option: subscriptions) {
            System.out.printf("%d %s %f %d\n", option.getPkId(), option.getDescription(), option.getCost(), option.getMonthsDur());
        }
        
        
        // Αλλαγή συνδρομητή
        query = em.createNamedQuery("Subscriber.findByCode", SubscriptionOption.class);
        query.setParameter("code", "1");
        Subscriber subscriber = (Subscriber) query.getSingleResult();
        
        em.getTransaction().begin();
        subscriber.setSubscriptionOption(o3);

        
        // Διαγραφή συνδρομητή
        query = em.createNamedQuery("Subscriber.findByCode", SubscriptionOption.class);
        query.setParameter("code", "2");
        subscriber = (Subscriber) query.getSingleResult();
        em.remove(subscriber);
        
        em.getTransaction().commit();
        
        // Κλείσιμο του EntityManager
        em.close();
        emf.close();
    }
    
}
