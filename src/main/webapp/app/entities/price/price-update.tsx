import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPrice } from 'app/shared/model/price.model';
import { ISOCurrencyCode } from 'app/shared/model/enumerations/iso-currency-code.model';
import { getEntity, updateEntity, createEntity, reset } from './price.reducer';

export const PriceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const priceEntity = useAppSelector(state => state.price.entity);
  const loading = useAppSelector(state => state.price.loading);
  const updating = useAppSelector(state => state.price.updating);
  const updateSuccess = useAppSelector(state => state.price.updateSuccess);
  const iSOCurrencyCodeValues = Object.keys(ISOCurrencyCode);

  const handleClose = () => {
    navigate('/price' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.startDate = convertDateTimeToServer(values.startDate);
    values.endDate = convertDateTimeToServer(values.endDate);

    const entity = {
      ...priceEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          startDate: displayDefaultDateTime(),
          endDate: displayDefaultDateTime(),
        }
      : {
          curr: 'EUR',
          ...priceEntity,
          startDate: convertDateTimeFromServer(priceEntity.startDate),
          endDate: convertDateTimeFromServer(priceEntity.endDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="inditexApp.price.home.createOrEditLabel" data-cy="PriceCreateUpdateHeading">
            <Translate contentKey="inditexApp.price.home.createOrEditLabel">Create or edit a Price</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="price-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('inditexApp.price.brandId')}
                id="price-brandId"
                name="brandId"
                data-cy="brandId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('inditexApp.price.startDate')}
                id="price-startDate"
                name="startDate"
                data-cy="startDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('inditexApp.price.endDate')}
                id="price-endDate"
                name="endDate"
                data-cy="endDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('inditexApp.price.priceList')}
                id="price-priceList"
                name="priceList"
                data-cy="priceList"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('inditexApp.price.productId')}
                id="price-productId"
                name="productId"
                data-cy="productId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('inditexApp.price.priority')}
                id="price-priority"
                name="priority"
                data-cy="priority"
                type="text"
              />
              <ValidatedField
                label={translate('inditexApp.price.priceValue')}
                id="price-priceValue"
                name="priceValue"
                data-cy="priceValue"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('inditexApp.price.curr')} id="price-curr" name="curr" data-cy="curr" type="select">
                {iSOCurrencyCodeValues.map(iSOCurrencyCode => (
                  <option value={iSOCurrencyCode} key={iSOCurrencyCode}>
                    {translate('inditexApp.ISOCurrencyCode.' + iSOCurrencyCode)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/price" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PriceUpdate;
