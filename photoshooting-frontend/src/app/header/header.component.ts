import { Component, OnInit } from '@angular/core';
import {UsersService} from '../users/users.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username: string;

  constructor(public usersService: UsersService ) { }

  
  ngOnInit(): void {
    this.username = this.usersService.getUsername();
  }


}
