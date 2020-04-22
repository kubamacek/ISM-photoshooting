import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Post} from './post';
import {Router} from '@angular/router'; 
import {PostsService} from '../posts.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm: FormGroup;
  post: Post;
  title = new FormControl('');
  photo = new FormControl('');
  description = new FormControl('');


  constructor(private postsService: PostsService, private router: Router) {
    this.addPostForm =  new FormGroup({
      title: this.title,
      photo: this.photo,
      description: this.description
    });
    this.post = {
      id: '',
      title: '',
      description: '',
      photo: '',
      author: ''
    }
  }

  ngOnInit(): void {
  }

  addPost(){
    this.post.description = this.addPostForm.get('description').value;
    this.post.title = this.addPostForm.get('title').value;
    this.post.photo = this.addPostForm.get('photo').value;
    this.postsService.addPost(this.post).subscribe(data => {
      console.log('Successfully added new post!')
      this.router.navigateByUrl('/');
    }, error => {
      console.log('Post creation failed.');
    })
  }

}
