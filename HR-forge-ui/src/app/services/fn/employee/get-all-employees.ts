/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseEmployeeResponse } from '../../models/page-response-employee-response';

export interface GetAllEmployees$Params {

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

export function getAllEmployees(http: HttpClient, rootUrl: string, params?: GetAllEmployees$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseEmployeeResponse>> {
  const rb = new RequestBuilder(rootUrl, getAllEmployees.PATH, 'get');
  if (params) {
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
      return r as StrictHttpResponse<PageResponseEmployeeResponse>;
    })
  );
}

getAllEmployees.PATH = '/employee';
