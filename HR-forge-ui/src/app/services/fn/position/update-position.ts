/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PositionRequest } from '../../models/position-request';

export interface UpdatePosition$Params {

/**
 * Position ID or name
 */
  'id-or-name': string;
  
    /**
     * Position details
     */
    body: PositionRequest
}

export function updatePosition(http: HttpClient, rootUrl: string, params: UpdatePosition$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, updatePosition.PATH, 'put');
  if (params) {
    rb.path('id-or-name', params['id-or-name'], {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<{
      }>;
    })
  );
}

updatePosition.PATH = '/position/{id-or-name}';
