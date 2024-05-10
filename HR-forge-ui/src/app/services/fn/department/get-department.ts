/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { DepartmentResponse } from '../../models/department-response';

export interface GetDepartment$Params {

/**
 * Department ID or name
 */
  'id-or-name': string;
}

export function getDepartment(http: HttpClient, rootUrl: string, params: GetDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<DepartmentResponse>> {
  const rb = new RequestBuilder(rootUrl, getDepartment.PATH, 'get');
  if (params) {
    rb.path('id-or-name', params['id-or-name'], {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<DepartmentResponse>;
    })
  );
}

getDepartment.PATH = '/department/{id-or-name}';
