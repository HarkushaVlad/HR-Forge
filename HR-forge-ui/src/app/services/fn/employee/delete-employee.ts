/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteEmployee$Params {

/**
 * Employee ID or email
 */
  'id-or-email': string;
}

export function deleteEmployee(http: HttpClient, rootUrl: string, params: DeleteEmployee$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, deleteEmployee.PATH, 'delete');
  if (params) {
    rb.path('id-or-email', params['id-or-email'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<{
      }>;
    })
  );
}

deleteEmployee.PATH = '/employee/{id-or-email}';
