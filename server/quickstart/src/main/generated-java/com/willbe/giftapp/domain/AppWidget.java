/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.willbe.giftapp.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "app_widget")
public class AppWidget implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(AppWidget.class.getName());

    // Raw attributes
    private Integer id;
    private Integer type;
    private String rule;

    // Many to one
    private App_ app;

    @Override
    public String entityClassName() {
        return AppWidget.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "id", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public AppWidget id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [type] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "\"type\"", nullable = false, precision = 10)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public AppWidget type(Integer type) {
        setType(type);
        return this;
    }
    // -- [rule] ------------------------

    @Size(max = 4000)
    @Column(name = "rule", length = 4000)
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public AppWidget rule(String rule) {
        setRule(rule);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: AppWidget.app ==> App_.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "app_id", nullable = false)
    @ManyToOne
    public App_ getApp() {
        return app;
    }

    /**
     * Set the {@link #app} without adding this AppWidget instance on the passed {@link #app}
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by {@link App_}
     */
    public void setApp(App_ app) {
        this.app = app;
    }

    public AppWidget app(App_ app) {
        setApp(app);
        return this;
    }

    /**
     * Apply the default values.
     */
    public AppWidget withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof AppWidget && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this AppWidget instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("type", getType()) //
                .add("rule", getRule()) //
                .toString();
    }
}