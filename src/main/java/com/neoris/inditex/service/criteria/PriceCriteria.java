package com.neoris.inditex.service.criteria;

import com.neoris.inditex.domain.enumeration.ISOCurrencyCode;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;

/**
 * Criteria class for the {@link com.neoris.inditex.domain.Price} entity. This class is used
 * in {@link com.neoris.inditex.web.rest.PriceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /prices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class PriceCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ISOCurrencyCode
     */
    public static class ISOCurrencyCodeFilter extends Filter<ISOCurrencyCode> {

        public ISOCurrencyCodeFilter() {}

        public ISOCurrencyCodeFilter(ISOCurrencyCodeFilter filter) {
            super(filter);
        }

        @Override
        public ISOCurrencyCodeFilter copy() {
            return new ISOCurrencyCodeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter brandId;

    private LongFilter priceList;

    private LongFilter productId;

    private DoubleFilter priceValue;

    private Boolean distinct;

    public PriceCriteria() {}

    public PriceCriteria(PriceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.brandId = other.brandId == null ? null : other.brandId.copy();
        this.priceList = other.priceList == null ? null : other.priceList.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
        this.priceValue = other.priceValue == null ? null : other.priceValue.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PriceCriteria copy() {
        return new PriceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getBrandId() {
        return brandId;
    }

    public LongFilter brandId() {
        if (brandId == null) {
            brandId = new LongFilter();
        }
        return brandId;
    }

    public void setBrandId(LongFilter brandId) {
        this.brandId = brandId;
    }

    public LongFilter getPriceList() {
        return priceList;
    }

    public LongFilter priceList() {
        if (priceList == null) {
            priceList = new LongFilter();
        }
        return priceList;
    }

    public void setPriceList(LongFilter priceList) {
        this.priceList = priceList;
    }

    public LongFilter getProductId() {
        return productId;
    }

    public LongFilter productId() {
        if (productId == null) {
            productId = new LongFilter();
        }
        return productId;
    }

    public void setProductId(LongFilter productId) {
        this.productId = productId;
    }

    public DoubleFilter getPriceValue() {
        return priceValue;
    }

    public DoubleFilter priceValue() {
        if (priceValue == null) {
            priceValue = new DoubleFilter();
        }
        return priceValue;
    }

    public void setPriceValue(DoubleFilter priceValue) {
        this.priceValue = priceValue;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PriceCriteria that = (PriceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(brandId, that.brandId) &&
            Objects.equals(priceList, that.priceList) &&
            Objects.equals(productId, that.productId) &&
            Objects.equals(priceValue, that.priceValue) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, priceList, productId, priceValue, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (brandId != null ? "brandId=" + brandId + ", " : "") +
            (priceList != null ? "priceList=" + priceList + ", " : "") +
            (productId != null ? "productId=" + productId + ", " : "") +
            (priceValue != null ? "priceValue=" + priceValue + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
