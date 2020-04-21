import { Injectable } from '@angular/core';
import { Registration } from './registration';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private backend_url = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) {
  }

  register(registration: Registration): Observable<any> {
    return this.httpClient.post(this.backend_url + 'users', registration);
  }
}
