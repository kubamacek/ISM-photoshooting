import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Post} from '../add-post/post';
import {PostsService} from '../posts.service';
import { UsersService } from '../users/users.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Observable<Array<Post>>;
  constructor(private postsService: PostsService, public usersService: UsersService) { }

  ngOnInit() {
    this.posts = this.postsService.getAllPosts();
  }

}
