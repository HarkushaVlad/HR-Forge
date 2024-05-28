/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponsePositionResponse } from '../../models/page-response-position-response';

export interface GetAllPositions$Params {

/**
 * Part of the position name
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

export function getAllPositions(http: HttpClient, rootUrl: string, params: GetAllPositions$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponsePositionResponse>> {
  const rb = new RequestBuilder(rootUrl, getAllPositions.PATH, 'get');
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
      return r as StrictHttpResponse<PageResponsePositionResponse>;
    })
  );
}

getAllPositions.PATH = '/position';
