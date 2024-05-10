/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createPosition } from '../fn/position/create-position';
import { CreatePosition$Params } from '../fn/position/create-position';
import { deletePosition } from '../fn/position/delete-position';
import { DeletePosition$Params } from '../fn/position/delete-position';
import { getAllPositions } from '../fn/position/get-all-positions';
import { GetAllPositions$Params } from '../fn/position/get-all-positions';
import { getPosition } from '../fn/position/get-position';
import { GetPosition$Params } from '../fn/position/get-position';
import { PageResponsePositionResponse } from '../models/page-response-position-response';
import { PositionResponse } from '../models/position-response';
import { updatePosition } from '../fn/position/update-position';
import { UpdatePosition$Params } from '../fn/position/update-position';


/**
 * API for managing positions
 */
@Injectable({ providedIn: 'root' })
export class PositionService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getPosition()` */
  static readonly GetPositionPath = '/position/{id-or-name}';

  /**
   * Find position by ID or name.
   *
   * Endpoint for retrieving a position by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getPosition()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPosition$Response(params: GetPosition$Params, context?: HttpContext): Observable<StrictHttpResponse<PositionResponse>> {
    return getPosition(this.http, this.rootUrl, params, context);
  }

  /**
   * Find position by ID or name.
   *
   * Endpoint for retrieving a position by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getPosition$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPosition(params: GetPosition$Params, context?: HttpContext): Observable<PositionResponse> {
    return this.getPosition$Response(params, context).pipe(
      map((r: StrictHttpResponse<PositionResponse>): PositionResponse => r.body)
    );
  }

  /** Path part for operation `updatePosition()` */
  static readonly UpdatePositionPath = '/position/{id-or-name}';

  /**
   * Update position.
   *
   * Endpoint for updating an existing position by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatePosition()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePosition$Response(params: UpdatePosition$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return updatePosition(this.http, this.rootUrl, params, context);
  }

  /**
   * Update position.
   *
   * Endpoint for updating an existing position by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatePosition$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePosition(params: UpdatePosition$Params, context?: HttpContext): Observable<{
}> {
    return this.updatePosition$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `deletePosition()` */
  static readonly DeletePositionPath = '/position/{id-or-name}';

  /**
   * Delete position.
   *
   * Endpoint for deleting a position by ID or name.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deletePosition()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePosition$Response(params: DeletePosition$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return deletePosition(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete position.
   *
   * Endpoint for deleting a position by ID or name.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deletePosition$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePosition(params: DeletePosition$Params, context?: HttpContext): Observable<{
}> {
    return this.deletePosition$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `getAllPositions()` */
  static readonly GetAllPositionsPath = '/position';

  /**
   * Find all positions.
   *
   * Endpoint for retrieving a paginated list of all positions.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllPositions()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPositions$Response(params?: GetAllPositions$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponsePositionResponse>> {
    return getAllPositions(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all positions.
   *
   * Endpoint for retrieving a paginated list of all positions.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllPositions$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPositions(params?: GetAllPositions$Params, context?: HttpContext): Observable<PageResponsePositionResponse> {
    return this.getAllPositions$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponsePositionResponse>): PageResponsePositionResponse => r.body)
    );
  }

  /** Path part for operation `createPosition()` */
  static readonly CreatePositionPath = '/position';

  /**
   * Create position.
   *
   * Endpoint for creating a new position.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createPosition()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPosition$Response(params: CreatePosition$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return createPosition(this.http, this.rootUrl, params, context);
  }

  /**
   * Create position.
   *
   * Endpoint for creating a new position.
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createPosition$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPosition(params: CreatePosition$Params, context?: HttpContext): Observable<{
}> {
    return this.createPosition$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

}
