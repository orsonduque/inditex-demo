import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './price.reducer';

export const PriceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const priceEntity = useAppSelector(state => state.price.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="priceDetailsHeading">
          <Translate contentKey="inditexApp.price.detail.title">Price</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{priceEntity.id}</dd>
          <dt>
            <span id="brandId">
              <Translate contentKey="inditexApp.price.brandId">Brand Id</Translate>
            </span>
          </dt>
          <dd>{priceEntity.brandId}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="inditexApp.price.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>{priceEntity.startDate ? <TextFormat value={priceEntity.startDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="inditexApp.price.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>{priceEntity.endDate ? <TextFormat value={priceEntity.endDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="priceList">
              <Translate contentKey="inditexApp.price.priceList">Price List</Translate>
            </span>
          </dt>
          <dd>{priceEntity.priceList}</dd>
          <dt>
            <span id="productId">
              <Translate contentKey="inditexApp.price.productId">Product Id</Translate>
            </span>
          </dt>
          <dd>{priceEntity.productId}</dd>
          <dt>
            <span id="priority">
              <Translate contentKey="inditexApp.price.priority">Priority</Translate>
            </span>
          </dt>
          <dd>{priceEntity.priority}</dd>
          <dt>
            <span id="priceValue">
              <Translate contentKey="inditexApp.price.priceValue">Price Value</Translate>
            </span>
          </dt>
          <dd>{priceEntity.priceValue}</dd>
          <dt>
            <span id="curr">
              <Translate contentKey="inditexApp.price.curr">Curr</Translate>
            </span>
          </dt>
          <dd>{priceEntity.curr}</dd>
        </dl>
        <Button tag={Link} to="/price" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/price/${priceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PriceDetail;
