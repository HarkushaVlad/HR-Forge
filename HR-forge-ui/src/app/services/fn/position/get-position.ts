/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PositionResponse } from '../../models/position-response';

export interface GetPosition$Params {

/**
 * Position ID or name
 */
  'id-or-name': string;
}

export function getPosition(http: HttpClient, rootUrl: string, params: GetPosition$Params, context?: HttpContext): Observable<StrictHttpResponse<PositionResponse>> {
  const rb = new RequestBuilder(rootUrl, getPosition.PATH, 'get');
  if (params) {
    rb.path('id-or-name', params['id-or-name'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PositionResponse>;
    })
  );
}

getPosition.PATH = '/position/{id-or-name}';
