/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteEmployee } from '../fn/employee/delete-employee';
import { DeleteEmployee$Params } from '../fn/employee/delete-employee';
import { EmployeeResponse } from '../models/employee-response';
import { getAllEmployees } from '../fn/employee/get-all-employees';
import { GetAllEmployees$Params } from '../fn/employee/get-all-employees';
import { getEmployee } from '../fn/employee/get-employee';
import { GetEmployee$Params } from '../fn/employee/get-employee';
import { PageResponseEmployeeResponse } from '../models/page-response-employee-response';
import { updateEmployee } from '../fn/employee/update-employee';
import { UpdateEmployee$Params } from '../fn/employee/update-employee';
import { updateEmployeePassword } from '../fn/employee/update-employee-password';
import { UpdateEmployeePassword$Params } from '../fn/employee/update-employee-password';


/**
 * API for managing employee data
 */
@Injectable({ providedIn: 'root' })
export class EmployeeService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getEmployee()` */
  static readonly GetEmployeePath = '/employee/{id-or-email}';

  /**
   * Find employee by ID or email.
   *
   * Endpoint for retrieving an employee by ID or email address.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getEmployee()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEmployee$Response(params: GetEmployee$Params, context?: HttpContext): Observable<StrictHttpResponse<EmployeeResponse>> {
    return getEmployee(this.http, this.rootUrl, params, context);
  }

  /**
   * Find employee by ID or email.
   *
   * Endpoint for retrieving an employee by ID or email address.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getEmployee$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEmployee(params: GetEmployee$Params, context?: HttpContext): Observable<EmployeeResponse> {
    return this.getEmployee$Response(params, context).pipe(
      map((r: StrictHttpResponse<EmployeeResponse>): EmployeeResponse => r.body)
    );
  }

  /** Path part for operation `updateEmployee()` */
  static readonly UpdateEmployeePath = '/employee/{id-or-email}';

  /**
   * Update employee details.
   *
   * Endpoint for updating employee details by ID or email.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateEmployee()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEmployee$Response(params: UpdateEmployee$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updateEmployee(this.http, this.rootUrl, params, context);
  }

  /**
   * Update employee details.
   *
   * Endpoint for updating employee details by ID or email.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateEmployee$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEmployee(params: UpdateEmployee$Params, context?: HttpContext): Observable<{
}> {
    return this.updateEmployee$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `deleteEmployee()` */
  static readonly DeleteEmployeePath = '/employee/{id-or-email}';

  /**
   * Delete employee.
   *
   * Endpoint for deleting an employee by ID or email.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteEmployee()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEmployee$Response(params: DeleteEmployee$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return deleteEmployee(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete employee.
   *
   * Endpoint for deleting an employee by ID or email.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteEmployee$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEmployee(params: DeleteEmployee$Params, context?: HttpContext): Observable<{
}> {
    return this.deleteEmployee$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `updateEmployeePassword()` */
  static readonly UpdateEmployeePasswordPath = '/employee/password/{id}';

  /**
   * Update employee password.
   *
   * Endpoint for updating an employee's password by ID (admin operation).
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateEmployeePassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEmployeePassword$Response(params: UpdateEmployeePassword$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updateEmployeePassword(this.http, this.rootUrl, params, context);
  }

  /**
   * Update employee password.
   *
   * Endpoint for updating an employee's password by ID (admin operation).
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateEmployeePassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEmployeePassword(params: UpdateEmployeePassword$Params, context?: HttpContext): Observable<{
}> {
    return this.updateEmployeePassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `getAllEmployees()` */
  static readonly GetAllEmployeesPath = '/employee';

  /**
   * Find all employees.
   *
   * Endpoint for retrieving a paginated list of all employees.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllEmployees()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllEmployees$Response(params: GetAllEmployees$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseEmployeeResponse>> {
    return getAllEmployees(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all employees.
   *
   * Endpoint for retrieving a paginated list of all employees.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllEmployees$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllEmployees(params: GetAllEmployees$Params, context?: HttpContext): Observable<PageResponseEmployeeResponse> {
    return this.getAllEmployees$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseEmployeeResponse>): PageResponseEmployeeResponse => r.body)
    );
  }

}
