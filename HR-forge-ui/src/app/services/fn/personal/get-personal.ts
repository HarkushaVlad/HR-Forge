/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { EmployeeResponse } from '../../models/employee-response';

export interface GetPersonal$Params {
}

export function getPersonal(http: HttpClient, rootUrl: string, params?: GetPersonal$Params, context?: HttpContext): Observable<StrictHttpResponse<EmployeeResponse>> {
  const rb = new RequestBuilder(rootUrl, getPersonal.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<EmployeeResponse>;
    })
  );
}

getPersonal.PATH = '/personal';
