/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { EmployeeRequest } from '../../models/employee-request';

export interface UpdateEmployee$Params {

/**
 * Employee ID or email
 */
  'id-or-email': string;
      body: EmployeeRequest
}

export function updateEmployee(http: HttpClient, rootUrl: string, params: UpdateEmployee$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, updateEmployee.PATH, 'put');
  if (params) {
    rb.path('id-or-email', params['id-or-email'], {});
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

updateEmployee.PATH = '/employee/{id-or-email}';
