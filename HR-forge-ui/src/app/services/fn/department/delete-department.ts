/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteDepartment$Params {

/**
 * Department ID or name
 */
  'id-or-name': string;
}

export function deleteDepartment(http: HttpClient, rootUrl: string, params: DeleteDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, deleteDepartment.PATH, 'delete');
  if (params) {
    rb.path('id-or-name', params['id-or-name'], {});
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

deleteDepartment.PATH = '/department/{id-or-name}';
