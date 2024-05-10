/* tslint:disable */
/* eslint-disable */
import { DepartmentResponse } from '../models/department-response';
export interface PageResponseDepartmentResponse {
  content?: Array<DepartmentResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
