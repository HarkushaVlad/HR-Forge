/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { DepartmentRequest } from '../../models/department-request';

export interface UpdateDepartment$Params {

/**
 * Department ID or name
 */
  'id-or-name': string;
  
    /**
     * Department details
     */
    body: DepartmentRequest
}

export function updateDepartment(http: HttpClient, rootUrl: string, params: UpdateDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, updateDepartment.PATH, 'put');
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

updateDepartment.PATH = '/department/{id-or-name}';
