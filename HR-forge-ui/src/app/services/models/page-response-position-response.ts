/* tslint:disable */
/* eslint-disable */
import { PositionResponse } from '../models/position-response';
export interface PageResponsePositionResponse {
  content?: Array<PositionResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
