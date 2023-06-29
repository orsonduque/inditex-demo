package com.neoris.inditex.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.neoris.inditex.domain.Price} entity.
 */
public class PriceOutputDTO implements Serializable {

    @NotNull
    private Long brandId;

    private Long productId;

    private Long priceList;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    @NotNull
    private Double priceValue;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
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

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    @Override
    public String toString() {
        return (
            "PriceOutputDTO{" +
            "brandId=" +
            brandId +
            ", productId=" +
            productId +
            ", priceList=" +
            priceList +
            ", startDate=" +
            startDate +
            ", endDate=" +
            endDate +
            ", priceValue=" +
            priceValue +
            '}'
        );
    }
}
