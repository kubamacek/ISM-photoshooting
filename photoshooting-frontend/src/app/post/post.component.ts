import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PostsService} from '../posts.service';
import {Post} from '../add-post/post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  post: Post;
  permaLink: Number;

  constructor(private router: ActivatedRoute, private postService: PostsService) {
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.permaLink = params['id'];
    });

    this.postService.getPost(this.permaLink).subscribe((data:Post) => {
      this.post = data;
    },(err: any) => {
      console.log('Failure Response');
    })
  }

}