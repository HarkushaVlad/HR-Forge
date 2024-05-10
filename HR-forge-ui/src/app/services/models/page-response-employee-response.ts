/* tslint:disable */
/* eslint-disable */
import { EmployeeResponse } from '../models/employee-response';
export interface PageResponseEmployeeResponse {
  content?: Array<EmployeeResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
