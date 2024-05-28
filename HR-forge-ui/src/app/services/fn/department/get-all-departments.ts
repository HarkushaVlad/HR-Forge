/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseDepartmentResponse } from '../../models/page-response-department-response';

export interface GetAllDepartments$Params {

/**
 * Part of the department name
 */
  name: string;

/**
 * Page number
 */
  page?: number;

/**
 * Page size
 */
  size?: number;

/**
 * Field to sort by
 */
  sortField?: string;

/**
 * Sort direction (ASC or DESC)
 */
  sortDirection?: string;
}

export function getAllDepartments(http: HttpClient, rootUrl: string, params: GetAllDepartments$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseDepartmentResponse>> {
  const rb = new RequestBuilder(rootUrl, getAllDepartments.PATH, 'get');
  if (params) {
    rb.query('name', params.name, {});
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
    rb.query('sortField', params.sortField, {});
    rb.query('sortDirection', params.sortDirection, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseDepartmentResponse>;
    })
  );
}

getAllDepartments.PATH = '/department';
