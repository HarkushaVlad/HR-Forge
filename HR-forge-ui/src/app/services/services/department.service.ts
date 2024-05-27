/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createDepartment } from '../fn/department/create-department';
import { CreateDepartment$Params } from '../fn/department/create-department';
import { deleteDepartment } from '../fn/department/delete-department';
import { DeleteDepartment$Params } from '../fn/department/delete-department';
import { DepartmentResponse } from '../models/department-response';
import { getAllDepartments } from '../fn/department/get-all-departments';
import { GetAllDepartments$Params } from '../fn/department/get-all-departments';
import { getDepartment } from '../fn/department/get-department';
import { GetDepartment$Params } from '../fn/department/get-department';
import { PageResponseDepartmentResponse } from '../models/page-response-department-response';
import { searchDepartmentsByName } from '../fn/department/search-departments-by-name';
import { SearchDepartmentsByName$Params } from '../fn/department/search-departments-by-name';
import { updateDepartment } from '../fn/department/update-department';
import { UpdateDepartment$Params } from '../fn/department/update-department';


/**
 * API for managing department data
 */
@Injectable({ providedIn: 'root' })
export class DepartmentService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getDepartment()` */
  static readonly GetDepartmentPath = '/department/{id-or-name}';

  /**
   * Find department by ID or name.
   *
   * Endpoint for retrieving a department by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getDepartment()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDepartment$Response(params: GetDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<DepartmentResponse>> {
    return getDepartment(this.http, this.rootUrl, params, context);
  }

  /**
   * Find department by ID or name.
   *
   * Endpoint for retrieving a department by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getDepartment$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDepartment(params: GetDepartment$Params, context?: HttpContext): Observable<DepartmentResponse> {
    return this.getDepartment$Response(params, context).pipe(
      map((r: StrictHttpResponse<DepartmentResponse>): DepartmentResponse => r.body)
    );
  }

  /** Path part for operation `updateDepartment()` */
  static readonly UpdateDepartmentPath = '/department/{id-or-name}';

  /**
   * Update department.
   *
   * Endpoint for updating an existing department by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateDepartment()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateDepartment$Response(params: UpdateDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updateDepartment(this.http, this.rootUrl, params, context);
  }

  /**
   * Update department.
   *
   * Endpoint for updating an existing department by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateDepartment$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateDepartment(params: UpdateDepartment$Params, context?: HttpContext): Observable<{
}> {
    return this.updateDepartment$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `deleteDepartment()` */
  static readonly DeleteDepartmentPath = '/department/{id-or-name}';

  /**
   * Delete department.
   *
   * Endpoint for deleting a department by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteDepartment()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteDepartment$Response(params: DeleteDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return deleteDepartment(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete department.
   *
   * Endpoint for deleting a department by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteDepartment$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteDepartment(params: DeleteDepartment$Params, context?: HttpContext): Observable<{
}> {
    return this.deleteDepartment$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `getAllDepartments()` */
  static readonly GetAllDepartmentsPath = '/department';

  /**
   * Find all departments.
   *
   * Endpoint for retrieving a paginated list of all departments.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllDepartments()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllDepartments$Response(params?: GetAllDepartments$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseDepartmentResponse>> {
    return getAllDepartments(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all departments.
   *
   * Endpoint for retrieving a paginated list of all departments.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllDepartments$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllDepartments(params?: GetAllDepartments$Params, context?: HttpContext): Observable<PageResponseDepartmentResponse> {
    return this.getAllDepartments$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseDepartmentResponse>): PageResponseDepartmentResponse => r.body)
    );
  }

  /** Path part for operation `createDepartment()` */
  static readonly CreateDepartmentPath = '/department';

  /**
   * Create department.
   *
   * Endpoint for creating a new department.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createDepartment()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createDepartment$Response(params: CreateDepartment$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return createDepartment(this.http, this.rootUrl, params, context);
  }

  /**
   * Create department.
   *
   * Endpoint for creating a new department.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createDepartment$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createDepartment(params: CreateDepartment$Params, context?: HttpContext): Observable<{
}> {
    return this.createDepartment$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `searchDepartmentsByName()` */
  static readonly SearchDepartmentsByNamePath = '/department/search';

  /**
   * Search departments by name.
   *
   * Endpoint for searching departments by a part of their name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `searchDepartmentsByName()` instead.
   *
   * This method doesn't expect any request body.
   */
  searchDepartmentsByName$Response(params: SearchDepartmentsByName$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseDepartmentResponse>> {
    return searchDepartmentsByName(this.http, this.rootUrl, params, context);
  }

  /**
   * Search departments by name.
   *
   * Endpoint for searching departments by a part of their name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `searchDepartmentsByName$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  searchDepartmentsByName(params: SearchDepartmentsByName$Params, context?: HttpContext): Observable<PageResponseDepartmentResponse> {
    return this.searchDepartmentsByName$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseDepartmentResponse>): PageResponseDepartmentResponse => r.body)
    );
  }

}
