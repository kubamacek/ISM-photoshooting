import { Injectable } from '@angular/core';
import {Post} from '../app/add-post/post';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: Post){
    return this.httpClient.post('http://localhost:8080/posts/', postPayload);
  }
}
