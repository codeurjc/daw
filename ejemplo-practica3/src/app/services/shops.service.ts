import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { ShopBasicDTO } from "../dtos/shopbasic.dto";

const BASE_URL = "/api/shops/";

@Injectable({ providedIn: "root" })
export class ShopsService {
  constructor(private httpClient: HttpClient) {}

  public getShops(): Observable<ShopBasicDTO[]> {
    return this.httpClient
      .get(BASE_URL)
      .pipe(catchError((error) => this.handleError(error))) as Observable<
      ShopBasicDTO[]
    >;
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text());
  }
}
