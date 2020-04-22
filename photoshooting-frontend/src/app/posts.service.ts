import { Injectable } from '@angular/core';
import {Post} from '../app/add-post/post';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: Post){
    return this.httpClient.post('http://localhost:8080/posts/', postPayload);
  }

  getAllPosts(): Observable<Array<Post>>{
    return this.httpClient.get<Array<Post>>("http://localhost:8080/posts");
  }

  getPost(permaLink: Number):Observable<Post>{
    return this.httpClient.get<Post>('http://localhost:8080/posts/' + permaLink);
  }
}
