import dayjs from 'dayjs';
import { ISOCurrencyCode } from 'app/shared/model/enumerations/iso-currency-code.model';

export interface IPrice {
  id?: number;
  brandId?: number;
  startDate?: string | null;
  endDate?: string | null;
  priceList?: number;
  productId?: number;
  priority?: number | null;
  priceValue?: number;
  curr?: ISOCurrencyCode;
}

export const defaultValue: Readonly<IPrice> = {};
