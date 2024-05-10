/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { EmployeeResponse } from '../models/employee-response';
import { getPersonal } from '../fn/personal/get-personal';
import { GetPersonal$Params } from '../fn/personal/get-personal';
import { updatePersonal } from '../fn/personal/update-personal';
import { UpdatePersonal$Params } from '../fn/personal/update-personal';
import { updatePersonalPassword } from '../fn/personal/update-personal-password';
import { UpdatePersonalPassword$Params } from '../fn/personal/update-personal-password';


/**
 * API for managing personal information
 */
@Injectable({ providedIn: 'root' })
export class PersonalService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getPersonal()` */
  static readonly GetPersonalPath = '/personal';

  /**
   * Find personal information.
   *
   * Endpoint for retrieving personal information of the authenticated user.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getPersonal()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPersonal$Response(params?: GetPersonal$Params, context?: HttpContext): Observable<StrictHttpResponse<EmployeeResponse>> {
    return getPersonal(this.http, this.rootUrl, params, context);
  }

  /**
   * Find personal information.
   *
   * Endpoint for retrieving personal information of the authenticated user.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getPersonal$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPersonal(params?: GetPersonal$Params, context?: HttpContext): Observable<EmployeeResponse> {
    return this.getPersonal$Response(params, context).pipe(
      map((r: StrictHttpResponse<EmployeeResponse>): EmployeeResponse => r.body)
    );
  }

  /** Path part for operation `updatePersonal()` */
  static readonly UpdatePersonalPath = '/personal';

  /**
   * Update personal information.
   *
   * Endpoint for updating personal information of the authenticated user.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatePersonal()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePersonal$Response(params: UpdatePersonal$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updatePersonal(this.http, this.rootUrl, params, context);
  }

  /**
   * Update personal information.
   *
   * Endpoint for updating personal information of the authenticated user.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatePersonal$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePersonal(params: UpdatePersonal$Params, context?: HttpContext): Observable<{
}> {
    return this.updatePersonal$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `updatePersonalPassword()` */
  static readonly UpdatePersonalPasswordPath = '/personal/password';

  /**
   * Update password.
   *
   * Endpoint for updating password of the authenticated user.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatePersonalPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePersonalPassword$Response(params: UpdatePersonalPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updatePersonalPassword(this.http, this.rootUrl, params, context);
  }

  /**
   * Update password.
   *
   * Endpoint for updating password of the authenticated user.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatePersonalPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePersonalPassword(params: UpdatePersonalPassword$Params, context?: HttpContext): Observable<{
}> {
    return this.updatePersonalPassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

}
