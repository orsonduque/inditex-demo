package com.neoris.inditex.service.dto;

import com.neoris.inditex.domain.enumeration.ISOCurrencyCode;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.neoris.inditex.domain.Price} entity.
 */
public class PriceDTO implements Serializable {

    private Long id;

    @NotNull
    private Long brandId;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    @NotNull
    private Long priceList;

    @NotNull
    private Long productId;

    private Integer priority;

    @NotNull
    private Double priceValue;

    @NotNull
    private ISOCurrencyCode curr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public ISOCurrencyCode getCurr() {
        return curr;
    }

    public void setCurr(ISOCurrencyCode curr) {
        this.curr = curr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceDTO)) {
            return false;
        }

        PriceDTO priceDTO = (PriceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, priceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceDTO{" +
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
