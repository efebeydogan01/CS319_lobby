import { Injectable } from '@angular/core';
import {Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {HttpUrls} from "./HttpUrls";

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  constructor( private http: HttpClient) { }

  getCovidInfo( uuid: string): Observable<any> {

    return this.http.get<any>( HttpUrls.baseUrl + "covid/get/" + uuid).
      pipe( tap( data => {
    }));
  }

}
