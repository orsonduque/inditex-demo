package com.neoris.inditex.domain;

import com.neoris.inditex.domain.enumeration.ISOCurrencyCode;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Price.
 */
@Entity
@Table(name = "price")
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @NotNull
    @Column(name = "price_list", nullable = false)
    private Long priceList;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "priority")
    private Integer priority;

    @NotNull
    @Column(name = "price_value", nullable = false)
    private Double priceValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "curr", nullable = false)
    private ISOCurrencyCode curr;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Price id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public Price brandId(Long brandId) {
        this.setBrandId(brandId);
        return this;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public Price startDate(ZonedDateTime startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public Price endDate(ZonedDateTime endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return this.priceList;
    }

    public Price priceList(Long priceList) {
        this.setPriceList(priceList);
        return this;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Price productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Price priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPriceValue() {
        return this.priceValue;
    }

    public Price priceValue(Double priceValue) {
        this.setPriceValue(priceValue);
        return this;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public ISOCurrencyCode getCurr() {
        return this.curr;
    }

    public Price curr(ISOCurrencyCode curr) {
        this.setCurr(curr);
        return this;
    }

    public void setCurr(ISOCurrencyCode curr) {
        this.curr = curr;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        return id != null && id.equals(((Price) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Price{" +
            "id=" + getId() +
            ", brandId=" + getBrandId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", priceList=" + getPriceList() +
            ", productId=" + getProductId() +
            ", priority=" + getPriority() +
            ", priceValue=" + getPriceValue() +
            ", curr='" + getCurr() + "'" +
            "}";
    }
}
